package aulaPratica11.visao;
import aulaPratica11.controle.ControleProduto;
import aulaPratica11.modelo.Produto;
import java.util.List;
import javax.swing.JOptionPane;

public class AppProduto {
    public static String menu(){
        return "1 - Cadastrar produto\n" +
               "2 - Pesquisar produto\n" +
               "3 - Listar produtos cadastrados\n" +
               "0 - Sair";
    }
    public static void main(String[] args) {
        ControleProduto cp = new ControleProduto();
        List<Produto> produtos = cp.listarProduto();

        int op = Integer.parseInt(JOptionPane.showInputDialog(menu()));

        while(op != 0){
            switch(op){
                case 1:{
                    int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
                    String descricao = JOptionPane.showInputDialog("Descreva o produto");
                    double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor (R$) do produto"));
                    cp.cadastraProduto(codigo, descricao, valor);
                    break;
                }
                case 2:{
                    int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto a ser pesquisado"));
                    Produto p = cp.pesquisarProduto(codigo);
                    if(p == null){
                        JOptionPane.showMessageDialog(null, "Produto não encontrado");
                    } else{
                        JOptionPane.showMessageDialog(null, "Produto existe e foi encontrado.\nDescrição: " + p.getDescricao() + "\nValor: R$ " + p.getValor());
                    }
                    break;
                }
                case 3:{
                    String lista = "";
                    for(Produto p : produtos){
                        lista += p.getCodigo() + " - " + p.getDescricao() + " - R$ " + p.getValor() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, "Lista de produtos:\n" + lista);
                    break;
                }
            }
            op = Integer.parseInt(JOptionPane.showInputDialog(menu()));
        }
    }
}

//2. da a) a h) toda feita como requisitado

//eu admito que para listar os produtos na h), ele faz, mas apenas lista apos a primeira execucao do programa. 
//nao tenho ideia do pq (?)
