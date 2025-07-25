package revisao;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AppBank {
    public static String menu(){
        return "1 - cadastrar conta\n2 - depositar\n3 - sacar\n4 - mostrar dados da conta\n0 - encerrar";
    }
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();

        int op = Integer.parseInt(JOptionPane.showInputDialog(null, menu(), "NICK FAZ PROGRAMA", JOptionPane.QUESTION_MESSAGE));

        while(op != 0){
            switch(op){
                case 1:{
                    String owner = JOptionPane.showInputDialog("Nome do cliente da conta");
                    double balance = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor (R$) do saldo da conta"));
                    Account a = new Account(owner, balance);
                    accounts.add(a);
                    break;
                }
                case 2:{
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da conta de depósito"));
                    for(Account a : accounts){
                        if(id == a.getId()){
                            double value = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor (R$) a ser depositado"));
                            a.deposit(value);
                        }
                    }
                    break;
                }
                case 3:{
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da conta de saque"));
                    for(Account a : accounts){
                        if(id == a.getId()){
                            double value = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor (R$) a ser sacado"));
                            a.withdraw(value);
                        }
                    }
                    break;
                }
                case 4:{
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da conta de extrato"));
                    for(Account a : accounts){
                        if(id == a.getId()){
                            JOptionPane.showMessageDialog(null, a.toString());
                        }
                    }
                    break;
                }
            }
            op = Integer.parseInt(JOptionPane.showInputDialog(null, menu(), "NICK FAZ PROGRAMA", JOptionPane.QUESTION_MESSAGE)); 
        }
    }
}
