package aulaPratica12.dados;

import aulaPratica12.modelo.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class RepositorioDisciplina implements IDisciplina{
    String arquivo = "disciplina.ser";

    public ArrayList<Disciplina> getAllDisciplinas(){
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        ObjectInputStream os = null;
        try(FileInputStream fis = new FileInputStream(arquivo)){ 
            while (fis.available()>0){
                os = new ObjectInputStream (fis);
                Disciplina d = (Disciplina)os.readObject();
                disciplinas.add(d);
            }
            os.close();
        } catch (FileNotFoundException ex){
            System.out.println("Erro na leitura do arquivo "+ arquivo +ex.getMessage());
        } catch (EOFException e){
            System.out.println("Erro de fim de arquivo");
        } catch (IOException | ClassNotFoundException ex){
            System.out.println ("Problema ao atualizar o arquivo"); 
        }
        return disciplinas;
    }
    public void createDisciplina (Disciplina disciplina){
        try{
            FileOutputStream fos = new FileOutputStream(arquivo, true);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(disciplina);
            os.close();
        } catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "ERRO! O ARQUIVO NÃO EXISTE!!!!!!", "NÃO EXISTE", JOptionPane.ERROR_MESSAGE);
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "ERRO! PROBLEMA NO INPUT/OUTPUT!", "I/O", JOptionPane.ERROR_MESSAGE);
        }
    }
    public Disciplina readDisciplina(int id){
        ArrayList<Disciplina> disciplinas = getAllDisciplinas();
        Disciplina disciplina = null;
        for (Disciplina d : disciplinas){
            if (d.getId() == id){
                disciplina = d;
                break;
            }
        }
        return disciplina;
    }
    public void updateDisciplina(Disciplina disciplina){
        //atualiza os dados da disciplina
        ArrayList<Disciplina> disciplinas = getAllDisciplinas();
        String nome = JOptionPane.showInputDialog("Nome da disciplina");
        int id = Integer.parseInt(JOptionPane.showInputDialog("Id da disciplina"));
        int cargaHoraria = Integer.parseInt(JOptionPane.showInputDialog("Carga Horária da disciplina"));
        disciplina.setNome(nome);
        disciplina.setId(id);
        disciplina.setCargaHoraria(cargaHoraria);
        disciplinas.add(disciplina);
        //atualiza o arquivo
        updateArquivo(disciplinas);
    }
    public void updateArquivo(ArrayList<Disciplina> disciplinas){
        try(FileOutputStream fos = new FileOutputStream(arquivo)){
            ObjectOutputStream os = new ObjectOutputStream(fos);
            for(Disciplina d : disciplinas){
                os.writeObject(d);
            }
            os.close();
        } catch (IOException ioe){
            System.out.println("Problema ao atualizar o arquivo");
        }
    }
    public void deleteDisciplina(Disciplina disciplina){
        ArrayList<Disciplina> disciplinas = getAllDisciplinas();
        boolean removeu = false;
        for(Disciplina d : disciplinas){
            if(d.getId() == disciplina.getId()){
                disciplinas.remove(disciplina);
                updateArquivo(disciplinas);
                removeu = true;
                break;
            }
        }
        if(!removeu){
            throw new RuntimeException("Produto não encontrado");
        }
    }
}
