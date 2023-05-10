package Model;

public class Caixa {

    private Status status;
    private static Caixa INSTANCE;

    private Caixa(){
        this.status = Status.FECHADO;
    }

    public synchronized static Caixa getInstance(){
        if(INSTANCE == null) INSTANCE = new Caixa();
        return INSTANCE;
    }

    public Status getStatus() {
        return status;
    }

    public void abrirCaixa() {
        status = Status.ABERTO;
    }

    public void fecharCaixa() {
        status = Status.FECHADO;
    }

}
