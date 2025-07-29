import java.util.Random;

public class atividade01{
    static Random random = new Random();
    static int[] array = new int[10];
    static int maiorValor = Integer.MIN_VALUE, menorValor = Integer.MAX_VALUE;
    static int soma=0;

    //1. Preenche o array com valores aleatórios
    public static void preencher(){
        for(int i=0; i<10; i++){
            array[i] = random.nextInt();
            System.out.println(array[i]);
        }
    }

    //2. Percorre e encontra o maior e o menor valor no array
    public static void encontrarMaiorMenor(){
        for(int i=0; i<10; i++){
            if(array[i] > maiorValor){
                maiorValor = array[i];
            }
            if(array[i] < menorValor){
                menorValor = array[i];
            }
        }
    }

    //3. Calcula a soma dos elementos no array
    public static void somarArray(){
        for(int i=0; i<10; i++){
            soma += array[i];
        }
    }

    public static void main(String[] args){
        //Chamada dos métodos
        preencher();
        encontrarMaiorMenor();
        somarArray();

        //5. Exibe todos os valores processados
        System.out.println("\nMaior valor: " + maiorValor);
        System.out.println("Menor valor: " + menorValor);
        System.out.println("A soma dos elementos do array é: " + soma);
        
        //4. Calcula a média dos valores no array
        System.out.println("A média é: " + soma/10);

    }
}