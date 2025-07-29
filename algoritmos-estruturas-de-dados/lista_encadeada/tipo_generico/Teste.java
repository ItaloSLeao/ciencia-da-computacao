public class Teste {
    public static void main(String[] args) {
        ListaEncadeadaSimples<Object> listaEncadeadaSimples = new ListaEncadeadaSimples<>(); //Instanciacao da lista encadeada simples
        ListaDuplamenteEncadeada<Object> listaDuplamenteEncadeada = new ListaDuplamenteEncadeada<>(); //Instanciacao da lista duplamente encadeada

        listaEncadeadaSimples.inserirInicio("na media"); //Insercao de um elemento string no inicio
        listaEncadeadaSimples.inserirInicio(10); //Insercao de um elemento int no inicio
        listaEncadeadaSimples.inserirFim(true); //Insercao de um elemento boolean no fim
        listaEncadeadaSimples.inserirInicio("aed eh bom demais"); //Insercao de um elemento string no inicio

        listaDuplamenteEncadeada.inserirInicio("palmeiras"); //Insercao de um elemento string no inicio
        listaDuplamenteEncadeada.inserirFim("CORINTHIANS É CAMPEÃO"); //Insercao de um elemento string no final
        listaDuplamenteEncadeada.inserirFim(true); //Insercao de um elemento boolean no final
        listaDuplamenteEncadeada.inserirInicio(0); //Insercao de um elemento int no inicio
        listaDuplamenteEncadeada.inserirInicio('X'); //Insercao de um elemento char no inicio
        listaDuplamenteEncadeada.inserirInicio(1); //Insercao de um elemento int no inicio
        listaDuplamenteEncadeada.inserirInicio("CORINTHIANS"); //Insercao de um elemento string no inicio

        System.out.println("\n"); //Da um espaco de uma linha para separar a impressao das listas

        listaEncadeadaSimples.exibirLista(); //exibe a lista encadeada simples
        System.out.println("\n"); //Da um espaco de uma linha para separar a impressao das listas
        listaDuplamenteEncadeada.exibirLista(); //exibe a lista duplamente encadeada

        listaEncadeadaSimples.remover(true); //remove o elemento "true" e a lista fica com 3 elementos
        listaDuplamenteEncadeada.remover("palmeiras"); //remove o elemento "palmeiras" e a lista fica com 6 elementos

        System.out.println("\n"); //Da um espaco de uma linha para separar a impressao das listas

        listaEncadeadaSimples.exibirLista(); //exibe a lista sem o true
        System.out.println("\n"); //Da um espaco de uma linha para separar a impressao das listas
        listaDuplamenteEncadeada.exibirLista(); //exibe a lista sem o palmeiras


    }
}