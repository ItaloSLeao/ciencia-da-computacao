package aulaPratica10;

public class Conta {
    private int numero;
    private double saldo;
    
    public Conta (){
        this.numero=0;
        this.saldo=0;
    }

    public Conta (int numero, double saldo){
        this.numero=numero;
        this.saldo=saldo;
    }
    
    public void sacar(double valor) {
        if (valor <= saldo && valor >= 0){
            saldo -= valor;
        } else{
            throw new IllegalArgumentException("Valor inválido, saldo insuficiente");
        }
            
    }
    
    public void depositar(double valor){
        if (valor >0){
            saldo += valor;
        } else{
            throw new IllegalArgumentException("Valor de depósito inválido");
        }
    }    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }
    
    @Override
    public String toString(){
        return "Numero: " + numero + 
                "\nSaldo: " + saldo;
    }
}
