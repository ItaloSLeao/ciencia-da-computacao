
public class ListaEncadeadaSimples {

    /*CLASSE ELEMENTO***************************************************************************************************************************/

    private final class Elemento{ //Assinatura da classe como privada para ser acessivel apenas internamente na classe da lista e como final para nao ser extendida
        Object dado; //Valor do tipo Object para o elemento da lista
        Elemento proximo; //Referencia ao endereco do Elemento proximo, que sucede na lista o Elemento.this de valor Object dado 
    
        Elemento(Object d, Elemento p){ //Construtor da classe Elemento
            dado = d; //Atribui o parametro d a dado
            proximo = p; //Atribui o parametro p a proximo
        }
    }

    /*******************************************************************************************************************************************/

    private Elemento head; //Elemento que se ajusta como ponteiro para o primeiro elemento da lista
    private Elemento tail; //Elemento que se ajusta como ponteiro para o ultimo elemento da lista
    private int tamanho; //Variavel inteira que armazena o tamanho da lista encadeada

    public ListaEncadeadaSimples(){}; //Por definicao, head e tails sao inicializadas nulas, desse modo, a lista tambem comeca nula, pois nao ha nada para os ponteiros apontarem

    /*METODOS OBRIGATORIOS**************************************************************************************************************************************************************/

    public void inserirInicio(Object item){
        Elemento temp = new Elemento(item, head); //Instanciacao de um elemento temp com dado = item e apontando para head para adquirir primeira posicao na lista 
        if(head == null){ //Se a variavel head esta vazia, entao temp aponta para null e acaba sendo tambem o ultimo da lista
            tail = temp; //E por isso tail aponta para temp tambem
        }
        head = temp; //Ao fim do metodo, head vai guardar em seu endereco o primeiro elemento, para que quando a lista nao mais esteja vazia, o elemento inserido no inicio aponte para o, agora, elemento sucessor
        tamanho++; //Atualizacao da variavel de controle do tamanho da lista encadeada para somar +1 ao adicionar um elemento no inicio da lista
    }


    public void inserirFim(Object item){
        Elemento temp = new Elemento(item, null); //Instanciacao de um elemento temp com o dado = item e apontando para null, pois agora eh o ultimo elemento da lista e nao tem sucessor
        if(head == null){ //Se head nao aponta para elemento algum, logo a lista nao tem primeiro elemento e portanto, eh vazia
            head = temp; //Sendo a lista vazia, ate a chamada do metodo, head aponta para temp, que ao ser inserido no fim tambem eh inserido no comeco
        } else{
            tail.proximo = temp; //Caso a lista nao esteja vazia, o, agora, penultimo elemento vai apontar para temp, pois anteriormente tail apontava para ele e agora vai apontar para temp
        }
        tail = temp; //De todo modo, tail vai apontar para temp, o ultimo elemento da lista no momento
        tamanho++; //Atualizacao da variavel de controle do tamanho da lista encadeada para somar +1 ao adicionar um elemento no fim da lista
    }


    public void remover(Object item){
        Elemento aux = head; //Inicializacao de um elemento auxiliar que se ajusta como ponteiro e aponta para head, que aponta para o primeiro elemento. Desse modo, aux.dado eh igual ao dado do primeiro elemento e aux.proximo aponta para o mesmo elemento que o sucessor do primeiro elemento
        Elemento antAux = null; //Inicializacao de um elemento, que se ajusta como ponteiro, anterior ao auxiliar, que por este apontar para o primeiro elemento da lista, antAux aponta para null, isto eh, nao aponta para elemento algum

        while(aux != null && aux.dado != item){ //Estrutura condicional de verificacao para: enquanto o elemento auxiliar nao apontar para nulo, ou seja, enquanto nao for o ultimo da lista; e enquanto o dado de aux nao for igual ao item passado como parametro no metodo:
            antAux = aux; //O elemento anterior a aux, aponta para o mesmo, tomando, assim, o lugar de aux na lista
            aux = aux.proximo; //Bem como, o elemento auxiliar aponta agora para o seu sucessor. 
            //Esse bloco no escopo condicional do while garante a progressao dos elementos auxiliares na lista, procurando o elemento com dado = item ate acha-lo ou a verificacao na lista terminar, chegando ao ultimo elemento
        }

        if(aux == null){ //Se o elemento auxiliar apontar para null, apos o while, significa que a lista foi completamente percorrida e o elemento nao foi encontrado
            System.out.println("Objeto nao encontrado");
            return; //Se o elemento nao foi encontrado na lista, o metodo encerra aqui para evitar custos na cadeia de if-else abaixo
        }

        if(aux == head){ //Se o elemento auxiliar apontar para head, apos o while, significa que o elemento com dado = item esta na primeira posicao
            head = aux.proximo; //Agora head vai apontar para o elemento seguinte ao elemento auxiliar, que esta apontando para o primeiro, isto eh, o segundo elemento (head.proximo) agora assumira a posicao de primeiro lugar na lista, excluindo o elemento com dado = item
            tamanho--; //Atualiza o valor do tamanho da lista para tamanho-1, ao excluir um elemento
        } 
        else if(aux == tail){ //Se o elemento auxiliar apontar para tail, apos o while, significa que o elemento com dado = item esta na ultima posicao
            tail = antAux; //Agora tail vai apontar para o elemento antecessor ao elemento auxiliar (antAux), que agora nao sera mais o penultimo, mas o ultimo, excluindo aux da lista encadeada
            tamanho--; //Atualiza o valor do tamanho da lista para tamanho-1, ao excluir um elemento
        }
        else{ //Caso nao seja o primeiro, nem o ultimo elemento, apos o while, entao:
            antAux.proximo = aux.proximo; //O elemento anterior ao auxiliar agora vai apontar para o sucessor de aux, tornando aux.proximo o sucessor direto de antAux e, dessa forma, removendo aux da lista encadeada
            tamanho--; //Atualiza o valor do tamanho da lista para tamanho-1, ao excluir um elemento
        }
    }


    public void exibirLista(){
        Elemento aux = head; //Inicializacao de um elemento auxiliar que aponta para head, apontando tambem para o primeiro elemento da lista

        if(head == null && tail == null){
            System.out.println("A lista ta vazia");
        }

        while(aux != null){ //Estrutura de condicao para percorrer completamente a lista encadeada, com verificacao se o elemento auxiliar nao aponta para null, ou seja, a lista foi percorrida
            System.out.print("[" + aux.dado + "] ->"); //Imprime o dado de aux a cada repeticao do laco entre colchetes com um encadeamento de seta para o proximo elemento
            aux = aux.proximo; //Atualiza o elemento auxiliar apontando agora para o seu sucessor, garantindo a progressao dos elementos auxiliares na lista encadeada e a impressao de cada elemento dela
        }
    }


    public Elemento getPrimeiro(){
        return head; //Retorna head, que aponta para o primeiro elemento da lista encadeada. Retornar head significa retornar a referencia do primeiro elemento, com seu dado e proximo, portanto, retornar o primeiro elemento.
    }

    public Elemento getUltimo(){
        return tail; //Retorna tail, que aponta para o ultimo elemento da lista. Retornar tail significa retornar a referencia do ultimo elemento, com seu dado e proximo, portanto, retornar o ultimo elemento.
    }

    /*METODOS EXTRAS**************************************************************************************************************************************************************************************/

    public int tamanho(){
        return tamanho; //Retorna o tamanho 
    }

    public boolean buscar(Object item){ //Faz a busca na lista encadeada passando um parametro Object item
        Elemento aux = head; //Inicializacao de um elemento auxiliar que aponta para head, apontando tambem para o primeiro elemento da lista

        while(aux != null && aux.dado == item){ //Estrutura de condicao para percorrer completamente a lista encadeada, com verificacao se o elemento auxiliar nao aponta para o null e se o aux.dado nao eh igual ao parametro item
            aux = aux.proximo; //Atualizacao dos elementos na lista que aux aponta, para poder percorrer a lista completamente. Assim, que aux sair do laco, sai apontando para o elemento cujo dado == item
        }

        if(aux == null){ //Se aux aponta para null, entao o elemento com dado == item nao esta na lista
            return false; //Retorna falso
        }
        else{ //Se aux nao aponta para null, entao o objeto foi encontrado
            return true; //Retorna verdadeiro
        }
    }

    public void limpar(){ //Remove os elementos da lista, deixando-a vazia
        head = null; //Como supracitado, head apontando para null, eh a definicao de uma lista vazia
        tail = null; //Bem como, tail apontar para null tambem eh a definicao de uma lista vazia
        //Remover um elemento do ponteiro de head "desorienta" o inicio da lista e remover de tail "desorienta" o final, nao cabendo orientacao alguma de elementos na lista, tornando a lista vazia
    }
   
    

}