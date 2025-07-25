package model;
import exceptions.ContainerVazioException;
import exceptions.ObjetoNaoEncontradoException;

public interface Fila<T> {
  void fazVazia();
  boolean estaVazia();
  Elemento<T> getPrimeiro() throws ContainerVazioException;
  void enfileirar(T objeto);
  Elemento<T> desenfileirar() throws ContainerVazioException, ObjetoNaoEncontradoException;

}