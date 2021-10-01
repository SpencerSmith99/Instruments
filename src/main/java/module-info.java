module assignment.instruments {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires javafx.media;

    /*
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.media;
    requires java.desktop; */

    opens assignment2.instruments to javafx.fxml;
    exports assignment2.instruments;
}