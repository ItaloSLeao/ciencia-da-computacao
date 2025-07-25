package aulaPratica08;
import java.util.ArrayList;

public abstract class Conta {
    private int numeroConta;
    private String cliente;
    private double saldo;
    private ArrayList <String> historico;
    private static int identificador;
    

    public Conta(){
        identificador++;
        numeroConta = identificador;
        historico = new ArrayList<>();
    }

    public Conta(String cliente){
        identificador++;
        numeroConta = identificador;
        historico = new ArrayList<>();
        this.cliente = cliente;
        saldo = 0.0;
    }

    public Conta(String cliente, double saldo){
        identificador++;
        numeroConta = identificador;
        historico = new ArrayList<>();
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public String getCliente() {
        return cliente;
    }
    public double getSaldo() {
        return saldo;
    }
    public int getNumeroConta() {
        return numeroConta;
    }

    public abstract String getTipo();

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double valor){
        if(valor>0){
            setSaldo(getSaldo() + valor);
            historico.add("\nDepositou R$ " + valor);
        }
    }
    public boolean sacar(double valor){
        if(getSaldo()>=valor && valor>0){
            setSaldo(getSaldo()-valor);
            historico.add("\nSacou R$ " + valor);
            return true;
        }
        return false;
    }
    public double consultarSaldo(){
        return getSaldo();
    }
    public void setCliente(String c){
        this.cliente = c;
    }
    public String mostrarHistorico(){
        String s = "";
        for(String h : historico){
            s += h;
        }
        return s+"\n";
    }
    public String recuperarDadosConta(){
        return "Tipo: " + getTipo() + "\nNúmero: " + numeroConta 
        + "\nSaldo: R$ " + saldo + mostrarHistorico(); 
    }

}
