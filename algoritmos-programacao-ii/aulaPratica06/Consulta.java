package aulaPratica06;
import java.util.ArrayList;

public class Consulta {
    private int codigo;
    private String animal;
    private String medico;
    private String data;
    private double valor;
    private ArrayList<Procedimento> procedimentos;
    private static int qtd;

    //Construtor ---------------------------------------------------------------
    public Consulta(String animal, String medico, String data, double valor){
        qtd++;
        codigo = qtd;
        this.animal = animal;
        this.medico = medico;
        this.data = data;
        this.valor = valor;
        procedimentos = new ArrayList<>();
    }

    //Getters (Nao tem necessidade de setters) ---------------------------------
    public int getCodigo() {
        return codigo;
    }
    public String getAnimal() {
        return animal;
    }
    public static int getQtd(){
        return qtd;
    }

    //Metodos ------------------------------------------------------------------
    public void inserirProcedimento(Procedimento p){
        procedimentos.add(p);
    }
    public void removerProcedimento(Procedimento p){
        procedimentos.remove(p);
    }
    public double calcularValorTotal(){
        double total = this.valor;
        for(Procedimento p : this.procedimentos){
            total += p.getValor();
        }
        return total;
    }
    public String imprimir(){
        String todosP = "";
        for(Procedimento p : procedimentos){
            todosP += p.imprimir();
        }
        return "Consulta: " + codigo + "\nAnimal: " + animal + "\nMédico: " + medico + "\nData: " + data + 
        "\n\n" + procedimentos.size() + " procedimentos:\n" + todosP + "Valor da consulta: R$ "+ valor + 
        "\nValor total: R$ " + calcularValorTotal();
    }

}
