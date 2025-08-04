/* ***************************************************************
* Autor............: Italo de Souza Leao
* Matricula........: 202410120
* Inicio...........: 17/06/2025
* Ultima alteracao.: 18/06/2025
* Nome.............: Principal.java
* Funcao...........: Realiza as importacoes necessarias para o pleno funcionamento proposto ao javaFX no trabalho, inicia a aplicacao, inicializa a tela principal do programa, 
carregando o respectivo arquivo fxml e executa a aplicacao escolhida.
****************************************************************/

//Importacao das bibliotecas e arquivos necessarios para o funcionamento adequado do programa
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import controller.ControllerTelaPrincipal;

@SuppressWarnings("unused") //Notacao de supressao de avisos de linha de codigo nao usada

public class Principal extends Application {

  /****************************************************************
  * Metodo: start
  * Funcao: Metodo da classe Application que carrega o arquivo fxml da tela principal da aplicacao e monta a cena, o palco e exibe ao usuario.
  * Parametros: Stage primaryStage, o palco da aplicacao javaFX
  * Retorno: void
  ****************************************************************/
  @Override //Notacao de sobrescricao
  public void start(Stage primaryStage) throws Exception { //Inicio do metodo start com tratamento de erros para a classe Exception e filhas

    FXMLLoader loader = new FXMLLoader(getClass().getResource("view/telaPrincipal.fxml")); //Carregamento do arquivo fxml da telaPrincipal para a variavel loader
    Parent root = loader.load(); //Definicao do no raiz (root) com o fxml da telaPrincipal e carregamento do respectivo controller pelo metodo .load()

    Scene scene = new Scene(root); //Definicao da cena (scene) com o root como parametro

    primaryStage.setScene(scene); //Definicao do palco (primaryStage) com a scene como parametro

    Image icon = new Image(getClass().getResourceAsStream("assets/icon-ring-producer.png")); //Instanciacao do icone da aplicacao
    primaryStage.getIcons().add(icon); //Definicao do icone do palco

    primaryStage.setTitle("RING PRODUCER-AND-CONSUMER"); //Definicao do titulo do Stage
    primaryStage.resizableProperty().setValue(false); //Definicao do Stage para nao redimensionavel, em prol de evitar erros com a parte grafica

    primaryStage.show(); //Apresentacao do Stage para o usuario
  } //Fim do metodo start

  /****************************************************************
  * Metodo: main
  * Funcao: Metodo que lanca os argumentos de linhas de comando para a execucao do programa.
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  public static void main(String[] args) {
    launch(args);
  } //Fim do metodo main

} //Fim da classe Principal