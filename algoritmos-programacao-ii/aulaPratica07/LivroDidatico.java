package aulaPratica07;

public class LivroDidatico extends Livro {
    private String disciplina;

    public LivroDidatico(String nome, double preco, int pagina, String autor, String disciplina){
        super(nome, preco, pagina, autor);
        this.disciplina = disciplina;
    }

    public String getDisciplina(){
        return disciplina;
    }
    public void setDisciplina(String disciplina){
        this.disciplina = disciplina;
    }

    public boolean ehCaro(){
        boolean caro = false;
        if(getPreco() > 200){caro = true;}
        return caro;
    }

    public String toString(){
        return super.toString() + "\nDisciplina: " + disciplina;
    }
}
