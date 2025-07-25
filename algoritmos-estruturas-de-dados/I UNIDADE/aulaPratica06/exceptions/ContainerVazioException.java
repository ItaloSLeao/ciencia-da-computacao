package exceptions;

public class ContainerVazioException extends Exception { //Afiliacao da excecao ContainerVazio a classe mae de todas as excecoes verificadas Exception
  public ContainerVazioException(){
    super("Seu Container de Dados esta vazio"); //Chamada do construtor de Exception com parametro da mensagem de ContainerVazioException
  }
}