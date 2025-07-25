
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class LerArquivoAluno {
    public static void main(String[] args) {
        String nome;
        long matricula;
        File arquivo = new File ("aluno.txt");
        try (Scanner lerArq = new Scanner(new FileReader(arquivo))){
            while (lerArq.hasNext()){
                nome = lerArq.next();
                matricula = lerArq.nextLong();
                System.out.println(nome + " - "+ matricula);
            }
			lerArq.close();
        }catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        
    	}   
    }
}
