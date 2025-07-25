/*****************************************************************
* Autor............: Italo de Souza Leao
* Matricula........: 202410120
* Inicio...........: 18/03/2025
* Ultima alteracao.: 23/03/2025
* Nome.............: ControleDosMovimentosDosTrens.java 
* Funcao...........: Classe que realiza o controle de movimento dos trens na tela, permitindo que os trens se movam de acordo com a posicao inicial escolhida pelo usuario e os pontos na tela, em que os trens terao de fazer
conversoes entre os trilhos ou retornar as suas posicoes iniciais, isto eh, quando o trajeto, de uma determinada posicao inicial, se encerra
******************************************************************/

package util; //Posicionamento da classe ControleDosMovimentosDosTrens em um diretorio util - adequado para inserir classes que nao sofrerao instanciacao ou serao instanciadas uma unica vez

import model.Trem; //Importacao da classe de modelo dos trens, Trem, para realizar a movimentacao daqueles na tela

public final class ControleDosMovimentosDosTrens { //Assinatura da classe como final, adequada para: classes sem filhas e classes que so contem metodos estaticos.

  //Ausencia de construtor na implementacao da classe, pois nao ha idealizacao de que ela seja instanciada


  /*****************************************************************
  * Metodo: moverTrem
  * Funcao: Chamar o metodo de movimento do trem de acordo com a posicao inicial escolhida pelo usuario
  * Parametros: Trem trem, o trem, em questao, a ser movido
  * Retorno: void
  *****************************************************************/
  public static void moverTrem(Trem trem) {
      switch (trem.getPosicaoInicial()) {
        case 0: //Caso a posicao inicial do trem seja inferior esquerda
          moverTremNaPosicao0(trem); //Chamada do metodo de movimento na tela dos trens na posicao inicial 0
          break;
        case 1: //Caso a posicao inicial do trem seja superior esquerda
          moverTremNaPosicao1(trem); //Chamada do metodo de movimento na tela dos trens na posicao inicial 1
          break;
        case 2: //Caso a posicao inicial do trem seja inferior direita
          moverTremNaPosicao2(trem); //Chamada do metodo de movimento na tela dos trens na posicao inicial 2
          break;
        case 3: //Caso a posicao inicial do trem seja superior direita
          moverTremNaPosicao3(trem); //Chamada do metodo de movimento na tela dos trens na posicao inicial 3 
          break;
        default:
          System.out.println("ERRO NO METODO moverTrem DA CLASSE ControleDosMovimentosDosTrens"); //Verificacao de possiveis ocorrencias nao esperadas no switch-case pela impressao de um texto no terminal
          break;
    } //Fim do switch-case

  } //Fim do metodo moverTrem



  /*****************************************************************
  * Metodo: moverTremNaPosicao0
  * Funcao: Realiza o movimento do trem na posicao inicial 0 - inferior esquerda, implementando a logica de movimento do trem na tela de acordo sua posicao em relacao aos eixos X e Y. O metodo eh chamado em uma instancia de um AnimationTimer, que atualiza a tela a cada frame. 
  Dessa forma, a imagem do trem eh movida de acordo os limites de movimentacao dos trilhos e sua velocidade, que acrescenta ou desconta pixels de posicao nos eixos, a depender da condicao. Os metodos subsequentes seguem o mesmo padrao de funcionamento, com excecao da posicao inicial.
  * Parametros: Trem trem, o trem, em questao, a ser movido
  * Retorno: void
  *****************************************************************/
  private static void moverTremNaPosicao0(Trem trem) {

    if (trem.getImagemTrem().getLayoutY() > 508) { //Checa a posicao do trem em relacao ao eixo Y, para a primeira secao de trilhos duplos, movimentando-o em linha reta
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y, acrescentando a posicao no eixo, conforme a velocidade do trem na primeira secao de trilhos duplos, da parte superior a inferior

    } else if (trem.getImagemTrem().getLayoutY() <= 508 && trem.getImagemTrem().getLayoutY() > 487) { //Checa a posicao em relacao ao eixo Y para fazer a primeira curva
      trem.getImagemTrem().setRotate(24); //Rotaciona o trem na diagonal a direita para o trilho intermediario 1
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y
      trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() + trem.getVelocidade()); //Movimenta o trem no eixo X

    } else if (trem.getImagemTrem().getLayoutY() <= 487 && trem.getImagemTrem().getLayoutY() > 383) { //Checa a posicao nos eixos Y e X, para fazer a segunda curva
      trem.getImagemTrem().setRotate(0); //Rotaciona para a rotacao original da imagem do trem, na vertical, para o trilho simples 1
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y, apenas, no primeiro trilho simples, da parte superior a inferior

    } else if (trem.getImagemTrem().getLayoutY() <= 383 && trem.getImagemTrem().getLayoutY() > 362) { //Checa a posicao no eixo Y, para fazer a terceira curva
      trem.getImagemTrem().setRotate(337); //Rotaciona o trem na diagonal a esquerda para o trilho intermediario 2
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y
      trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() - trem.getVelocidade()); //Movimenta o trem no eixo X

    } else if (trem.getImagemTrem().getLayoutY() <= 362 && trem.getImagemTrem().getLayoutY() > 227) { //Checa a posicao nos eixos Y e X, para fazer a quarta curva
      trem.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho duplo
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y apenas, na segunda secao de trilhos duplos, da parte superior a inferior

    } else if (trem.getImagemTrem().getLayoutY() <= 227 && trem.getImagemTrem().getLayoutY() > 205) { //Checa a posicao no eixo Y, para fazer quinta curva
      trem.getImagemTrem().setRotate(24); //Rotaciona o trem na diagonal a direita para o trilho intermediario 3
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y
      trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() + trem.getVelocidade()); //Movimenta o trem no eixo X

    } else if (trem.getImagemTrem().getLayoutY() <= 205 && trem.getImagemTrem().getLayoutY() > 100) { //Checa a posicao nos eixos Y e X, para fazer a sexta curva
      trem.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho simples 2
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y, apenas, no segundo trilho simples, da parte superior a inferior

    } else if (trem.getImagemTrem().getLayoutY() <= 100 && trem.getImagemTrem().getLayoutY() > 75) { //Checa a posicao no eixo Y, para fazer a setima curva
      trem.getImagemTrem().setRotate(337); //Rotaciona o trem na diagonal a esquerda para o trilho intermediario 4
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y
      trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() - trem.getVelocidade()); //Movimenta o trem no eixo X

    } else if (trem.getImagemTrem().getLayoutY() <= 75 && trem.getImagemTrem().getLayoutY() > -92) { //Checa a posicao nos eixos Y e X, para fazer a oitava curva
      trem.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho duplo
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y, apenas, na terceira secao de trilhos duplos, da parte superior a inferior

    } else if (trem.getImagemTrem().getLayoutY() <= -92) { //Checa a posicao no eixo Y, para saber se o caminho do trilho a esquerda, pela parte inferior terminou
      trem.getImagemTrem().setLayoutX(469); //Posiciona a imagem do trem no eixo X, correspondente a posicao superior direita
      trem.getImagemTrem().setLayoutY(601); //Posiciona a imagem do trem no eixo Y, correspondente a posicao superior direita
      trem.getImagemTrem().setRotate(0); //Rotaciona a imagem do trem para 180 graus, para ajustar a orientacao da continuacao do movimentacao

    } //Fim da sequencia de condicionais else-if

  } //Fim do metodo moverNaPosicao0



  /*****************************************************************
  * Metodo: moverTremNaPosicao1
  * Funcao: Segue a mesma logica de movimentacao dos trens de moverTremNaPosicao0, entretanto, na orientacao de posicionamento 1 - superior esquerda.
  * Parametros: Trem trem, o trem, em questao, a ser movido
  * Retorno: void
  *****************************************************************/
  private static void moverTremNaPosicao1(Trem trem) {

    if (trem.getImagemTrem().getLayoutY() < 60) { //Checa a posicao do trem em relacao ao eixo Y, para a primeira secao de trilhos duplos, movimentando-o em linha reta
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade()); //Movimenta o trem na primeira secao de trilhos duplos, da parte superior a inferior

    } else if (trem.getImagemTrem().getLayoutY() >= 60 && trem.getImagemTrem().getLayoutY() < 85) { //Checa a posicao do trem no eixo Y para fazer a primeira curva
      trem.getImagemTrem().setRotate(156); //Rotaciona na diagonal a direita para o trilho intermediario 1
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade());
      trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() + trem.getVelocidade());

    } else if (trem.getImagemTrem().getLayoutY() >= 85 && trem.getImagemTrem().getLayoutY() < 182) { //Checa a posicao no eixo Y para fazer a segunda curva
      trem.getImagemTrem().setRotate(180); //Rotaciona na vertical, com orientacao invertida, por estar na parte superior, para o trilho simples 1
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade()); //Movimenta o trem no primeiro trilho simples, da parte superior a inferior

    } else if (trem.getImagemTrem().getLayoutY() >= 182 && trem.getImagemTrem().getLayoutY() < 211) { //Checa a posicao no eixo Y para fazer a terceira curva
      trem.getImagemTrem().setRotate(203); //Rotaciona na diagonal a esquerda para o trilho intermediario 2
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade());
      trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() - trem.getVelocidade());

    } else if (trem.getImagemTrem().getLayoutY() >= 211 && trem.getImagemTrem().getLayoutY() < 328) { //Checa a posicao no eixo Y para fazer a quarta curva
      trem.getImagemTrem().setRotate(180); //Rotaciona na vertical, com orientacao invertida, para o trilho duplo 2
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade()); //Movimenta o trem na segunda secao de trilhos duplos, da parte superior a inferior

    } else if (trem.getImagemTrem().getLayoutY() >= 328 && trem.getImagemTrem().getLayoutY() < 357) { //Checa a posicao no eixo Y para fazer a quinta curva
      trem.getImagemTrem().setRotate(156); //Rotaciona na diagonal a direita para o trilho intermediario 3
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade());
      trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() + trem.getVelocidade());

    } else if (trem.getImagemTrem().getLayoutY() >= 357 && trem.getImagemTrem().getLayoutY() < 454) { //Checa a posicao no eixo Y para fazer a sexta curva
      trem.getImagemTrem().setRotate(180); //Rotaciona na vertical, com orientacao invertida, para o trilho simples 2
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade()); //Movimenta o trem no segundo trilho simples, da parte superior a inferior

    } else if (trem.getImagemTrem().getLayoutY() >= 454 && trem.getImagemTrem().getLayoutY() < 481) { //Checa a posicao do trem no eixo Y para fazer a setima curva
      trem.getImagemTrem().setRotate(203); //Rotaciona na diagonal a esquerda para o trilho intermediario 4
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade());
      trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() - trem.getVelocidade());

    } else if (trem.getImagemTrem().getLayoutY() >= 481 && trem.getImagemTrem().getLayoutY() < 693) { //Checa a posicao no eixo Y para fazer a oitava curva
      trem.getImagemTrem().setRotate(180); //Rotaciona o trem na vertical, com orientacao invertida
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade()); //Movimenta o trem na terceira secao de trilhos duplos, da parte superior a inferior

    } else if (trem.getImagemTrem().getLayoutY() >= 693) { //Checa a posicao no eixo Y, para saber se o caminho do trilho a esquerda, pela parte superior, terminou
      trem.getImagemTrem().setLayoutX(469); //Posiciona a imagem do trem no eixo X, correspondente a posicao inferior direita
      trem.getImagemTrem().setLayoutY(0); //Posiciona a imagem do trem no eixo Y, correspondente a posicao inferior direita
      trem.getImagemTrem().setRotate(180); //Rotaciona a imagem do trem em 0 graus, para ajustar a orientacao da continuacao do movimentacao
      
    } //Fim da sequencia de condicionais else-if

  } //Fim do metodo moverTremNaPosicao1


  
  /*****************************************************************
  * Metodo: moverTremNaPosicao2
  * Funcao: Segue a mesma logica de movimentacao dos trens de moverTremNaPosicao0 e moverTremNaPosicao1, entretanto, na orientacao de posicionamento 2 - inferior direita.
  * Parametros: Trem trem, o trem, em questao, a ser movido
  * Retorno: void
  *****************************************************************/
  private static void moverTremNaPosicao2(Trem trem) {

    if (trem.getImagemTrem().getLayoutY() > 508) { //Checa a posicao do trem em relacao ao eixo Y, para a primeira secao de trilhos duplos, movimentando-o em linha reta
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y, apenas, isto eh, na primeira secao de trilhos simples, da parte inferior a superior

    } else if (trem.getImagemTrem().getLayoutY() <= 508 && trem.getImagemTrem().getLayoutY() > 487) { //Checa a posicao em relacao ao eixo Y para fazer a primeira curva
      trem.getImagemTrem().setRotate(321); //Rotaciona o trem na diagonal a esquerda para o trilho intermediario 1
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade());
      trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() - trem.getVelocidade());

    } else if (trem.getImagemTrem().getLayoutY() <= 487 && trem.getImagemTrem().getLayoutY() > 383) { //Checa a posicao em relacao ao eixo Y para fazer a segunda curva
      trem.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho simples 1
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y, apenas, isto eh, no primeiro trilho simples, da parte inferior a superior

    } else if (trem.getImagemTrem().getLayoutY() <= 383 && trem.getImagemTrem().getLayoutY() > 362) { //Checa a posicao em relacao ao eixo Y para fazer a trilha curva
      trem.getImagemTrem().setRotate(24); //Rotaciona o trem na diagonal a direita para o trilho intermediario 2
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade());
      trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() + trem.getVelocidade());

    } else if (trem.getImagemTrem().getLayoutY() <= 362 && trem.getImagemTrem().getLayoutY() > 227) { //Checa a posicao em relacao ao eixo Y para fazer a quarta curva
      trem.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho duplo 2
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y, na segunda secao de trilhos duplos, da parte inferior a superior

    } else if (trem.getImagemTrem().getLayoutY() <= 227 && trem.getImagemTrem().getLayoutY() > 205) { //Checa a posicao em relacao ao eixo Y para fazer a quinta curva
      trem.getImagemTrem().setRotate(321); //Rotaciona o trem na diagonal a esquerda para o trilho intermediario 3
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade());
      trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() - trem.getVelocidade());

    } else if (trem.getImagemTrem().getLayoutY() <= 205 && trem.getImagemTrem().getLayoutY() > 100) { //Checa a posicao em relacao ao eixo Y para fazer a sexta curva
      trem.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho simples 2
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y, no segundo trilho simples, da parte inferior a superior  

    } else if (trem.getImagemTrem().getLayoutY() <= 100 && trem.getImagemTrem().getLayoutY() > 75) { //Checa a posicao em relacao ao eixo Y para fazer a setima curva
      trem.getImagemTrem().setRotate(24); //Rotaciona o trem na diagonal a direita para o trilho intermediario 4
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade());
      trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() + trem.getVelocidade());

    } else if (trem.getImagemTrem().getLayoutY() <= 75 && trem.getImagemTrem().getLayoutY() > -92) { //Checa a posicao em relacao ao eixo Y para fazer a oitava curva
      trem.getImagemTrem().setRotate(0); //Rotaciona o trem na vertical para o trilho duplo 3
      trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() - trem.getVelocidade()); //Movimenta o trem no eixo Y, na terceira secao de trilhos duplos, da parte inferior a superior

    } else if (trem.getImagemTrem().getLayoutY() <= -92) { //Checa a posicao no eixo Y, para saber se o caminho do trilho a direita, pela parte inferior, terminou
      trem.getImagemTrem().setLayoutX(515); //Posiciona a imagem do trem no eixo X, correspondente a posicao superior esquerda
      trem.getImagemTrem().setLayoutY(601); //Posiciona a imagem do trem no eixo Y, correspondente a posicao superior esquerda
      trem.getImagemTrem().setRotate(0); //Rotaciona a imagem do trem em 180 graus, para ajustar a orientacao da continuacao do movimentacao

    } //Fim da sequencia de condicionais else-if

  } //Fim do metodo moverTremNaPosicao2



  /*****************************************************************
  * Metodo: moverTremNaPosicao3
  * Funcao: Segue o mesmo principio dos metodos moverTremNaPosicao precedentes, porem com a logica de movimento do trem na posicao inicial 3 - superior direita. Modifica-se, dessa forma, a rotacao da imagem do trem e eh determinado como ele se movimenta no eixo Y e X de acordo com a posicao que ele se encontra no momento
  * Parametros: Trem trem, o trem, em questao, a ser movido
  * Retorno: void
  ******************************************************************/
  private static void moverTremNaPosicao3(Trem trem) {

      if (trem.getImagemTrem().getLayoutY() < 60) { //Checa a posicao do trem em relacao ao eixo Y, para a primeira secao de trilhos duplos, movimentando-o em linha reta
        trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade()); //Movimenta o trem na primeira secao de trilhos duplos, da parte superior para inferior

      } else if (trem.getImagemTrem().getLayoutY() >= 60 && trem.getImagemTrem().getLayoutY() < 85) { //Checa a posicao do trem no eixo Y, para fazer a primeira curva
        trem.getImagemTrem().setRotate(210); //Rotaciona a imagem do trem na diagonal a esquerda para o trilho intermediario 1
        trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade());
        trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() - trem.getVelocidade());

      } else if (trem.getImagemTrem().getLayoutY() >= 85 && trem.getImagemTrem().getLayoutY() < 182) { //Checa a posicao do trem nos eixo Y e X, para fazer a segunda curva
        trem.getImagemTrem().setRotate(180); //Rotaciona a imagem do trem na vertical, com orientacao invertida, para o trilho simples 1
        trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade());

      } else if (trem.getImagemTrem().getLayoutY() >= 182 && trem.getImagemTrem().getLayoutY() < 211) { //Checa a posicao do trem no eixo Y, para fazer a terceira curva
        trem.getImagemTrem().setRotate(156); //Rotaciona o trem na diagonal a direita para o trilho intermediario 2
        trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade());
        trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() + trem.getVelocidade());

      } else if (trem.getImagemTrem().getLayoutY() >= 211 && trem.getImagemTrem().getLayoutY() < 328) { //Checa a posicao do trem nos eixos Y e X, para fazer a quarta curva
        trem.getImagemTrem().setRotate(180); //Rotaciona o trem na vertical, com orientacao invertida, para o trilho duplo 2
        trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade()); //Movimenta o trem na segunda secao de trilhos duplos, da parte superior para inferior

      } else if (trem.getImagemTrem().getLayoutY() >= 328 && trem.getImagemTrem().getLayoutY() < 357) { //Checa a posicao do trem no eixo Y, para fazer a quinta curva
        trem.getImagemTrem().setRotate(210); //Rotaciona o trem na diagonal a esquerda para o trilho intermediario 3
        trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade());
        trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() - trem.getVelocidade());

      } else if (trem.getImagemTrem().getLayoutY() >= 357 && trem.getImagemTrem().getLayoutY() < 454) { //Checa a posicao do trem nos eixos X e Y, para fazer a sexta curva
        trem.getImagemTrem().setRotate(180); //Rotaciona o trem na vertical, orientacao invertida, para o trilho simples 2
        trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade());

      } else if (trem.getImagemTrem().getLayoutY() >= 454 && trem.getImagemTrem().getLayoutY() < 481) { //Checa a posicao no eixo Y, para fazer a setima curva
        trem.getImagemTrem().setRotate(156); //Rotaciona o trem na diagonal a direita para o trilho intermediario 4
        trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade());
        trem.getImagemTrem().setLayoutX(trem.getImagemTrem().getLayoutX() + trem.getVelocidade());

      } else if (trem.getImagemTrem().getLayoutY() >= 481 && trem.getImagemTrem().getLayoutY() < 693) { //Checa a posicao nos eixos X e Y, para fazer a oitava curva
        trem.getImagemTrem().setRotate(180); //Rotaciona o trem na vertical, orientacao invertida, para o trilho duplo 3
        trem.getImagemTrem().setLayoutY(trem.getImagemTrem().getLayoutY() + trem.getVelocidade()); //Movimenta o trem na terceira secao de trilhos duplos, da parte superior para inferior

      } else if (trem.getImagemTrem().getLayoutY() >= 693) { //Checa a posicao no eixo Y, para saber se o caminho do trilho a direita, pela parte superior, terminou
        trem.getImagemTrem().setLayoutX(515); //Posiciona a imagem do trem no eixo X, correspondente a posicao inferior esquerda
        trem.getImagemTrem().setLayoutY(0); //Posiciona a imagem do trem no eixo Y, correspondente a posicao inferior esquerda
        trem.getImagemTrem().setRotate(180); //Rotaciona a imagem do trem em 180 graus, para ajustar a orientacao da continuacao do movimentacao

      } //Fim da sequencia de condicionais else-if

    } //Fim do metodo moverTremNaPosicao3

} //Fim da classe ControleDosMovimentosDosTrens