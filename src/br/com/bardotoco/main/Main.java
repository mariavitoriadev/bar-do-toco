package br.com.bardotoco.main;

import br.com.bardotoco.persistence.utils.DatabaseBuilder;

public class Main {
    public static void main(String[] args) {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }
}