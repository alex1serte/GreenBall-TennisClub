/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {

    //========================================================
    // objects defined into FXML file with fx:id 
    @FXML
    private Button botonHorasNoRegistro;
    @FXML
    private Button botonInicial;
    @FXML
    private ImageView auxIcon;

    //Creamos una instancia de la clase Club
    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
    private void iniciarSesion(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioSesion.fxml"));
        Parent root = null;
        try{
         root = loader.load();
        }catch(IOException e){e.printStackTrace();}
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Inicio de sesión");
        stage.setResizable(false);
        Image icono = new Image("\\resources\\icono.png");
        stage.getIcons().add(icono);
        stage.show();
        Club club;
        try {
            club = Club.getInstance();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void verSelector() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservasNoLogin.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Selecciona la pista");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        Image icono = new Image("\\resources\\icono.png");
        stage.getIcons().add(icono);
        stage.show();

    }

    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        botonInicial.setOnAction(this::iniciarSesion);
        auxIcon.setImage(new Image("\\resources\\overview_FILL0_wght600_GRAD200_opsz48.png"));
        botonHorasNoRegistro.setOnMouseEntered((event) -> {
            auxIcon.setImage(new Image("\\resources\\botón.png"));
        });
        botonHorasNoRegistro.setOnMouseExited((event) -> {
            auxIcon.setImage(new Image("\\resources\\overview_FILL0_wght600_GRAD200_opsz48.png"));
        });
    }

}
