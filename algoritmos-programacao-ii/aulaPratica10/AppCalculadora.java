package aulaPratica10;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppCalculadora {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int x, y;
        Calculadora c = new Calculadora();
        try{
            System.out.println("Digite o primeiro número: ");
            x = ler.nextInt();
            System.out.println("Digite o segundo número: ");
            y = ler.nextInt();
            System.out.println("Soma: " + c.somar(x, y));
            System.out.println("Subtração: " + c.subtrair(x, y));
            try{
                System.out.println("Divisão: " + c.dividir(x, y));
            } catch(ArithmeticException e){
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("Multiplicação: " + c.multiplicar(x, y));
        } catch(InputMismatchException e){
            System.out.println("Entrada inválida");
            System.out.println(e.toString());
        }
        ler.close();
    }
}

//2. a) x=20 e y=a: Exception in thread "main" java.util.InputMismatchException (diferenca de entrada de caracter, int para string)
//2. b) x=10.0 e y=2: Exception in thread "main" java.util.InputMismatchException (diferenca de entrada de caracter, int para float)
//2. c) x = 10 e y=0: Exception in thread "main" java.lang.ArithmeticException: / by zero (excecao aritmetica em divisao por 0)

//3. feito.

//4. há um erro de compilação, pois a exceção aritmetica e de entrada ficam inalcançáveis, já que Exception é mãe de todas as exceções e trata elas.
//Para resolver essa redundancia, a mãe das exceções fica no final, indo da mais específica para a mais geral.

//5. feito.


