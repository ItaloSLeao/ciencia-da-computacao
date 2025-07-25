package aulaPratica08;
import java.lang.Math;

public class Poupanca extends Conta{
    private double rendimentoMes;
    private int mes; //esse atributo é so pra guardar a qtd de meses cada vez q o calcularRendimento for chamado

    public Poupanca(String cliente, double saldo, double rendimentoMesP){
        super(cliente, saldo);
        this.rendimentoMes = rendimentoMesP/100;
    }
    public double calcularRendimento(int mes){
        double total = Math.pow((1+rendimentoMes),mes); //formula pro rendimento composto a cada mes
        this.mes = mes;
        return getSaldo()*total;
    }

    public double getRendimentoMes() {
        return rendimentoMes;
    }
    public String getTipo(){
        return "Poupança";
    }
    public void setRendimentoMes(double rendimento){
        rendimentoMes = rendimento;
    }
    @Override
    public String recuperarDadosConta(){
        return super.recuperarDadosConta() + "Rendimento após " + mes + " mês(es): R$ " + calcularRendimento(mes);
    }

}
