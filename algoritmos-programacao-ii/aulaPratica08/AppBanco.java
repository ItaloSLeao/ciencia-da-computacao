package aulaPratica08;
import java.util.ArrayList;

public class AppBanco {
    public static void main(String[] args) {
        ContaCorrente cc = new ContaCorrente("italo", 1000, 1, 5);
        Poupanca p = new Poupanca("italo", 2000, 2);
        cc.depositar(100); //100-1% = 99 q será depositado
        cc.sacar(100); //100+5% = 105 q será sacado
        p.calcularRendimento(5);
        ArrayList <Conta> contas = new ArrayList<>();
        contas.add(cc);
        contas.add(p);
        for(Conta c : contas){
            System.out.println(c.recuperarDadosConta());
        }
    }
}

//5. Testado.

//7. Uma classe abstrata é uma classe que nao pode ser instanciada
//diretamente, mas serve como modelo para suas classes filhas. 
//O uso da classe abstrata evita a repeticao de metodos em comum nas classes
//herdadas, ou seja, fornece uma base para as heranças completarem.

//8. O compilador notifica que um metodo abstrato nao pode ter um corpo, ou seja,
//apenas um molde de método para ser implementado pelas classes filhas; e 
//que a classe Poupança tem que implementar o metodo abstrato da classe Conta.

//10. Ao momento que o metodo getTipo() se tornou abstrato na classe mãe, ele se tornou
//obrigatório para as classes filhas; esse é um recurso da p.o.o em java, em que se 
//um método é abstrato, ele garante responsabilidade de ser herdado, ou não irá compilar.
//Então, se apagarmos esse método da classe, no programa principal temos que dar a certeza
//de que ao usar o getTipo(), estamos tratando ou de ContaCorrente ou de Poupanca, pq esses
//metodos sao especificos das filhas e nao mais existem em Conta, que é a classe de objetos do array contas.

//11. Sim, é possível. Ao usar o método getTipo() em recuperarDadosConta(), em tempo de execução
//o compilador irá identificar a classe do objeto para aplicar o respectivo getTipo() e exibir os dados.
//Como dito, o metodo estando abstrato obriga a implementacao do mesmo para as classes filhas, garantindo
//que o ele sera realmente usado.

//12. Testado.
