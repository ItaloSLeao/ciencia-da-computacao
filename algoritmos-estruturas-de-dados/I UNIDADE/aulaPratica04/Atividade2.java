import exceptions.ContainerVazioException;
import exceptions.ObjetoNaoEncontradoException;
import model.PilhaEncadeada;

public class Atividade2 {

  private static boolean ehValida(String expressao) throws ContainerVazioException, ObjetoNaoEncontradoException{ //Tratamento das excecoes da pilha na assinatura do metodo
    PilhaEncadeada<Character> pilha = new PilhaEncadeada<>(); //Instanciacao de uma pilha com tipo de dado Character

    for(int i = 0; i < expressao.length(); i++){ //Laco de repeticao para percorrer os caracteres da expressao
      char caracter = expressao.charAt(i); //Uma variavel caracter recebe, a cada repeticao, o caracter da posicao i da expressao

      if(caracter == '('){ //Se o caracter da posicao i for '(', entao o programa empilha
        pilha.push(caracter); //Empilha caracter
      }
      else if(caracter == ')'){ //Se for um parenteses de fechamento ')', entao o programa desempilha

        if(pilha.estaVazia()){return false;} //Se a pilha estiver vazia, ao aparecer ')', entao os parenteses definitivamente nao estao balanceados e retorna falso

        char topo = pilha.pop().getDado(); //Extrai o elemento topo da pilha e armazena seu dado na variavel char topo

        if(!(topo == '(' && caracter == ')')){ //Faz a comparacao, em que, se o topo da pilha nao for '(' quando o caracter eh ')', entao os parenteses estao desbalanceados
          return false; //Retorna falso
        }

      } //Fim do else-if

    } //Fim do for

    return pilha.estaVazia(); //Se a pilha estiver vazia, significa que ao desempilhar, os parenteses formavam par '(' e ')', entao estavam balanceados e retorna verdadeiro
  }

  public static void main(String[] args) throws ContainerVazioException, ObjetoNaoEncontradoException{ //Tratamento das excecoes da pilha na assinatura do metodo main
    String valida1 = "(a+b)", valida2 = "((1+2) * (3-4))"; //Inicializacao de duas variaveis de teste validas
    String invalida1 = "(a+b", invalida2 = "((a*b) + c))"; //Inicializacao de duas variaveis de teste invalidas

    System.out.println("A expressao " + valida1 + " eh valida: " + ehValida(valida1)); //Eh valida: true
    System.out.println("A expressao " + valida2 + " eh valida: " + ehValida(valida2)); //Eh valida: true
    System.out.println("A expressao " + invalida1 + " eh valida: " + ehValida(invalida1)); //Eh valida: false
    System.out.println("A expressao " + invalida2 + " eh valida: " + ehValida(invalida2)); //Eh valida: false
  }
}

/*
 * Atividade 2 – Verificador de parenteses balanceados
 * Implemente um metodo que verifica se uma expressao matematica tem os parenteses corretamente balanceados. 
 * Use uma pilha para empilhar os parenteses de abertura (e desempilhar ao encontrar os de fechamento ).
 * A expressao eh valida se, ao final, a pilha estiver vazia.
 * Exemplos:
 * Validas: (a + b), ((1 + 2) * (3 - 4))
 * Invalidas: (a + b, ((a * b) + c))
 */
