/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */
public class SelectorPistasNOUsableController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private HBox pista5;
    @FXML
    private Rectangle rec5;
    @FXML
    private HBox pista6;
    @FXML
    private Rectangle rec6;
    @FXML
    private HBox pista4;
    @FXML
    private Rectangle rec4;
    @FXML
    private HBox pista3;
    @FXML
    private Rectangle rec3;
    @FXML
    private HBox pista2;
    @FXML
    private Rectangle rec2;
    @FXML
    private HBox pista1;
    @FXML
    private Rectangle rec1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) pista2.getScene().getWindow();
        stage.close();
    }

}
