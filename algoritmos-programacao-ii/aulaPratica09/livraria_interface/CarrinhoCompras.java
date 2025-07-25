package aulaPratica09.livraria_interface;
import java.util.ArrayList;

public class CarrinhoCompras {
    ArrayList <IProduto> produtos = new ArrayList<>();
    
    public void adicionar(IProduto ip){
        produtos.add(ip);
    }
    
    //1.h) metodo comentado;
    //1.n) metodo descomentado;
    public boolean aplicarDesconto (IPromocional p, double desconto){
        return p.aplicaDesconto(desconto);
    }
    
    public double getTotal (){
        double total =0;
        for (IProduto ip: produtos){
            total += ip.getValor();
        }
        return total;
    }  

}
