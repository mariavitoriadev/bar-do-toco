package Model;

public class Caixa {

    private Status status;
    private static Caixa INSTANCE;

    private Caixa(){}

    public synchronized static Caixa getInstance(){
        if(INSTANCE == null) INSTANCE = new Caixa();
        return INSTANCE;
    }

}
