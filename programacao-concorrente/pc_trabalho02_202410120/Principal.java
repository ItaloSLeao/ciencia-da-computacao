/*****************************************************************
* Autor............: Italo de Souza Leao
* Matricula........: 202410120
* Inicio...........: 03/05/2025
* Ultima alteracao.: 05/05/2025
* Nome.............: Principal.java
* Funcao...........: Realiza as importacoes necessarias para o pleno funcionamento proposto ao JavaFX no trabalho, inicia a aplicacao, 
inicializa a tela principal do programa, carregando o respectivo arquivo fxml, e executa a aplicacao escolhida.
*****************************************************************/

//Importacoes das bibliotecas e classes do JavaFX necessarias para o funcionamento adequado do programa
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import controller.ControllerTelaPrincipal; //Importacao da classe de controller utilizada na execucao da aplicacao

@SuppressWarnings("unused") //Supressao dos avisos de elementos que o compilador interpreta como nao utilizados, ainda que sejam necessarios para compilar o programa corretamente. Nesse caso, a importacao da classe do controller

public class Principal extends Application {
  /*****************************************************************
  * Metodo: start
  * Funcao: Inicializa a aplicacao, carregando o  telaPrincipal.fxml no palco (Stage) primaryStage
  * Parametros: Um Stage primaryStage, que eh o palco da aplicacao
  * Retorno: void
  *****************************************************************/
  @Override //Sobrescricao do metodo start da classe Application da biblioteca dp JavaFX
  public void start(Stage primaryStage) throws Exception { //Inicio do metodo start com tratamento de erros para a classe Exception e filhas

    FXMLLoader loader = new FXMLLoader(getClass().getResource("view/telaPrincipal.fxml")); //Carregamento do arquivo fxml da telaPrincipal para a variavel loader
		Parent root = loader.load(); //Definicao do no raiz (root) com o fxml da telaPrincipal e carregamento do respectivo controller pelo metodo .load()
		Scene scene = new Scene(root); //Definicao da cena (scene) com o root como parametro

    Image icon = new Image(getClass().getResourceAsStream("assets/train-piece-icon.png")); //Definicao da imagem train-piece-icon.png como Image icon

		primaryStage.setScene(scene); //Definicao do palco (primaryStage) com a scene como parametro
    primaryStage.getIcons().add(icon); //Adiciona a variavel Image icon aos icones do primaryStage
		primaryStage.setTitle("TRAIN PIECE"); //Definicao do titulo do Stage
		primaryStage.resizableProperty().setValue(false); //Definicao do Stage para nao redimensionavel, em prol de evitar erros com a parte grafica
		primaryStage.show(); //Apresentacao do Stage para o usuario

  } //Fim do metodo start
  

  /*****************************************************************
  * Metodo: main
  * Funcao: Atua como ponto de partida para a execucao de um codigo java. Esse eh o metodo ao qual o Java Virtual Machine (JVM) procura para iniciar o programa. Em versoes
  do Java 9 ou superiores, em uma aplicacao JavaFX, o metodo main nao eh o ponto de partida, e nem eh obrigatorio, porem ele se mantem necessario no Java 8.
  * Parametros: Um array de Strings chamado args, contendo os argumentos de linhas de comando java
  * Retorno: void
  *****************************************************************/
  public static void main(String[] args) {
    launch(args); /*Chama do metodo estatico da classe Application do JavaFX, que recebe os argumentos de linhas de comando e configura, automaticamente, um ambiente especial para roda-lo, 
    junto a uma thread dedicada (JavaFX Application Thread) e um ciclo de vida bem definido para a aplicacao*/
  } //Fim do metodo main

} //Fim da classe Principal