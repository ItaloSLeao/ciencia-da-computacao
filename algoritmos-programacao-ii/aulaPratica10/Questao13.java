package aulaPratica10;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Questao13 {
    static ArrayList<Conta> contas = new ArrayList<>();
    public static String menu(){
        return  "Digite:\n" +
                "1 - Criar conta \n" +
                "2 - Depositar \n"+
                "3 - Sacar \n"+
                "4 - Consultar saldo\n"+
                "0 - para sair";
    }
    public static void main(String[] args) {
        
        int op = Integer.parseInt(JOptionPane.showInputDialog(menu()));

        while(op != 0){
            switch(op){
                case 1:{
                    int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta"));
                    double saldo = Double.parseDouble(JOptionPane.showInputDialog("Digite o saldo da conta " + numero));
                    Conta c = new Conta(numero, saldo);
                    contas.add(c);
                    break;
                }
                case 2:{
                    int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta"));
                    if(procurarContaNumero(numero) == null){
                        JOptionPane.showMessageDialog(null, "Conta não encontrada");
                        break;
                    } else{
                        Conta c = procurarContaNumero(numero);
                        double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite quanto deseja depositar"));
                        try{
                            c.depositar(valor);
                            JOptionPane.showMessageDialog(null, "Valor depositado à conta " + c.getNumero());
                        } catch(IllegalArgumentException e){
                            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                }
                case 3:{
                    int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta"));
                    if(procurarContaNumero(numero) == null){
                        JOptionPane.showMessageDialog(null, "Conta não encontrada");
                        break;
                    } else{
                        Conta c = procurarContaNumero(numero);
                        double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite quanto deseja sacar"));
                        try{
                            c.sacar(valor);
                            JOptionPane.showMessageDialog(null, "Valor sacado da conta " + c.getNumero());
                        } catch(IllegalArgumentException e){
                            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                }
                case 4:{
                    int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta"));
                    Conta c = procurarContaNumero(numero);
                    if(c == null){
                        JOptionPane.showMessageDialog(null, "Conta não encontrada");
                        break;
                    } else{
                        JOptionPane.showMessageDialog(null, "O saldo da conta " + c.getNumero() + " é: R$ " + c.getSaldo());
                    }
                }
            }
            op = Integer.parseInt(JOptionPane.showInputDialog(menu())); 
        }
    }

    public static Conta procurarContaNumero(int numero){
        for(Conta c : contas){
            if(numero == c.getNumero()){
                return c;
            }
        }
        return null;
    }
}
