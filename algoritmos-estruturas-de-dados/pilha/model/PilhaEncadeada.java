package model;

import exceptions.ContainerVazioException;
import exceptions.ObjetoNaoEncontradoException;

public class PilhaEncadeada<T> implements IPilha<T>{
  private ListaEncadeadaSimples<T> lista; //Declaracao de um atributo de ListaEncadeadaSimples para implementar as funcionalidades de uma pilha encadeada

  public PilhaEncadeada(){ //Construtor da classe
    lista = new ListaEncadeadaSimples<>();
  }

  @Override
  public void push(T objectT){
    lista.inserirInicio(objectT); //Chamada do metodo inserirInicio(T) da lista encadeada, que recebe o dado do elemento como parametro e o insere no topo da pilha
  }

  @Override
  public void fazVazia(){
    lista.limpar(); //Chamada do metodo limpar() da lista encadeada, que desorienta os ponteiros head e tail e torna a pilha vazia tambem, consequentemente
  }

  @Override
  public Elemento<T> pop() throws ContainerVazioException, ObjetoNaoEncontradoException{
    if(lista.getTamanho() == 0){
      throw new ContainerVazioException(); //Caso a lista esteja vazia, lanca a excecao correspondente ao container estar vazio, pois nao se pode remover algo de uma lista nula
    }
    Elemento<T> temp = lista.getPrimeiro(); //Armazena o primeiro elemento da lista encadeada, e o topo da pilha, na variavel local temp
    lista.extrair(lista.getPrimeiro().getDado()); //Extrai o elemento topo da pilha e primeiro elemento da lista encadeada
    return temp; //Retorna o elemento topo da pilha, armazenado em temp
  }

  @Override
  public Elemento<T> getTop() throws ContainerVazioException{
    if(lista.getTamanho() == 0){
      throw new ContainerVazioException(); //Caso a lista esteja vazia, lanca a excecao correspondente ao container estar vazio, pois nao se pode retornar um elemento de uma lista vazia
    }
    return lista.getPrimeiro(); //Retorna o primeiro elemento da lista encadeada, que consequentemente, tambem eh o topo da pilha
  }

  @Override
  public boolean estaVazia(){
    return lista.getTamanho() == 0; //Retorna a veracidade da afirmacao de que a variavel tamanho da lista encadeada esta zerada, ou seja, se a lista e, consequentemente, a pilha estao vazias
  }

  @Override
  public void exibirPilha() throws ContainerVazioException{
    lista.exibirLista();
  }
}
