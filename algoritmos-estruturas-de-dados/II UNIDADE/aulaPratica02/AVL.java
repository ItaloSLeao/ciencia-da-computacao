public class AVL<T> extends Node<T>{
    private int fatorBalanceamento;
    
    public AVL(T v){
        super(v);
        fatorBalanceamento = 0;
    }
}