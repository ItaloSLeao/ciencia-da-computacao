/* ***************************************************************
* Autor............: Italo de Souza Leao
* Matricula........: 202410120
* Inicio...........: 16/06/2025
* Ultima alteracao.: 04/08/2025
* Nome.............: Buffer.java
* Funcao...........: Modela o buffer de itens do programa e controla a entrada e a retirada de elementos, nos aspectos visuais.
****************************************************************/

package model;

//Importacoes necessarias para implementar o buffer de aneis
import javafx.application.Platform;
import java.util.concurrent.Semaphore;

public class Buffer {
  Anel[] bufferDeAneis; //Arranjo de aneis para representar o buffer do problema produtor-consumidor
  int indiceIn, indiceOut; //Indices de insercao e remocao de itens do buffer
  private Semaphore quantidadeItens; //Semaforo interno para controlar a quantidade de itens no buffer

  public Buffer(int tamanho) { //Construtor da classe
    bufferDeAneis = new Anel[tamanho];
    indiceIn = indiceOut = 0; //Inicializacao dos indices de entrada e saida do buffer como 0
    quantidadeItens = new Semaphore(0); //Semaforo inicializado com 0 permissoes (buffer vazio)
  } //Fim do construtor

  /****************************************************************
  * Metodo: inserirItem
  * Funcao: Insere um item no buffer pelo metodo tornarVisivel da classe Anel
  * Parametros: Anel anel, o item a ser inserido no buffer
  * Retorno: void
  ****************************************************************/
  public void inserirItem(Anel anel){
    bufferDeAneis[indiceIn] = anel; //A celula de indiceIn do buffer recebe o anel

    int indiceAtual = indiceIn; //Guarda o indice atual de insercao

    Platform.runLater(() -> {
      anel.tornarVisivel(indiceAtual); //Mostra o anel na tela da aplicacao
    }); //Fim do metodo de Platform

    indiceIn = (indiceIn + 1) % bufferDeAneis.length; //Acrescenta o indice de entrada pelo modulo do tamanho do buffer garantindo a circularidade
    quantidadeItens.release(); //Incrementa o contador de itens no buffer
  } //Fim de inserirItem

  /****************************************************************
  * Metodo: removerItem
  * Funcao: Retira um item do buffer e retorna o anel retirado. 
  * Parametros: void
  * Retorno: Anel, o anel retirado do buffer
  ****************************************************************/
  public Anel removerItem(){
    Anel item = null;
    item = bufferDeAneis[indiceOut]; //O item recebe o anel do buffer no indiceOut
    indiceOut = (indiceOut + 1) % bufferDeAneis.length; //Acrescimo do indiceOut garantindo a circularidade do buffer
    try {
      quantidadeItens.acquire(); //Decrementa o contador de itens no buffer
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return item; //Retorna o item extraido, seja ele um objeto anel ou nulo
  } //Fim de removerItem

  /****************************************************************
  * Metodo: estaVazio
  * Funcao: boolean, a veracidade se o buffer esta vazio ou nao
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  public boolean estaVazio(){
    return quantidadeItens.availablePermits() == 0; //Se o semaforo nao tem permissoes, o buffer esta vazio
  } //Fim do metodo estaVazio

  /****************************************************************
  * Metodo: estaCheio
  * Funcao: boolean, a veracidade se o buffer esta cheio ou nao
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  public boolean estaCheio(){
    return quantidadeItens.availablePermits() == bufferDeAneis.length; //Se o semaforo tem todas as permissoes, o buffer esta cheio
  } //Fim do metodo estaCheio

} //Fim da classe