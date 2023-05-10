package Model;
import java.util.Comparator;
import java.util.Map;

public class Extrato implements Comparator<int> {
    private int valorTotal;
    Map<int, String=""> valorDasContas;
}






public class ComparatorInts implements Comparator<integer> {

    Map<integer, string=""> base;

    public ComparatorInts(Map<integer, string=""> base) {
        this.base = base;
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        return base.get(o1).compareTo(base.get(o2));
    }
}