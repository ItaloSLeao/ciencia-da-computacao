import exceptions.ContainerVazioException;
import exceptions.ObjetoNaoEncontradoException;
import model.PilhaEncadeada;

public class Atividade3 {

  private static long converterDecimalBinario(int numero) throws ContainerVazioException, ObjetoNaoEncontradoException{ //Tratamento da excecao da pilha na assinatura do metodo
    PilhaEncadeada<Integer> pilha_de_restos = new PilhaEncadeada<>(); //Instanciacao de uma pilha para guardar os restos das divisoes
    String numeroBinario = ""; //Inicializacao de uma String numeroBinario para concatenar os restos das divisoes do numero e formar o numero inteiro

    while(numero != 0){ //Enquanto o numero nao for igual a 0
      pilha_de_restos.push(numero%2); //Empilha o resto da divisao do numero por 2
      numero /= 2; //Divide o numero por dois, com numero recebendo o valor numero/2
    }

    while(!pilha_de_restos.estaVazia()){ //Enquanto a pilha de restos nao estiver vazia
      numeroBinario += pilha_de_restos.pop().getDado(); //Desempilha os restos na String de numeroBinario
    }

    return Long.parseLong(numeroBinario); //Retorna o valor convertido de String concatenada dos restos para o numero binario inteiro longo verdadeiramente
  }

  public static void main(String[] args) throws ContainerVazioException, ObjetoNaoEncontradoException{ //Tratamento da excecao do metodo 
    int[] decimais = {28, 2, 2006, 33, 22, 1910, 2012, 2000}; //Array de decimais para serem convertidos
    for(int i=0; i < decimais.length; i++){ //Laco que imprime a conversao dos decimais para binario
      System.out.println("Conversao de " + decimais[i] + " para binario: " + converterDecimalBinario(decimais[i])); //Imprime cada index do array e sua conversao respetiva
    }
  }
}

/*
 * Atividade 3 – Conversao de decimal para binario
 * Escreva um metodo que converta um numero decimal para binario utilizando uma pilha.
 * A logica consiste em dividir o numero por 2, empilhar o resto e repetir ate o numero ser 0.
 * Depois, desempilhe os restos para formar o numero binario.
 * Exemplo:
 * Entrada: 13
 * Saida: 1101
 */
