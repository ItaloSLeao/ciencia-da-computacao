package aulaPratica07;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class App {

    public static String menu(){
        return "Digite:\n" +
                "1 - Cadastrar produto \n" +
                "2 - Cadastrar livro \n" +
                "3 - Cadastrar livro didático \n" +
                "4 - Imprimir produtos \n"+
                "0 - para sair";
    }
    public static void main(String[] args) {
        ArrayList <Produto> produtos = new ArrayList<>();
        int op = 1;
		while(op!=0) {
			op = Integer.parseInt(JOptionPane.showInputDialog(menu() + "\nDigite a opção escolhida"));
			switch(op) {
			case 0:
                JOptionPane.showMessageDialog(null, "Operações encerradas. O meu bom dia, boa tarde, boa noite!", "ATÉ MAIS VER", JOptionPane.INFORMATION_MESSAGE);
				break;
			case 1 :
				String nome = JOptionPane.showInputDialog("Digite o nome do produto");
				double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preco do produto"));
				Produto produto = new Produto(nome,preco);
				produtos.add(produto);
				break;
			case 2: 
				nome = JOptionPane.showInputDialog("Digite o nome do livro");
				preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preco do produto"));
				String autor = JOptionPane.showInputDialog("Digite o nome do autor");
				int pagina = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de paginas do livro "));
				Livro livro = new Livro(nome,preco,pagina,autor);
				produtos.add(livro);
				break;
			case 3 :
				nome = JOptionPane.showInputDialog("Digite o nome do livro");
				preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do produto"));
				autor = JOptionPane.showInputDialog("Digite o nome do autor");
				pagina = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de páginas do livro"));
				String disciplina = JOptionPane.showInputDialog("Digite a disciplina");
				LivroDidatico livroDid = new LivroDidatico(nome,preco,pagina,autor,disciplina);
				produtos.add(livroDid);
			case 4 :
                String i = "";
                for(Produto p : produtos){
                    i = i + p.toString() + "\n-----------\n";
                }
				JOptionPane.showMessageDialog(null, i);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida.\n" + menu(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}

