/*****************************************************************
* Autor............: Italo de Souza Leao
* Matricula........: 202410120
* Inicio...........: 15/03/2025
* Ultima alteracao.: 23/03/2025
* Nome.............: ControllerTelaPrincipal.java
* Funcao...........: Controla a tela principal da aplicacao, onde, nesta: realiza, por meio de timers, a chamada dos metodos de movimentacao dos trens; controla: as velocidades destes, dinamicamente; a escolha do posicionamento inicial dos trens;
as funcoes de: fechar a aplicacao e resetar as posicoes e as velocidades.
*****************************************************************/

package controller; //Posicionamento da classe ControllerTelaPrincipal no diretorio controller - adequado para inserior arquivos da logica da aplicacao, a fim de seguir o padrao MVC de arquitetura de software. Neste caso, para programacao desktop

//Importacoes das bibliotecas necessarias para a implementacao da interface Initializable
import java.net.URL;
import java.util.ResourceBundle;

//Importacoes das bibliotecas e classes do JavaFX, externas ao projeto, necessarias para o pleno funcionamento do programa
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

//Importacoes das classes internas ao projeto necessarias para a compilacao e execucao correta do programa
import model.Trem;
import util.ControleDosMovimentosDosTrens;

public class ControllerTelaPrincipal implements Initializable { //Implementacao da interface Initializable na assinatura da classe para configurar componentes graficos, suas variaveis e logicas de operacao e dados ou recursos externos
	@FXML
	private ChoiceBox<String> choicebox_telaPrincipal; //Insere o id da caixa de escolhas das posicoes iniciais dos trens (choicebox_telaPrincipal) do arquivo telaPrincipal.fxml

	@FXML
	private Slider sliderTrem1, sliderTrem2; //Insere os ids dos sliders, que controlam a velocidade dos dois trens (trem1 e trem2), no arquivo telaPrincipal.fxml

  @FXML
  private Button botaoDeFechar, botaoReset; //Insere os ids dos botoes, que sao responsaveis por: fechar a aplicacao e resetar o movimento e a velocidade dos trens, respectivamente; no arquivo telaPrincipal.fxml

	@FXML
	private ImageView imagemTrem1, imagemTrem2, imagemBotaoFechar, imagemBotaoReset, promptChoiceBox; //Insere os ids das imagens dos trens, que irao se mover pelos trilhos, dos botoes de fechar e de reset, e do texto de prompt da choicebox no arquivo telaPrincipal.fxml

  private String[] opcoesDePosicoesIniciais = {"Inferior esquerdo | Inferior direito", "Superior esquerdo | Superior direito", "Superior esquerdo | Inferior direito", "Inferior esquerdo | Superior direito"}; //Declaracao de um arranjo que guarda as 4 opcoes disponiveis para a posicao inicial dos trens

  private int indiceEscolhaPosicoes; //Declaracao de uma variavel inteira, que guardara o indice da escolha feita na choicebox, e ira variar de 0 a 3 (definicao de arranjos(n): indices variam de 0 a n-1)

  private Trem trem1, trem2; //Declaracao de variaveis do tipo Trem que irao fazer referencia as instancias de trem1 e trem2

  private double variavelDeMovimentoGrafico = 0.025; //Declaracao de uma variavel que controla o movimento grafico dos trens pela tela, limitando a 5,0% a velocidade original do movimento na tela, visando propiciar uma melhor visualizacao dos trens se movimentando
    //|-> OBS: Mediante testes, foi observado que a taxa de movimentacao dos trens depende do SO que o programa esta sendo executado e do seu modo de desempenho. Esta variavel esta ajustada para um linux no desempenho maximo (6 vezes mais rapido que o windows na mesma configuracao)

  private boolean primeiraEscolha = true; //Inicializacao de uma variavel que controla a verificacao da ordem de ocorrencia do evento de escolha de posicionamento inicial dos trens, isto eh, se eh a primeira vez que esta sendo escolhido

  AnimationTimer animacaoTrem1 = new AnimationTimer() { //Instanciacao de um objeto AnimationTimer animacaoTrem1, usando a implementacao de uma subclasse anonima de AnimationTimer, nesta parte especifica do codigo

    /*****************************************************************
    * Metodo: handle
    * Funcao: Permitir o movimento grafico do trem 1 na tela pela chamada do metodo abstrato de AnimationTimer, a cada frame da animacao, que executa tudo que esta contido nele.
    * Parametros: long now, um valor longo representando o tempo atual do sistema em nanossegundos (ns). Esse valor eh passado, automaticamente, pelo JavaFX quando o metodo handle eh invocado.
    * Retorno: void
    *****************************************************************/
    @Override //Sobrescricao do metodo handle
    public void handle(long now){
      trem1.setVelocidade(sliderTrem1.getValue() * variavelDeMovimentoGrafico); //Define a velocidade de trem1 como a multiplicacao do valor no slider de velocidade correspondente, pelo valor da variavelDeMovimentoGrafico
      ControleDosMovimentosDosTrens.moverTrem(trem1);
    } //Fim do metodo handle

  }; //Fim da implementacao de animacaoTrem1

  AnimationTimer animacaoTrem2 = new AnimationTimer() { //Bem como o animacaoTrem1, o objeto animacaoTrem2 usa a implementacao de uma subclasse anonima de AnimationTimer, nesta parte especifica do codigo

    /*****************************************************************
    * Metodo: handle
    * Funcao: Permitir o movimento grafico do trem 2 na tela pela chamada do metodo abstrato de AnimationTimer, a cada frame da animacao, que executa tudo que esta contido nele.
    * Parametros: long now, um valor longo representando o tempo atual do sistema em nanossegundos (ns). Esse valor eh passado, automaticamente, pelo JavaFX quando o metodo handle eh invocado.
    * Retorno: void
    *****************************************************************/
    @Override //Sobrescricao do metodo handle
    public void handle(long now){
      trem2.setVelocidade(sliderTrem2.getValue() * variavelDeMovimentoGrafico); //Define a velocidade de trem2 como a multiplicacao do valor no slider de velocidade correspondente, pelo valor da variavelDeMovimentoGrafico
      ControleDosMovimentosDosTrens.moverTrem(trem2);
    } //Fim do metodo handle

  }; //Fim da implementacao de animacaoTrem2



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



	/*****************************************************************
  * Metodo: fecharAplicacao
  * Funcao: Fecha a aplicacao quando o usuario clica no botao de fechar.
  * Parametros: Nenhum. Este metodo ja esta associado ao onAction() do botaoDeFechar pela tag "#fecharAplicacao" no arquivo telaPrincipal.fxml
  * Retorno: void
  *****************************************************************/
  @FXML //Anotacao para designar que fecharAplicacao eh um metodo de acao que eh chamado quando ha um clique no botaoDeFechar
  public void fecharAplicacao() {
    System.exit(0);
  } //Fim do metodo fecharAplicacao



  /*****************************************************************
  * Metodo: resetarPosicoesEVelocidades
  * Funcao: Reiniciar a animacao, de acordo as posicoes dos trens escolhidas e a velocidade padrao estabelecida, assim que o botaoReset eh clicado
  * Parametros: Nenhum. Este metodo ja esta associado ao onAction() do botaoReset pela tag "#resetarPosicoesEVelocidades" no arquivo telaPrincipal.fxml
  * Retorno: void
  *****************************************************************/
  @FXML //Anotacao para designar que resetarPosicoesEVelocidades eh um metodo de acao que eh chamado quando ha um clique no botaoReset
  public void resetarPosicoesEVelocidades(){
    posicionarTrens(); //Chamada do metodo posicionarTrens, que reseta as posicoes dos trens
    sliderTrem1.setValue(10); //Reseta o valor do sliderTrem1 para 1, fazendo a velocidade retornar ao valor correspondente a 1 no slider
    sliderTrem2.setValue(10); //Reseta o valor do sliderTrem2 para 1, fazendo a velocidade retornar ao valor correspondente a 1 no slider
  } //Fim do metodo resetarPosicoesEVelocidades



  /*****************************************************************
  * Metodo: posicionarTrens
  * Funcao: Define o posicionamento inicial dos trens de acordo com a escolha do usuario na choicebox, no momento em que ele eh chamado. Na inicalizacao da aplicacao, o posicionamento eh
  feito pela primeira vez. Nas chamadas subsequentes, o metodo reseta as posicoes dos trens, sem alterar sua velocidade.
  * Parametros: nenhum
  * Retorno: void
  * Observacoes: Esse metodo eh chamado no metodo initialize, no metodo resetar e quando o usuario faz alguma acao na choicebox logo ele eh responsavel por definir a posicao inicial dos trens de acordo com a escolha do usuario sobre as posicoes dos trens no momento em que ele eh chamado
  *****************************************************************/
  public void posicionarTrens(){
    /* As opcoes de posicoes iniciais na choicebox seguem este padrao:
		* Trem 1 					  | Trem 2
		* Inferior esquerdo | Inferior direito
		* Superior esquerdo | Superior direito
		* Superior esquerdo | Inferior direito
    * Inferior direito  | Superior direito*/

    switch(indiceEscolhaPosicoes){ //Switch-case que define o posicionamento inicial dos trens de acordo com a escolha feita na choicebox

      case 0: //Escolha: Inferior Esquerdo | Inferior Direito. O indice do arranjo correspondente a essa opcao eh 0. Logo, no padrao supracitado, os trens 1 e 2 serao posicionados nas posicoes inferior esquerda e inferior direita, respectivamente
        trem1 = new Trem(imagemTrem1, 0); //Instanciacao de trem1 com definicao da sua imagem imagemTrem1 e seu caso de posicionamento 0, estabelecido na classe Trem
        trem2 = new Trem(imagemTrem2, 2); //Instanciacao de trem2 com definicao da sua imagem imagemTrem2 e seu caso de posicionamento 2, estabelecido na classe Trem
        break;
      case 1: //Escolha: Superior esquerdo | Superior direito. O indice do arranjo correspondente a essa opcao eh 1. O padrao se repete nos casos subsequentes
        trem1 = new Trem(imagemTrem1, 1); //Instanciacao de trem1 com definicao da sua imagem imagemTrem1 e seu caso de posicionamento 1, estabelecido na classe Trem
        trem2 = new Trem(imagemTrem2, 3); //Instanciacao de trem2 com definicao da sua imagem imagemTrem2 e seu caso de posicionamento 3, estabelecido na classe Trem
        break;
      case 2: //Escolha: Superior esquerdo | Inferior direito. O indice do arranjo correspondente a essa opcao eh 2.
        trem1 = new Trem(imagemTrem1, 1); //Instanciacao de trem1 com definicao da sua imagem imagemTrem1 e seu caso de posicionamento 1, estabelecido na classe Trem
        trem2 = new Trem(imagemTrem2, 2); //Instanciacao de trem2 com definicao da sua imagem imagemTrem2 e seu caso de posicionamento 2, estabelecido na classe Trem
        break;
      case 3: //Escolha: Inferior direito | Superior direito. O indice do arranjo correspondente a essa opcao eh 3.
        trem1 = new Trem(imagemTrem1, 0); //Instanciacao de trem1 com definicao da sua imagem imagemTrem1 e seu caso de posicionamento 0, estabelecido na classe Trem
        trem2 = new Trem(imagemTrem2, 3); //Instanciacao de trem2 com definicao da sua imagem imagemTrem2 e seu caso de posicionamento 3, estabelecido na classe Trem
        break;
      default:
        System.out.println("ERRO NO METODO posicionarTrens DA CLASSE ControllerTelaPrincipal"); //Verificacao de possiveis ocorrencias nao esperadas no switch-case pela impressao de um texto no terminal
        break;
    } //Fim do switch-case

  } //Fim do metodo posicionarTrens




	/*****************************************************************
  * Metodo: initialize
  * Funcao: Configurar os componentes da interface gráfica, os dados iniciais ou realizar qualquer outra inicializacao necessaria para a aplicacao, antes que a mesma seja exibida para o usuario.
  Após o carregamento do arquivo telaPrincipal.fxml e a injecao dos componentes deste no ControllerTelaPrincipal, o arranjo de opcoes de posicoes iniciais eh carregado na choicebox_telaPrincipal, 
  eventos do tipo "listeners" (OnAction(), OnMouseEntered(), OnMouseExited(), etc.) são configurados e erros como "NullPointerException" são evitados.
  * Parametros: Um URL location e um ResourceBundle resources, que sao parametros padroes do metodo initialize
  * Retorno: void
  *****************************************************************/
	@Override
  public void initialize(URL location, ResourceBundle resources) {
		choicebox_telaPrincipal.getItems().addAll(opcoesDePosicoesIniciais); //Acrescenta, na choicebox_telaPrincipal, as opcoes guardadas no array opcoesDePosicoesIniciais, quando a telaPrincipal eh carregada

    choicebox_telaPrincipal.setOnAction(event -> { //Configura um bloco de codigo que devera ser executado, assim que o evento onAction da choicebox for acionado. Neste caso, a escolha de uma das opcoes

      indiceEscolhaPosicoes = choicebox_telaPrincipal.getSelectionModel().getSelectedIndex(); //Atualiza, a cada escolha feita, o indice correspondente a opcao selecionada no arranjo

      if(primeiraEscolha){ //Bloco condicional que verifica se eh a primeira vez que o evento onAction() esta sendo acionado
        posicionarTrens(); //Chamada do metodo posicionarTrens para que ele organize a disposicao dos trens nas suas respectivas posicoes
        sliderTrem1.setValue(10); //Definicao do valor inicial do slider de velocidade do trem 1, assim que a escolha for feita, como 1
        sliderTrem2.setValue(10); //Definicao do valor inicial do slider de velocidade do trem 2, assim que a escolha for feita, como 1
        primeiraEscolha = false; //Atribuicao do valor de falsidade a variavel de controle para que as proximas ativacoes do evento nao sejam executadas com redefinicao do valor de velocidade
      } else{
        posicionarTrens(); //Chamada do metodo posicionarTrens para que ele organize a disposicao dos trens nas suas respectivas posicoes, conforme a opcao escolhida, sem alterar a velocidade, da segunda vez em diante que o onAction() for ativado
      }

      promptChoiceBox.setVisible(false); //Torna o ImageView promptChoiceBox, sobreposto a choicebox, invisivel
      imagemTrem1.setOpacity(1); //Define a opacidade da imagem do trem 1, que, a priori, comeca com valor 0, para 1
      imagemTrem2.setOpacity(1); //Define a opacidade da imagem do trem 2, que, a priori, comeca com valor 0, para 1

      animacaoTrem1.start(); //Inicializacao da animacao de movimento do trem1 pela tela, pela chamada do metodo start() da classe AnimationTimer
      animacaoTrem2.start(); //Inicializacao da animacao de movimento do trem2 pela tela, pela chamada do metodo start() da classe AnimationTimer

    }); //Fim do metodo setOnAction() da choicebox_telaPrincipal 
    
    imagemTrem1.setOnMouseEntered(event -> aplicarEscala(imagemTrem1, 1.05)); //Configura um evento ouvinte onMouseEntered para quando o mouse passar acima do imagemTrem1, seja aplicado uma escala nas suas dimensoes (fator 1.15 ou 115%)
    imagemTrem1.setOnMouseExited(event -> aplicarEscala(imagemTrem1, 1.0)); //Configura um evento ouvinte onMouseExited para quando o mouse de cima do imagemTrem1, seja aplicado uma escala nas suas dimensoes (fator 1.0 ou 100%), retornando a dimensao original
    imagemTrem2.setOnMouseEntered(event -> aplicarEscala(imagemTrem2, 1.05)); //Configura um evento ouvinte onMouseEntered para quando o mouse passar acima do imagemTrem2, seja aplicado uma escala nas suas dimensoes (fator 1.15 ou 115%)
    imagemTrem2.setOnMouseExited(event -> aplicarEscala(imagemTrem2, 1.0)); //Configura um evento ouvinte onMouseExited para quando o mouse de cima da imagemTrem2, seja aplicado uma escala nas suas dimensoes (fator 1.0 ou 100%), retornando a dimensao original
    
    botaoDeFechar.setOnMouseEntered(event -> aplicarEscala(imagemBotaoFechar, 1.15)); //Configura um evento ouvinte onMouseEntered para quando o mouse passar acima do botaoDeFechar, seja aplicado uma escala nas dimensoes de imagemBotaoFechar (fator 1.15 ou 115%)
    botaoDeFechar.setOnMouseExited(event -> aplicarEscala(imagemBotaoFechar, 1.0)); //Configura um evento ouvinte onMouseExited para quando o mouse sair de cima do botaoDeFechar, seja aplicado uma escala nas suas dimensoes (fator 1.0 ou 100%), retornando a dimensao original
    botaoReset.setOnMouseEntered(event -> aplicarEscala(imagemBotaoReset, 1.15)); //Configura um evento ouvinte onMouseEntered para quando o mouse passar acima do botaoReset, seja aplicado uma escala nas dimensoes de imagemBotaoReset (fator 1.15 ou 115%)
    botaoReset.setOnMouseExited(event -> aplicarEscala(imagemBotaoReset, 1.0)); //Configura um evento ouvinte onMouseExited para quando o mouse sair de cima do botaoReset, seja aplicado uma escala nas suas dimensoes (fator 1.0 ou 100%), retornando a dimensao original

  } //Fim do metodo initialize

} //Fim da classe ControllerTelaPrincipal