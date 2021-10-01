package assignment2.instruments;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Spencer Smith
 * @author Anna Fortenberry
 */

public class AlertController implements Initializable {

    @FXML public Label error;

    public void setAlertText(String text) {
        // set text from another class
        error.setText(text);
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
}
