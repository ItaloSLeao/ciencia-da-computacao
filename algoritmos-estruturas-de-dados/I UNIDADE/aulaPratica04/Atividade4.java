import exceptions.ContainerVazioException;
import exceptions.ObjetoNaoEncontradoException;
import model.PilhaEncadeada;

public class Atividade4 {

  private static int prioridade(char c){ //Metodo para verificar a prioridade entre os operadores da expressao
    switch(c){
      case '+':
      case '-':
        return 1; //Menor prioridade de operacao para soma e subtracao
      case '*':
      case '/':
        return 2; //Maior prioridade de operacao para multipilcacao e divisao
      default:
        return 0; //Qualquer outro operador, como () tem prioridade 0
    }
  }

  private static String infixaPosfixa(String expressao) throws ContainerVazioException, ObjetoNaoEncontradoException{ //Tratamento de excecao na assinatura do metodo

    StringBuilder saida = new StringBuilder(); //Instanciacao de uma StringBuilder para armazenar a saida da conversao
    PilhaEncadeada<Character> pilha = new PilhaEncadeada<>(); //Instanciacao de uma pilha para controlar os operadores e parenteses

    for(int i = 0; i < expressao.length(); i++){ //Laco de repeticao para percorrer a String expressao
      char c = expressao.charAt(i); //Atribui a char c cada caracter da expressao

      if(Character.isLetterOrDigit(c)){ //Verifica se o caracter eh uma letra ou um digito
        saida.append(c); //Adiciona a saida
      } else if(c == ' '){
        continue; //Se c for um whitespace, entao ele deve ser ignorado e o algoritmo continua
      } else if(c == '('){
        pilha.push(c); //Se c for um parenteses de abertura, ele eh empilhado
      } else if(c == ')'){ //Se c for um parenteses de fechamento

        while(!pilha.estaVazia() && pilha.getTop().getDado() != '('){ //Enquanto a pilha nao estiver vazia e o topo da pilha nao for a um encontro com um parenteses de abertura
          saida.append(pilha.pop().getDado()); //Eh feito o desempilhamento dos operadores para a saida
        }

        if(!pilha.estaVazia()){ //Se a pilha nao estiver vazia (para evitar excecao de ContainerVazio)
          pilha.pop(); //Desempilha o parenteses de abertura que restou ao ser encontrado, mas sem destino a lugar algum, apenas o remove
        }
      } else{ //Se for um operador
        while(!pilha.estaVazia() && prioridade(c) <= prioridade(pilha.getTop().getDado())){ //Enquanto a pilha nao estiver vazia e a prioridade do operador for maior que o operador do topo
          saida.append(pilha.pop().getDado()); //Faz o desempilhamento para a saida
        }
        pilha.push(c); //Empilha o operador
      }
    }

    while(!pilha.estaVazia()){ //Enquanto a pilha nao estiver vazia
      saida.append(pilha.pop().getDado()); //Desempilha para a saida
    }

    return saida.toString(); //Retorna a saida em forma de String formatada para pos-fixa
  }

  private static String infixaPrefixa(String expressao) throws ContainerVazioException, ObjetoNaoEncontradoException{ //Tratamento de excecao na assinatura do metodo
    String invertida = new StringBuilder(expressao) //Uso do construtor da classe StringBuilder para usar o metodo .reverse()
          .reverse() //Inverte a expressao
          .toString() //Transforma em string
          .replace('(', '?') //Troca o ( por um caracter intermediario
          .replace(')', '(') //Troca o ) por um (
          .replace('?', ')'); //E agora sim troca o intermediario de ( por um )

    String posfixa = infixaPosfixa(invertida); //Converte a expressao invertida em posfixa
    String prefixa = new StringBuilder(posfixa).reverse().toString(); //Constroi em SceneBuilder com parametro posfixa para usar o .reverse e transforma em String

    return prefixa; //Retorna a String com a expressao prefixa
  }

  public static void main(String[] args) throws ContainerVazioException, ObjetoNaoEncontradoException{ //Tratamento de excecao na assinatura do metodo
    String[] infixas = {"A + B * C", "(A + B) * (C - D)", "A + B + C + D", "A * (B + C / D)"};

    for (int i = 0; i < infixas.length; i++){
      System.out.println("Conversao de infixa " + infixas[i] + " para pos-fixa: " + infixaPosfixa(infixas[i]));
    }

    for (int i = 0; i < infixas.length; i++){
      System.out.println("Conversao de infixa " + infixas[i] + " para pre-fixa: " + infixaPrefixa(infixas[i]));
    }
  }

}

/*
 * Atividade 4 – Conversao de Expressao Infixa para Pos-Fixa e Pre-Fixa
 * 
 * Enunciado:
 * Implemente dois algoritmos em Java que convertem uma expressao aritmetica em
 * notacao infixa (como A + B * C) para:
 * 1. Pos-fixa (tambem chamada de notacao polonesa reversa): A B C * +
 * 2. Pre-fixa (tambem chamada de notacao polonesa): + A * B C
 * Voce deve utilizar pilhas (como PilhaEncadeada<Character> ou PilhaEncadeada<String>) para realizar as conversoes.
 * 
 * Regras para Conversao Infixa → Pos-Fixa
 * Operandos (letras ou numeros) sao adicionados diretamente a saida.
 * Parenteses controlam a precedencia.
 * Operadores sao empilhados e desempilhados de acordo com sua precedencia e associatividade.
 * Ao encontrar um parentese de fechamento ), desempilhe ate encontrar (.
 * 
 * Regras para Conversao Infixa → Pre-Fixa
 * 1. Inverta a expressao infixa.
 * 2. Troque parenteses: ( por ) e vice-versa.
 * 3. Aplique a mesma logica da pos-fixa.
 * 4. Inverta o resultado final.
 * 
 * Exemplo de Conversao
 * Expressao Infixa: (A + B) * C
 * Pos-Fixa: A B + C *
 * Pre-Fixa: * + A B C
 * 
 * Sugestoes de Expressoes para Teste
 * A + B * C
 * (A + B) * (C - D)
 * A + B + C + D
 * A * (B + C / D)
 * 
 * Dicas:
 * Implemente uma funcao de prioridade para operadores (+, -, *, /).
 * Utilize a pilha para armazenar operadores e construir a expressao resultante.
 * Use uma estrutura auxiliar (StringBuilder) para a saida.
 */