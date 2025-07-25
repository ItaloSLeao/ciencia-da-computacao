package aulaPratica06;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

//O cachorrinho no icone do joptionpane é apenas um teste de estilização grafica
public class AppVeterinario {
    public static String menu(){
        return "1 - Cadastrar procedimentos\n" +
               "2 - Realizar consulta\n" +
               "3 - Imprimir dados da consulta\n" +
               "4 - Imprimir dados dos procedimentos\n" + 
               "0 - Encerrar operações";
    }
    public static void main(String[] args) throws MalformedURLException, IOException {
        ArrayList<Consulta> consultas = new ArrayList<>();
        ArrayList<Procedimento> procedimentos = new ArrayList<>();

        String imagemUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSr2nKMDKjoKC2n71bVY8GecjnM_jrDbr2iw&s";

        ImageIcon icone = new ImageIcon(new URL(imagemUrl));
        ImageIcon resizedIcone = new ImageIcon(icone.getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH));

        JOptionPane.showMessageDialog(null, "Bem-vindo(a) ao JavAppet's", "JAVAPPET'S", JOptionPane.INFORMATION_MESSAGE, resizedIcone);
        int opp = JOptionPane.YES_OPTION;

        while(opp == JOptionPane.YES_OPTION){
            int op = Integer.parseInt(JOptionPane.showInputDialog(null, menu(), "JAVAPPET'S", JOptionPane.QUESTION_MESSAGE));
            switch(op){
                case 1:{
                    int o = JOptionPane.YES_OPTION;
                    while(o == JOptionPane.YES_OPTION){
                        String descricao = JOptionPane.showInputDialog("Digite a descrição do procedimento");
                        double valor = Double.parseDouble(JOptionPane.showInputDialog("Insira o valor (R$) do " + descricao));
                        Procedimento p = new Procedimento(descricao, valor);
                        procedimentos.add(p);
                        o = JOptionPane.showConfirmDialog(null, "Deseja continuar cadastrando procedimentos?", "Escolha uma opção", JOptionPane.YES_NO_OPTION);
                    }
                    break;
                }

                case 2:{
                    String animal = JOptionPane.showInputDialog("Digite o nome ou espécie do animal");
                    String medico = JOptionPane.showInputDialog("Digite o nome do médico(a) veterinário(a)");
                    String data = JOptionPane.showInputDialog("Digite a data da consulta");
                    double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor (R$) da consulta (fora procedimentos)"));
                    Consulta c = new Consulta(animal, medico, data, valor);
                    consultas.add(c);

                    int o = JOptionPane.showConfirmDialog(null, "Quer adicionar algum procedimento à consulta?", "Escolha uma opção", JOptionPane.YES_NO_OPTION);
                        while(o == JOptionPane.YES_OPTION){
                            int codigo = Integer.parseInt(JOptionPane.showInputDialog(todosProcedimentos(procedimentos) + "\nDigite o código do procedimento a ser adiconado"));
                            for(Procedimento p : procedimentos){
                                if(codigo == p.getCodigo()){
                                    c.inserirProcedimento(p);
                                    break;
                                }
                            }
                            o = JOptionPane.showConfirmDialog(null, "Quer continuar adicionando algum procedimento à consulta?", "Escolha uma opção", JOptionPane.YES_NO_OPTION);
                        }
                        if(o == JOptionPane.NO_OPTION){JOptionPane.showMessageDialog(null, "Consulta realizada com sucesso", "JAVAPPET'S", JOptionPane.INFORMATION_MESSAGE, resizedIcone); break;}
                        
                    o = JOptionPane.showConfirmDialog(null, "Quer retirar algum procedimento da consulta?", "Escolha uma opção", JOptionPane.YES_NO_OPTION);
                    while(o == JOptionPane.YES_OPTION){
                        int codigo = Integer.parseInt(JOptionPane.showInputDialog(todosProcedimentos(procedimentos) + "\nDigite o código do procedimento a ser removido"));
                        for(Procedimento p : procedimentos){
                            if(codigo == p.getCodigo()){
                                c.removerProcedimento(p);
                                break;
                            }
                        }
                        o = JOptionPane.showConfirmDialog(null, "Quer continuar removendo algum procedimento da consulta?", "Escolha uma opção", JOptionPane.YES_NO_OPTION);
                    }
                    
                    JOptionPane.showMessageDialog(null, "Consulta realizada com sucesso", "JAVAPPET'S", JOptionPane.INFORMATION_MESSAGE, resizedIcone);
                    break;
                }

                case 3:{
                    String todasC = Consulta.getQtd() + " consultas realizadas:\n";
                    for(Consulta c : consultas){
                        todasC += c.getCodigo() + " - " + c.getAnimal() + "\n"; 
                    }
                    int codigo = Integer.parseInt(JOptionPane.showInputDialog(todasC + "Digite o código da consulta"));
                    for(Consulta c : consultas){
                        if(codigo == c.getCodigo()){
                            JOptionPane.showMessageDialog(null, c.imprimir(), "JAVAPPET'S", JOptionPane.INFORMATION_MESSAGE, resizedIcone);
                        }
                    }
                    break;
                }

                case 4:{
                    JOptionPane.showMessageDialog(null, Procedimento.getQtd() + " procedimentos:\n" + todosProcedimentos(procedimentos), "JAVAPPET'S", JOptionPane.ERROR_MESSAGE, resizedIcone);
                    break;
                }

                case 0:{
                    JOptionPane.showMessageDialog(null, "OPERAÇÕES ENCERRADAS! BOM DIA, BOA TARDE, BOA NOITE!!", "JAVAPPET'S", JOptionPane.INFORMATION_MESSAGE, resizedIcone);
                    opp = JOptionPane.NO_OPTION;
                    break;
                }
            }
        }
    }

    public static String todosProcedimentos(ArrayList<Procedimento> procedimentos){
        String todosP = "";
        for(Procedimento p : procedimentos){
            todosP += p.imprimir();
        }
        return todosP;
    }
}
