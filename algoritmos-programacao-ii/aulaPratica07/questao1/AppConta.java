package aulaPratica07.questao1;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AppConta {
    static ArrayList<Conta> accounts = new ArrayList<Conta>();

    public static String menu(){
        return "Type:\n" +
               "1 - Register an account" +
               "\n2 - Make a deposit" +
               "\n3 - Make a withdraw" + 
               "\n4 - Consult the balance" +
               "\n0 - End of operations";
    }
    public static void main(String[] args) {
        int op = Integer.parseInt(JOptionPane.showInputDialog(null, menu(), "OPERATIONS MENU", JOptionPane.INFORMATION_MESSAGE));
        while(op != 0){
            switch(op){
                case 1:{
                    String name = JOptionPane.showInputDialog("Insert the client's name");
                    double balance = Double.parseDouble(JOptionPane.showInputDialog("Insert the account's balance"));
                    registerAcc(name, balance);
                    break;
                }
                case 2:{
                    String name = JOptionPane.showInputDialog("Insert the client's name");
                    Conta acc = searchAccount(name);
                    while(acc == null){
                        double balance = Double.parseDouble(JOptionPane.showInputDialog("YOUR ACCOUNT IS NOT REGISTERED!\n\nInsert the account's balance to register a new account"));
                        registerAcc(name, balance);
                        acc = searchAccount(name);
                    }
                    double value = Double.parseDouble(JOptionPane.showInputDialog("Insert how much you want to deposit to the account " + acc.getNumber()));
                    acc.deposit(value);
                    break;
                }
                case 3:{
                    String name = JOptionPane.showInputDialog("Insert the client's name");
                    Conta acc = searchAccount(name);
                    while(acc == null){
                        double balance = Double.parseDouble(JOptionPane.showInputDialog("YOUR ACCOUNT IS NOT REGISTERED!\n\nInsert the account's balance to register a new account"));
                        registerAcc(name, balance);
                        acc = searchAccount(name);
                    }
                    double value = Double.parseDouble(JOptionPane.showInputDialog("Insert how much you want to withdraw from the account " + acc.getNumber()));
                    acc.withDraw(value);
                    break;
                }
                case 4:{ //era pra consultar apenas o saldo, mas eu coloquei todos os dados logo
                    String name = JOptionPane.showInputDialog("Insert the client's name");
                    Conta acc = searchAccount(name);
                    while(acc == null){
                        double balance = Double.parseDouble(JOptionPane.showInputDialog("YOUR ACCOUNT IS NOT REGISTERED!\n\nInsert the account's balance to register a new account"));
                        registerAcc(name, balance);
                        acc = searchAccount(name);
                    }
                    JOptionPane.showMessageDialog(null, acc.toString());
                    break;
                }
                default:{
                    op = Integer.parseInt(JOptionPane.showInputDialog("Invalid option. Insert:\n" + menu()));
                }
            }
            op = Integer.parseInt(JOptionPane.showInputDialog(null, menu(), "OPERATIONS MENU", JOptionPane.INFORMATION_MESSAGE));
        }
        switch(op){
            case 0:{
                JOptionPane.showMessageDialog(null, "End of operations! Good Morning, Good Afternoon and Good Night!");
                break;
            }
        }
    }

    public static Conta searchAccount(String name){
        for(Conta acc : accounts){
            if(name.equalsIgnoreCase(acc.getName())){
                return acc;
            }
        }
        return null;
    }

    public static void registerAcc(String name, double balance){
        int opp = JOptionPane.showConfirmDialog(null, "IS YOUR ACCOUNT FROM A SPECIAL TYPE?", "?", JOptionPane.YES_NO_OPTION);
        if(opp == JOptionPane.YES_OPTION){
            ContaEspecial account = new ContaEspecial(name, balance);
            accounts.add(account);
        } else{
            Conta account = new Conta(name,balance);
            accounts.add(account);
        }
    }
}
