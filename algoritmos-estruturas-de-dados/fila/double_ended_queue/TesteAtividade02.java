import exceptions.ContainerCheioException;
import exceptions.ContainerVazioException;

public class TesteAtividade02 {
    /*Exercicio:
    1. Insira 5 elementos no inicio e 5 no fim da deque.
    2. Remova 2 elementos do inicio e 2 do fim.
    3. Mostre o primeiro e o  ultimo elemento da deque restante. */
    public static void main(String[] args) throws ContainerCheioException, ContainerVazioException{
        DequeArray deque = new DequeArray(10);
        deque.enfileirarInicio(1);
        deque.enfileirarInicio(2);
        deque.enfileirarInicio(3);
        deque.enfileirarInicio(4);
        deque.enfileirarInicio(5);
        deque.enfileirarFim(6);
        deque.enfileirarFim(7);
        deque.enfileirarFim(8);
        deque.enfileirarFim(9);
        deque.enfileirarFim(10);

        deque.desenfileirarInicio();
        deque.desenfileirarInicio();
        deque.desenfileirarFim();
        deque.desenfileirarFim();

        System.out.println(deque.getPrimeiro());
        System.out.println(deque.getUltimo());
    }
}