package aulaPratica09.livraria_interface;

public class Revista implements IProduto, IPromocional{
    String nome;
    String descricao;
    double valor;
    String editora;

    //construtor ----------------------------------------
    public Revista(String nome, String descricao, double valor, String editora) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.editora = editora;
    }

    //getters e setters ---------------------------------
    public String getEditora() {
        return editora;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public void setEditora(String editora) {
        this.editora = editora;
    }

    //metodos da interface produto implementados ----------------
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public double getValor() {
        return valor;
    }

    //metodo da interface promocional implemetado ---------------
    public boolean aplicaDesconto(double porcentagem){
        if(porcentagem > 0.10){
            return false;
        } else{
            valor -= (valor*porcentagem);
            return true;
        }
    }
    
    
}
