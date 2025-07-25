package aulaPratica08;

public class ContaCorrente extends Conta {
    private double taxaDeposito; //as taxas estao em decimal
    private double taxaSaque; //no construtor, os parametros sao taxas em percentual

    public ContaCorrente(String cliente, double saldo, double taxaDepositoP, double taxaSaqueP){
        super(cliente, saldo);
        this.taxaDeposito = taxaDepositoP/100;
        this.taxaSaque = taxaSaqueP/100;
    }

    
    public void depositar(double valor){
        super.depositar(valor*(1 - taxaDeposito)); //já deposita com a taxa descontada
    }
    
    public boolean sacar(double valor){
        return super.sacar(valor*(1+taxaSaque)); //verifica se consegue sacar o valor + taxa
    }

    public double getTaxaDeposito() {
        return taxaDeposito;
    }
    public double getTaxaSaque() {
        return taxaSaque;
    }
    public String getTipo(){
        return "Conta Corrente";
    }
    public void setTaxaDeposito(double taxaDeposito) {
        this.taxaDeposito = taxaDeposito;
    }
    public void setTaxaSaque(double taxaSaque) {
        this.taxaSaque = taxaSaque;
    }

}
