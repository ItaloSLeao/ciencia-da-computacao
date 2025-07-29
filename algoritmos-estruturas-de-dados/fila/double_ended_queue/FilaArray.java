
import exceptions.ContainerVazioException;
import exceptions.ContainerCheioException;

public class FilaArray{
    private Object[] array;
    private int head, tail, count;

    public FilaArray(int tam) {
        array = new Object[tam];
        head = 0;
        tail = tam-1;
        count = 0;
    }

    public void fazVazia(){
        while(count > 0){
            array[head] = null; //Esvazia a posicao atual do array
            head++; //Incrementa head para progredir o esvaziamento no array
            if(head == array.length){
                head = 0; //Ao chegar ao fim do array, head volta para 0
            }
            count--; //A cada esvaziamento, decrementa o tamanho do array
        }
    }

    public Object getPrimeiro() throws ContainerVazioException{
        if (estaVazia()) {
            throw new ContainerVazioException();
        }
        return array[head];
    }

    public Object getUltimo() throws ContainerVazioException{
        if(estaVazia()){
            throw new ContainerVazioException();
        }
        return array[tail];
    }

    public void enfileirar(Object objeto) throws ContainerCheioException{
        if (estaCheia()) {
            throw new ContainerCheioException();
        }
        tail++;
        if(tail == array.length){
            tail = 0;
        }
        array[tail] = objeto;
        count++;
    }

    public Object desenfileirar() throws ContainerVazioException {
        if (estaVazia()) {
            throw new ContainerVazioException();
        }
        Object result = array[head];
        array[head] = null;
        head++;
        if(head == array.length){
            head = 0;
        }
        count--;
        return result;
    }

    public boolean estaVazia() {
        return count == 0;
    }

    public boolean estaCheia() {
        return count == array.length;
    }

    public int getHead(){
        return head;
    }

    public int getTail(){
        return tail;
    }

    public Object[] getArray(){
        return array;
    }

    public int getCount(){
        return count;
    }

    public void setHead(int h){
        head = h;
    }

    public void setTail(int t){
        tail = t;
    }

    public void setCount(int c){
        count = c;
    }
}