package model;

import exceptions.ContainerVazioException;
import exceptions.ObjetoNaoEncontradoException;

public interface IPilha<T>{
  public void push(T objectT); //Metodo padrao das pilhas que reserva objectT na memoria na posicao topo da pilha (last-in first-out)
  public void fazVazia(); //Metodo que esvazia a pilha
  public Elemento<T> pop() throws ContainerVazioException, ObjetoNaoEncontradoException; //Metodo padrao das pilhas que remove o objeto na posicao topo da lista e o retorna
  public Elemento<T> getTop() throws ContainerVazioException; //Metodo que retorna o objeto no topo da lista, sem remove-lo como pop()
  public boolean estaVazia(); //Metodo que retorna o valor verdade (true-false) da proposicao: "a pilha esta vazia"
  public void exibirPilha() throws ContainerVazioException;
}