package aulaPratica07.livraria.modelo;

public class Papelaria extends Produto {
    String description;
    String producer;

    public Papelaria(String name, double cost, String description, String producer){
        super(name, cost);
        this.description = description;
        this.producer = producer;
    }

    public String getDescription() {
        return description;
    }
    public String getProducer() {
        return producer;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setProducer(String producer) {
        this.producer = producer;
    }

    public double calculatePrice(){
        return getCost()*2.00;
    }
    public String toString(){
        return getCode() + " - " + getName() + "\nCosts: R$ " + getCost() + "\n" + "Description: " + description + 
               "\nProducer: " + producer + "\nSale Price: R$ " + this.calculatePrice();
    }
}
