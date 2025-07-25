/*****************************************************************
* Autor............: Italo de Souza Leao
* Matricula........: 202410120
* Inicio...........: 17/03/2025
* Ultima alteracao.: 23/03/2025
* Nome.............: Trem.java
* Funcao...........: Define a classe de modelo Trem, abstracao do funcionamento pensado de um trem para a aplicacao, com os atributos: imagemTrem - representacao grafica; 
velocidade, taxa de pixels que a imagemTrem se move pelos trilhos; posicaoInicial, padrao de posicionamento, mediante a escolha do usuario, anterior a movimentacao dos trens. 
*****************************************************************/

package model; //Posicionamento da classe Trem no diretorio model - adequado para inserir arquivos de modelacao de objetos, a fim de seguir o padrao MVC de arquitetura de software. Neste caso, para programacao desktop 

import javafx.scene.image.ImageView; //Importacao da classe ImageView para a declaracao do atributo da classe Trem, imagemTrem


public class Trem{
  private ImageView imagemTrem;
  private double velocidade = 0.0;
  private int posicaoInicial;
  
  public Trem(ImageView imagemTrem, int posicaoInicial){ //Construtor da classe

    this.imagemTrem = imagemTrem; //Atribui a imagemTrem o parametro ImageView com a imagem do trem
	this.posicaoInicial = posicaoInicial; //Atribui a posicaoInicial o parametro inteiro com a posicao inicial dos trens

    switch (posicaoInicial) {

	  case 0: //Caso em que a posicao inicial do trem eh inferior esquerda
	    imagemTrem.setLayoutX(469); //Posiciona a imagem do trem alinhada horizontalmente ao trilho esquerdo do circuito
		imagemTrem.setLayoutY(601); //Posiciona a imagem do trem alinhada verticalmente ao inicio (orientacaoo: de baixo para cima) do trilho direito do circuito
		imagemTrem.setRotate(0.0); //Mantem a rotacao da imagem do trem, pois estao padronizadas na vertical
		break;
	  case 1: //Caso em que a posicial inicial do trem eh superior esquerda
		imagemTrem.setLayoutX(469); //Posiciona a imagem do trem alinhada horizontalmente ao trilho esquerdo do circuito
		imagemTrem.setLayoutY(0); //Posiciona a imagem do trem alinhada verticalmente ao fim do trilho direito (orientacao: de baixo para cima) do circuito
		imagemTrem.setRotate(180.0); //Rotaciona a imagem do trem em 180 graus a fim que o final do trilho direito, isto eh, a parte superior direita, seja, nesta orientacao, o inicio
		break;
	  case 2: //Caso em que a posicao inicial do trem eh inferior direita
		imagemTrem.setLayoutX(515); //Posiciona a imagem do trem alinhada horizontalmente ao trilho direito do circuito
		imagemTrem.setLayoutY(601); //Posiciona a imagem do trem alinhada verticalmente ao inicio (orientacao: de baixo para cima) do trilho direito do circuito
		imagemTrem.setRotate(0.0); //Mantem a rotacao da imagem do trem, pois estao padronizadas na vertical
		break;
	  case 3: //Caso em que a posicial inicial do trem eh superior direita
		imagemTrem.setLayoutX(515); //Posiciona a imagem do trem alinhada horizontalmente ao trilho direito do circuito
		imagemTrem.setLayoutY(0); //Posiciona a imagem do trem alinhada verticalmente ao fim do trilho direito (orientacao: de baixo para cima) do circuito
		imagemTrem.setRotate(180.0); //Rotaciona a imagem do trem em 180 graus a fim que o final do trilho direito, isto eh, a parte superior direita, seja, nesta orientacao, o inicio
		break;
	  default:
		System.out.println("ERRO NO CONSTRUTOR DA CLASSE TREM"); //Verificacao de possiveis ocorrencias nao esperadas no switch-case pela impressao de um texto no terminal
		break;

	} //Fim do switch-case

  } //Fim do construtor da classe


  /*****************************************************************
  * Metodo: getimagemTrem
  * Funcao: Retorna a imagem do trem, um ImageView, que representa o trem graficamente na tela, e permite a movimentacao daquele na tela
  * Parametros: nenhum
  * Retorno: ImageView, o atributo imagemTrem
  *****************************************************************/
  public ImageView getImagemTrem() {
    return imagemTrem;
  } //Fim do metodo getImagemTrem


  /*****************************************************************
  * Metodo: getVelocidade
  * Funcao: Retorna a velocidade do trem, um numero double que permite modificar a variavel de movimento que muda a posicao do trem na tela
  * Parametros: nenhum
  * Retorno: double, o atributo velocidade
  *****************************************************************/
  public double getVelocidade() {
    return velocidade;
  } //Fim do metodo getVelocidade


  /*****************************************************************
  * Metodo: getPosicaoInicial
  * Funcao: Retorna a posicao inicial do trem escolhida pelo usuario para controle da ordem de movimento dos trens
  * Parametros: nenhum
  * Retorno: int, o atributo inteiro posicaoInicial, que representa o posicionamento inicial do trem
  *****************************************************************/
  public int getPosicaoInicial() {
    return posicaoInicial;
  } //Fim do metodo getPosicaoInicial
	

  /*****************************************************************
  * Metodo: setVelocidade
  * Funcao: Modifica a velocidade do trem, um numero double, que permite modificar a variavel de movimento, que muda a posicao do trem na tela
  * Parametros: double velocidade, a velocidade do trem
  * Retorno: void
  *****************************************************************/
  public void setVelocidade(double velocidade) {
    this.velocidade = velocidade;
  } //Fim do metodo setVelocidade

} //Fim da classe Trem