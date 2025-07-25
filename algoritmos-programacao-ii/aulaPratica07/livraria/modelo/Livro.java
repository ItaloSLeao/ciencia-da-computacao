package aulaPratica07.livraria.modelo;

public class Livro extends Produto{
    String author;
    String publisher;

    public Livro(String name, double cost, String author, String publisher){
        super(name, cost);
        this.author = author;
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double calculatePrice(){
        return getCost()*1.50;
    }
    public String toString(){
        return getCode() + " - " + getName() + "\nCosts: R$ " + getCost() + "\n" + "Author: " + author + 
               "\nPublisher: " + publisher + "\nSale Price: R$ " + this.calculatePrice();
    }
}
