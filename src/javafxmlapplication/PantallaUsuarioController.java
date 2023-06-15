/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import static javafx.stage.Window.getWindows;
import static javafxmlapplication.MisReservasController.auxiliarConFechas;
import static javafxmlapplication.MisReservasController.auxiliarConHoras;
import javax.swing.text.html.parser.AttributeList;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */
public class PantallaUsuarioController implements Initializable {

    @FXML
    private Button pistas;
    @FXML
    private Button reservas;
    @FXML
    private Button inicio;
    @FXML
    private MenuItem cerrarSesion;
    @FXML
    ImageView imageLittle;
    @FXML
    private MenuItem editarButton;
    @FXML
    private MenuItem passwordChange;
    @FXML
    private ListView<String> listView;
    
    //---------------
    ArrayList<String> listaDeReservas;
    ObservableList<String> listContainer;
    
    List<Booking> listaDeReservasOriginales;
    List<Booking> listaDeReservasModificadas = new ArrayList<>();
    
    
    
    
    private ImageView imageContainer;
    private Label nom;
    private Label apellidos;
    private Label tel;
    private Label nick;
    
    private String user = RegistroController.user;
    static String pass = RegistroController.password;
    @FXML
    private ImageView fotoField;
    @FXML
    private Label nombreField;
    @FXML
    private Label surnameField;
    @FXML
    private Label nicknameField;
    @FXML
    private Label phoneField;
    private Label ccField;
    private Label svcField;
    @FXML
    private ImageView refreshButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshButton.setImage(new Image("\\resources\\refresh_FILL0_wght600_GRAD200_opsz48.png"));
        
        refreshButton.setOnMouseEntered((event) -> {
            refreshButton.setImage(new Image("\\resources\\output-onlinegiftools.gif"));
        });
        refreshButton.setOnMouseExited((event) -> {
             refreshButton.setImage(new Image("\\resources\\refresh_FILL0_wght600_GRAD200_opsz48.png"));
        });
        try {
            Club club = Club.getInstance();
            Member miembro = InicioSesionController.loginUser;
            String name = miembro.getName();
            nombreField.setText(name);
            String surname = miembro.getSurname();
            surnameField.setText(surname);
            String phone = miembro.getTelephone();
            phoneField.setText(phone);
            Image imagen = miembro.getImage();
            fotoField.setImage(imagen);
            String nickuser = miembro.getNickName();
            nicknameField.setText(nickuser);


            
             List<Booking> userReservas = club.getUserBookings(miembro.getNickName());
             List<String> lista = new ArrayList();
             for(Booking reserva : userReservas){
                 int diaR = auxiliarConFechas(reserva.getMadeForDay());
                 int diaH = auxiliarConFechas(LocalDate.now());
                 int horaR = auxiliarConHoras(reserva.getFromTime());
                 int horaH = auxiliarConHoras(LocalTime.now());
                 if(diaR == diaH && horaR < horaH){
                 }else if(diaR < diaH){
                 }else{
                 String texto = "Dia: " + reserva.getMadeForDay() + "\t\tHora: " + reserva.getFromTime();
                 lista.add(texto);
                 }
             }
             ObservableList<String> listContainer = FXCollections.observableArrayList(lista);
             listView.setItems(listContainer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
 

        
        
       
        
        
    }

    public void cerrarSesion(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) reservas.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Pantalla inicial");
            Image icono = new Image("\\resources\\icono.png");
            stage.getIcons().add(icono);
            // Obtener la lista de ventanas abiertas
            List<Window> windows = Window.getWindows();

            // Cerrar cada ventana de la lista
            for (Window window : windows) {
                if (window instanceof Stage) {
                    Stage thisStage = (Stage) window;
                    thisStage.close();
                }
            }
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void edit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CambiarContraseña.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            Image icono = new Image("\\resources\\icono.png");
            stage.getIcons().add(icono);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void profileEditing() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditarPerfil.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            Image icono = new Image("\\resources\\icono.png");
            stage.getIcons().add(icono);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void changePassword() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CambiarContraseña.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cambiar contraseña");
            stage.setResizable(false);
            Image icono = new Image("\\resources\\icono.png");
            stage.getIcons().add(icono);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void volverAtras() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) inicio.getScene().getWindow();
            Scene scene = new Scene(root);
            RegistroController.password = "";
            RegistroController.user = "";
            stage.setTitle("Tennis Club");
            stage.setScene(scene);
            Image icono = new Image("\\resources\\icono.png");
            stage.getIcons().add(icono);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    @FXML
    public void mostrarRecursos() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MisReservas.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        Image icono = new Image("\\resources\\icono.png");
        stage.getIcons().add(icono);
        stage.setResizable(false);
        stage.show();
        
    }
    
    @FXML
    public void mostrarPistas(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectorPistas.fxml"));
            Stage stage = new Stage();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            Image icono = new Image("\\resources\\icono.png");
        stage.getIcons().add(icono);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void cargarElementos(){
        try{
            Club club =  Club.getInstance();
            Member miembro = club.getMemberByCredentials(RegistroController.user, RegistroController.password);
            String nombre = miembro.getName();
            String apellidos = miembro.getSurname();
            String nickname = miembro.getNickName();
            String phone = miembro.getTelephone();
            Image imagen = miembro.getImage();
            
            nombreField.setText(nombre);
            surnameField.setText(apellidos);
            nicknameField.setText(nickname);
            phoneField.setText(phone);
            imageContainer.setImage(imagen);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    @FXML
    public void recargarPagina() throws IOException, InterruptedException{
        Stage thisStage = (Stage) phoneField.getScene().getWindow();
        double x = thisStage.getWidth();
        double y = thisStage.getHeight();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaUsuario.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) phoneField.getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("Pantallla de usuario");
    Image icono = new Image("\\resources\\icono.png");
        stage.getIcons().add(icono);
        stage.setWidth(x);
        stage.setHeight(y);
    stage.show();
    }
}
