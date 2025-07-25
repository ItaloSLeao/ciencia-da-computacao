package aulaPratica06;

public class Procedimento{
    private int codigo;
    private String descricao;
    private double valor;
    private static int qtd;

    //Construtor -----------------------------------------
    public Procedimento(String descricao, double valor){
        qtd++;
        codigo = qtd;
        this.descricao = descricao;
        this.valor = valor;
    }

    //Getters --------------------------------------------
    public int getCodigo() {
        return codigo;
    }
    public double getValor() {
        return valor;
    }
    public static int getQtd(){
        return qtd;
    }

    //Metodos --------------------------------------------
    public String imprimir(){
            return codigo + " - " + descricao + " - R$ " + valor + "\n";
    }
}