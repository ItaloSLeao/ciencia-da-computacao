package atividade02;

import exceptions.ContainerCheioException;
import exceptions.ContainerVazioException;

public interface Deque {
    void fazVazia();
    boolean estaVazia();
    Object getPrimeiro() throws ContainerVazioException;
    Object getUltimo() throws ContainerVazioException;
    void enfileirarInicio(Object objeto) throws ContainerCheioException;
    void enfileirarFim(Object objeto) throws ContainerCheioException;
    Object desenfileirarInicio() throws ContainerVazioException;
    Object desenfileirarFim() throws ContainerVazioException;
}
