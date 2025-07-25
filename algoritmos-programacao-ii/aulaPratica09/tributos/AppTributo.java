package aulaPratica09.tributos;
import javax.swing.JOptionPane;

public class AppTributo {

    public static String Menu(){
        return "Vamos cadastrar cada imposto arrecadado. Digite:\n" +
               "1 - Consultoria\n" +
               "2 - Imóvel\n" +
               "3 - Profissional Autônomo\n" +
               "4 - Total parcial arrecadado\n" +
               "0 - Encerrar";
    }
    public static void main(String[] args) {
        CalculaTributo c = new CalculaTributo();
        int op = Integer.parseInt(JOptionPane.showInputDialog(Menu()));

        while(op != 0){
            switch(op){
                case 1:{
                    String descricao = JOptionPane.showInputDialog("Consultoria - ISS\nDescreva o tributo: ");
                    double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor em R$ do tributo:"));
                    Consultoria cc = new Consultoria(valor, descricao);
                    c.adicionarTributo(cc);
                    break;
                }
                case 2:{
                    String endereco = JOptionPane.showInputDialog("Imóvel - IPTU\nDigite o endereço do imóvel: ");
                    double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor em R$ do imóvel:"));
                    double tamanho = Double.parseDouble(JOptionPane.showInputDialog("Digite o tamanho em m² do imóvel:"));
                    Imovel i = new Imovel(valor, tamanho, endereco);
                    c.adicionarTributo(i);
                    break;
                }
                case 3:{
                    String nome = JOptionPane.showInputDialog("Profissional Autônomo - INSS\nDigite o nome do profissional:");
                    String endereco = JOptionPane.showInputDialog("Digite o endereço do profissional " + nome + ": ");
                    double remuneracao = Double.parseDouble(JOptionPane.showInputDialog("Digite a remuneração em R$ do profissional " + nome + ":"));
                    long cpf = Long.parseLong(JOptionPane.showInputDialog("Digite o CPF do profissional " + nome + ":"));
                    ProfissionalAutonomo p = new ProfissionalAutonomo(nome, cpf, endereco, remuneracao);
                    c.adicionarTributo(p);
                    break;
                }
                case 4:{
                    JOptionPane.showMessageDialog(null, "Total arrecadado com os impostos dos tributos: R$ " + c.calculaTotalTributo());
                    break;
                }
                default:{
                    JOptionPane.showMessageDialog(null, "Operação inválida. Tente novamente");
                    break;
                }
            }
            op = Integer.parseInt(JOptionPane.showInputDialog(Menu()));
        }

        switch(op){
            case 0:
                JOptionPane.showMessageDialog(null, "Operações encerradas!!\nValor total arrecadado em impostos até o fim desse programa: R$ " + c.calculaTotalTributo());
                break;
        }
    }
}
