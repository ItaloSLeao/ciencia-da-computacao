
public class ListaDuplamenteEncadeada {

  public final class Elemento {
    private Object dado;
    private Elemento anterior, proximo;

    Elemento(Object dado, Elemento proximo) {
      this.dado = dado;
      this.proximo = proximo;
    }

    Elemento(Object dado, Elemento anterior, Elemento proximo) {
      this.dado = dado;
      this.anterior = anterior;
      this.proximo = proximo;
    }

    public void inserirAntes(Object item) {
      Elemento temp = anterior; //Armazena o valor do anterior antigo em temp
      Elemento anteriorNovo = new Elemento(item, temp, this); //Modifica this.anterior para um novo elemento com dado item e que aponta para o antigo anterior como anterior e this como proximo
      setAnterior(anteriorNovo);
      if (getPrimeiro() == this) {
        setPrimeiro(anterior); //Se this for o primeiro elemento, Elemento anterior passa a ser o primeiro
      }
      setTamanho(getTamanho() + 1);
    }

    public void inserirDepois(Object item) {
      Elemento temp = proximo;
      proximo = new Elemento(item, this, temp); //Modifica this.proximo para um novo elemento com dado item e que aponta para this como anterior e o antigo proximo como proximo
      if (getUltimo() == this) {
        setUltimo(proximo); //Se this for o ultimo elemento, Elemento proximo passa a ser o ultimo
      }
      setTamanho(getTamanho() + 1);
    }

    public Object getDado() {return dado;}

    public void setDado(Object dado) {this.dado = dado;}

    public Elemento getAnterior() {return anterior;}

    public void setAnterior(Elemento anterior) {this.anterior = anterior;}

    public Elemento getProximo() {return proximo;}

    public void setProximo(Elemento proximo) {this.proximo = proximo;}
  }

  private Elemento head;
  private Elemento tail;
  private int tamanho;

  public ListaDuplamenteEncadeada(){};

  public void inserirInicio(Object item) {
    Elemento temp = new Elemento(item, null, head);
    if (head == null) {
      tail = temp;
    } else {
      head.setAnterior(temp);
    }
    head = temp;
    tamanho++;
  }

  public void inserirFim(Object item) {
    Elemento temp = new Elemento(item, tail, null);
    if (head == null) {
      head = temp;
    } else {
      tail.setProximo(temp);
    }
    tail = temp;
    tamanho++;
  }

  public void remover(Object item) {
    Elemento aux = head;

    while (aux != null && !(aux.getDado().equals(item))) {
      aux = aux.getProximo();
    }

    if (aux == null) {
      System.out.println("Objeto nao encontrado");
      return;
    }

    if (aux == head) {
      head = aux.getProximo();
      aux.setAnterior(null);
      tamanho--;
    } else if (aux == tail) {
      tail = aux.getAnterior();
      tail.setProximo(null);
      tamanho--;
    } else {
      aux.getAnterior().setProximo(aux.getProximo());
      tamanho--;
    }
  }

  public void exibirLista() {
    Elemento aux = head;

    if (head == null && tail == null) {
      System.out.println("A lista ta vazia");
    }

    while (aux != null) {
      System.out.print("[" + aux.getDado() + "] ⇆");
      aux = aux.getProximo();
    }
  }

  public Elemento getPrimeiro() {return head;}

  public Elemento getUltimo() {return tail;}

  public int getTamanho() {return tamanho;}

  public void setPrimeiro(Elemento e) {head = e;}

  public void setUltimo(Elemento e) {tail = e;}

  public void setTamanho(int t) {tamanho = t;}
}