/*****************************************************************
* Autor............: Italo de Souza Leao
* Matricula........: 202410120
* Inicio...........: 02/05/2025
* Ultima alteracao.: 05/05/2025
* Nome.............: ThreadTrem2.java
* Funcao...........: Modela a forma de funcionamento do processo de movimentacao do trem 2 na aplicacao, realizando o controle da colisao
entre o trem 1 e o trem 2, pelos algoritmos de solucao de concorrencia. Alem de conter toda a logica de movimentacao grafica do trem 2, de
acordo sua posicao inicial, velocidade inicial e solucao escolhida.
*****************************************************************/

package model;

//Importacoes necessarias para aplicar a logica de movimentacao grafica do trem 2 nos trilhos
import controller.ControllerTelaPrincipal;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class ThreadTrem2 extends Thread{
  private ImageView imagemTrem;
  private double velocidade = 0.0;
  private int posicaoInicial;
  private final Slider slider; //Slider de velocidade correspondente ao trem 2 na tela principal
  private int solucao; //Solucao escolhida pelo usuario para o controle de concorrencia
  private final int processo = 1; //Processo do trem 2 utilizado na solucao de peterson para o controle de interesse na regiao critica
  //|-> Por definicao aqui, o processo do trem 2 eh identificado como 1

  private boolean zonaCritica1 = false; //Variavel que controla a veracidade se o trem 2 esta na zona critica 1
  private boolean zonaCritica2 = false; //Variavel que controla a veracidade se o trem 2 esta na zona critica 2

  //Construtor da classe Train2Thread que define a imagem do trem, a sua posicao inicial, o seu slider de velocidade e a solucao do problema de sincronizacao por valores passados como parametro
  public ThreadTrem2(ImageView imagemTrem, int posicaoInicial, Slider slider, int solucao) {
      this.imagemTrem = imagemTrem;
      this.slider = slider;
      this.posicaoInicial = posicaoInicial;
      this.velocidade = slider.getValue(); //Define a velocidade do trem pelo valor correspondente ao slider
      this.solucao = solucao; //Define a solucao do problema de sincronizacao pelo indice dela no array correspondente na classe controllerTelaPrincipal

      switch (posicaoInicial) {
        case 2: //Caso em que a posicao inicial do trem eh inferior direita
        imagemTrem.setLayoutX(515); //Posiciona a imagem do trem alinhada horizontalmente ao trilho direito do circuito
        imagemTrem.setLayoutY(601); //Posiciona a imagem do trem alinhada verticalmente ao inicio (orientacao: de baixo para cima) do trilho direito do circuito
        imagemTrem.setRotate(0.0); //Mantem a rotacao da imagem do trem, pois estao padronizadas na vertical
        break;
        case 3: //Caso em que a posicial inicial do trem eh superior direita
        imagemTrem.setLayoutX(515); //Posiciona a imagem do trem alinhada horizontalmente ao trilho direito do circuito
        imagemTrem.setLayoutY(0); //Posiciona a imagem do trem alinhada verticalmente ao fim do trilho direito (orientacao: de baixo para cima) do circuito
        imagemTrem.setRotate(180.0); //Rotaciona a imagem do trem em 180 graus a fim que o final do trilho direito, isto eh, a parte superior direita, seja, nesta orientacao, o inicio
        break;
      } //Fim do switch case
  }

  // Classe interna para criacao de um timer proprio de trem 2 que estende a classe AnimationTimer para controlar o movimento do trem 2
  private class TimerTrem2 extends AnimationTimer{
    private ThreadTrem2 trem; //Objeto do tipo ThreadTrem2 que sera utilizado para controlar o movimento do trem 1
    

    //Construtor da classe TimerTrem2 que define o trem que sera movido por meio de um objeto do tipo ThreadTrem2
    public TimerTrem2(ThreadTrem2 trem){
      this.trem = trem;
    }

    @Override//Soobrescricao do metodo handle da classe AnimationTimer para controlar o movimento do trem 2
    /****************************************************************
    * Metodo: handle
    * Funcao: Metodo que eh invocado a cada frame da animacao chamando o metodo moverTrem para controlar o movimento do trem 2
    * Parametros: long now, o tempo atual do frame
    * Retorno: void
    ****************************************************************/
    public void handle(long now) {
      //Utilizando o Platform.runLater() para atualizar a interface grafica dentro da mesma thread do JavaFX, evitando erros de animacao na tela
      Platform.runLater(() -> {trem.moverTrem();}); //Fim do Platform.runLater
    } //Fim do metodo handle
  } //Fim da classe TimerTrem2

  // Cria o timer de movimento do trem 2 instanciando a classe MyTimer criada acima
  AnimationTimer timerDeMovimento = new TimerTrem2(this); //Criacao do timer de movimento do trem 2 instanciando pelo construtor da classe TimerTrem2 acima

  /****************************************************************
  * Metodo: getTimerDeMovimento
  * Funcao: Metodo que retorna o timer de movimento do trem 2 para ser controlado na tela
  * Parametros: nenhum
  * Retorno: AnimationTimer, o timer de movimento do trem 2
  ****************************************************************/
  public AnimationTimer getTimerDeMovimento(){
    return timerDeMovimento;
  }

  @Override//Soobrescricao do metodo run da classe Thread para iniciar o timer de movimento do trem 2
  /****************************************************************
  * Metodo: run
  * Funcao: Metodo que eh chamado quando a thread eh iniciada, dando inicio ao timer de movimento do trem 2
  * Parametros: void
  * Retorno: void
  ****************************************************************/
  public void run() {
    timerDeMovimento.start(); //Inicia o timerDeMovimento
  }

  /* ***************************************************************
  * Metodo: moverTrem
  * Funcao: Metodo que controla o movimento do trem 2, de acordo com a posicao inicial do trem, a velocidade do trem e a solucao do problema de sincronizacao escolhida pelo usuario
  * Parametros: nenhum
  * Retorno: void
  *****************************************************************/
  public void moverTrem() {

    this.setVelocidade(slider.getValue() * 0.025); //Definicao da velocidade do trem 2 para o valor do slider de velocidade e ajuste escalar da velocidade para 2,5% da original

    //Switch que controla o movimento do trem de acordo com a posicao inicial do trem
    switch (this.posicaoInicial) {
      case 2:
        if (this.getImagemTrem().getLayoutY() > 508) { //Checa a posicao do trem em relacao ao eixo Y, para a primeira secao de trilhos duplos, movimentando-o em linha reta
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y, apenas, isto eh, na primeira secao de trilhos simples, da parte inferior a superior
      
        } else if (this.getImagemTrem().getLayoutY() <= 508 && this.getImagemTrem().getLayoutY() > 487) { //Checa a posicao em relacao ao eixo Y para fazer a primeira curva
          //Entrada na Zona 1
          if(!entrouNaZonaCritica1(solucao) && !zonaCritica1){ //Se a zona critica 1 esta ocupada e nao eh o trem 2 que esta ocupando
            break; //A movimentacao do trem para pelo break no switch-case ate que ela fique desocupada
          }
          this.getImagemTrem().setRotate(321); //Rotaciona o trem na diagonal a esquerda para o trilho intermediario 1
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade());
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() - this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() <= 487 && this.getImagemTrem().getLayoutY() > 383) { //Checa a posicao em relacao ao eixo Y para fazer a segunda curva
          this.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho simples 1
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y, apenas, isto eh, no primeiro trilho simples, da parte inferior a superior
      
        } else if (this.getImagemTrem().getLayoutY() <= 383 && this.getImagemTrem().getLayoutY() > 362) { //Checa a posicao em relacao ao eixo Y para fazer a trilha curva
          this.getImagemTrem().setRotate(24); //Rotaciona o trem na diagonal a direita para o trilho intermediario 2
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade());
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() + this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() <= 362 && this.getImagemTrem().getLayoutY() > 227) { //Checa a posicao em relacao ao eixo Y para fazer a quarta curva
          saiuDaZonaCritica1(solucao); //Altera o estado de ocupacao da zona 1 para desocupado
          this.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho duplo 2
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y, na segunda secao de trilhos duplos, da parte inferior a superior
      
        } else if (this.getImagemTrem().getLayoutY() <= 227 && this.getImagemTrem().getLayoutY() > 205) { //Checa a posicao em relacao ao eixo Y para fazer a quinta curva
          if(!entrouNaZonaCritica2(solucao) && !zonaCritica2){ //Se a zona 2 esta ocupada e nao eh o trem 2 que esta ocupando
            break; //O break no switch-case para a movimentacao do trem ate que a zona esteja desocupada
          }
          this.getImagemTrem().setRotate(321); //Rotaciona o trem na diagonal a esquerda para o trilho intermediario 3
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade());
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() - this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() <= 205 && this.getImagemTrem().getLayoutY() > 100) { //Checa a posicao em relacao ao eixo Y para fazer a sexta curva
          this.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho simples 2
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y, no segundo trilho simples, da parte inferior a superior  
      
        } else if (this.getImagemTrem().getLayoutY() <= 100 && this.getImagemTrem().getLayoutY() > 75) { //Checa a posicao em relacao ao eixo Y para fazer a setima curva
          this.getImagemTrem().setRotate(24); //Rotaciona o trem na diagonal a direita para o trilho intermediario 4
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade());
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() + this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() <= 75 && this.getImagemTrem().getLayoutY() > -92) { //Checa a posicao em relacao ao eixo Y para fazer a oitava curva
          saiuDaZonaCritica2(solucao); //Altera o estado de ocupacao da zona 2 para desocupado
          this.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho duplo 3
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() - this.getVelocidade()); //Movimenta o trem no eixo Y, na terceira secao de trilhos duplos, da parte inferior a superior
      
        } else if (this.getImagemTrem().getLayoutY() <= -92) { //Checa a posicao no eixo Y, para saber se o caminho do trilho a direita, pela parte inferior, terminou
          this.getImagemTrem().setLayoutX(515); //Posiciona a imagem do trem no eixo X, correspondente a posicao superior esquerda
          this.getImagemTrem().setLayoutY(601); //Posiciona a imagem do trem no eixo Y, correspondente a posicao superior esquerda
          this.getImagemTrem().setRotate(0); //Rotaciona a imagem do trem em 180 graus, para ajustar a orientacao da continuacao do movimentacao
      
        } //Fim da sequencia de condicionais else-if
        break;
      case 3:
        if (this.getImagemTrem().getLayoutY() < 60) { //Checa a posicao do trem em relacao ao eixo Y, para a primeira secao de trilhos duplos, movimentando-o em linha reta
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade()); //Movimenta o trem na primeira secao de trilhos duplos, da parte superior para inferior
      
        } else if (this.getImagemTrem().getLayoutY() >= 60 && this.getImagemTrem().getLayoutY() < 85) { //Checa a posicao do trem no eixo Y, para fazer a primeira curva
          //Entrada na Zona 2, pois o trem esta vindo da parte superior a inferior
          if(!entrouNaZonaCritica2(solucao) && !zonaCritica2){ //Se a zona 2 esta ocupada e nao eh o trem 2 que esta ocupando
            break; //Para a movimentacao do trem ate que a zona fique desocupada
          }
          this.getImagemTrem().setRotate(210); //Rotaciona a imagem do trem na diagonal a esquerda para o trilho intermediario 1
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade());
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() - this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() >= 85 && this.getImagemTrem().getLayoutY() < 182) { //Checa a posicao do trem nos eixo Y e X, para fazer a segunda curva
          this.getImagemTrem().setRotate(180); //Rotaciona a imagem do trem na vertical, com orientacao invertida, para o trilho simples 1
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() >= 182 && this.getImagemTrem().getLayoutY() < 211) { //Checa a posicao do trem no eixo Y, para fazer a terceira curva
          this.getImagemTrem().setRotate(156); //Rotaciona o trem na diagonal a direita para o trilho intermediario 2
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade());
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() + this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() >= 211 && this.getImagemTrem().getLayoutY() < 328) { //Checa a posicao do trem nos eixos Y e X, para fazer a quarta curva
          saiuDaZonaCritica2(solucao); //Altera o estado de ocupacao da zona 2 para desocupado
          this.getImagemTrem().setRotate(180); //Rotaciona o trem na vertical, com orientacao invertida, para o trilho duplo 2
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade()); //Movimenta o trem na segunda secao de trilhos duplos, da parte superior para inferior
      
        } else if (this.getImagemTrem().getLayoutY() >= 328 && this.getImagemTrem().getLayoutY() < 357) { //Checa a posicao do trem no eixo Y, para fazer a quinta curva
          //Entrada na Zona 1
          if(!entrouNaZonaCritica1(solucao) && !zonaCritica1){ //Se a zona 1 esta ocupada e nao eh o trem 2 que esta ocupando
            break; //Para a movimentacao do trem ate a zona estar desocupada
          }
          this.getImagemTrem().setRotate(210); //Rotaciona o trem na diagonal a esquerda para o trilho intermediario 3
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade());
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() - this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() >= 357 && this.getImagemTrem().getLayoutY() < 454) { //Checa a posicao do trem nos eixos X e Y, para fazer a sexta curva
          this.getImagemTrem().setRotate(180); //Rotaciona o trem na vertical, orientacao invertida, para o trilho simples 2
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() >= 454 && this.getImagemTrem().getLayoutY() < 481) { //Checa a posicao no eixo Y, para fazer a setima curva
          this.getImagemTrem().setRotate(156); //Rotaciona o trem na diagonal a direita para o trilho intermediario 4
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade());
          this.getImagemTrem().setLayoutX(this.getImagemTrem().getLayoutX() + this.getVelocidade());
      
        } else if (this.getImagemTrem().getLayoutY() >= 481 && this.getImagemTrem().getLayoutY() < 693) { //Checa a posicao nos eixos X e Y, para fazer a oitava curva
          saiuDaZonaCritica1(solucao); //Altera o estado da zona 1 para desocupada
          this.getImagemTrem().setRotate(180); //Rotaciona o trem na vertical, orientacao invertida, para o trilho duplo 3
          this.getImagemTrem().setLayoutY(this.getImagemTrem().getLayoutY() + this.getVelocidade()); //Movimenta o trem na terceira secao de trilhos duplos, da parte superior para inferior
      
        } else if (this.getImagemTrem().getLayoutY() >= 693) { //Checa a posicao no eixo Y, para saber se o caminho do trilho a direita, pela parte superior, terminou
          this.getImagemTrem().setLayoutX(515); //Posiciona a imagem do trem no eixo X, correspondente a posicao inferior esquerda
          this.getImagemTrem().setLayoutY(0); //Posiciona a imagem do trem no eixo Y, correspondente a posicao inferior esquerda
          this.getImagemTrem().setRotate(180); //Rotaciona a imagem do trem em 180 graus, para ajustar a orientacao da continuacao do movimentacao
      
        } //Fim da sequencia de condicionais else-if
        break;
    } //Fim do switch case
  } //Fim do metodo moverTrem


  /*****************************************************************
  * Metodo: entrouNaZonaCritica1
  * Funcao: Metodo que controla a entrada do trem 2 na zona critica 1 de acordo com a solucao escolhida pelo usuario
  * Parametros: int solucao, a solucao escolhida pelo usuario para o controle de concorrencia entre os trens
  * Retorno: boolean, true se o trem entrou na zona critica 1 e false caso contrario
  *****************************************************************/  
  public boolean entrouNaZonaCritica1(int solucao) {
    switch (solucao) {
      case 0:{ //Variavel de travamento da zona critica 1

        if(ControllerTelaPrincipal.variavelDeTravamento1 == 1){ //Em forma de recurso compartilhado entre as threads, a variavelDeTravamento1 eh usada para verificar a ocupacao da zona 1
          return false; //Retorna false caso a variavel de travamento da zona critica 1 seja igual a 1, isto eh, a zona esta ocupada
        }else{ //Caso a variavel de travamento seja igual a 0, isto eh, a zona esta desocupada
          ControllerTelaPrincipal.variavelDeTravamento1 = 1; //Altera o estado da variavelDeTravamento para 1, para ocupar a zona 1
          zonaCritica1 = true; //Variavel de controle da zona critica 1 do trem 2, garantindo que o trem que esta na zona critica 1 eh ele
          return true; //Retorna true, o trem 2 entrou na zona critica 1
        }

      } //Fim do case 0

      case 1:{ //Estrita alternancia da zona critica 1

        if(ControllerTelaPrincipal.turno_estrita_alternancia1 == 0){ //Novamente, em um recurso compartilhado, eh feita a verificacao do turno da zona 1
          return false; //Caso o turno seja 0, o turno da zona 1 pertence ao processo 0, isto eh, threadTrem2
        }else{
          zonaCritica1 = true; //Ocupa a zona 1, garantindo que o trem 2 que esta ocupando
          return true; //Retorna true, o trem 2 entrou na zona critica 1
        }

      } //Fim do case 1
      
      case 2:{ //Solucao de Peterson para a zona critica 1

        int outroProcesso; //Variavel que armazena o valor do processo do outro trem
        outroProcesso = 1 - processo; //Como utilizamos 2 processos um com valor 0 e outro com valor 1, essa variavel eh utilizada controlar a alternancia dos turnos
        ControllerTelaPrincipal.interesseNaZona1[processo] = true; //Define o interesse do processo do trem 2 na zona critica 1, ou seja o interesse do trem 2 em entrar na zona critica 1
        ControllerTelaPrincipal.turno_solucao_peterson1 = processo; //Define o turno do processo do trem 2 na regiao critica 1, ou seja diz que o turno atual da zona critica 1 eh do trem 2
        if(ControllerTelaPrincipal.turno_solucao_peterson1 == processo && ControllerTelaPrincipal.interesseNaZona1[outroProcesso] == true){
          //Verificacao se o turno da zona critica 1 eh do trem 1 e se o outro processo tem interesse na zona critica 1, caso ambas as condicoes sejam verdadeiras o trem nao entra na zona critica 1 ate o turno ser desocupado
          return false; //Retorna false, o turno esta ocupado
        } else{ //Caso o turno da zona 1 nao seja do trem 1 ou se o outro processo nao tem interesse de entrar na zona critica 1, o trem 2 entra na zona critica 1
          zonaCritica1 = true; //O trem 2 esta ocupando a zona 1
          return true; //Retorna true, o trem 2 esta ocupando a zona critica 1
        }

      }//Fim do case 2

      default:
        return true; //Em outros casos, eh permitida a entrada do trem na zona, sem controle de colisao
    }//Fim do switch
  }//Fim do metodo entrouNaZonaCritica1


  /*****************************************************************
  * Metodo: saiuDaZonaCritica1
  * Funcao: Metodo que controla a saida do trem 2 da zona critica 1 de acordo com a solucao escolhida pelo usuario
  * Parametros: int solucao, a solucao escolhida pelo usuario para o controle de concorrencia entre os trens
  * Retorno: void
  *****************************************************************/
  public void saiuDaZonaCritica1(int solucao) {
    switch (solucao) {
      case 0:{//Variavel de travamento da zona critica 1
        ControllerTelaPrincipal.variavelDeTravamento1 = 0;
        //Caso o trem saia da zona critica 1 a variavel de travamento da zona critica 1 eh desocupada
        zonaCritica1 = false; //O trem 2 nao esta mais ocupando a zona 1
        break;

      }//Fim do case 0

      case 1:{//Estrita alternancia da zona critica 1
        ControllerTelaPrincipal.turno_estrita_alternancia1 = 0;
        //Caso o trem saia da zona critica 1 o turno da zona critica 1 se torna do outro trem
        zonaCritica1 = false; //O trem 2 nao esta mais ocupando a zona 1
        break;

      }//Fim do case 1

      case 2:{//Solucao de Peterson para a zona critica 1
        ControllerTelaPrincipal.interesseNaZona1[processo] = false;
        //Caso o trem saia da zona critica 1 o interesse do processo do trem 2 na regiao critica 1 eh falso afinal ele nao esta mais na zona critica 1
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

        if(ControllerTelaPrincipal.variavelDeTravamento2 == 1){ //Verifica a ocupacao da zona 1
          return false; //Impede a entrada do trem na zona
        }else{
          ControllerTelaPrincipal.variavelDeTravamento2 = 1;
          //Caso a variavel de travamento da zona critica 2 nao esteja ocupada o trem entra na zona critica 2 e a variavel de travamento eh ocupada
          zonaCritica2 = true; //O trem 2 esta ocupando a zona 2
          return true; //Permite a entrada do trem na zona
        }

      }//Fim do case 0

      case 1:{//Estrita alternancia da zona critica 2

        if(ControllerTelaPrincipal.turno_estrita_alternancia2 == 0){
          //Verifica se o turno da zona critica 2 esta ocupado, caso esteja o trem nao entra na zona critica 2 ate ele ser desocupado
          return false;
        }else{
          //Caso o turno da zona critica 2 nao esteja ocupado o trem entra na zona critica 2 e a variavel de controle da zona critica 2 eh ocupada
          zonaCritica2 = true; //O trem 2 ocupa a zona 2
          return true; //Permite a entrada do trem na zona
        }

      }//Fim do case 1

      case 2:{//Solucao de Peterson para a zona critica 2

        int outroProcesso;//Variavel que armazena o valor do processo do outro trem
        outroProcesso = 1 - processo;// Como utilizamos 2 processos um com valor 0 e outro com valor 1, essa variavel eh utilizada para receber e controlar algumas condicoes do outro processo
        ControllerTelaPrincipal.interesseNaZona2[processo] = true;// Define o interesse do processo do trem 2 na regiao critica 2, ou seja o interesse do trem 2 em entrar na zona critica 2
        ControllerTelaPrincipal.turno_solucao_peterson2 = processo;// Define o turno do processo do trem 2 na regiao critica 2, ou seja diz que o turno atual da zona critica 2 eh do trem 2
        if(ControllerTelaPrincipal.turno_solucao_peterson2 == processo && ControllerTelaPrincipal.interesseNaZona2[outroProcesso] == true){
          //Verifica se o turno da zona critica 2 eh do trem 2 e se o outro processo tem interesse na regiao critica 2, caso ambas as condicoes sejam verdadeiras o trem nao entra na zona critica 2 ate o turno ser desocupado
          return false;
        }else{
          //Caso o turno da zona critica 2 seja do trem 2 e o outro processo nao tenha interesse na regiao critica 2, o trem entra na zona critica 2
          zonaCritica2 = true;
          return true;
        }

      }//Fim do case 2

      default:
        return true; //Em outros casos, o metodo permite a entrada na zona, sem controle de colisao
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
      case 0:{ //Variavel de travamento da zona critica 2
        ControllerTelaPrincipal.variavelDeTravamento2 = 0;
        //Caso o trem saia da zona critica 2 a variavel de travamento da zona critica 2 eh desocupada
        zonaCritica2 = false;
        break;

      }//Fim do case 0

      case 1:{ //Estrita alternancia da zona critica 2
        ControllerTelaPrincipal.turno_estrita_alternancia2 = 0;
        //Caso o trem saia da zona critica 2 o turno da zona critica 2 se torna do outro trem
        zonaCritica2 = false;
        break;

      }//Fim do case 1

      case 2:{ //Solucao de Peterson para a zona critica 2
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
  } //Fim do metodo getPosicaoInicial

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
  } //Fim do metodo getSolucao

  /*****************************************************************
  * Metodo: setPosicaoInicial
  * Funcao: Metodo que define a posicao inicial do trem 1
  * Parametros: int posicaoInicial, a posicao inicial do trem 1
  * Retorno: void
  *****************************************************************/
  public void setPosicaoInicial(int posicaoInicial) {
    this.posicaoInicial = posicaoInicial;
  } //Fim do metodo setPosicaoInicial

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

} //Fim da classe ThreadTrem2