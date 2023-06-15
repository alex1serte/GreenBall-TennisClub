/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */
public class SelectorPistasController implements Initializable {

    @FXML
    private HBox pista5;
    @FXML
    private HBox pista6;
    @FXML
    private HBox pista4;
    @FXML
    private HBox pista3;
    @FXML
    private HBox pista2;
    @FXML
    private HBox pista1;
    Rectangle aux3;
    @FXML
    private Button backButton;
    @FXML
    Rectangle rec5;
    @FXML
    Rectangle rec6;
    @FXML
    Rectangle rec4;
    @FXML
    Rectangle rec2;
    @FXML
    Rectangle rec1;
    @FXML
    private Rectangle rec3;
    static String numeroDePista = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void mostrarNumeroDePista(MouseEvent e) throws IOException {
        HBox caja = (HBox) e.getSource();
        String id = caja.getId();
        id = id.substring(id.length() - 1);
        
        numeroDePista = id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreadorReserva.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage2 = new Stage();
        stage2.setScene(scene);
        stage2.setTitle("Crear reserva");
        stage2.show();
        Image icono = new Image("\\resources\\icono.png");
        stage2.getIcons().add(icono);
        Stage stage = (Stage) pista2.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void mostrarNumeroDePistaAUX(MouseEvent e) throws IOException {
        Rectangle caja = (Rectangle) e.getSource();
        String id = caja.getId();
        id = id.substring(id.length() - 1);
        
        numeroDePista = id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreadorReserva.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage2 = new Stage();
        stage2.setScene(scene);
        stage2.setTitle("Crear reserva");
        Image icono = new Image("\\resources\\icono.png");
        stage2.getIcons().add(icono);
        stage2.show();
        Stage stage = (Stage) pista2.getScene().getWindow();
        stage.close();
    }

    public void cerrarVentana(ActionEvent e) {
       try{
        Rectangle caja = (Rectangle) e.getSource();
        String id = caja.getId();
        id = id.substring(id.length() - 1);
        numeroDePista = id;
        Stage stage = (Stage) pista2.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreadorReserva.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage2 = new Stage();
        stage2.setScene(scene);
        stage2.setTitle("Crear reserva");
        Image icono = new Image("\\resources\\icono.png");
        stage2.getIcons().add(icono);
        stage2.show();
        stage.close();
        
        
        }catch(Exception exception){exception.printStackTrace();}
    }
    
    
    @FXML
        public void cerrarVentanaActual() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

}
