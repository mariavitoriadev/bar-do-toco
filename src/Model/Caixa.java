package Model;

public class Caixa {

    private Status status;
    private static Caixa INSTANCE;

    private Caixa(){}

    private synchronized static Caixa getInstance(){
        if(INSTANCE == null) INSTANCE = new Caixa();
        return INSTANCE;
    }

}
