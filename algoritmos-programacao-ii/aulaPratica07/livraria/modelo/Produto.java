package aulaPratica07.livraria.modelo;

public class Produto {
    private int code;
    private String name;
    private double cost;
    private static int qtd=0;


    public Produto(String name, double cost){
        this.name = name;
        this.cost = cost;
        qtd++;
        code = qtd;
    }

    public int getCode() {
        return code;
    }
    public double getCost() {
        return cost;
    }
    public String getName() {
        return name;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double calculatePrice(){
        return cost*1.20;
    }
    public String toString(){
        return code + " - " + name + "\nCosts: R$ " + cost + "\n";
    }
}
