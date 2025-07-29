package exceptions;

public class ObjetoNaoEncontradoException extends Exception { // Afiliacao da excecao ObjetoNaoEncontrado a classe mae de todas as excecoes verificadas Exception
  public ObjetoNaoEncontradoException() {
    super("O objeto nao foi encontrado"); // Chamada do construtor de Exception com parametro da mensagem de ObjetoNaoEncontradoException
  }
}
