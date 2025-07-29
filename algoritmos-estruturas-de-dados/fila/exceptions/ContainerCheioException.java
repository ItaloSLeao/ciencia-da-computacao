package exceptions;

public class ContainerCheioException extends Exception { //Afiliacao da excecao ContainerCheio a classe mae de todas as excecoes verificadas Exception
    public ContainerCheioException(){
      super("Seu Container de Dados esta cheio"); //Chamada do construtor de Exception com parametro da mensagem de ContainerCheioException
    }
  }
