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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import static javafxmlapplication.ConfirmarReservaController.confirmado;
import model.Booking;
import model.Club;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */
public class SeleccionDePagoController implements Initializable {

    
    @FXML
    private Button payNowButton;
    @FXML
    private Button payLaterButton;
    static boolean pagarDespues = false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void pagarDespues() {
        pagarDespues = true;
        Banco1Controller.confirmado = true;
        Stage stage = (Stage) payLaterButton.getScene().getWindow();
       
        stage.close();
    }

    @FXML
    public void pagarAhora() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Banco1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage2 = new Stage();
        stage2.setScene(scene);
        stage2.setResizable(false);
        Image icono = new Image("\\resources\\icono.png");
        stage2.getIcons().add(icono);
        stage2.setTitle("CuboBank - Confirmación de pago");
        
        stage2.showAndWait();

       
        Stage stage = (Stage) payNowButton.getScene().getWindow();
            stage.close();
        
    }
    
    /*
    public Booking initReserva(){
    try{
        Club club = Club.getInstance();
        Booking reserva = club.
    }catch(Exception e){
        e.printStackTrace();
    }
    return null;
    }
*/
}
