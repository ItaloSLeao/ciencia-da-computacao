/* ***************************************************************
* Autor............: Italo de Souza Leao
* Matricula........: 202410120
* Inicio...........: 11/06/2025
* Ultima alteracao.: 04/08/2025
* Nome.............: ControllerTelaPrincipal.java
* Funcao...........: Controla as acoes tomadas pelo usuario na tela da aplicacao, pelos cliques nos botoes e interacoes com os sliders.
****************************************************************/

package controller;

//Importacoes das bibliotecas necessarias para a implementacao da interface Initializable
import java.net.URL;
import java.util.ResourceBundle;

import model.*; //Importacao das classes modelo para o problema do produtor-consumidor

//Importacoes das bibliotecas necessarias para o pleno funcionamento do programa
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import java.util.concurrent.Semaphore;

public class ControllerTelaPrincipal implements Initializable {

  @FXML //Notacao FXML
  private AnchorPane anchorPane; //Insere o id do anchor pane da aplicacao

  @FXML //Notacao FXML
  private Button botaoReset, botaoPausarRetomarProdutor, botaoPausarRetomarConsumidor, botaoFechar; //Insere os ids dos botoes de resetar a aplicacao, pausa/retomada do produtor e do consumidor e do botao de fechar a aplicacao

  @FXML //Notacao FXML
  private ImageView imagemBotaoReset, imagemBotaoPR_Produtor, imagemBotaoPR_Consumidor, imagemBotaoFechar; //Insere os ids das imagens dos respectivos botoes inseridos acima na aplicacao

  @FXML //Notacao FXML
  private ImageView imagemProdutor, imagemConsumidor, animacaoGifProdutor, animacaoGifConsumidor; //Insere os ids das imagens e dos gifs do produtor e do consumidor

  @FXML //Notacap FXML
  private Slider sliderProdutor, sliderConsumidor; //Insere os ids dos sliders que controlam as velocidades do produtor e do consumidor na aplicacao

  private Buffer buffer; //Instanciacao do buffer de itens do problema

  private Produtor produtor; //Instanciacao do produtor
 
  private Consumidor consumidor; //Instanciacao do consumidor

  private static Semaphore mutex = new Semaphore(1); //Semaforo para controlar exclusao mutua no buffer
  private static Semaphore cheio = new Semaphore(0); //Semaforo para controlar a quantidade de itens no buffer
  private static Semaphore vazio = new Semaphore(6); //Semaforo para controlar a quantidade de espacos vazios no buffer



  /*****************************************************************
  * Metodo: aplicarEscala
  * Funcao: Aplicar um fator de escala nas imagens (ImageView) dos botoes, quando o mouse é passado acima dos mesmos. Essa acao garante uma maior interatividade do usuario com a aplicacao.
  Isso é feito com o auxilio das classes do JavaFX: ScaleTransition, que realiza essa animacao, frame a frame; Duration, que retorna uma instancia de duracao para a abrangencia da animacao.
  * Parametros: Uma ImageView imageView e um double escala
  * Retorno: void
  *****************************************************************/
  @FXML //Anotacao para designar que aplicarEscala eh um metodo de acao que eh chamado quando ha uma interacao com um componente fxml
  private void aplicarEscala(ImageView imageView, double escala) {
    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), imageView);
    scaleTransition.setToX(escala); //Aplicacao da escala no eixo X, ou seja, largura
    scaleTransition.setToY(escala); //Aplicacao da escala no eixo Y, ou seja, altura
    scaleTransition.play(); //Inicia a animacao
  } //Fim do metodo aplicarEscala


  /****************************************************************
  * Metodo: resetarThreads
  * Funcao: Paralisa as threads, remove os aneis que possam estar acima da mesa e reseta os valores dos sliders do produtor e do consumidor 
  para o valor base 10 e, por fim, inicializa as threads novamente.
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  @FXML //Notacao FXML
  public void resetarThreads(){
    produtor.stopRun(); //Para a thread do produtor
    consumidor.stopRun(); //Para a thread do consumidor
    Platform.runLater(() -> {
      anchorPane.getChildren().removeIf(node -> {
        if(node instanceof ImageView){
          ImageView imageView = (ImageView) node;
          Image imagem = new Image(getClass().getResourceAsStream("/assets/icon-um-anel.png"));
          return imageView.getImage() != null && imageView.getImage().equals(imagem);
        }
        return false;
      }); //Fim do removeIf do anchorPane
      sliderProdutor.setValue(10); //Retorna o valor do slider do produtor para o valor base 10
      sliderConsumidor.setValue(10); //Retorna o valor do slider do consumidor para o valor base 10

      //Retorna as configuracoes originais do botaoPausarRetomarProdutor
      botaoPausarRetomarProdutor.setText("PAUSAR"); 
      imagemBotaoPR_Produtor.setImage(new Image(getClass().getResourceAsStream("/assets/icon-botao-pausar.png")));
      //Retorna as configuracoes originais do botaoPausarRetomarConsumidor
      botaoPausarRetomarConsumidor.setText("PAUSAR");
      imagemBotaoPR_Consumidor.setImage(new Image(getClass().getResourceAsStream("/assets/icon-botao-pausar.png")));
    }); //Fim do Platform
    inicializarThreads();
  } //Fim do metodo resetarThreads

  /****************************************************************
  * Metodo: pausarRetomarProdutor
  * Funcao: Fazer a parada da thread do produtor quando o botao estiver em pausar e com o icone correspondente e fazer a retomada da thread quando o botao estiver em retomar
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  @FXML //Notacao FXML
  public void pausarRetomarProdutor(){
    if(botaoPausarRetomarProdutor.getText().equals("PAUSAR")){
      produtor.stopRun();
      sliderProdutor.setValue(0); //Zera o valor do slider do produtor, parando a thread
      botaoPausarRetomarProdutor.setText("RETOMAR");
      imagemBotaoPR_Produtor.setImage(new Image(getClass().getResourceAsStream("/assets/icon-botao-retomar.png")));
    } else if(botaoPausarRetomarProdutor.getText().equals("RETOMAR")){
      sliderProdutor.setValue(10); //Retorna o valor do slider do produtor para o valor base 10
      produtor = new Produtor(imagemProdutor, animacaoGifProdutor, sliderProdutor, buffer, anchorPane);
      produtor.start();
      botaoPausarRetomarProdutor.setText("PAUSAR");
      imagemBotaoPR_Produtor.setImage(new Image(getClass().getResourceAsStream("/assets/icon-botao-pausar.png")));
    }
  } //Fim do metodo pausarRetomarProdutor

  /****************************************************************
  * Metodo: pausarRetomarConsumidor
  * Funcao: Fazer a parada da thread do consumidor quando o botao estiver em pausar e com o icone correspondente e fazer a retomada da thread quando o botao estiver em retomar
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  @FXML //Notacao FXML
  public void pausarRetomarConsumidor(){
    if(botaoPausarRetomarConsumidor.getText().equals("PAUSAR")){
      consumidor.stopRun();
      sliderConsumidor.setValue(0); //Zera o valor do slider do consumidor, parando a thread
      botaoPausarRetomarConsumidor.setText("RETOMAR");
      imagemBotaoPR_Consumidor.setImage(new Image(getClass().getResourceAsStream("/assets/icon-botao-retomar.png")));
    } else if(botaoPausarRetomarConsumidor.getText().equals("RETOMAR")){
      sliderConsumidor.setValue(10); //Retorna o valor do slider do consumidor para o valor base 10
      consumidor = new Consumidor(imagemConsumidor, animacaoGifConsumidor, sliderConsumidor, buffer);
      consumidor.start();
      botaoPausarRetomarConsumidor.setText("PAUSAR");
      imagemBotaoPR_Consumidor.setImage(new Image(getClass().getResourceAsStream("/assets/icon-botao-pausar.png")));
    }
  } //Fim do metodo pausarRetomarConsumidor

  /****************************************************************
  * Metodo: fechar
  * Funcao: Fechar a aplicacao quando o botaoFechar for clicado
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  @FXML //Notacao FXML
  public void fechar(){
    System.exit(0);
  } //Fim do metodo fechar


  /****************************************************************
  * Metodo: inicializarThreads
  * Funcao: Realizar a construcao dos objetos de buffer, produtor e consumidor e alem disso, inicializar as threads de produtor e consumidor
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  private void inicializarThreads() {
    buffer = new Buffer(6); //Buffer para 6 aneis de poder (distribuidos a sociedade do anel)
    produtor = new Produtor(imagemProdutor, animacaoGifProdutor, sliderProdutor, buffer, anchorPane);
    consumidor = new Consumidor(imagemConsumidor, animacaoGifConsumidor, sliderConsumidor, buffer);
    produtor.start();
    consumidor.start();
  } //Fim do metodo inicializarThreads


  /****************************************************************
  * Metodo: getMutex
  * Funcao: Retorna o semaforo mutex
  * Parametros: void
  * Retorno: Semaphore
  ****************************************************************/
  public static Semaphore getMutex(){
    return mutex;
  } //Fim do metodo getMutex

  /****************************************************************
  * Metodo: getCheio
  * Funcao: Retorna o semaforo cheio
  * Parametros: void
  * Retorno: Semaphore
  ****************************************************************/
  public static Semaphore getCheio(){
    return cheio;
  } //Fim do metodo getCheio

  /****************************************************************
  * Metodo: getVazio
  * Funcao: Retorna o semaforo vazio
  * Parametros: void
  * Retorno: Semaphore
  ****************************************************************/
  public static Semaphore getVazio(){
    return vazio;
  } //Fim do metodo getVazio



  /****************************************************************
  * Metodo: initialize
  * Funcao: Configurar os componentes da interface gráfica, os dados iniciais ou realizar qualquer outra inicialização necessária para a aplicacao, antes que a mesma seja exibida para o usario.
  * Parametros: URL location e ResourceBundle resources
  * Retorno: void
  ****************************************************************/
  @Override //Notacao de sobrescricao do metodo initialize da classe Initializable
  public void initialize(URL location, ResourceBundle resources) {
    inicializarThreads();
    botaoFechar.setOnMouseEntered(event -> aplicarEscala(imagemBotaoFechar, 1.15)); //Configura um evento ouvinte onMouseEntered, para aplicarEscala 1.15 na imagemBotaoFechar
    botaoFechar.setOnMouseExited(event -> aplicarEscala(imagemBotaoFechar, 1.0)); //Configura um evento ouvinte onMouseExited, para aplicarEscala 1.0 na imagemBotaoFechar
  } //Fim do metodo initialize

} //Fim da classe Controller