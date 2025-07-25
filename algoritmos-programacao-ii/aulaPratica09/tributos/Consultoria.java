package aulaPratica09.tributos;

public class Consultoria implements ITributavel{
    private double valor;
    private String descricao;

    public Consultoria(double valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    @Override
    public String getTipoImposto(){return "ISS";}

    @Override
    public double getAliquota(){return 0.15;}

    @Override
    public double getValorImposto(){return getValor()*getAliquota();}

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    

    
}
