import model.ListaEncadeadaSimples;
import exceptions.ContainerVazioException;
import exceptions.ObjetoNaoEncontradoException;
import model.Elemento;
import model.Fila;

public class FilaEncadeada<T> implements Fila<T>{

    private ListaEncadeadaSimples<T> lista;

    public FilaEncadeada(){
        lista = new ListaEncadeadaSimples<>();
    }

    public void fazVazia(){
        lista.limpar();
    }

    public boolean estaVazia(){
        return lista.getTamanho() == 0;
    }

    public Elemento<T> getPrimeiro() throws ContainerVazioException{
        if(lista.getTamanho() == 0){
            throw new ContainerVazioException();
        }
        return lista.getPrimeiro();
    }

    public void enfileirar(T objeto){
        lista.inserirFim(objeto);
    }

    public Elemento<T> desenfileirar() throws ContainerVazioException, ObjetoNaoEncontradoException{
        if(lista.getTamanho() == 0){
            throw new ContainerVazioException(); //Caso a lista esteja vazia, lanca a excecao correspondente ao container estar vazio, pois nao se pode remover algo de uma lista nula
        }
        Elemento<T> temp = lista.getPrimeiro();
        lista.extrair(lista.getPrimeiro().getDado());
        return temp;
    }


}
