package aulaPratica09.livraria_interface;

public class MiniLivro extends Livro implements IPromocional{
    public MiniLivro(){
        super();
    }

    @Override
    public boolean aplicaDesconto(double porcentagem){
        return false;
    }

}
