import Dao.ContaDao;
import Dao.ContaSqliteDao;
import Model.Conta;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello and welcome!");

        // Criando uma instância de Timestamp a partir de um objeto Date
        Date date = new Date();
        Timestamp timestamp1 = new Timestamp(date.getTime());

        // Criando uma instância de Timestamp diretamente a partir de um valor de tempo em milissegundos
        long timeInMillis = System.currentTimeMillis();
        Timestamp timestamp2 = new Timestamp(timeInMillis);


        System.out.println(timestamp1);
        System.out.println(timestamp2);
        System.out.println();


        // testando o salvar a data no sqlite3

        Conta conta = new Conta(1, LocalDateTime.now(), LocalDateTime.now(), 2, 2, 2);
        ContaDao dao = new ContaSqliteDao();

        System.out.println(conta.getHorarioAbertura());
        System.out.println(conta.getHorarioFechamento());

        dao.criarTabela();
        dao.salvar(conta);

        List<LocalDateTime> horas = dao.mostraHora();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // exibe a data e a hora do bd precisa alterar para mostrar todos os dados que tem no bd e não somente a data e a hora pois só estou testando se salvou
        for (LocalDateTime hora : horas) {
            String horaFormatada = hora.format(formatter);
            System.out.println(horaFormatada);
        }

    }
}