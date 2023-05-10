import java.sql.Timestamp;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.print("Hello and welcome!");

        // Criando uma instância de Timestamp a partir de um objeto Date
        Date date = new Date();
        Timestamp timestamp1 = new Timestamp(date.getTime());

        // Criando uma instância de Timestamp diretamente a partir de um valor de tempo em milissegundos
        long timeInMillis = System.currentTimeMillis();
        Timestamp timestamp2 = new Timestamp(timeInMillis);

        System.out.println(timestamp1);
        System.out.println(timestamp2);

    }
}