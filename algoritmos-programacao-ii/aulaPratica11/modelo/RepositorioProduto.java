package aulaPratica11.modelo;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;

public class RepositorioProduto {
    private final String nomeArquivo = "produto.csv";
    File arquivo = new File (nomeArquivo);
    
    public void salvarProduto (Produto p) {
        try{
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("" + p.getCodigo() + "\t" + p.getDescricao() + "\t" + p.getValor());
            //coloquei o metodo do bw para escrever numa linha só, pq maisa disse q nao interferiria
            bw.newLine();
            bw.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    public List<Produto> listarProduto() {
         List<Produto> produtos = new ArrayList<>();
         try(Scanner sc = new Scanner(new FileReader(arquivo))) { 
            while (sc.hasNext()){
                Produto p = new Produto();
                int codigo = sc.nextInt();
                String descricao = sc.next();
                double valor = (Double.parseDouble(sc.next()));
                p.setCodigo(codigo);
                p.setDescricao(descricao);
                p.setValor(valor);
                produtos.add(p);
            }
         }catch (FileNotFoundException ex){
             System.out.println("Erro na leitura do arquivo "+ nomeArquivo +ex.getMessage());
         }
         return produtos;
    }

    public Produto lerProduto(int codigo){
        List<Produto> produtos = listarProduto();

        for(Produto p : produtos){
            if(p.getCodigo() == codigo){
                return p;
            }
        }

        return null;
    }


}
