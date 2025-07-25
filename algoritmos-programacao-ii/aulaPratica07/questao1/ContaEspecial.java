package aulaPratica07.questao1;
import javax.swing.*;

public class ContaEspecial extends Conta {
    public ContaEspecial(String name, double balance){
        super(name, balance);
    }

    public void withDraw(double value){
        while(value<0){
            String valueS = JOptionPane.showInputDialog(null, "Invalid value. Insert again", "ERROR", JOptionPane.ERROR_MESSAGE);
            value = Double.parseDouble(valueS);
        }
        double parcialBalance = getBalance() - (1.001*value); //taxa de 0.1%
        setBalance(parcialBalance);

    }

    public String toString(){
        return "Account owner's name: " + getName() +
               "\nAccount number: " + getNumber() +
               "\nAccount balance: " + getBalance();
    }
}
