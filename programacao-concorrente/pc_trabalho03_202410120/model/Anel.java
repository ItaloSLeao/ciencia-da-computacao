/* ***************************************************************
* Autor............: Italo de Souza Leao
* Matricula........: 202410120
* Inicio...........: 13/06/2025
* Ultima alteracao.: 04/08/2025
* Nome.............: Anel.java
* Funcao...........: Modela as caracterisiticas e os comportamentos do anel que eh o produto nesse programa.
****************************************************************/

package model;

//Importacoes necessarias para o funcionamento
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class Anel {

  private ImageView imagemAnel; //Imagem do anel
  private AnchorPane anchorPane; //Anchorpane que o anel vai ser inserido
  private int[] posicoes = {460, 410, 360, 310, 260, 210}; //Arranjo de posicoes Y dos aneis na tela da aplicacao, conforme forem adicionados

  public Anel(AnchorPane anchorPane){ //Construtor da classe
    this.anchorPane = anchorPane;
  } //Fim do construtor

  /****************************************************************
  * Metodo: tornarVisivel
  * Funcao: Adiciona um anel ao anchorPane e o reajusta conforme sua posicao no buffer
  * Parametros: int indiceIn, o indice do item que esta entrando no buffer
  * Retorno: void
  ****************************************************************/
  public void tornarVisivel(int indiceIn){
    imagemAnel = new ImageView(new Image(getClass().getResourceAsStream("/assets/icon-um-anel.png"))); //Instancia uma nova imagem de um anel
    imagemAnel.setLayoutX(660); //Define sua posicao X padrao para 695
    imagemAnel.setLayoutY(posicoes[indiceIn]); //Define sua posicao Y na tela de acordo com o indice do item no buffer
    anchorPane.getChildren().add(imagemAnel); //Adiciona a imagem do anel do anchor pane
  } //Fim do metodo tornarVisivel

  /****************************************************************
  * Metodo: tornarInvisivel
  * Funcao: Remover o anel do anchorPane, retirando-o do buffer
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  public void tornarInvisivel(){
    anchorPane.getChildren().remove(imagemAnel); //Remove a imagem da instancia Anel.this dos filhos do anchor pane
  } //Fim do metodo tornarInvisivel
  
} //Fim da classe Anel