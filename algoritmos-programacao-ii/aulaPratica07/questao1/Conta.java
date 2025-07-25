package aulaPratica07.questao1;
import javax.swing.*;

public class Conta {
    private String name;
    private int number;
    private double balance;
    private static int qtd;


    public Conta(String name, double balance){
        this.name = name;
        this.balance = balance;
        qtd++;
        number=qtd;
    }

    public double getBalance() {
        return balance;
    }
    public String getName() {
        return name;
    }
    public int getNumber() {
        return number;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public void deposit(double value){
        balance += value;
    }

    public void withDraw(double value){
        while(value<0){
            String valueS = JOptionPane.showInputDialog(null, "Invalid value. Insert again", "ERROR", JOptionPane.ERROR_MESSAGE);
            value = Double.parseDouble(valueS);
        }
        if(value > balance){JOptionPane.showMessageDialog(null, "VERY HIGH WITHDRAWAL! EXCEEDS THE BALANCE", "ERROR", JOptionPane.ERROR_MESSAGE);}
        else{balance -= (1.005*value);} //taxa de 0.5%
    }

    public String toString(){
        return "Account owner's name:" + name +
               "\nAccount number:" + number +
               "\nAccount balance" + balance;
    }
}
