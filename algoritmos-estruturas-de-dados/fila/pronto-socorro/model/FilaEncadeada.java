package model;
import exceptions.ContainerVazioException;
import exceptions.ObjetoNaoEncontradoException;

public class FilaEncadeada<T>{
    private ListaEncadeadaSimples<T> lista; //Campo de uma lista encadeada que guarda os elementos da fila

    public FilaEncadeada(){
        lista = new ListaEncadeadaSimples<>(); //Instanciacao da lista, quando a fila eh instanciada
    }

    public void fazVazia(){
        lista.limpar(); //Esvazia a lista
    }

    public boolean estaVazia(){
        return lista.getTamanho() == 0; //Retorna a condicao se a lista esta vazia, isto eh, se a fila tambem esta vazia
    }

    public Elemento<T> getPrimeiro() throws ContainerVazioException{
        if(getTamanho() == 0){
            throw new ContainerVazioException(); //Caso a lista esteja vazia, lanca a excecao correspondente
        }
        
        return lista.getPrimeiro(); //Retorna o primeiro elemento da lista
    }

    public int getTamanho(){
        return lista.getTamanho(); //Retorna o tamanho da lista que eh o tamanho da fila
    }

    public void enfileirar(T objeto){
        lista.inserirFim(objeto); //Insere o objeto generico no fim da lista, como corresponde o enfileiramento
    }

    public Elemento<T> desenfileirar() throws ContainerVazioException, ObjetoNaoEncontradoException{
        if(getTamanho() == 0){
            throw new ContainerVazioException(); //Caso a lista esteja vazia, lanca a excecao correspondente ao container estar vazio, pois nao se pode remover algo de uma lista nula
        }

        Elemento<T> temp = getPrimeiro(); //Armazena o primeiro elemento da lista
        lista.extrair(lista.getPrimeiro().getDado()); //Retira o primeiro elemento da lista
        return temp; //Retorna-o
    }
}