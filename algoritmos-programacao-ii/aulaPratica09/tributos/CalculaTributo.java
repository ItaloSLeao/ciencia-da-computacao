package aulaPratica09.tributos;
import java.util.ArrayList;

public class CalculaTributo {
    ArrayList<ITributavel> tributaveis = new ArrayList<>();

    public void adicionarTributo(ITributavel t){
        tributaveis.add(t);
    }

    public double calculaTotalTributo(){
        double total = 0;
        for(ITributavel t : tributaveis){
            total += t.getValorImposto();
        }
        return total;
    }
}
