/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class CambiarContraseñaController implements Initializable {

    @FXML
    private Label notMatchingPassword;
    @FXML
    private PasswordField secondPassword;
    @FXML
    private TextField firstPassword;
    @FXML
    private GridPane confirmButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void cambiarContraseña() {
        Club club;
        try {
            club = Club.getInstance();
            String nombre = RegistroController.user;
            String key = RegistroController.password;
            Member miembro = club.getMemberByCredentials(nombre, key);
            String passOne = firstPassword.getText();
            String passTwo = secondPassword.getText();
            if (passOne.equals(passTwo)) {
                EditarPerfilController.pass = passOne;
                notMatchingPassword.setVisible(false);
                miembro.setPassword(passTwo);
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Cambio de contraseña");
                info.setHeaderText(RegistroController.user);
                info.setContentText("Su contraseña ha sido cambiada exitosamente");
                info.showAndWait();
                Stage stage = (Stage) notMatchingPassword.getScene().getWindow();
                stage.close();
            } else {
                notMatchingPassword.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cerraVentana() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
 
}
