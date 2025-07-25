package aulaPratica01;

import java.util.Arrays; //Importa a classe de Arrays da biblioteca Java

public class MeuArray {
    private static int[] arrayInterno = new int[0]; //Array inicializado vazio
    private static int tamanho = 0; //Variável que guarda o tamanho do arayInterno durante o programa

    public static void redimensionarArray(){
        arrayInterno = Arrays.copyOf(arrayInterno, tamanho); //Método da biblioteca de Arrays que redefine o arrayInterno com o tamanho atualizado e os elementos previamente alocados
    }

    public static void adicionarElemento(int elemento){
        tamanho++; //Aumenta o tamanho do arrayInterno a cada adição de elemento
        redimensionarArray();
        arrayInterno[tamanho-1] = elemento; //Adiciona o elemento à última posição possível do array no momento
    }

    public static boolean procurarElemento(int elemento){
        for(int i=0; i<arrayInterno.length; i++){ //Compara o elemento de referência com os elementos do array
            if(elemento == arrayInterno[i]){
                return true; //Retorna a validade da comparação
            }
        }
        return false; //Retorna a validade da comparação
    }

    public static void ordenarElementosDoArray(){ //Utiliza o algoritmo bubble-sort para organizar
        Arrays.sort(arrayInterno, 0, tamanho); //Método da biblioteca de Arrays que ordena os elementos em ordem crescente partindo do index 0
    }

    public static void exibirElementosDoArray(){ 
        String elementos = "";
        for(int i=0; i<arrayInterno.length; i++){
            elementos += arrayInterno[i] + "  "; //Guarda os elementos do array e os espaça por três espaços
        }
        System.out.println(elementos); //Ao ser chamado, exibe no terminal de comandos os elementos do arrayInterno
    }

    public static void main(String[] args){

        //Testa o método adicionarElemento(int elemento)
        adicionarElemento(28);
        adicionarElemento(12);
        adicionarElemento(7);
        adicionarElemento(8);
        adicionarElemento(1);
        adicionarElemento(100);

        //Testa o método procurarElemento(int elemento) e imprime se ele está ou não no array
        if(procurarElemento(10)){
            System.out.println("O elemento está no array");
        }else{
            System.out.println("O elemento não está no array");
        }

        //Testa o método ordenarElementosArray()
        ordenarElementosDoArray();

        //Testa o método exibirElementosDoArray() com a impressão do array
        exibirElementosDoArray();


        //***********************************************************************************
        //Saída:
        //O elemento não está no array
        //1  7  8  12  28  100
        //***********************************************************************************
        //OS MÉTODOS ADICIONARAM ELEMENTOS, PROCURARAM UM ELEMENTO, ORDENARAM-NOS E OS EXIBIRAM CORRETAMENTE,
        //REDIMENSIONANDO DINAMICAMENTE O ARRAYINTERNO QUE COMEÇA VAZIO E SE ATUALIZA
        //***********************************************************************************
    }
}
