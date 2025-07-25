package aulaPratica10;

public class Questao06 {
    public static void	main(String[] args) {
        System.out.println("inicio do main");
        try{
            metodo1();
        } catch(NullPointerException e){
            System.out.println("erro: " + e);
        }
        System.out.println("fim do main");
        }
        static  void metodo1() {
            System.out.println("inicio do metodo1");
            metodo2();
            System.out.println("fim do metodo1");
        }
        static void	metodo2() {
            System.out.println("inicio do metodo2");
            Conta cc = new Conta();
            for (int i = 0; i <= 15; i++) {
               cc.depositar(i + 1000);
               System.out.println(cc.getSaldo());
               if (i == 5)	{
                   cc = null;
               }
            }
            System.out.println("fim do metodo2");
        }
}

//6. c) Exception in thread "main" java.lang.NullPointerException: Cannot invoke "aulaPratica10.Conta.depositar(double)" because "cc" is null
//Quando o contador se torna 5 no for, a conta fica nula, entao não é possivel chamar o metodo depositar.

//7. após essa alteração, quando há a exceção nula, agora informa "Erro: " e 
//a exceção gerada quando a conta se torna nula, então a execução do programa continua a partir
//do catch.

//8. após colocar o try catch dentro do loop, a partir de i = 5, a exceção continua a se repetir, pois a conta continua nula.
//entao a NullPointerException se repete 10 vezes, pois após o catch o laço é executado.

//9. agora, assim que a exceção é identificada na chamada do método 2, sua execução é parada e o erro é tratado. entretanto, 
//não há sinalização de que o método 2 foi finalizado, a execução para em i = 5 no for.

//10. agora, quando a exceção é identificada, ela é tratada e o método 1 é interrompido na chamada do método 2
//então não há mais sinalização de que o método 1 foi finalizado, bem como o método 2.

//11. feito

//12. feito