/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import static javafx.stage.Window.getWindows;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class InicioSesionController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button hereButton;
    private Button cancelRegisterButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField userID;
    @FXML
    private Button botonIniciar;
    @FXML
    private Label incorrectPasswordText;
    @FXML
    private Label incorrectUserText;
    @FXML
    private BorderPane container;

    static Member loginUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        container.setOnKeyPressed((event) -> {
            KeyCode code = event.getCode();
            if (code.equals(KeyCode.ENTER)) {
                iniciarSesion();
            }
        });
    }

    @FXML
    public void registerPage() {
        hereButton.setOnAction(e -> {
            try {
                Stage stage = (Stage) hereButton.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Registro.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(true);
                stage.setTitle("Registro de usuario");
                stage.setResizable(false);
                Image icono = new Image("\\resources\\icono.png");
                stage.getIcons().add(icono);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    public void cerrarOperacion() throws IOException {
        cancelButton.setOnAction(e -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }

    @FXML
    public void iniciarSesion() {
        String user = userID.getText();
        Club club;
        try {
            club = Club.getInstance();
            if (club.existsLogin(user)) {
                String password = passwordField.getText();
                Member login = null;
                try {
                    loginUser = login = club.getMemberByCredentials(user, password);
                } catch (Exception e) {
                    System.err.println("ERROR");
                }
                if (login != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaUsuario.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    Image icono = new Image("\\resources\\icono.png");
                    stage.getIcons().add(icono);
                    stage.setTitle("Pantalla de usuario");
                    ObservableList<Window> lista = getWindows();
                    for (Window window : lista) {
                        Stage thisStage = (Stage) window;
                        thisStage.close();
                    }

                    // Cerrar la ventana actual
                    Stage currentStage = (Stage) cancelButton.getScene().getWindow();
                    currentStage.close();
                    RegistroController.user = user;
                    RegistroController.password = password;
                    try {

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    stage.show();
                } else {
                    incorrectPasswordText.setVisible(true);
                    incorrectUserText.setVisible(false);
                }

            } else {
                incorrectPasswordText.setVisible(false);
                incorrectUserText.setVisible(true);
            }
        } catch (Exception exception) {
            System.err.println("Error");
        }

    }

    static String rutaDeLaImagen = "";

    public static void colocarImagen(Club club) {
        try {
            Member m = club.getMemberByCredentials(RegistroController.user, RegistroController.password);
            Image image = m.getImage();
            rutaDeLaImagen = image.getUrl();
          
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void metodoExecutor() throws IOException {
        iniciarSesion();
        cerrarOperacion();
    }

}
