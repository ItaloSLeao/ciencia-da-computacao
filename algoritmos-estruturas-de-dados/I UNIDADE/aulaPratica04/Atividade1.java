import exceptions.ContainerVazioException;
import exceptions.ObjetoNaoEncontradoException;
import model.PilhaEncadeada;

public class Atividade1 {

  private static String inversaoDePalavras(String frase){ //Metodo estatico que retorna a String com a frase invertida do parametro String frase
    String[] palavras = frase.split("\\s+"); //Inicializacao de um array de String palavras, contendo a frase "cortada" em palavras com busca de expressoes sem whitespaces
    PilhaEncadeada<String> pilha = new PilhaEncadeada<>(); //Instanciacao de uma pilha para fazer o controle da insercao das palavras
    StringBuilder fraseInvertida = new StringBuilder(); //Instanciacao de um objeto StringBuilder para reconstruir a frase apos desimpilhar

    for(String palavra : palavras){ //Laco de repeticao que percorre cada String palavra do array palavras
      pilha.push(palavra); //Empilha cada palavra no topo da pilha 
    }

    while(!pilha.estaVazia()){ //Laco de repeticao para desempilhar as palavras, com condicao de nao estar vazia

      try{ //Bloco try-catch para tratar as excecoes trataveis lancadas pelos metodos da pilha

        fraseInvertida.append(pilha.pop().getDado()); //Adiciona ao "apendice" da construcao fraseInvertida o dado String de cada elemento da pilha

        if(!pilha.estaVazia()){ //Se a pilha nao estiver vazia
          fraseInvertida.append(" "); //Adiciona tambem, ao final de cada repeticao, um espaco para as palavras ficarem organizadas
        }
      }
      catch(ContainerVazioException e1){ //Tratamento da excecao, caso esse metodo seja acionado com a pilha vazia
        System.out.println(e1.getMessage());
      }
      catch(ObjetoNaoEncontradoException e2){ //Tratamento da excecao, caso o elemento buscado para extrair, nesse caso o top, nao seja encontrado na lista encadeada da pilha
        System.out.println(e2.getMessage());
      }

    }

    return fraseInvertida.toString(); //Retorna a String com a fraseInvertida apos transformar a instancia de SceneBuilder em String
  }

  public static void main(String[] args){

    String frase = "Ich mag kaffee nicht", fraseInvertida = inversaoDePalavras(frase); //Inicializacao das Strings com a frase original e a atribuicao do metodo aplicado a frase para a fraseInvertida

    if(frase.equals(fraseInvertida)){ //Se a frase for igual a sua forma invertida
      System.out.println("'" + frase + "' e '" + fraseInvertida + "' sao palindromos"); //Imprime que eh palindroma
    } else{
      System.out.println("'" + frase + "' e '" + fraseInvertida + "' nao sao palindromos"); //Se nao for, nao eh palindroma
    }

  }
}

/*
 * Atividade 1 – Inversao de palavras e Palindromo
 * Crie um programa que leia uma frase qualquer digitada pelo usuario. Em seguida, empilhe cada palavra da frase utilizando PilhaEncadeada<String>.
 * Por fim, desempilhe as palavras e exiba a frase invertida. Verifique se a frase eh palindroma ou nao.
 * Exemplo: Entrada: eu gosto de cafe
 * Saida: cafe de gosto eu
 */
