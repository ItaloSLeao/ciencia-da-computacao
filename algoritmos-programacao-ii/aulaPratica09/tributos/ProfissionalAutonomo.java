package aulaPratica09.tributos;

public class ProfissionalAutonomo implements ITributavel{
    private String nome;
    private long cpf;
    private String endereco;
    private double remuneracao;
    
    public ProfissionalAutonomo(String nome, long cpf, String endereco, double remuneracao) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.remuneracao = remuneracao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(double remuneracao) {
        this.remuneracao = remuneracao;
    }

    @Override
    public String getTipoImposto(){return "INSS";}
    public double getAliquota(){return 0.11;}
    public double getValorImposto(){return getAliquota()*getRemuneracao();}
    
}
