package model;

import exceptions.ContainerVazioException;
import exceptions.ObjetoNaoEncontradoException;

public class ListaEncadeadaSimples<T> {
  private Elemento<T> head; // Elemento que se ajusta como ponteiro para o primeiro elemento da lista
  private Elemento<T> tail; // Elemento que se ajusta como ponteiro para o ultimo elemento da lista
  private int tamanho; // Variavel inteira que armazena o tamanho da lista encadeada

  public ListaEncadeadaSimples(){}; //Por definicao, head e tails sao inicializadas nulas, desse modo, a lista tambem comeca nula, pois nao ha nada para os ponteiros apontarem

  public void inserirInicio(T item) {
    Elemento<T> temp = new Elemento<T>(item, head);
    if (head == null) {
      tail = temp;
    }
    head = temp;
    tamanho++;
  }

  public void inserirFim(T item) {
    Elemento<T> temp = new Elemento<T>(item, null);
    if (head == null) {
      head = temp;
    } else {
      tail.setProximo(temp);
    }
    tail = temp;
    tamanho++;
  }

  public void extrair(T item) throws ObjetoNaoEncontradoException{
    Elemento<T> aux = head;
    Elemento<T> antAux = null;

    while (aux != null && !(aux.getDado().equals(item))) {
      antAux = aux;
      aux = aux.getProximo();

    }

    if (aux == null) {
      throw new ObjetoNaoEncontradoException(); //Lanca a excecao correspondente, caso o objeto nao seja encontrado na lista, afinal nao eh possivel remover algo que nao existe numa lista
    }

    if (aux == head) {
      head = aux.getProximo();
      tamanho--;
    } else if (aux == tail) {
      tail = antAux;
      tail.setProximo(null);
      tamanho--;
    } else {
      antAux.setProximo(aux.getProximo());
      tamanho--;
    }
  }

  public void exibirLista() throws ContainerVazioException{
    Elemento<T> aux = head;

    if (head == null && tail == null) {
      throw new ContainerVazioException(); //Lanca a excecao correspondente, caso a lista esteja vazia, afinal nao eh possivel imprimir, adequadamente, uma lista vazia
    }

    while (aux != null) {
      System.out.print("[" + aux.getDado() + "] ->");
      aux = aux.getProximo();
    }
  }

  public void limpar(){
    head = null;
    tail = null;
    tamanho = 0;
  }

  public Elemento<T> getPrimeiro() {
    return head;
  }

  public Elemento<T> getUltimo() {
    return tail;
  }

  public int getTamanho(){
    return tamanho;
  }
}
