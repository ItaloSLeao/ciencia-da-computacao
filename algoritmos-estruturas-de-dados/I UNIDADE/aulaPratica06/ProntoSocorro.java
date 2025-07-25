//Importacao das excecoes do pacote exceptions
import exceptions.ContainerVazioException; 
import exceptions.ObjetoNaoEncontradoException;

//Importacao das classe do pacote model
import model.Elemento;
import model.FilaEncadeada;
import model.Paciente;
//Importacao das classes Scanner e ArrayList do pacote util
import java.util.Scanner;
import java.util.ArrayList;

public class ProntoSocorro{
    //Instanciacao das filas de pacientes de prioridade grave, moderada e leve, respectivamente
    private static FilaEncadeada<Paciente> pacientesGraves = new FilaEncadeada<>();
    private static FilaEncadeada<Paciente> pacientesModerados = new FilaEncadeada<>();
    private static FilaEncadeada<Paciente> pacientesLeves = new FilaEncadeada<>();
    //Instanciacao de tres listas que vao guardar os nomes dos pacientes atendidos em cada fila
    private static ArrayList<String> atendidosG = new ArrayList<>();
    private static ArrayList<String> atendidosM = new ArrayList<>();
    private static ArrayList<String> atendidosL = new ArrayList<>();

    private static int atendimentosGraves, atendimentosModerados, atendimentosLeves; //Variaveis inteiras que armazenam a quantidade de atendimentos feits-os em cada fila

    private static void exibirAtendidos(ArrayList<String> lista){ //Metodo que imprime a lista de atendidos da fila que corresponder a lista no parametro
        for(String a : lista){
            System.out.println("-> " + a);
        }
    }

    private static void exibirEstadoDasFilas() throws ContainerVazioException{ //Metodo que exibe os estados atuais das filas, com a quantidade de pacientes aguardando atendimento na fila
        System.out.println("O estado atual da fila de prioridade Grave é de: " + pacientesGraves.getTamanho() + " pacientes em espera");
        System.out.println("O estado atual da fila de prioridade Moderada é de: " + pacientesModerados.getTamanho() + " pacientes em espera");
        System.out.println("O estado atual da fila de prioridade Leve é de: " + pacientesLeves.getTamanho() + " pacientes em espera");
    }

    private static void atenderPaciente() throws ContainerVazioException, ObjetoNaoEncontradoException{ //Metodo que faz o atendimento de um paciente por vez
        if(!pacientesGraves.estaVazia()){ //Se a fila de prioridade grave nao estiver vazia, entao...
            Elemento<Paciente> ep = pacientesGraves.desenfileirar(); //Um elemento<paciente> eh desenfileirado e armazenado
            atendidosG.add(ep.getDado().getNome()); //O nome do paciente eh adicionado a lista de nomes graves atendidos
            atendimentosGraves++; //Acrescimo de pacientes graves atendidos
        } else if(!pacientesModerados.estaVazia()){ //No caso da fila grave estar vazia e a fila moderada nao...
            Elemento<Paciente> ep = pacientesModerados.desenfileirar(); //Um elemento<paciente> eh desenfileirado e armazenado
            atendidosM.add(ep.getDado().getNome()); //O nome do paciente eh adicionado a lista de nomes moderados atendidos
            atendimentosModerados++; //Acrescimo de pacientes moderados atendidos
        } else if(!pacientesLeves.estaVazia()){ //No caso das outras duas filas estarem vazias, e a fila leve nao...
            Elemento<Paciente> ep = pacientesLeves.desenfileirar(); //Um elemento<paciente> eh desenfileirado e armazenado
            atendidosL.add(ep.getDado().getNome()); //O nome do paciente eh adicionado a lista de nomes leves atendidos
            atendimentosLeves++; //Acrescimo de pacientes leves atendidos
        }
    }

    private static void adicionarPaciente(Paciente p){ //Metodo que adiciona um paciente do parametro a fila correspondente
        switch(p.getPrioridade()){ //Condicao de casos da prioridade do paciente
            case "Grave":
                pacientesGraves.enfileirar(p); //Se for "Grave" adiciona a pacientesGraves
                break;
            case "Moderada":
                pacientesModerados.enfileirar(p); //Se for "Moderada" adiciona a pacientesModerados
                break;
            case "Leve":
                pacientesLeves.enfileirar(p); //Se for "Leve" adiciona a pacientesLeves
                break;
        }
    }

    private static void exibirRelatorios() throws ContainerVazioException{
        exibirEstadoDasFilas(); //Exibe primeiramente os estados das filas
        System.out.println(atendimentosGraves + " pacientes em estado grave atendidos:"); //Imprime quantos pacientes graves foram atendidos
        exibirAtendidos(atendidosG); //Exibe os nomes dos pacientes graves atendidos
        System.out.println(atendimentosModerados + " pacientes em estado moderado atendidos:"); //Imprime quantos pacientes moderados foram atendidos
        exibirAtendidos(atendidosM); //Exibe os nomes dos pacientes moderados atendidos
        System.out.println(atendimentosLeves + " pacientes em estado leve atendidos:"); //Imprime quantos pacientes leves foram atendidos
        exibirAtendidos(atendidosL); //Exibe os nomes dos pacientes leves atendidos
    }

    public static void main(String[] args) throws ContainerVazioException, ObjetoNaoEncontradoException{
        Scanner scanner = new Scanner(System.in); //Instanciacao de um scanner para ler a entrada de dados

        System.out.println("Olá! Seja bem vindo ao Pronto Socorro, gostaria de:\n1 - Inserir pacientes\n2 - Atender pacientes\n3 - Exibir estado tual das filas;\n4 - Exibir relatório final de atendimentos\n5 - Sair do Pronto Socorro");
        int escolha = scanner.nextInt(); //Recolhe a escolha de operacoes disponiveis na variavel

        while(escolha != 5){ //Enquanto a escolha for diferente de 5 para sair do pronto socorro

            switch(escolha){
                case 1:{
                    System.out.println("Quantos pacientes você quer inserir?");
                    int quantidade = scanner.nextInt(); //Recolhe a quantidade de pacientes a serem inseridos
                    scanner.nextLine(); //Limpa o buffer

                    for(int i = 0; i < quantidade; i++){ //Laco para adicionar os pacientes a quantidade de vezes indicada
                        System.out.print("Digite o nome do(a) paciente: ");
                        String nome = scanner.nextLine(); //Recolhe o nome do paciente
                        System.out.println("Digite a sua prioridade de atendimento:\nGrave\nModerada\nLeve");
                        String prioridade = scanner.nextLine(); //Recolhe prioridade de atendimento do paciente

                        Paciente p = new Paciente(nome, prioridade); //Instanciacao de um paciente com o nome e a prioridade escolhidos
                        adicionarPaciente(p); //Adiciona o paciente a fila correspondente
                    }

                    break;
                }
                case 2:{
                    System.out.println("Quantos pacientes deseja atender?");
                    int quantidade = scanner.nextInt(); //Recolhe a quantidade de pacientes a serem atendidos

                    for(int i = 0; i < quantidade; i++){ //Laco para atender um paciente por vez, a quantidade de vezes indicada
                        atenderPaciente(); //Atende o paciente e se quantidade > pacientes existentes, simplesmente nao tem atendimento, so ha se tiver paciente
                    }

                    break;
                }
                case 3:{
                    exibirEstadoDasFilas(); //Exibe o estado das filas
                    break;
                }
                case 4:{
                    exibirRelatorios(); //Exibe os relatorios finais de atendimentos das filas
                    break;
                }
                default:{break;} //Qualquer coisa diferente de 1, 2, 3 ou 4 encaminha diretamente para uma nova requisicao de operacao
            }

            System.out.println("Deseja:\n1 - Inserir pacientes\n2 - Atender pacientes\n3 - Exibir estado atual das filas\n4 - Exibir relatório final de atendimentos\n5 - Sair do Pronto Socorro");
            escolha = scanner.nextInt(); //Recolhe a operacao escolhida na variavel escolha
            scanner.nextLine(); //Limpa o buffer
        }
        scanner.close(); //Fecha o scanner para evitar resource leak
    }
}