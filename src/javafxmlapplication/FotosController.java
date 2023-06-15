/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */
public class FotosController implements Initializable {

    @FXML
    private ImageView foto1;

    @FXML
    private ImageView foto2;
    @FXML
    private ImageView foto3;
    @FXML
    private ImageView foto4;
    @FXML
    private ImageView foto5;
    @FXML
    private ImageView foto6;
    @FXML
    private ImageView foto7;
    @FXML
    private ImageView foto8;
    @FXML
    private ImageView foto9;
    @FXML
    private ImageView foto10;
    @FXML
    private ImageView foto11;

    public static String imageToPass = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }

    @FXML
    public void selectFoto(MouseEvent event) {
        if (event.getSource() instanceof VBox) {
            VBox vBox = (VBox) event.getSource();
            ImageView imageView = (ImageView) vBox.getChildren().get(0);
            String imageUrl = imageView.getImage().getUrl();
            int pos = imageUrl.indexOf("resources");
            imageUrl = imageUrl.substring(pos - 1);
            imageUrl = convertirDirectorio(imageUrl);
            imageToPass = imageUrl;
           
            Stage stage = (Stage) foto1.getScene().getWindow();
            stage.close();
            RegistroController aux = new RegistroController();
            aux.updateImage();
        } else {

            ImageView imageView = (ImageView) event.getSource();
            String imageUrl = imageView.getImage().getUrl();
            int pos = imageUrl.indexOf("resources");
            imageUrl = imageUrl.substring(pos - 1);
            imageUrl = convertirDirectorio(imageUrl);
            imageToPass = imageUrl;
            Stage stage = (Stage) foto1.getScene().getWindow();
            stage.close();
            RegistroController aux = new RegistroController();
            aux.updateImage();
        }

    }

    private static String convertirDirectorio(String directorio) {
        return directorio.replace('/', '\\');
    }

    /*
    public void obtenerFoto(ActionEvent e){
        RegistroController aux = new RegistroController();
        aux.setContainerFoto(selectFoto(e),e);
        
     */
}
