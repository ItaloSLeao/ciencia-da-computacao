package aulaPratica12.visao;

import javax.swing.JOptionPane;

import aulaPratica12.controle.ControleDisciplina;

public class AppDisciplina {
    public static String menu(){
        return "Digite:\n" +
        "1 - Cadastrar disciplina \n" +
        "2 - Pesquisar disciplina\n"+
        "3 - Listar disciplinas \n"+
        "4 - Atualizar disciplina \n"+
        "5 - Deletar disciplina \n"+
        "0 - para sair";
    }
    public static void main(String[] args) {
        ControleDisciplina cd = new ControleDisciplina();
        int op;
        op = Integer.parseInt(JOptionPane.showInputDialog(menu()));
        while (op!=0){
            switch (op){
            case 1:{ //cadastar disciplina
                String nome = JOptionPane.showInputDialog("Nome da disciplina:");
                int id = Integer.parseInt(JOptionPane.showInputDialog("Id da disciplina"));
                int ch = Integer.parseInt(JOptionPane.showInputDialog("Carga horária (h)"));
                cd.cadastrarDisciplina(nome, id, ch);
                break;
            }
            case 2:{ //Pesquisar disciplina
                int id = Integer.parseInt(JOptionPane.showInputDialog("Id da disciplina"));
                if(cd.pesquisarDisciplina(id) == null){
                    JOptionPane.showMessageDialog(null, "Disciplina não encontrada");
                } else{JOptionPane.showMessageDialog(null, "Disciplina encontrada");}
                break;
            }
            case 3: {//Listar disciplinas
                JOptionPane.showMessageDialog(null, cd.imprimir());
                break;
            }
            case 4: {//Atualizar disciplinas
                int id = Integer.parseInt(JOptionPane.showInputDialog("Id da disciplina"));
                cd.atualizarDisciplina(cd.pesquisarDisciplina(id));
                break;
            }
            case 5: {//Deletar disciplinas
                int id = Integer.parseInt(JOptionPane.showInputDialog("Id da disciplina"));
                cd.removerDisciplina(cd.pesquisarDisciplina(id));
                break;
            }
            }
            op = Integer.parseInt(JOptionPane.showInputDialog(menu()));
        }
    }
}
