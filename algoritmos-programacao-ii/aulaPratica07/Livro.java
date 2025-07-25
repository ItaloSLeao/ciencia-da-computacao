package aulaPratica07;

public class Livro extends Produto {
    private int pagina;
    private String autor;

    public Livro(String nome, double preco, int pagina, String autor){
        super(nome, preco);
        this.pagina = pagina;
        this.autor = autor;
    }

    public boolean ehGrande(){
        boolean grande = false;
        if(pagina>200){grande = true;}
        return grande;
    }

    public int getPagina() {
        return pagina;
    }
    public String getAutor() {
        return autor;
    }
    public void setPagina(int pagina) {
        this.pagina = pagina;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String toString(){
        return "Código: " + getCodigo() +
               "\nTítulo: " + getNome() +
               "\nPreço: R$ " + getPreco() +
               "\nQuantidade de páginas: " + pagina +
               "\nAutor do livro: " + autor;
    }
}
