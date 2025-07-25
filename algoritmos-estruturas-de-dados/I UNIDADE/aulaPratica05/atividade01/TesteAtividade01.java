import exceptions.ContainerVazioException;
import exceptions.ObjetoNaoEncontradoException;

public class TesteAtividade01 {

    /*Exercicio:
    1. Enfileire os numeros 10, 20 e 30.
    2. Mostre o primeiro elemento da fila.
    3. Desenfileire todos os elementos, imprimindo cada um.
    4. Verifique se a fila esta vazia ao final. 
    */
    public static void main(String[] args) throws ContainerVazioException, ObjetoNaoEncontradoException{
        FilaEncadeada<Integer> fila = new FilaEncadeada<>();
        fila.enfileirar(10); //Enfileira 10
        fila.enfileirar(20); //Enfileira 20
        fila.enfileirar(30); //Enfileira 30

        System.out.println(fila.getPrimeiro().getDado()); //Imprime o dado int do primeiro elemento

        System.out.println(fila.desenfileirar().getDado()); //Desenfileira e imprime 10
        System.out.println(fila.desenfileirar().getDado()); //Desenfileira e imprime 20
        System.out.println(fila.desenfileirar().getDado()); //Desenfileira e imprime 30

        if(fila.estaVazia()){
            System.out.println("A fila esta vazia");
        } else{
            System.out.println("A fila nao esta vazia");
        }

    }
}
