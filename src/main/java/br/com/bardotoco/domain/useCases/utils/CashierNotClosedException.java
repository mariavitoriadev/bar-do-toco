package main.java.br.com.bardotoco.domain.useCases.utils;

public class CashierNotClosedException extends RuntimeException{
        public CashierNotClosedException(String message) {
            super(message);
        }
}
