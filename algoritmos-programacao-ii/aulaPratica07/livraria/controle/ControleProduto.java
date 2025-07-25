package aulaPratica07.livraria.controle;
import java.util.ArrayList;

import aulaPratica07.livraria.modelo.Livro;
import aulaPratica07.livraria.modelo.Papelaria;
import aulaPratica07.livraria.modelo.Produto;
import javax.swing.*;

public class ControleProduto {
    private ArrayList<Produto> products;

    public ControleProduto(){
        products = new ArrayList<Produto>();
    }

    public void registerProduct(String name, double cost, boolean op){
        if(op){
            String author = JOptionPane.showInputDialog("Insert the author's name");
            String publisher = JOptionPane.showInputDialog("Insert the publisher name");
            Livro book = new Livro(name, cost, author, publisher);
            products.add(book);
        }
        else{
            String description = JOptionPane.showInputDialog("Insert the product description");
            String producer = JOptionPane.showInputDialog("Insert the product producer");
            Papelaria stationery = new Papelaria(name, cost, description, producer);
            products.add(stationery);
        }
    }
    public Produto searchByCode(int code){
        for(Produto p : products){
            if(p.getCode() == code){
                return p;
            }
        }
        return null;
    }
    public Produto searchByName(String name){
        for(Produto p : products){
            if(p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }
    public void removeProduct(int code){
        Produto p = searchByCode(code);
        products.remove(p);
    }
    public String printAllProducts(){
        String all = "";
        for(Produto p : products){
            all += p.toString() + "\n-----------\n";
        }
        return all;
    }
}
