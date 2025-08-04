/* ***************************************************************
* Autor............: Italo de Souza Leao
* Matricula........: 202410120
* Inicio...........: 15/06/2025
* Ultima alteracao.: 04/08/2025
* Nome.............: Consumidor.java
* Funcao...........: Modela o consumidor do programa, sua imagem, animacao, slider de velocidade, 
referencia ao buffer de itens que ele consome e modela os seus comportamentos como thread.
****************************************************************/

package model;

//Importacoes necessarias para o funcionamento da Thread consumidora
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import controller.ControllerTelaPrincipal;

public class Consumidor extends Thread{

  private ImageView imagem; //Referencia a imagem do consumidor na tela da aplicacao
  private ImageView animacaoGif; //Imageview contendo o gif de animacao do consumidor
  private Slider slider; //Referencia ao slider do consumidor na tela da aplicacao
  private Buffer buffer; //Referencia ao buffer que o consumidor ira inserir seus produtos
  private volatile boolean estaRodando = true; //Variavel booleana volatil de controle para determinar se a thread foi comandada a parar ou ainda esta rodando

  public Consumidor(ImageView imagem, ImageView animacaoGif, Slider slider, Buffer buffer){ //Construtor da classe
      this.imagem = imagem;
      this.animacaoGif = animacaoGif;
      this.slider = slider;
      this.buffer = buffer;
  } //Fim do construtor


  /****************************************************************
  * Metodo: run
  * Funcao: Metodo herdado da classe Thread que roda a thread. Ele inicializa a animacao da thread e faz a conferencia das condicoes para continuar rodando. 
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  public void run(){
    while(estaRodando){ //Se a thread estiver ativa
      if(slider.getValue() != 0 && !(buffer.estaVazio())){ //Se o slider nao estiver em 0 e o buffer nao estiver vazio
        Platform.runLater(() -> {
          startAnimacao(); //Inicia a animacao do consumidor
        });
        try{
          ControllerTelaPrincipal.getCheio().acquire(); //Decrementa os espacos ocupados no buffer
          ControllerTelaPrincipal.getMutex().acquire(); //Altera o valor do mutex para bloquear acoes durante a remocao
          Anel item = buffer.removerItem(); //Um item do tipo anel recebe o retorno do metodo de remocao do buffer
          ControllerTelaPrincipal.getMutex().release(); //Altera o valor do mutex para liberar as acoes no buffer
          ControllerTelaPrincipal.getVazio().release(); //Incrementa os espacos vazios no buffer
          consumirAnel(item); //Consome o item retirado do buffer
        } catch (InterruptedException e){
          e.printStackTrace();
        }
      } else{ //Caso contrario (o slider esta em 0 ou o buffer esta vazio)
        Platform.runLater(() -> {
            stopAnimacao(); //Paralisa a animacao do consumidor
        }); //Fim do runLater
      } //Fim do if-else

      try{
        // Calcula o tempo de sleep com velocidade dinâmica baseada no slider
        // Quanto maior o valor do slider, menor o tempo de sleep (maior velocidade)
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
    stopAnimacao(); //Paralisa a animacao
  }

  /****************************************************************
  * Metodo: startAnimacao
  * Funcao: Metodo para inicializar a animacao da thread.
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  public void startAnimacao(){
    imagem.setOpacity(0); //Torna a imagem do consumidor parado invisivel
    animacaoGif.setOpacity(1); //Torna a animacao de gif do consumidor visivel
  }

  /****************************************************************
  * Metodo: stopAnimacao
  * Funcao: Metodo para paralisar a animacao da thread.
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  public void stopAnimacao(){
    animacaoGif.setOpacity(0); //Torna a animacao gif do consumidor invisivel
    imagem.setOpacity(1); //Torna a imagem do consumidor parado visivel
  }

  /****************************************************************
  * Metodo: consumirAnel
  * Funcao: Metodo para consumir o item retirado do buffer pelo consumidor.
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  private void consumirAnel(Anel anel){
    Platform.runLater(() -> {
        anel.tornarInvisivel(); //Retira o anel da tela de aplicacao
    }); //Fim do metodo de Platform
  } //Fim do metodo consumirAnel

} //Fim da classe