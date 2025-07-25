package atividade03;

import exceptions.ContainerVazioException;

public class TesteAtividade03 {
  public static void main(String[] args) throws ContainerVazioException{
    ListaClassificadaEncadeada listaStrings = new ListaClassificadaEncadeada(); //Instanciacao da listaStrings
    listaStrings.inserir("Carlos"); //Insere "Carlos" na lista
    listaStrings.inserir("Amanda"); //Insere "Amanda" na lista
    listaStrings.inserir("Beatriz"); //Insere "Beatriz" na lista
    listaStrings.inserir("Eduardo"); //Insere "Eduardo" na lista

    if(listaStrings.eMembro("Amanda")){ //Verifica se a string "Amanda" eh membro da lista
      System.out.println("Amanda eh membro da lista"); //Imprime que eh
    } else{
      System.out.println("Amanda nao eh membro da lista"); //Imprime que nao eh
    }

    listaStrings.inserirAntes("Bruno", "Beatriz"); //O metodo inserir com dois parametros Object procura a posicao do segundo na lista e adiciona o primeiro antes dele

    listaStrings.remover("Carlos"); //Remove a string "Carlos" da lista

    for(int i = 0; i < listaStrings.getLista().getTamanho(); i++){
      System.out.println(listaStrings.get(i));
    }


  }

}
