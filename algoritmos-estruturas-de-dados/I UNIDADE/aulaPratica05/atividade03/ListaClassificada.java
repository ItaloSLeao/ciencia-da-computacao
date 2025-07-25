package atividade03;

import exceptions.ContainerVazioException;

public interface ListaClassificada {
  Object get(int i);
  Cursor procurarPosicao(Object o);
  boolean eMembro(Object o);
  void inserir(Object o);
  void remover(Object o) throws ContainerVazioException;
  Object procurar(Object o);
}