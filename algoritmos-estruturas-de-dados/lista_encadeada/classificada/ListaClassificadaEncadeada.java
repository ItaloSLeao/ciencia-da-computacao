
import exceptions.ContainerVazioException;

public class ListaClassificadaEncadeada implements ListaClassificada {

  protected class MeuCursor implements Cursor {
    ListaDuplamenteEncadeada.Elemento elemento;

    MeuCursor(ListaDuplamenteEncadeada.Elemento elemento) {
      this.elemento = elemento;
    }

    public Object getDado() {
      return elemento.getDado();
    }

    public void inserirAntes(Object o) {
      elemento.inserirAntes(o);
    }

    public void inserirDepois(Object o) {
      elemento.inserirDepois(o);
    }

    public void remover() {
      getLista().remover(elemento.getDado());
    }
  }

  private ListaDuplamenteEncadeada lista;

  public ListaClassificadaEncadeada() {
    lista = new ListaDuplamenteEncadeada();
  }

  public Object get(int offset) throws IndexOutOfBoundsException {
    if (offset < 0 || offset >= lista.getTamanho()) {
      throw new IndexOutOfBoundsException();
    }

    ListaDuplamenteEncadeada.Elemento e;
    e = lista.getPrimeiro();

    for (int i = 0; i < offset && e != null; i++) {
      e = e.getProximo();
    }

    return e.getDado();
  }

  public Cursor procurarPosicao(Object o) {
    ListaDuplamenteEncadeada.Elemento e;
    for (e = lista.getPrimeiro(); e != null; e = e.getProximo()) {
      Object obj = e.getDado();
      if (obj.equals(o)) {
        break;
      }
    }

    return new MeuCursor(e);
  }

  public boolean eMembro(Object o) {
    ListaDuplamenteEncadeada.Elemento e;
    for (e = lista.getPrimeiro(); e != null; e = e.getProximo()) {
      if (e.getDado().equals(o)) {
        return true;
      }
    }
    return false;
  }

  public void inserir(Object o) {
    lista.inserirFim(o); //Insere um elemento com dado Object o no fim da lista
  }

  public void inserirAntes(Object o, Object oA){
    Cursor cursor = procurarPosicao(oA); //Procura oA na lista, retorna o cursor do elemento
    cursor.inserirAntes(o); //Insere antes de oA o elemento com dado Object o
  }

  public void remover(Object o) throws ContainerVazioException {
    if (lista.getTamanho() == 0) {
      throw new ContainerVazioException();
    }

    lista.remover(o);
    lista.setTamanho(lista.getTamanho() - 1);
  }

  public Object procurar(Object o) {
    ListaDuplamenteEncadeada.Elemento e;
    for (e = lista.getPrimeiro(); e != null; e = e.getProximo()) {
      Object obj = e.getDado();
      if (obj.equals(o)) {
        return obj;
      }
    }
    return null;
  }

  public ListaDuplamenteEncadeada getLista() {return lista;}
}