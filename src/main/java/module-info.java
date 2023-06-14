module com.bardotoco {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.bardotoco.application.view to javafx.fxml;
    opens com.bardotoco.application.controller to javafx.fxml;
    opens com.bardotoco.domain.entities.account to javafx.base;
    opens com.bardotoco.domain.entities.product to javafx.base;
    opens com.bardotoco.domain.entities.clientTable to javafx.base;
    opens com.bardotoco.domain.entities.cashier to javafx.base;
    opens com.bardotoco.domain.entities.saleitem to javafx.base;

    exports com.bardotoco.application.view;
    exports com.bardotoco.application.controller;
}
