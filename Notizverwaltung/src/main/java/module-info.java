module com.hak.notizverwaltung {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.hak.notizverwaltung to javafx.fxml;
    exports com.hak.notizverwaltung;
    exports at.hakimst.apr.db;
    opens at.hakimst.apr.db to javafx.fxml;
}