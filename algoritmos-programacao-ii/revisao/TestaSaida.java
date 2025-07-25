package revisao;

public class TestaSaida {
    public static void main(String[] args) {
        TestaSaida ts = new TestaSaida();
        ts.faz();
    }
    void faz(){
        int a = 10;
        for (int i = 1; i<8; i++){
            a++;
            if (i>4){
                System.out.print(a++ + " ");
            }
            if (a>17){
                System.out.println("i = " + (float)i);
                break;
            }
        }
    }

}

//01. a) 15 17 i = 6.0
//b) Declara a variável ts do tipo TesteSaida chamando o construtor TesteSaida(). Na linha 7, é declarado
//a variável local do método da classe TesteSaida.
//c) A variável de instância é propria para cada objeto criado na instanciação da classe, a variável de
//classe no entanto é unica para todos os objetos criados na classe.