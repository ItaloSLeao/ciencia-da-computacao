package atividade03;

public interface Cursor {
  Object getDado();
  void inserirAntes(Object o);
  void inserirDepois(Object o);
  void remover();
}