public class Teste {
    public static void main(String[] args) {
        ListaEncadeadaSimples listaEncadeadaSimples = new ListaEncadeadaSimples(); //Instanciacao da lista
        listaEncadeadaSimples.inserirInicio("na media"); //Insercao de um elemento no inicio
        listaEncadeadaSimples.inserirInicio(10); //Insercao de um elemento no inicio
        listaEncadeadaSimples.inserirFim(true); //Insercao de um elemento no fim
        listaEncadeadaSimples.inserirInicio("aed eh bom demais"); //Insercao de um elemento no inicio

        listaEncadeadaSimples.exibirLista(); //exibe a lista

        listaEncadeadaSimples.remover(true); //remove o elemento "true" e a lista fica com 3 elementos

        System.out.println("\nTamanho da lista: "+listaEncadeadaSimples.tamanho()); //3 elementos

        if(listaEncadeadaSimples.buscar(10)){//Esta na lista
            System.out.println("Esta na lista");
        } else{
            System.out.println("Nao esta na lista");
        }

        listaEncadeadaSimples.limpar(); //remove todos os elementos

        listaEncadeadaSimples.exibirLista(); //a lista ta vazia
    }
}
