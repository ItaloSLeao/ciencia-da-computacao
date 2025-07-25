package atividade02;

import exceptions.ContainerCheioException;
import exceptions.ContainerVazioException;

public class DequeArray extends FilaArray implements Deque{
    public DequeArray(int tam){
        super(tam);
    }

    public void fazVazia(){
        super.fazVazia();
    }

    public boolean estaVazia(){
        return super.estaVazia();
    }

    public Object getPrimeiro() throws ContainerVazioException{
        return super.getPrimeiro();
    }

    public Object getUltimo() throws ContainerVazioException{
        return super.getUltimo();
    }

    public void enfileirarInicio(Object objeto) throws ContainerCheioException{
        if(estaCheia()){
            throw new ContainerCheioException();
        }
        setHead(getHead() - 1);
        if(getHead() < 0){
            setHead(getArray().length - 1);
        }
        getArray()[getHead()] = objeto;
        setCount(getCount() + 1);
    }

    public void enfileirarFim(Object objeto) throws ContainerCheioException{
        super.enfileirar(objeto);
    }

    public Object desenfileirarInicio() throws ContainerVazioException{
        return desenfileirar();
    }

    public Object desenfileirarFim() throws ContainerVazioException{
        if(estaVazia()){
            throw new ContainerVazioException();
        }
        Object elemento = getArray()[getTail()];

        setTail(getTail() - 1);
        if(getTail() < 0){
            setTail(getArray().length - 1);
        }

        setCount(getCount() - 1);

        if(estaVazia()){
            setHead(0);
            setTail(getArray().length - 1);
        }

        return elemento;
    }
}
