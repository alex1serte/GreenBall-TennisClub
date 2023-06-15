/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import static javafx.stage.Window.getWindows;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */
public class Banco1Controller implements Initializable {

    @FXML
    private Button confirmButton;

    static boolean confirmado = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void confirmar() {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        confirmado = true;
        stage.close();
    }

}
