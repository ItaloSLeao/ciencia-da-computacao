package aulaPratica12.controle;

import java.util.ArrayList;

import aulaPratica12.dados.RepositorioDisciplina;
import aulaPratica12.modelo.Disciplina;
import aulaPratica12.modelo.IDisciplina;

public class ControleDisciplina {
    IDisciplina repositorioDisciplina = new RepositorioDisciplina();
    
    public void cadastrarDisciplina (String nome, int id, int ch){
        Disciplina d = new Disciplina (nome, id, ch);
        repositorioDisciplina.createDisciplina(d);
    }
    
    public Disciplina pesquisarDisciplina (int id) {
        return repositorioDisciplina.readDisciplina(id) ;
    }
    
    public ArrayList<Disciplina> listarDisciplina() {
        return (ArrayList <Disciplina>)repositorioDisciplina.getAllDisciplinas();
    }
    
    public String imprimir(){
        String res="";
        ArrayList<Disciplina> disc = listarDisciplina();
        for (int i=0; i<disc.size(); i++){
            res += disc.get(i).imprimir() + "\n---------\n";
        }
        return res;
    }
    
    public void removerDisciplina (Disciplina d) {
        repositorioDisciplina.deleteDisciplina(d);
    }
    
    public void atualizarDisciplina (Disciplina d){
        repositorioDisciplina.updateDisciplina(d);
    }
}
