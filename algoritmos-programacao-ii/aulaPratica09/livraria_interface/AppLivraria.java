package aulaPratica09.livraria_interface;

public class AppLivraria {
    public static void main(String[] args) {
        CarrinhoCompras carrinho = new CarrinhoCompras();
        LivroFisico fisico = new LivroFisico() ;
        fisico.setNome("Java como programar");
        fisico.setValor(200);
        carrinho.adicionar(fisico);
        
        Ebook ebook = new Ebook("comprador: italo@uesb.com.br");
        ebook.setNome("Java");
        ebook.setValor(55.5);
        carrinho.adicionar(ebook);
        //carrinho.aplicarDesconto(ebook, 0.05);

        Revista revista = new Revista("recreio", "revista jovem", 15.00, "editora abril");
        carrinho.adicionar(revista);


        System.out.println("Total do carrinho R$: " + carrinho.getTotal());
    }

}

//1. i) O código também nao compilou, pois a classe AppLivraria está chamando
//o método aplicarDesconto da classe CarrinhoCompras, mas como a questao 1.h)
//mandou comentar, esse metodo praticamente nao existe. Para que o codigo compile
//será comentado a linha de codigo q esse método é chamado.

