

import java.io.FileWriter;
import java.io.BufferedWriter;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class EscreverArquivoAluno{
    public static void main(String[] args) {
        String nome;
        long matricula;
        File arquivo = new File("aluno.txt");
        FileWriter fw=null;
        BufferedWriter bw = null;
        try{
            fw = new FileWriter(arquivo, true);
            bw = new BufferedWriter(fw);
            int op=1;
            while (op !=0){
                nome = JOptionPane.showInputDialog("Digite o nome do aluno");
                matricula = Long.parseLong(JOptionPane.showInputDialog("Digite a matricula do aluno"));
                bw.write (nome);
                bw.write ("\t " + matricula );
                bw.newLine();
                op = Integer.parseInt(JOptionPane.showInputDialog("Deseja cadastrar outro aluno?\n 1 - sim \n0 - não"));
            }
                    
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            try {
                if (bw!=null)
                    bw.close();
            } catch (IOException e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }

        try(FileReader fr = new FileReader(arquivo)){
            BufferedReader br = new BufferedReader(fr);
            while(br.ready()){
                String linha = br.readLine();
                System.out.println(linha);
            }
            br.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}

//1.a) nas linhas 31 a 37 (26 a 32 na atividade), está sendo executado o escopo do finally
//que se trata de um bloco de código que é executado independentemente de existirem exceções
//ou não. Nesse caso, se o arquivo não for nulo, ele é fechado, e em caso de alguma exceção é lançada IOException

//1.b) os antigos dados não continuam no arquivo. a cada vez que o programa é executado, os novos dados são
//sobrescrito em cima dos antigos, fazendo com que os dados antigos se percam.

//1.c) agora com a alteração do construtor do FileWriter, é possível escrever no final do arquivo os novos dados
//de cada execução do programa, com o comando boolean append = true;

//1.d) 