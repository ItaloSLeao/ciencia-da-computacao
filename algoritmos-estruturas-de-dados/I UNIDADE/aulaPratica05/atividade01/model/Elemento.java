package model;


public final class Elemento<T> {
  private T dado; //Valor do tipo Object para o elemento da lista
  private Elemento<T> anterior, proximo; //Referencia ao endereco dos Elemento anterior e proximo, que antecede e sucede, respectivamente, na lista o Elemento.this de valor T dado
  
  Elemento(T dado, Elemento<T> proximo){ //Construtor da classe Elemento com parametros T dado e Elemento<T> proximo
    this.dado = dado; //Atribui o parametro dado a this.dado
    this.proximo = proximo; //Atribui o parametro proximo a this.proximo
  }

  Elemento(T dado, Elemento<T> anterior, Elemento<T> proximo){ //Construtor da classe Elemento com parametros T dado, Elemento<T> anterior e proximo
    this.dado = dado; //Atribui o parametro dado a this.dado
    this.anterior = anterior; //Atribui o parametro anterior a this.anterior
    this.proximo = proximo; //Atribui p parametro proximo a this.proximo
  }

  public T getDado(){ //Metodo que retorna o dado do elemento
    return dado;
  }

  public void setDado(T dado){ //Metodo que modifica o valor T de this.dado = dado
    this.dado = dado;
  }

  public Elemento<T> getAnterior(){  //Metodo que retorna o elemento anterior do elemento this
    return anterior;
  }

  public void setAnterior(Elemento<T> anterior){ //Metodo que modifica o elemento anterior de this
    this.anterior = anterior;
  }

  public Elemento<T> getProximo(){ //Metodo que retorna o proximo elemento de this
    return proximo;
  }

  public void setProximo(Elemento<T> proximo){ //Metodo que modifica o proximo elemento de this
    this.proximo = proximo;
  }
}
