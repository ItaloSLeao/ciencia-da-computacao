/* ***************************************************************
* Autor............: Italo de Souza Leao
* Matricula........: 202410120
* Inicio...........: 15/06/2025
* Ultima alteracao.: 04/08/2025
* Nome.............: Produtor.java
* Funcao...........: Modela o produtor do programa, com sua imagem, animacao, slider, buffer, 
anchorPane e modela suas funcionalidades como thread.
****************************************************************/

package model;

//Importacoes necessarias para o funcionamento da Thread produtora
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.application.Platform;
import controller.ControllerTelaPrincipal;

public class Produtor extends Thread{
  private ImageView imagem; //Referencia a imagem do produtor na tela da aplicacao
  private ImageView animacaoGif; //Imageview contendo o gif de animacao do produtor
  private Slider slider; //Referencia ao slider do produtor na tela da aplicacao
  private Buffer buffer; //Referencia ao buffer que o produtor ira inserir seus produtos
  private AnchorPane anchorPane; //Referencia ao anchor pane que a imagem do produtor esta contido
  private volatile boolean estaRodando = true; //Variavel booleana volatil de controle para determinar se a thread foi comandada a parar ou ainda esta rodando

  public Produtor(ImageView imagem, ImageView animacaoGif, Slider slider, Buffer buffer, AnchorPane anchorPane){ //Construtor da classe
      this.imagem = imagem;
      this.animacaoGif = animacaoGif;
      this.slider = slider;
      this.buffer = buffer;
      this.anchorPane = anchorPane;
  } //Fim do construtor


  /****************************************************************
  * Metodo: run
  * Funcao: Metodo herdado da classe Thread que roda a thread. Ele inicializa a animacao da thread e faz a conferencia das condicoes para continuar rodando. 
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  public void run(){
    while(estaRodando){ //Enquanto a thread estiver ativa
      if(slider.getValue() != 0 && !(buffer.estaCheio())){ //Se o valor do slider de velocidade for diferente de 0 e o buffer nao estiver cheio
        Platform.runLater(() -> {
          startAnimacao(); //Inicia a animacao do produtor
        });
        try{
          Anel item = produzirAnel(); //Um item do tipo anel recebe o retorno do metodo de producao
          ControllerTelaPrincipal.getVazio().acquire(); //Decrementa os espacos vazios no buffer
          ControllerTelaPrincipal.getMutex().acquire(); //Altera o valor do mutex para bloquear acoes durante a insercao
          buffer.inserirItem(item); //Insere o item no buffer
          ControllerTelaPrincipal.getMutex().release(); //Altera o valor do mutex para liberar as acoes no buffer
          ControllerTelaPrincipal.getCheio().release(); //Incrementa os espacos ocupados no buffer
        } catch (InterruptedException e){
          e.printStackTrace();
        }
      } else{ //Caso contrario (o buffer estar cheio ou o slider estiver em 0)
        Platform.runLater(() -> {
          stopAnimacao(); //Paralisa a animacao do produtor
        }); //Fim do runLater
      } //Fim do if-else

      try{
        //Calcula o tempo de sleep com velocidade dinâmica baseada no sliderQuanto maior o valor do slider, menor o tempo de sleep (maior velocidade)
        long tempoSleep = 600 - (long) slider.getValue();
        sleep(tempoSleep); //Coloca a thread para dormir pelo tempo em milissegundos
      } catch(InterruptedException e){
        e.printStackTrace();
      } //Fim do try-catch
    } //Fim do while
  } //Fim do metodo run
  

  /****************************************************************
  * Metodo: stopRun
  * Funcao: Metodo para paralisar a thread.
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  public void stopRun(){
    estaRodando = false; //A thread nao esta mais rodando
    stopAnimacao();
  } //Fim do metodo stopRun

    /****************************************************************
  * Metodo: startAnimacao
  * Funcao: Metodo para inicializar a animacao da thread.
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  public void startAnimacao(){
    imagem.setOpacity(0); //Torna a imagem do produtor parado invisivel
    animacaoGif.setOpacity(1); //Torna a animacao de gif do produtor visivel
  } //Fim do metodo startAnimacao

  /****************************************************************
  * Metodo: stopAnimacao
  * Funcao: Metodo para paralisar a animacao da thread.
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  public void stopAnimacao(){
    animacaoGif.setOpacity(0); //Torna a animacao gif do produtor invisivel
    imagem.setOpacity(1); //Torna a imagem do produtor parado visivel
  } //Fim do metodo stopAnimacao

  /****************************************************************
  * Metodo: produzirAnel
  * Funcao: Metodo para produzir o item a ser inserido no buffer pelo produtor.
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  private Anel produzirAnel(){
    Anel anel = new Anel(anchorPane); //Produz um novo anel com o anchorPane do produtor de referencia
    return anel; //Retorna o anel produzido;
  } //Fim do metodo produzirAnel

} //Fim da classe