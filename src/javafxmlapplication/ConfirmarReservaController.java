/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */
public class ConfirmarReservaController implements Initializable {

    @FXML
    private Label textMessage;
    static String texto = "";
    static boolean confirmado = false;
    @FXML
    private Button acceptButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textMessage.setText(texto);
    }

    @FXML
    public void cerrarVentana() {
        Stage stage = (Stage) textMessage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void confirmarReserva() throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SeleccionDePago.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage2 = new Stage();
        stage2.setScene(scene);
        stage2.setResizable(false);
        stage2.setTitle("Selección de pago");
        Image icono = new Image("\\resources\\icono.png");
        stage2.getIcons().add(icono);
        stage2.showAndWait();
        if(Banco1Controller.confirmado == true){
            confirmado = true;
        }
        Stage stage = (Stage) textMessage.getScene().getWindow();
        stage.close();
    }

}
