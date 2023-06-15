/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import static javafx.stage.Window.getWindows;
import model.Club;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */
public class RegistroController implements Initializable {

    @FXML
    Button cancelRegisterButton;
    @FXML
    Button fotoSeleccion;

    Image myImage = null;
    @FXML
    ImageView imageContainer;
    @FXML
    TextField nameContainer;
    @FXML
    TextField surnameContainer;
    @FXML
    TextField phoneContainer;
    @FXML
    TextField nicknameContainer;
    @FXML
    TextField passwordContainer;
    @FXML
    TextField creditCardNumber;
    @FXML
    TextField cvvContainer;
    @FXML
    Button createRegisterButton;
    @FXML
    Label userRepeated;
    @FXML
    BorderPane macro;

    static String user = "";
    static String password = "";
    @FXML
    private Label textoBlanco;
    private static String textoMaximoSVC = "";
    private static String textoMaximoPhone = "";
    private static String textoMaximocc = "";

    Map<Member, Integer> mapDeMiembros = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cvvContainer.setOnKeyTyped((event) -> {
            if (cvvContainer.getText().length() <= 3) {
                textoMaximoSVC = cvvContainer.getText();
            } else {
                cvvContainer.setText(textoMaximoSVC);
            }
        });

        phoneContainer.setOnKeyTyped((event) -> {
            if (phoneContainer.getText().length() <= 9) {
                textoMaximoPhone = phoneContainer.getText();
            } else {
                phoneContainer.setText(textoMaximoPhone);
            }
        });
        
        
        creditCardNumber.setOnKeyTyped((event) -> {
            if (creditCardNumber.getText().length() <= 16) {
                textoMaximocc = creditCardNumber.getText();
            } else {
                creditCardNumber.setText(textoMaximocc);
            }
        });
    }

    @FXML
    public void cerrarOperacionRegistro() throws IOException {
        cancelRegisterButton.setOnAction(e -> {
            Stage stage = (Stage) cancelRegisterButton.getScene().getWindow();
            stage.close();
        });
    }

    @FXML
    public void mostrarFotos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Fotos.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Selección de foto de perfil");
            stage.initModality(Modality.APPLICATION_MODAL);
            Image icono = new Image("\\resources\\icono.png");
            stage.getIcons().add(icono);
            stage.showAndWait();
            updateImage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateImage() {
        myImage = new Image(FotosController.imageToPass);
        if (imageContainer != null) {
            imageContainer.setImage(myImage);
        }
    }

    @FXML
    public void crearRegistro() {
        long cardNumber = -1;
        int phoneNumber = -1;
        int cvvNumber = -1;
        String name = nameContainer.getText();
        String surname = surnameContainer.getText();
        String phone = phoneContainer.getText();
        String nickname = nicknameContainer.getText();
        String password = passwordContainer.getText();
        String card = creditCardNumber.getText();
        String cvv = cvvContainer.getText();

        try {
            cardNumber = Long.parseLong(card);
            cvvNumber = Integer.parseInt(cvv);
            phoneNumber = Integer.parseInt(phone);
        } catch (NumberFormatException error) {
            System.err.println("El número de tarjeta o el CVV han sido mal introducidos");
            return;
        }

        Club club;
        try {
            club = Club.getInstance();

            if (!club.existsLogin(nickname)) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmationMessage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Mensaje de confirmación");
                Image icono = new Image("\\resources\\icono.png");
                stage.getIcons().add(icono);
                stage.setResizable(false);
                stage.show();
                
                this.password = password;
                Member nuevoMiembro = club.registerMember(name, surname, Integer.toString(phoneNumber), nickname, password, Long.toString(cardNumber), cvvNumber, imageContainer.getImage());
                Random r = new Random();
                int numeroDeBanco = r.nextInt(-1, 2) + 1;
                mapDeMiembros.put(nuevoMiembro, numeroDeBanco);
                Stage thisStage = (Stage) phoneContainer.getScene().getWindow();
                thisStage.close();

            } else {
                userRepeated.setVisible(true);
                return;
            }

            

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        Stage stage = (Stage) cvvContainer.getScene().getWindow();
        stage.close();
    }

}
