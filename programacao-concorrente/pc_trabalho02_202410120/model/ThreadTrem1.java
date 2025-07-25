/****************************************************************
* Autor............: Italo de Souza Leao
* Matricula........: 202410120
* Inicio...........: 27/04/2025
* Ultima alteracao.: 05/05/2025
* Nome.............: ThreadTrem1.java
* Funcao...........: Classe que controla todo a dinamica de movimento do trem 1, a esquerda, por meio de uma thread. Essa classe define a imagem do trem, a velocidade, 
a posicao inicial escolhida, o slider dele no ControllerTelaPrincipal, o algoritimo de solucao de sincronizacao escolhido, bem como controla a entrada e saida do trem das zonas criticas de impacto.
*****************************************************************/

package model;
//Importacoes necessarias para aplicar a logica de movimentacao grafica do trem 1 na tela
import javafx.scene.image.ImageView;
import controller.ControllerTelaPrincipal;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Slider;

public class ThreadTrem1 extends Thread {

  private ImageView imagemTrem;
  private double velocidade = 0.0;
  private int posicaoInicial;
  private final Slider slider; //Slider de velocidade correspondente ao trem 1 na tela principal
  private int solucao; //Solucao escolhida pelo usuario para o controle de concorrencia
  private final int processo = 0; //Processo do trem 1 utilizado na solucao de peterson para o controle de interesse na regiao critica
  //|-> Por definicao, o processo do trem 1 eh identificado como 0

  private boolean zonaCritica1 = false; //Variavel que controla a entrada e saida do trem na zona critica 1
  private boolean zonaCritica2 = false; //Variavel que controla a entrada e saida do trem na zona critica 2

  //Construtor da classe Train2Thread que define a imagem do trem, a posicao inicial do trem, o slider de velocidade e a solucao do problema de sincronizacao por valores passados como parametro
  public ThreadTrem1(ImageView imagemTrem, int posicaoInicial, Slider slider, int solucao) {
    this.imagemTrem = imagemTrem;
    this.slider = slider;
    this.posicaoInicial = posicaoInicial;
    this.velocidade = slider.getValue();
    this.solucao = solucao;//define a solucao do problema de sincronizacao

    switch (posicaoInicial) {
    case 0: //Caso em que a posicao inicial do trem eh inferior esquerda
      imagemTrem.setLayoutX(469); //Posiciona a imagem do trem alinhada horizontalmente ao trilho esquerdo do circuito
      imagemTrem.setLayoutY(601); //Posiciona a imagem do trem alinhada verticalmente ao inicio (orientacaoo: de baixo para cima) do trilho direito do circuito
      imagemTrem.setRotate(0.0); //Mantem a rotacao da imagem do trem, pois estao padronizadas na vertical
      break;
    case 1: //Caso em que a posicial inicial do trem eh superior esquerda
      imagemTrem.setLayoutX(469); //Posiciona a imagem do trem alinhada horizontalmente ao trilho esquerdo do circuito
      imagemTrem.setLayoutY(0); //Posiciona a imagem do trem alinhada verticalmente ao fim do trilho direito (orientacao: de baixo para cima) do circuito
      imagemTrem.setRotate(180.0); //Rotaciona a imagem do trem em 180 graus a fim que o final do trilho direito, isto eh, a parte superior direita, seja, nesta orientacao, o inicio
      break;
    }

  }

  //Classe interna para criacao de um timer proprio que extende a classe AnimationTimer para controlar o movimento do trem 1
  private class TimerTrem1 extends AnimationTimer{
    private ThreadTrem1 trem; //Objeto do tipo ThreadTrem1 que sera utilizado para controlar o movimento do trem 1
    

    //Construtor da classe TimerTrem1 que define o trem que sera movido por meio de um objeto do tipo ThreadTrem1
    public TimerTrem1(ThreadTrem1 trem){
      this.trem = trem;
    }

    @Override//Soobrescricao do metodo handle da classe AnimationTimer para controlar o movimento do trem 1
    /****************************************************************
    * Metodo: handle
    * Funcao: Metodo que eh invocado a cada frame da animacao chamando o metodo moveTrain para controlar o movimento do trem 1
    * Parametros: long now, o tempo atual do frame
    * Retorno: void
    ****************************************************************/
    public void handle(long now) {
      //Utilizacao do Platform.runLater() para atualizar a interface grafica dentro da mesma thread do JavaFX, evitando erros de animacao da tela
      Platform.runLater(() -> {trem.moverTrem();}); //Fim do Platform.runLater
    } //Fim do metodo handle
  } //Fim da classe TimerTrem1

  //Instancia o timer de movimento do trem 1 pelo construtor definido na classe TimerTrem1 acima
  AnimationTimer timerDeMovimento = new TimerTrem1(this);

  /****************************************************************
  * Metodo: getTimerDeMovimento
  * Funcao: Metodo que retorna o timer de movimento do trem 1 para ser controlado na tela
  * Parametros: nenhum
  * Retorno: AnimationTimer, o timer de movimento do trem 1
  ****************************************************************/
  public AnimationTimer getTimerDeMovimento(){
    return timerDeMovimento;
  }

  @Override//Soobrescricao do metodo run da classe Thread para iniciar o timer de movimento do trem 1
  /****************************************************************
  * Metodo: run
  * Funcao: Metodo que é chamado quando a thread é iniciada, iniciando o timer de movimento do trem 1
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  public void run() {
    timerDeMovimento.start();
  }

  /****************************************************************
  * Metodo: moverTrem
  * Funcao: Metodo que controla o movimento do trem 1, de acordo com a posicao inicial do trem, a velocidade do trem e a solucao do problema de sincronizacao escolhida pelo usuario
  * Parametros: nenhum
  * Retorno: void
  ****************************************************************/
  public void moverTrem() {

    this.setVelocidade(slider.getValue() * 0.025); //Definicao da velocidade do trem 1 para o valor do slider de velocidade com ajuste escalar para 2,5% da velocidade original

    //Switch que controla o movimento do trem de acordo com a posicao inicial do trem
    switch (this.posicaoInicial) {
      case 0:
        if (this.getImagemTrem().getLayoutY() > 508) { //Checa a posicao do trem em relacao ao eixo Y, para a primeira secao de trilhos duplos, movimentando-o em linha reta
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y, acrescentando a posicao no eixo, conforme a velocidade do trem na primeira secao de trilhos duplos, da parte superior a inferior

        } else if (this.getImagemTrem().getLayoutY() <= 508 && this.getImagemTrem().getLayoutY() > 487) { //Checa a posicao em relacao ao eixo Y para fazer a primeira curva
          //Entrada na Zona Critica 1
          if (!entrouNaZonaCritica1(solucao) && !zonaCritica1) { //Faz a verificacao de permissao de entrada na zona critica 1, de acordo a solucao escolhida
            break; //Caso a zona critica 1 esteja ocupada e seja falso que o trem 1 esteja ocupando ela, este trem para o seu movimento ate estar desocupada
          }

          this.getImagemTrem().setRotate(24); //Rotaciona o trem na diagonal a direita para o trilho intermediario 1
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() + this.getVelocidade()); //Movimenta o trem no eixo X
      
        } else if (this.getImagemTrem().getLayoutY() <= 487 && this.getImagemTrem().getLayoutY() > 383) { //Checa a posicao nos eixos Y e X, para fazer a segunda curva
          this.getImagemTrem().setRotate(0); //Rotaciona para a rotacao original da imagem do trem, na vertical, para o trilho simples 1
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y, apenas, no primeiro trilho simples, da parte superior a inferior
      
        } else if (this.getImagemTrem().getLayoutY() <= 383 && this.getImagemTrem().getLayoutY() > 362) { //Checa a posicao no eixo Y, para fazer a terceira curva
          this.getImagemTrem().setRotate(337); //Rotaciona o trem na diagonal a esquerda para o trilho intermediario 2
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() - this.getVelocidade()); //Movimenta o trem no eixo X
      
        } else if (this.getImagemTrem().getLayoutY() <= 362 && this.getImagemTrem().getLayoutY() > 227) { //Checa a posicao nos eixos Y e X, para fazer a quarta curva
          saiuDaZonaCritica1(solucao); //Altera o estado de ocupacao da zona critica 1 pelo trem 1
          this.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho duplo
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y apenas, na segunda secao de trilhos duplos, da parte superior a inferior
      
        } else if (this.getImagemTrem().getLayoutY() <= 227 && this.getImagemTrem().getLayoutY() > 205) { //Checa a posicao no eixo Y, para fazer quinta curva
          //Entrada na Zona Critica 2
          if(!entrouNaZonaCritica2(solucao) && !zonaCritica2){ //Faz a verificacao de permissao de entrada na zona critica 2, de acordo a solucao escolhida
            break; //Caso a zona critica 2 esteja ocupada e seja falso que o trem 1 esteja ocupando ela, este trem para o seu movimento ate estar desocupada
          }
          this.getImagemTrem().setRotate(24); //Rotaciona o trem na diagonal a direita para o trilho intermediario 3
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() + this.getVelocidade()); //Movimenta o trem no eixo X
      
        } else if (this.getImagemTrem().getLayoutY() <= 205 && this.getImagemTrem().getLayoutY() > 100) { //Checa a posicao nos eixos Y e X, para fazer a sexta curva
          this.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho simples 2
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y, apenas, no segundo trilho simples, da parte superior a inferior
      
        } else if (this.getImagemTrem().getLayoutY() <= 100 && this.getImagemTrem().getLayoutY() > 75) { //Checa a posicao no eixo Y, para fazer a setima curva
          this.getImagemTrem().setRotate(337); //Rotaciona o trem na diagonal a esquerda para o trilho intermediario 4
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() - this.getVelocidade()); //Movimenta o trem no eixo X
      
        } else if (this.getImagemTrem().getLayoutY() <= 75 && this.getImagemTrem().getLayoutY() > -92) { //Checa a posicao nos eixos Y e X, para fazer a oitava curva
          saiuDaZonaCritica2(solucao); //Altera o estado de ocupacao da zona critica 2 pelo trem 1
          this.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho duplo
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y, apenas, na terceira secao de trilhos duplos, da parte superior a inferior
      
        } else if (this.getImagemTrem().getLayoutY() <= -92) { //Checa a posicao no eixo Y, para saber se o caminho do trilho a esquerda, pela parte inferior terminou
          this.getImagemTrem().setLayoutX(469); //Posiciona a imagem do trem no eixo X, correspondente a posicao superior direita
          this.getImagemTrem().setLayoutY(601); //Posiciona a imagem do trem no eixo Y, correspondente a posicao superior direita
          this.getImagemTrem().setRotate(0); //Rotaciona a imagem do trem para 180 graus, para ajustar a orientacao da continuacao do movimentacao
      
        } //Fim da sequencia de condicionais else-if
        break;
      case 1:
        if (this.getImagemTrem().getLayoutY() < 60) { //Checa a posicao do trem em relacao ao eixo Y, para a primeira secao de trilhos duplos, movimentando-o em linha reta
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade()); //Movimenta o trem na primeira secao de trilhos duplos, da parte superior a inferior
      
        } else if (this.getImagemTrem().getLayoutY() >= 60 && this.getImagemTrem().getLayoutY() < 85) { //Checa a posicao do trem no eixo Y para fazer a primeira curva
          //Entrada na Zona Critica 2, afinal esta vindo da parte superior
          if (!entrouNaZonaCritica2(solucao) && !zonaCritica2) { //Faz a verificacao de permissao de entrada na zona critica 2, de acordo a solucao escolhida
            break; //Caso a zona critica 2 esteja ocupada e seja falso que o trem 1 esteja ocupando ela, este trem para o seu movimento ate estar desocupada
          }

          this.getImagemTrem().setRotate(156); //Rotaciona na diagonal a direita para o trilho intermediario 1
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade());
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() + this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() >= 85 && this.getImagemTrem().getLayoutY() < 182) { //Checa a posicao no eixo Y para fazer a segunda curva
          this.getImagemTrem().setRotate(180); //Rotaciona na vertical, com orientacao invertida, por estar na parte superior, para o trilho simples 1
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade()); //Movimenta o trem no primeiro trilho simples, da parte superior a inferior
      
        } else if (this.getImagemTrem().getLayoutY() >= 182 && this.getImagemTrem().getLayoutY() < 211) { //Checa a posicao no eixo Y para fazer a terceira curva
          this.getImagemTrem().setRotate(203); //Rotaciona na diagonal a esquerda para o trilho intermediario 2
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade());
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() - this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() >= 211 && this.getImagemTrem().getLayoutY() < 328) { //Checa a posicao no eixo Y para fazer a quarta curva
          saiuDaZonaCritica2(solucao); //Altera o estado de ocupacao da zona critica 2 pelo trem 1
          this.getImagemTrem().setRotate(180); //Rotaciona na vertical, com orientacao invertida, para o trilho duplo 2
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade()); //Movimenta o trem na segunda secao de trilhos duplos, da parte superior a inferior
      
        } else if (this.getImagemTrem().getLayoutY() >= 328 && this.getImagemTrem().getLayoutY() < 357) { //Checa a posicao no eixo Y para fazer a quinta curva
          //Entrada na Zona Critica 1
          if (!entrouNaZonaCritica1(solucao) && !zonaCritica1) { //Faz a verificacao de permissao de entrada na zona critica 1, de acordo a solucao escolhida
            break; //Caso a zona critica 1 esteja ocupada e seja falso que o trem 1 esteja ocupando ela, este trem para o seu movimento ate estar desocupada
          }
          this.getImagemTrem().setRotate(156); //Rotaciona na diagonal a direita para o trilho intermediario 3
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade());
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() + this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() >= 357 && this.getImagemTrem().getLayoutY() < 454) { //Checa a posicao no eixo Y para fazer a sexta curva
          this.getImagemTrem().setRotate(180); //Rotaciona na vertical, com orientacao invertida, para o trilho simples 2
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade()); //Movimenta o trem no segundo trilho simples, da parte superior a inferior
      
        } else if (this.getImagemTrem().getLayoutY() >= 454 && this.getImagemTrem().getLayoutY() < 481) { //Checa a posicao do trem no eixo Y para fazer a setima curva
          this.getImagemTrem().setRotate(203); //Rotaciona na diagonal a esquerda para o trilho intermediario 4
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade());
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() - this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() >= 481 && this.getImagemTrem().getLayoutY() < 693) { //Checa a posicao no eixo Y para fazer a oitava curva
          saiuDaZonaCritica1(solucao); //Altera o estado de ocupacao da zona critica 1 pelo trem 1
          this.getImagemTrem().setRotate(180); //Rotaciona o trem na vertical, com orientacao invertida
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade()); //Movimenta o trem na terceira secao de trilhos duplos, da parte superior a inferior
      
        } else if (this.getImagemTrem().getLayoutY() >= 693) { //Checa a posicao no eixo Y, para saber se o caminho do trilho a esquerda, pela parte superior, terminou
          this.getImagemTrem().setLayoutX(469); //Posiciona a imagem do trem no eixo X, correspondente a posicao inferior direita
          this.getImagemTrem().setLayoutY(0); //Posiciona a imagem do trem no eixo Y, correspondente a posicao inferior direita
          this.getImagemTrem().setRotate(180); //Rotaciona a imagem do trem em 0 graus, para ajustar a orientacao da continuacao do movimentacao
          
        } //Fim da sequencia de condicionais else-if
        break;
    } //Fim do switch case
  } //Fim do metodo moverTrem


  /****************************************************************
  * Metodo: entrouNaZonaCritica1
  * Funcao: Metodo que controla a entrada do trem 1 na zona critica 1 de acordo com a solucao escolhida pelo usuario
  * Parametros: int solucao, que eh a solucao escolhida pelo usuario para o problema de concorrencia entre as threads dos trens
  * Retorno: boolean, true, se o trem entrou na zona critica 1, e false, caso nao tenha entrado
  *****************************************************************/  
  public boolean entrouNaZonaCritica1(int solucao) {
    switch (solucao) {
      case 0:{ //Solucao por variavel de travamento

        if(ControllerTelaPrincipal.variavelDeTravamento1 == 1){
          return false; //Retorna false caso a variavel de travamento da zona critica 1 esteja ocupada, impedindo o trem de entrar na zona 1
        } else{
          ControllerTelaPrincipal.variavelDeTravamento1 = 1;
          //Caso a variavel de travamento da zona critica 1 nao esteja ocupada o trem entra na zona critica 1 e a variavel de travamento eh ocupada
          zonaCritica1 = true; //O trem 1 ocupa a zona critica 1
          return true; //Permite a entrada do trem na zona
        }

      }//Fim do case 0

      case 1:{ //Solucao por estrita alternancia

        if(ControllerTelaPrincipal.turno_estrita_alternancia1 == 1){ //Verifica se o turno da zona 1 pertence ao outro processo (threadTrem2)
          return false; //Impede a entrada do trem na zona
        }else{
          zonaCritica1 = true; //O trem 1 entra na zona 1
          return true;
        }

      }//Fim do case 1
      
      case 2:{ //Solucao de Peterson

        int outroProcesso; //Variavel que armazena o valor do processo do outro trem
        outroProcesso = 1 - processo; //Como utilizamos 2 processos, um com valor 0 e outro com valor 1, essa variavel eh utilizada para controlar a alternancia dos processos
        ControllerTelaPrincipal.interesseNaZona1[processo]= true; //Define o interesse do processo do trem 2 na regiao critica 1, ou seja o interesse do trem 2 em entrar na zona critica 1
        ControllerTelaPrincipal.turno_solucao_peterson1 = processo; //Define o turno do processo do trem 2 na regiao critica 1, ou seja diz que o turno atual da zona critica 1 eh do trem 2
        if(ControllerTelaPrincipal.turno_solucao_peterson1 == processo && ControllerTelaPrincipal.interesseNaZona1[outroProcesso] == true){
          //Verifica se o turno da zona critica 1 eh do trem 1 e se o outro processo tem interesse na zona critica 1, caso ambas as condicoes sejam verdadeiras o trem nao entra na zona critica 1 ate o turno ser desocupado
          return false; //Impede a entrada do trem na zona
        } else{ //Caso o turno da zona critica 1 nao seja do trem 1 ou se o outro processo nao tem interesse na zona critica 1
          zonaCritica1 = true; //O trem 2 ocupa a zona 2
          return true; //Permite a entrada do trem na zona
        }

      }//Fim do case 2

      default:
        return true; //Em outros casos eh permitida a entrada do trem, sem controle de colisao
    }//Fim do switch

  }//Fim do metodo entrouNaZonaCritica1


  /*****************************************************************
  * Metodo: saiuDaZonaCritica1
  * Funcao: Metodo que controla a garantia de saida do trem 1 da zona critica 1, de acordo com a solucao escolhida pelo usuario
  * Parametros: int solucao, a solucao escolhida pelo usuario para o problema de concorrencia dos trens
  * Retorno: void
  *****************************************************************/
  public void saiuDaZonaCritica1(int solucao) {
    switch (solucao) {
      case 0:{//Variavel de travamento da zona critica 1
        ControllerTelaPrincipal.variavelDeTravamento1 = 0;
        zonaCritica1 = false;
        break;

      }//Fim do case 0

      case 1:{//Estrita alternancia da zona critica 1
        ControllerTelaPrincipal.turno_estrita_alternancia1 = 1;
        zonaCritica1 = false;
        break;

      }//Fim do case 1

      case 2:{//Solucao de Peterson para a zona critica 1
        ControllerTelaPrincipal.interesseNaZona1[processo] = false;
        zonaCritica1 = false;
        break;

      }//Fim do case 2
    }//Fim do switch
  }//Fim do metodo saiuDaZonaCritica1

  /*****************************************************************
  * Metodo: entrouNaZonaCritica2
  * Funcao: Metodo que controla a entrada do trem 2 na zona critica 2 de acordo com a solucao escolhida pelo usuario
  * Parametros: int solucao, a solucao escolhida pelo usuario para o controle de concorrencia entre os trens
  * Retorno: boolean, true se o trem entrou na zona critica 2 e false caso contrario
  *****************************************************************/
  public boolean entrouNaZonaCritica2(int solucao) {
    switch (solucao) {
      case 0:{//Variavel de travamento da zona critica 2

        if(ControllerTelaPrincipal.variavelDeTravamento2 == 1){
          return false;
        }else{
          ControllerTelaPrincipal.variavelDeTravamento2 = 1;
          zonaCritica2 = true;
          return true;
        }

      }//Fim do case 0

      case 1:{//Estrita alternancia da zona critica 2

        if(ControllerTelaPrincipal.turno_estrita_alternancia2 == 1){
          return false;
        }else{
          zonaCritica2 = true;
          return true;
        }

      }//Fim do case 1

      case 2:{//Solucao de Peterson para a zona critica 2

        int outroProcesso;
        outroProcesso = 1 - processo;
        ControllerTelaPrincipal.interesseNaZona2[processo] = true;
        ControllerTelaPrincipal.turno_solucao_peterson2 = processo;
        if(ControllerTelaPrincipal.turno_solucao_peterson2 == processo && ControllerTelaPrincipal.interesseNaZona2[outroProcesso] == true){
          return false;
        }else{
          zonaCritica2 = true;
          return true;
        }

      }//Fim do case 2

      default:
        return true; //Em outros casos, eh permitida a entrada do trem na zona, sem controle de colisao
    }//Fim do switch
  }//Fim do metodo entrouNaZonaCritica2

  /*****************************************************************
  * Metodo: saiuDaZonaCritica2
  * Funcao: Metodo que controla a saida do trem 2 da zona critica 2 de acordo com a solucao escolhida pelo usuario
  * Parametros: int solucao, a solucao escolhida pelo usuario para o controle de concorrencia entre os trens
  * Retorno: void
  *****************************************************************/
  public void saiuDaZonaCritica2(int solucao) {
    switch (solucao) {
      case 0:{//Variavel de travamento da zona critica 2
        ControllerTelaPrincipal.variavelDeTravamento2 = 0;
        zonaCritica2 = false;
        break;

      }//Fim do case 0

      case 1:{//Estrita alternancia da zona critica 2
        ControllerTelaPrincipal.turno_estrita_alternancia2 = 1;
        zonaCritica2 = false;
        break;

      }//Fim do case 1

      case 2:{//Solucao de Peterson para a zona critica 2
        ControllerTelaPrincipal.interesseNaZona2[processo] = false;
        zonaCritica2 = false;
        break;

      }//Fim do case 2
    
      default:
        break;
    }//Fim do switch
  }//Fim do metodo saiuDaZonaCritica2

  /*****************************************************************
  * Metodo: getImagemTrem
  * Funcao: Retorna a imagem do trem, um ImageView, que representa o trem graficamente na tela, e permite a movimentacao daquele na tela
  * Parametros: nenhum
  * Retorno: ImageView, o atributo imagemTrem
  *****************************************************************/
  public ImageView getImagemTrem() {
    return imagemTrem;
  } //Fim do metodo getImagemTrem

  /*****************************************************************
  * Metodo: getposicaoInicial
  * Funcao: Metodo que retorna a posicao inicial do trem 1
  * Parametros: nenhum
  * Retorno: int, a posicao inicial do trem 1
  *****************************************************************/
  public int getposicaoInicial() {
    return posicaoInicial;
  }

  /*****************************************************************
  * Metodo: getVelocidade
  * Funcao: Retorna a velocidade do trem, um numero double que permite modificar a variavel de movimento que muda a posicao do trem na tela
  * Parametros: nenhum
  * Retorno: double, o atributo velocidade
  *****************************************************************/
  public double getVelocidade() {
    return velocidade;
  } //Fim do metodo getVelocidade

  /*****************************************************************
  * Metodo: getSolucao
  * Funcao: Metodo que retorna a solucao do trem 1
  * Parametros: nenhum
  * Retorno: int, a solucao do trem 1
  *****************************************************************/
  public int getSolucao() {
    return solucao;
  }

  /*****************************************************************
  * Metodo: setPosicaoInicial
  * Funcao: Metodo que define a posicao inicial do trem 1
  * Parametros: int posicaoInicial, a posicao inicial do trem 1
  * Retorno: void
  *****************************************************************/
  public void setPosicaoInicial(int posicaoInicial) {
    this.posicaoInicial = posicaoInicial;
  }

  /*****************************************************************
  * Metodo: setVelocidade
  * Funcao: Modifica a velocidade do trem, um numero double, que permite modificar a variavel de movimento, que muda a posicao do trem na tela
  * Parametros: double velocidade, a velocidade do trem
  * Retorno: void
  *****************************************************************/
  public void setVelocidade(double velocidade) {
    this.velocidade = velocidade;
  } //Fim do metodo setVelocidade

  /*****************************************************************
  * Metodo: setSolucao
  * Funcao: Metodo que define a solucao do trem 1
  * Parametros: int solucao, a solucao escolhida pelo usuario para o controle de concorrencia entre os trens
  * Retorno: void
  *****************************************************************/
  public void setSolucao(int solucao) {
    this.solucao = solucao;
  } //Fim do metodo setSolucao

} //Fim da classe ThreadTrem1