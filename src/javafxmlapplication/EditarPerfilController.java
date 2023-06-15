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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Club;
import model.Member;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class EditarPerfilController implements Initializable {

    @FXML
    private BorderPane macro;
    @FXML
    private Button cancelRegisterButton;
    @FXML
    private TextField nameContainer;
    @FXML
    private TextField surnameContainer;
    @FXML
    private TextField phoneContainer;
    @FXML
    private TextField nicknameContainer;
    @FXML
    private TextField creditCardNumber;
    @FXML
    private TextField cvvContainer;
    @FXML
    private Label userRepeated;
    @FXML
    private ImageView imageContainer;
    @FXML
    private Button fotoSeleccion;
    @FXML
    private Button editButton;

    private boolean clickbutton = false;

    private String user = RegistroController.user;
    static String pass = RegistroController.password;
        private static String textoMaximoSVC = "";
    private static String textoMaximoPhone = "";
    private static String textoMaximocc = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Club club = Club.getInstance();
            Member miembro = club.getMemberByCredentials(RegistroController.user, RegistroController.password);
            String name = miembro.getName();
            nameContainer.setPromptText(name);
            String surname = miembro.getSurname();
            surnameContainer.setPromptText(surname);
            String phone = miembro.getTelephone();
            phoneContainer.setPromptText(phone);
            String cCard = miembro.getCreditCard();
            creditCardNumber.setPromptText(cCard);
            int cvv = miembro.getSvc();
            cvvContainer.setPromptText(Integer.toString(cvv));
            Image imagen = miembro.getImage();
            imageContainer.setImage(imagen);
            String nick = miembro.getNickName();
            nicknameContainer.setPromptText(nick);

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void mostrarFotos(ActionEvent event) {
        try {
            clickbutton = true;
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

    @FXML
    private void cerrarOperacionRegistro() {
        Stage stage = (Stage) imageContainer.getScene().getWindow();
        stage.close();
    }

    Image myImage = null;

    public void updateImage() {

        myImage = new Image(FotosController.imageToPass);
        if (imageContainer != null) {
            imageContainer.setImage(myImage);

        }
    }

    static String getPass() {
        return pass;
    }

    @FXML
    public void editar() {
        try {
            String message = "";
            Club club = Club.getInstance();
            String contraseña = getPass();
            Member miembro = club.getMemberByCredentials(user, contraseña);
            String newName = nameContainer.getText();
            if (!newName.equals("")) {
                miembro.setName(newName);
                message += "Nombre\n";
            }
            String newSurname = surnameContainer.getText();
            if (!newSurname.equals("")) {
                miembro.setSurname(newSurname);
                message += "Apellidos\n";
            }
            String newPhone = phoneContainer.getText();
            if (!newPhone.equals("")) {
                miembro.setTelephone(newPhone);
                message += "Telefono\n";
            }
            String newCard = creditCardNumber.getText();
            if (!newCard.equals("")) {
                miembro.setCreditCard(newCard);
                message += "Tarjeta de Crédito\n";
            }
            String svc = cvvContainer.getText();
            if (!svc.equals("")) {
                miembro.setSvc(Integer.parseInt(svc));
                message += "SVC\n";
            }

            Image imageSelected = imageContainer.getImage();
            miembro.setImage(imageSelected);
            if (clickbutton == true) {
                message += "Imágen de perfil\n";
            }
            if (!message.equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmación de cambios");
                alert.setHeaderText(RegistroController.user);
                alert.setGraphic(new ImageView(miembro.getImage()));
                alert.setContentText("Estos datos han sido modificados:\n" + message);
                alert.show();
            }
            Stage stage = (Stage) cvvContainer.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
