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
        tail = temp; //Se estiver vazia, tail tambem aponta para temp
      }
      head = temp; //Head aponta para temp como primeiro elemento
      tamanho++; //Acrescimo de tamanho
    }
  
    public void inserirFim(T item) {
      Elemento<T> temp = new Elemento<T>(item, null);
      
      if (head == null) {
        head = temp; //Se estiver vazia, head tambem aponta para temp
      } else {
        tail.setProximo(temp); //Se nao, ajusta o proximo elemento de tail
      }
      tail = temp; //Tail aponta para temp
      tamanho++; //Acrescimo de tamanho
    }
  
    public void extrair(T item) throws ObjetoNaoEncontradoException{
      Elemento<T> aux = head; //Elemento auxiliar aponta para head
      Elemento<T> antAux = null; //Anterior ao auxiliar
  
      while (aux != null && !(aux.getDado().equals(item))) {
        antAux = aux; //O anterior do auxiliar aponta para ele
        aux = aux.getProximo(); //Faz a progressao de aux na lista ate encontrar, ou nao, o elemento
      }
  
      if (aux == null) {
        throw new ObjetoNaoEncontradoException(); //Lanca a excecao correspondente, caso o objeto nao seja encontrado na lista, afinal nao eh possivel remover algo que nao existe numa lista
      }
  
      if (aux == head) {
        head = aux.getProximo(); //Ajusta head para o proximo elemento de aux, retirando aux da lista
        tamanho--; //Descrescimo de tamanho
      } else if (aux == tail) {
        tail = antAux; //Tail aponta para antAux
        tail.setProximo(null); //Anula o proximo elemento de tail
        tamanho--; //Decrescimo de tamanho
      } else {
        antAux.setProximo(aux.getProximo()); //Ajusta o proximo do antAux para o proximo de aux, tirando-o da lista
        tamanho--; //Decrescimo de tamanho
      }
    }
  
    public void exibirLista() throws ContainerVazioException{
      Elemento<T> aux = head;
  
      if (head == null && tail == null) {
        throw new ContainerVazioException(); //Lanca a excecao correspondente, caso a lista esteja vazia, afinal nao eh possivel imprimir, adequadamente, uma lista vazia
      }
  
      while (aux != null) {
        System.out.println(aux.getDado()); 
        aux = aux.getProximo();
      }
    }
  
    public void limpar(){
      head = null; //Head aponta para null
      tail = null; //Tail aponta para null
      tamanho = 0; //Tamanho eh resetado a 0
    }
  
    public Elemento<T> getPrimeiro() {
      return head; //Retorna o primeiro elemento, para onde head aponta
    }
  
    public int getTamanho(){
      return tamanho; //Retorna o tamanho da lista
    }
  }