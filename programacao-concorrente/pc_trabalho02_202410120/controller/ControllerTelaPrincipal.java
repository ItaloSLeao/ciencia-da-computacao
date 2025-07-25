/*****************************************************************
* Autor............: Italo de Souza Leao
* Matricula........: 202410120
* Inicio...........: 02/05/2025
* Ultima alteracao.: 05/05/2025
* Nome.............: ControllerTelaPrincipal.java
* Funcao...........: Controla a tela principal da aplicacao, onde, nesta: realiza a chamada das threads de movimentacao dos trens; controla: as velocidades destes, dinamicamente; a escolha do posicionamento inicial dos trens;
a escolha da solucao da concorrencia entre eles; as funcoes de: fechar a aplicacao e resetar as posicoes e as velocidades.
*****************************************************************/

package controller;

//Importacoes das bibliotecas necessarias para a implementacao da interface Initializable
import java.net.URL;
import java.util.ResourceBundle;

//Importacoes das bibliotecas e classes do JavaFX, externas ao projeto, necessarias para o pleno funcionamento do programa
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.ScaleTransition;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

//Importacoes das classes internas ao projeto necessarias para a compilacao e execucao correta do programa
import model.ThreadTrem1;
import model.ThreadTrem2;

public class ControllerTelaPrincipal implements Initializable { //Implementacao da interface Initializable na assinatura da classe para configurar componentes graficos, suas variaveis e logicas de operacao e dados ou recursos externos
	@FXML
	private ChoiceBox<String> choiceBox_posicoes, choiceBox_solucoes; //Insere o id das caixas de escolhas das posicoes iniciais dos trens (choiceBox_posicoes) e das solucoes de concorrencia (choiceBox_solucoes) do arquivo telaPrincipal.fxml

	@FXML
	private Slider sliderTrem1, sliderTrem2; //Insere os ids dos sliders, que controlam a velocidade dos dois trens (trem1 e trem2), no arquivo telaPrincipal.fxml

  @FXML
  private Button botaoDeFechar, botaoReset; //Insere os ids dos botoes, que sao responsaveis por: fechar a aplicacao e resetar o movimento e a velocidade dos trens, respectivamente; no arquivo telaPrincipal.fxml

	@FXML
	private ImageView imagemTrem1, imagemTrem2, imagemBotaoFechar, imagemBotaoReset, promptChoiceBox1, promptChoiceBox2; //Insere os ids das imagens dos trens, que irao se mover pelos trilhos, dos botoes de fechar e de reset, e dos textos de prompt das choiceboxes no arquivo telaPrincipal.fxml

  private String[] opcoesDePosicoesIniciais = {"Inferior esquerdo | Inferior direito", "Superior esquerdo | Superior direito", "Superior esquerdo | Inferior direito", "Inferior esquerdo | Superior direito"}; //Declaracao de um arranjo que guarda as 4 opcoes disponiveis para a posicao inicial dos trens
  private String[] opcoesDeSolucoes = {"Variaveis de travamento", "Estrita alternancia", "Solucao de Peterson"}; //Declaracao de um arranjo que guarda as 3 opcoes disponiveis de solucao para o problema de sincronizacao dos trens

  private int indiceEscolhaPosicoes = 4; //Declaracao de uma variavel inteira, que guardara o indice da escolha feita na choiceBox_posicoes, e ira variar de 0 a 3 (definicao de arranjos(n): indices variam de 0 a n-1)
  private int indiceEscolhaSolucoes = 3; //Declaracao de uma variavel inteira, que guardara o indice da escolha feita na choiceBox_solucoes, e ira variar de 0 a 2 (definicao de arranjos(n): indices variam de 0 a n-1)
  /*Os indices acima sao inicializados com valores que extrapolam os seus respectivos arranjos, de modo a nao ter escolha definida, ate que seja feita*/

  private ThreadTrem1 threadTrem1; //Declaracao de variavel do tipo ThreadTrem1 que ira fazer referencia a instancia de controle de movimentacao do trem 1
  private ThreadTrem2 threadTrem2; //Declaracao de variavel do tipo ThreadTrem2 que ira fazer referencia a instancia de controle de movimentacao do trem 2

  private boolean primeiraEscolha = true; //Inicializacao de uma variavel que controla a verificacao da ordem de ocorrencia do evento de escolha de posicionamento inicial dos trens, isto eh, se eh a primeira vez que esta sendo escolhido

  //Variaveis estaticas volateis de Travamento para essa solucao da concorrencia entre os trens
  public static volatile int variavelDeTravamento1 = 0; //Variavel de travamento da rotina da zona critica 1
  public static volatile int variavelDeTravamento2 = 0; //Variavel de travamento da rotina da zona critica 2
  //Variaveis estaticas volateis para a solucao de concorrencia pela Estrita Alternancia
  public static volatile int turno_estrita_alternancia1 = 0; //Variavel de turno de estrita alternancia da threadTrem1 
  public static volatile int turno_estrita_alternancia2 = 0; //Variavel de turno de estrita alternancia da threadTrem2
  //Variaveis estaticas volateis para o problema de concorrencia pela Solucao de Peterson
  public static volatile int turno_solucao_peterson1; //Variavel de turno da solucao de peterson da threadTrem1
  public static volatile boolean[] interesseNaZona1 = {false, false}; //Arranjo de interesse dos processos na zona 1 (Modelo de ordenacao do array: {threadTrem1, threadTrem2})
  public static volatile int turno_solucao_peterson2; //Variavel de turno da solucao de peterson da threadTrem2
  public static volatile boolean[] interesseNaZona2 = {false, false}; //Arranjo de interesse dos processos na zona 2

  /*As variaveis acima sao volateis para possibilitar a leitura e compartilhamento delas por e entre threads no Java
  **Elas operam em forma de recurso compartilhado entre os processos, a fim de controlar as situacoes de colisoes**/



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
    //Para o movimento grafico dos trens na tela, para que o reposicionamento seja feito
    threadTrem1.getTimerDeMovimento().stop();
    threadTrem2.getTimerDeMovimento().stop();

    /* As opcoes de posicoes iniciais na choicebox seguem este padrao:
		* Trem 1 					  | Trem 2
		* Inferior esquerdo | Inferior direito
		* Superior esquerdo | Superior direito
		* Superior esquerdo | Inferior direito
    * Inferior esquerdo  | Superior direito*/

    switch(indiceEscolhaPosicoes){ //Switch-case que define o posicionamento inicial dos trens de acordo com a escolha feita na choicebox

      case 0: //Escolha: Inferior Esquerdo | Inferior Direito. O indice do arranjo correspondente a essa opcao eh 0. Logo, no padrao supracitado, os trens 1 e 2 serao posicionados nas posicoes inferior esquerda e inferior direita, respectivamente
        threadTrem1 = new ThreadTrem1(imagemTrem1, 0, sliderTrem1, indiceEscolhaSolucoes); //Instanciacao de trem1 com definicao da sua imagem imagemTrem1 e seu caso de posicionamento 0, estabelecido na classe Trem
        threadTrem2 = new ThreadTrem2(imagemTrem2, 2, sliderTrem2, indiceEscolhaSolucoes); //Instanciacao de trem2 com definicao da sua imagem imagemTrem2 e seu caso de posicionamento 2, estabelecido na classe Trem
        break;
      case 1: //Escolha: Superior esquerdo | Superior direito. O indice do arranjo correspondente a essa opcao eh 1. O padrao se repete nos casos subsequentes
        threadTrem1 = new ThreadTrem1(imagemTrem1, 1, sliderTrem1, indiceEscolhaSolucoes);  //Instanciacao de trem1 com definicao da sua imagem imagemTrem1 e seu caso de posicionamento 1, estabelecido na classe Trem
        threadTrem2 = new ThreadTrem2(imagemTrem2, 3, sliderTrem2, indiceEscolhaSolucoes); //Instanciacao de trem2 com definicao da sua imagem imagemTrem2 e seu caso de posicionamento 3, estabelecido na classe Trem
        break;
      case 2: //Escolha: Superior esquerdo | Inferior direito. O indice do arranjo correspondente a essa opcao eh 2.
        threadTrem1 = new ThreadTrem1(imagemTrem1, 1, sliderTrem1, indiceEscolhaSolucoes);  //Instanciacao de trem1 com definicao da sua imagem imagemTrem1 e seu caso de posicionamento 1, estabelecido na classe Trem
        threadTrem2 = new ThreadTrem2(imagemTrem2, 2, sliderTrem2, indiceEscolhaSolucoes); //Instanciacao de trem2 com definicao da sua imagem imagemTrem2 e seu caso de posicionamento 2, estabelecido na classe Trem
        break;
      case 3: //Escolha: Inferior esquerdo | Superior direito. O indice do arranjo correspondente a essa opcao eh 3.
        threadTrem1 = new ThreadTrem1(imagemTrem1, 0, sliderTrem1, indiceEscolhaSolucoes); //Instanciacao de trem1 com definicao da sua imagem imagemTrem1 e seu caso de posicionamento 0, estabelecido na classe Trem
        threadTrem2 = new ThreadTrem2(imagemTrem2, 3, sliderTrem2, indiceEscolhaSolucoes); //Instanciacao de trem2 com definicao da sua imagem imagemTrem2 e seu caso de posicionamento 3, estabelecido na classe Trem
        break;
      default:
        System.out.println("ERRO NO METODO posicionarTrens DA CLASSE ControllerTelaPrincipal"); //Verificacao de possiveis ocorrencias nao esperadas no switch-case pela impressao de um texto no terminal
        break;
    } //Fim do switch-case

    threadTrem1.run(); //Chamada do metodo que inicia a thread do trem 1, junto ao seu movimento grafico na tela
    threadTrem2.run(); //Chamada do metodo que inicia a thread do trem 2, junto ao seu movimento grafico na tela

    //Reinicializacao dos valores das variaveis de travamento, dos turnos e dos interesses dos processos nas zonas
    variavelDeTravamento1 = 0;
    variavelDeTravamento2 = 0;
    turno_estrita_alternancia1 = 0;
    turno_estrita_alternancia2 = 0;
    interesseNaZona1[0] = false;
    interesseNaZona1[1] = false;
    interesseNaZona2[0] = false;
    interesseNaZona2[1] = false;

  } //Fim do metodo posicionarTrens




	/*****************************************************************
  * Metodo: initialize
  * Funcao: Configurar os componentes da interface gráfica, os dados iniciais ou realizar qualquer outra inicializacao necessaria para a aplicacao, antes que a mesma seja exibida para o usuario.
  Após o carregamento do arquivo telaPrincipal.fxml e a injecao dos componentes deste no ControllerTelaPrincipal, o arranjo de opcoes de posicoes iniciais eh carregado na choicebox_telaPrincipal, 
  eventos do tipo "listeners" (OnAction(), OnMouseEntered(), OnMouseExited(), etc.) são configurados e erros como "NullPointerException" são evitados. As threads de movimentacao dos trens sao
  inicializadas neste metodo, ainda que nao tenham sido escolhidas as opcoes de posicionamento inicial ou de solucoes de concorrencia.
  * Parametros: Um URL location e um ResourceBundle resources, que sao parametros padroes do metodo initialize
  * Retorno: void
  *****************************************************************/
	@Override
  public void initialize(URL location, ResourceBundle resources) {
		choiceBox_posicoes.getItems().addAll(opcoesDePosicoesIniciais); //Acrescenta, na choiceBox_posicoes, as opcoes guardadas no array opcoesDePosicoesIniciais, quando a telaPrincipal eh carregada
    choiceBox_solucoes.getItems().addAll(opcoesDeSolucoes); //Acrescenta, na choiceBox_solucoes, as opcoes guardadas no array opcoesDeSolucoes, quandp a telaPrincipal eh carregada

    choiceBox_posicoes.setOnAction(event -> { //Configura um bloco de codigo que devera ser executado, assim que o evento onAction da choicebox for acionado. Neste caso, a escolha de uma das opcoes

      indiceEscolhaPosicoes = choiceBox_posicoes.getSelectionModel().getSelectedIndex(); //Atualiza, a cada escolha feita, o indice correspondente a opcao selecionada no arranjo

      if(primeiraEscolha){ //Bloco condicional que verifica se eh a primeira vez que o evento onAction() esta sendo acionado
        posicionarTrens(); //Chamada do metodo posicionarTrens para que ele organize a disposicao dos trens nas suas respectivas posicoes
        sliderTrem1.setValue(10); //Definicao do valor inicial do slider de velocidade do trem 1, assim que a escolha for feita, como 1
        sliderTrem2.setValue(10); //Definicao do valor inicial do slider de velocidade do trem 2, assim que a escolha for feita, como 1
        primeiraEscolha = false; //Atribuicao do valor de falsidade a variavel de controle para que as proximas ativacoes do evento nao sejam executadas com redefinicao do valor de velocidade
      } else{
        posicionarTrens(); //Chamada do metodo posicionarTrens para que ele organize a disposicao dos trens nas suas respectivas posicoes, conforme a opcao escolhida, sem alterar a velocidade, da segunda vez em diante que o onAction() for ativado
      }

      promptChoiceBox1.setVisible(false); //Torna o ImageView promptChoiceBox1, sobreposto a choicebox, invisivel
      imagemTrem1.setOpacity(1); //Define a opacidade da imagem do trem 1, que, a priori, comeca com valor 0, para 1
      imagemTrem2.setOpacity(1); //Define a opacidade da imagem do trem 2, que, a priori, comeca com valor 0, para 1

    }); //Fim do metodo setOnAction() da choicebox_telaPrincipal

    choiceBox_solucoes.setOnAction(event -> { //Configura um bloco de codigo que devera ser executado, assim que o evento onAction da choicebox for acionado

      indiceEscolhaSolucoes = choiceBox_solucoes.getSelectionModel().getSelectedIndex(); //O indice da escolha de solucoes eh carregado e atualizado a cada opcao selecionada
      promptChoiceBox2.setVisible(false); //Torna o ImageView promptChoiceBox2, sobreposto a choicebox, invisivel
      threadTrem1.setSolucao(indiceEscolhaSolucoes); //Atualiza o atributo de solucao da threamTrem1 de acordo com o indice da solucao escolhida na choicebox
      threadTrem2.setSolucao(indiceEscolhaSolucoes); //Atualiza o atributo de solucao da threamTrem2 de acordo com o indice da solucao escolhida na choicebox
      //|-> Caso a opcao de posicionamento ainda nao tenha sido escolhida, o terminal apenas imprime um texto notificando um erro, mas a movimentacao grafica nao acontece ate que a opcao de posicao inicial tenha sido escolhida
      /*Caso esse metodo nao seja chamado antes da escolha das posicoes, a movimentacao dos trens comeca sem controle de colisoes, ate que o metodo onAction da
      choiceBox_solucoes seja acionado, a escolha seja feita e a logica de solucao da concorrencia seja aplicada*/
    });

    //Instanciacao das threads dos trens com suas imagens e sliders fixos, e os indices das escolhas definidos fora dos seus arranjos, de modo a nao iniciar uma thread nula 
    threadTrem1 = new ThreadTrem1(imagemTrem1, indiceEscolhaPosicoes, sliderTrem1, indiceEscolhaSolucoes);
    threadTrem2 = new ThreadTrem2(imagemTrem2, indiceEscolhaPosicoes, sliderTrem2, turno_solucao_peterson1);
    //Inicializacao das threads de movimentacao dos trens pelo metodo run() herdado de Thread
    threadTrem1.start();
    threadTrem2.start();
    
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