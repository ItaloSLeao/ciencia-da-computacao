
public class BinaryTree{
    public static void main(String[] args){
        Node<Integer> raiz = new Node<>(1);
        raiz.inserirOrdenado(2);
        raiz.inserirOrdenado(3);
        raiz.inserirOrdenado(0);
        raiz.inserirOrdenado(6);
        raiz.inserirOrdenado(4);

        System.out.println("InOrdem:");
        raiz.imprimirInOrdem();
        System.out.println("\nPreOrdem:");
        raiz.imprimirPreOrdem();
        System.out.println("\nPosOrdem:");
        raiz.imprimirPosOrdem();
        System.out.println("\nLargura:");
        raiz.imprimirEmLargura();

        System.out.println("\nAltura: " + raiz.calcularAltura());
        System.out.println("Altura: " + raiz.calcularAlturaEmLargura());

        System.out.println("Total de nos: " + raiz.calcularTotalNodes());
        System.out.println("Total de folhas: " + raiz.calcularTotalFolhas());

        System.out.println("No com menor valor: " + raiz.NodeComMenorValor().getDado());
        System.out.println("No com maior valor: " + raiz.NodeComMaiorValor().getDado());

        raiz.removerNode(3, null);
        raiz.removerNode(0, null);
        raiz.removerNode(1, null);

        System.out.println("\nInOrdem apos as remocoes:");
        raiz.imprimirInOrdem();


    }

}
