package model;

public class Paciente { //Classe modelo dos pacientes do pronto socorro
    private String nome; //Atributo String do nome do paciente
    private String prioridade; //Atributo String do estado de prioridade do paciente

    public Paciente(String nome, String prioridade){ //Construtor da classe
        this.nome = nome;
        this.prioridade = prioridade;
    }

    /*Getters e setters necessarios---------------------------------------------------------------*/

    public String getNome() {
        return nome; //Retorna o nome do paciente
    }

    public void setNome(String nome) {
        this.nome = nome; //Altera o nome do paciente
    }

    public String getPrioridade() {
        return prioridade; //Retorna a prioridade do paciente
    }
}