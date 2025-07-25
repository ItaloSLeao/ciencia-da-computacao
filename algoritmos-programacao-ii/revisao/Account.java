package revisao;

import java.util.ArrayList;

public class Account {
    private int id=0;
    private String owner;
    private double balance;
    private ArrayList <String> statement;
    private static int idd;
    

    public Account(){
        idd++;
        id = idd;
        statement = new ArrayList<>();
    }

    public Account(String owner, double balance){
        idd++;
        id = idd;
        statement = new ArrayList<>();
        this.owner = owner;
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    
    public String getOwner(){
        return owner;
    }

    public int getId(){
        return id;
    }

    public void deposit(double value){
        if(value>0){
            setBalance(getBalance() + value);
            statement.add("\nDepositou R$ " + value);
        }
    }
    public boolean withdraw(double value){
        if(getBalance()>=value && value>0){
            setBalance(getBalance()-value);
            statement.add("\nSacou R$ " + value);
            return true;
        }
        return false;
    }

    public String showStatement(){
        String st = "";
        for(String s : statement){
            st += s;
        }
        return st +"\n";
    }
    public String toString(){
        return "\nNúmero: " + id + "\nCliente: " + owner + "\nSaldo: R$ " + balance + "\n-----EXTRATO----------------" + showStatement(); 
    }
}
