import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unchecked")

public class Node<T>{
    private T dado;
    private Node<T> filho_esq, filho_dir;

    /*CONSTRUTORES**********************************************/
    public Node(T dado){
        this.dado = dado;
        filho_dir = filho_esq = null;
    }

    public Node(T dado, Node<T> filho_esq, Node<T> filho_dir){
        this.dado = dado;
        this.filho_esq = filho_esq;
        this.filho_dir = filho_dir;
    }

    /*GETTERS E SETTERS****************************************/
    public T getDado(){
        return dado;
    }
    public Node<T> getFilhoEsq(){
        return filho_esq;
    }
    public Node<T> getFilhoDir(){
        return filho_dir;
    }

    public void setDado(T dado){
        this.dado = dado;
    }
    public void setEsq(Node<T> filho_esq){
        this.filho_esq = filho_esq;
    }

    public void setDir(Node<T> filho_dir){
        this.filho_dir = filho_dir;
    }

    public boolean inserirOrdenado(T d){
        if(d.toString().compareTo(this.getDado().toString()) < 0){
            if(this.getFilhoEsq() != null){
                return this.getFilhoEsq().inserirOrdenado(d);
            } else{
                Node<T> n = new Node<T>(d);
                this.setEsq(n);
                return true;
            }
        } else{
            if(this.getFilhoDir() != null){
                return this.getFilhoDir().inserirOrdenado(d);
            } else{
                Node<T> n = new Node<T>(d);
                this.setDir(n);
                return true;
            }
        }
    }

    /*IMPRESSOES*************************************************/

    /*EM PROFUNDIDADE***********************************/
    public void imprimirInOrdem(){
        if(this.getFilhoEsq() != null){
            this.getFilhoEsq().imprimirInOrdem();
        }

        System.out.print(this.dado + "");

        if(this.getFilhoDir() != null){
            this.getFilhoDir().imprimirInOrdem();
        }
    }

    public void imprimirPreOrdem(){
        System.out.print(this.dado + "");

        if(this.getFilhoEsq() != null){
            this.getFilhoEsq().imprimirPreOrdem();
        }

        if(this.getFilhoDir() != null){
            this.getFilhoDir().imprimirPreOrdem();
        }
    }

    public void imprimirPosOrdem(){
        if(this.getFilhoEsq() != null){
            this.getFilhoEsq().imprimirPosOrdem();
        }

        if(this.getFilhoDir() != null){
            this.getFilhoDir().imprimirPosOrdem();
        }

        System.out.print(this.dado + "");
    }

    /*EM LARGURA*************************************/
    public void imprimirEmLargura(){
        Queue<Object> f = new LinkedList<Object>();
        f.offer(this);
        while(f.peek() != null){
            Node<T> n = (Node<T>) f.poll();
            if(n.getFilhoEsq() != null){
                f.offer(n.getFilhoEsq());
            }
            if(n.getFilhoDir() != null){
                f.offer(n.getFilhoDir());
            }
            System.out.print(n.getDado() + "");
        }
    }

    public Node<T> pesquisarDado(T d){
        int comparacao = d.toString().compareTo(this.getDado().toString());
        if(comparacao < 0){
            if(this.getFilhoEsq() != null){
                return this.getFilhoEsq().pesquisarDado(d);
            } else{
                return null;
            }
        } else if(comparacao > 0){
            if(this.getFilhoDir() != null){
                return this.getFilhoDir().pesquisarDado(d);
            } else{
                return null;
            }
        } else{
            return this;
        }
    }

    /*CALCULO DA ALTURA DA ARVORE**************************************/
    /*RECURSAO*************************************************/
    public int calcularAltura(){
        int alturaE = 0;
        int alturaD = 0;
        if(this.getFilhoEsq() != null){
            alturaE = this.getFilhoEsq().calcularAltura() + 1;
        } if(this.getFilhoDir() != null){
            alturaD = this.getFilhoDir().calcularAltura() + 1;
        }

        return Math.max(alturaE, alturaD);
    }

    /*LARGURA************************************************/
    public int calcularAlturaEmLargura(){
        Queue<Object> f = new LinkedList<Object>();
        Queue<Integer> dist = new LinkedList<Integer>();
        f.offer(this);
        dist.offer(0);
        int alturaMaxima = 0;
        while(f.peek() != null){
            Node<T> n = (Node<T>) f.poll();
            int distPai = dist.poll();
            if(n.getFilhoEsq() != null){
                f.offer(n.getFilhoEsq());
                dist.offer(distPai + 1);
            }
            if(n.getFilhoDir() != null){
                f.offer(n.getFilhoDir());
                dist.offer(distPai + 1);
            }
            if(distPai > alturaMaxima){
                alturaMaxima = distPai;
            }
        }
        return alturaMaxima;
    }

    /*CONTAGEM DE NODES***************************************************/

    public int calcularTotalNodes(){
        int totalE = 0;
        int totalD = 0;
        if(this.getFilhoEsq() != null){
            totalE = this.getFilhoEsq().calcularTotalNodes();
        }
        if(this.getFilhoDir() != null){
            totalD = this.getFilhoDir().calcularTotalNodes();
        }
        return totalE + totalD + 1;
    }

    public int calcularTotalFolhas(){
        int totalE = 0;
        int totalD = 0;
        boolean folha = true;
        if(this.getFilhoEsq() != null){
            totalE = this.getFilhoEsq().calcularTotalFolhas();
            folha = false;
        }
        if(this.getFilhoDir() != null){
            totalD = this.getFilhoDir().calcularTotalFolhas();
            folha = false;
        }
        if(folha){
            return 1;
        } else{
            return totalE + totalD;
        }
    }

    /*REMOCAO*************************************************/
    public boolean removerNode(T d, Node<T> noPai){
        int comparacao = d.toString().compareTo(this.getDado().toString());
        if(comparacao < 0){
            if(this.getFilhoEsq() != null){
                return this.getFilhoEsq().removerNode(d, this);
            } else{
                return false;
            }
        } else if(comparacao > 0){
            if(this.getFilhoDir() != null){
                return this.getFilhoDir().removerNode(d, this);
            } else{
                return false;
            }
        } else{
            //Caso seja uma folha
            if(this.getFilhoEsq() == null && this.getFilhoDir() == null){
                this.setFilhoNode(noPai, null);
                return true;
            }
            //Caso tenha um so filho, esquerdo
            else if(this.getFilhoEsq() != null && this.getFilhoDir() == null){
                this.setFilhoNode(noPai, this.getFilhoEsq());
                return true;
            }
            //Caso tenha um so filho, direito
            else if(this.getFilhoEsq() == null && this.getFilhoDir() != null){
                this.setFilhoNode(noPai, this.getFilhoDir());
                return true;
            }
            //Caso tenha dois filhos
            else{
                Node<T> menorNodeDir = this.getFilhoDir().NodeComMenorValor();
                Node<T> paiMenorNodeDir = (menorNodeDir != this.getFilhoDir()) ?
                                          this.getFilhoDir().encontrarPai(menorNodeDir) : this;
                this.setDado(menorNodeDir.getDado());
                menorNodeDir.setFilhoNode(paiMenorNodeDir, menorNodeDir.getFilhoDir());
                return true;
            }
        }
    }


    /*METODOS AUXILIARES PARA A REMOCAO***********************/
    public void setFilhoNode(Node<T> nodePai, Node<T> novoNode){
        if(nodePai.getFilhoEsq() == this){
            nodePai.setEsq(novoNode);
        } else if(nodePai.getFilhoDir() == this){
            nodePai.setDir(novoNode);
        }
    }

    public Node<T> NodeComMenorValor(){
        if(this.getFilhoEsq() != null){
            return this.getFilhoEsq().NodeComMenorValor();
        } else{
            return this;
        }
    }

    public Node<T> NodeComMaiorValor(){
        if(this.getFilhoDir() != null){
            return this.getFilhoDir().NodeComMaiorValor();
        } else{
            return this;
        }
    }

    public Node<T> encontrarPai(Node<T> noFilho){
        if(this.getFilhoEsq() == noFilho || this.getFilhoDir() == noFilho){
            return this;
        }
        if(noFilho.getDado().toString().compareTo(this.getDado().toString()) > 0){
            if(this.getFilhoEsq() != null){
                return this.getFilhoEsq().encontrarPai(noFilho);
            } else{
                return null;
            }
        } else{
            if(this.getFilhoDir() != null){
                return this.getFilhoDir().encontrarPai(noFilho);
            } else{
                return null;
            }
        }
    }
}