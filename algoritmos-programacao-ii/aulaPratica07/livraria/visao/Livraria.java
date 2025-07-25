package aulaPratica07.livraria.visao;
import aulaPratica07.livraria.modelo.*;
import aulaPratica07.livraria.controle.*;
import javax.swing.*;

public class Livraria {
    public static String menu(){
        return "1 - Register a book" + 
               "\n2 - Register stationery item" +
               "\n3 - Search a produt by code" +
               "\n4 - Search product by name" +
               "\n5 - List products" +
               "\n0 - End of operations";
    }
    public static void main(String[] args) {
        int op = Integer.parseInt(JOptionPane.showInputDialog(menu()));
        ControleProduto register = new ControleProduto();
        while(op != 0){
            switch(op){
                case 1:{
                    boolean opp = true;
                    String name = JOptionPane.showInputDialog("Insert the product name");
                    double cost = Double.parseDouble(JOptionPane.showInputDialog("Insert the product cost"));
                    register.registerProduct(name, cost, opp);
                    break;
                }
                case 2:{
                    boolean opp = false;
                    String name = JOptionPane.showInputDialog("Insert the product name");
                    double cost = Double.parseDouble(JOptionPane.showInputDialog("Insert the product cost"));
                    register.registerProduct(name, cost, opp);
                    break;
                }
                case 3:{
                    int code = Integer.parseInt(JOptionPane.showInputDialog("Insert the product code"));
                    Produto p = register.searchByCode(code);
                    if(p != null){
                        JOptionPane.showMessageDialog(null, "PRODUCT FOUND\n" + p.getCode() + " - " + p.getName(), "!", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "PRODUCT NOT FOUND", "XXXXXXXX", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case 4:{
                    String name = JOptionPane.showInputDialog("Insert the product name");
                    Produto p = register.searchByName(name);
                    if(p != null){
                        JOptionPane.showMessageDialog(null, "PRODUCT FOUND\n" + p.getCode() + " - " + p.getName(), "!", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "PRODUCT NOT FOUND", "XXXXXXXX", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case 5:{
                    JOptionPane.showMessageDialog(null, register.printAllProducts());
                    break;
                }
                case 6:{
                    int code = Integer.parseInt(JOptionPane.showInputDialog("Insert the product code you want to remove from the list"));
                    register.removeProduct(code);
                    JOptionPane.showMessageDialog(null, "PRODUCT REMOVED", "", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                default:{
                    op = Integer.parseInt(JOptionPane.showInputDialog("Invalid option. Insert:\n" + menu()));
                }
            }
            op = Integer.parseInt(JOptionPane.showInputDialog(menu()));
        }
        switch(op){
            case 0:{
                JOptionPane.showMessageDialog(null, "END OF OPERATIONS! Good morning, Good afternoon, Good night!");
                break;
            }
        }
    }
}
