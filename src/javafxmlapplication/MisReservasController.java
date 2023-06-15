/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateTimeStringConverter;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;
import model.Member;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.Window;
import static javafx.stage.Window.getWindows;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */
public class MisReservasController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML

    private Button locationButton;

    @FXML
    private Label reserveDate;

    @FXML
    private Label reserveHour;

    @FXML
    private Label reserveCourt;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button backButton;
    ArrayList<String> listaDeReservas;
    ObservableList<String> listContainer;
    @FXML
    private Label payHeader;
    @FXML
    private Button payButton;
    @FXML
    private Label reservaPayed;
    List<Booking> listaDeReservasOriginales;
    List<Booking> listaDeReservasOriginales2;
    List<Booking> listaDeReservasModificadas = new ArrayList<>();
    @FXML
    private Button reserveButton;

    private String user = RegistroController.user;
    static String pass = RegistroController.password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Club club = Club.getInstance();
            listaDeReservasOriginales = club.getUserBookings(RegistroController.user);
            listaDeReservas = new ArrayList<>();
            reservaPayed.setText("Estado de la reserva: ");
            payButton.setVisible(false);
            payHeader.setVisible(false);

            int iterator = 0;
            List<Booking> listaDeReservasOriginales2 = new ArrayList<>();

            for (int size = listaDeReservasOriginales.size() - 1; size >= 0; size--) {
                listaDeReservasOriginales2.add(listaDeReservasOriginales.get(size));
            }

            for (Booking reserva : listaDeReservasOriginales2) {
                if (iterator >= 10) {
                    break;
                }

                listaDeReservasModificadas.add(reserva);

                iterator++;
            }
            for (Booking reserva : listaDeReservasModificadas) {
                String texto = "Reserva  -> \t Fecha:  " + reserva.getMadeForDay() + "\t\tHora: " + reserva.getFromTime();
                listaDeReservas.add(texto);
            }

            if (listaDeReservasModificadas.isEmpty()) {
                locationButton.setDisable(true);
                cancelButton.setDisable(true);
            }
            listContainer = FXCollections.observableArrayList(listaDeReservas);
            listView.setItems(listContainer);

            if (listContainer.size() >= 0) {
                try {
                    listView.getSelectionModel().select(0);
                    reserveDate.setText(listaDeReservasModificadas.get(0).getMadeForDay().toString());
                    reserveHour.setText(listaDeReservasModificadas.get(0).getFromTime().toString());
                    reserveCourt.setText(listaDeReservasModificadas.get(0).getCourt().getName().substring(listaDeReservasModificadas.get(0).getCourt().getName().length() - 1));
                } catch (IndexOutOfBoundsException exception) {;
                }
            }
            cancelButton.setOnAction((event) -> {
                int selectedIndex = listView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    Booking reserva = listaDeReservasOriginales2.get(selectedIndex);
                    try {
                        LocalDate today = LocalDate.now();
                        LocalTime todayTime = LocalTime.now();
                        LocalDate reservaFecha = reserva.getMadeForDay();
                        LocalTime reservaTime = reserva.getFromTime();
                        LocalDate fechaActual = LocalDate.now();
                        LocalTime currentTime = LocalTime.now();

                        int timeNow = auxiliarConHoras(todayTime);
                        int reservaTiempo = auxiliarConHoras(reservaTime);

                        if ((!fechaActual.equals(reservaFecha) && !(fechaActual.equals(reservaFecha.minusDays(1)) && reservaTiempo < timeNow))) {
                            club.removeBooking(reserva);
                            listContainer.remove(selectedIndex);
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("MisReservas.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                Stage stage = (Stage) reservaPayed.getScene().getWindow();
                                stage.setScene(scene);
                                stage.setTitle("Mis reservas");
                                stage.setResizable(false);
                                Image icono = new Image("\\resources\\icono.png");
        stage.getIcons().add(icono);
                                stage.show();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("NegativoHoras.fxml"));
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.setTitle("Error al cancelar");
                            Image icono = new Image("\\resources\\icono.png");
        stage.getIcons().add(icono);
                            stage.show();
                        }

                        if (listContainer.isEmpty()) {
                            cancelButton.setDisable(true);
                            locationButton.setDisable(true);
                            reserveDate.setText("");
                            reserveHour.setText("");
                            reserveCourt.setText("");
                            payButton.setVisible(false);
                            payHeader.setVisible(false);
                        } else {
                            listView.getSelectionModel().select(0);
                            Booking firstReservation = listaDeReservasOriginales2.get(0);
                            reserveDate.setText(firstReservation.getMadeForDay().toString());
                            reserveHour.setText(firstReservation.getFromTime().toString());
                            reserveCourt.setText(firstReservation.getCourt().getName().substring(firstReservation.getCourt().getName().length() - 1));
                            boolean pagado = firstReservation.getPaid();
                            if (pagado) {
                                reservaPayed.setText("Estado de pago: ABONADA");
                            } else {
                                reservaPayed.setText("Estado de pago: NO ABONADA");
                            }
                        }
                    } catch (ClubDAOException ex) {
                        ex.printStackTrace();
                    } catch (Exception ex) {
                        Logger.getLogger(MisReservasController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            listView.getSelectionModel().selectedIndexProperty().addListener((ov, o, n) -> {
                try {
                    int i = listView.getSelectionModel().getSelectedIndex();
                    Booking reserva = listaDeReservasOriginales2.get(i);
                    LocalTime hour = reserva.getFromTime();
                    LocalDate fecha = reserva.getMadeForDay();
                    Court pista = reserva.getCourt();
                    String nombre = pista.getName().substring(pista.getName().length() - 1);
                    reserveCourt.setText(nombre);
                    reserveDate.setText(fecha.toString());
                    reserveHour.setText(hour.toString());
                    cancelButton.setDisable(false);
                    boolean isPagado = reserva.getPaid();
                    if (!isPagado) {
                        payButton.setVisible(true);
                        payHeader.setVisible(true);
                        reservaPayed.setText("Estado de pago: NO ABONADA");
                    } else {
                        payButton.setVisible(false);
                        payHeader.setVisible(false);
                        reservaPayed.setText("Estado de pago: ABONADA");
                    }
                } catch (IndexOutOfBoundsException ex) {
                    reserveCourt.setText("");
                    reserveDate.setText("");
                    reserveHour.setText("");
                    cancelButton.setDisable(true);
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML

    public void cargarLocalizacion() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        String numeroDePista = reserveCourt.getText();
        String name = "rec" + numeroDePista;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectorPistasNOUsable.fxml"));
            Parent root = loader.load();
            SelectorPistasNOUsableController selectorPistasController = loader.getController();

            Field field = selectorPistasController.getClass().getDeclaredField(name);
            field.setAccessible(true);
            Rectangle rectangulo = (Rectangle) field.get(selectorPistasController);

            rectangulo.setStyle("-fx-opacity: 1; -fx-background-color: red");

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            Image icono = new Image("\\resources\\icono.png");
        stage.getIcons().add(icono);
        stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML

    public void volverAtras() throws IOException {
        Stage stage = (Stage) reserveCourt.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void pagarAhora() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SeleccionDePago.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Selección de pago");
        Image icono = new Image("\\resources\\icono.png");
        stage.getIcons().add(icono);

        stage.showAndWait();
        if (Banco1Controller.confirmado == true) {
            int pos = listView.getSelectionModel().getSelectedIndex();
            Booking reserva = listaDeReservasModificadas.get(pos);
            reserva.setPaid(true);
        }
        listView.getSelectionModel().select(0);

    }

    public static int auxiliarConFechas(LocalDate dia) {
        int res = 0;
        String fecha = dia.toString();
        String[] partes = fecha.split("-");
        String basic = "";
        for (String parte : partes) {
            basic += parte;
        }
        res = Integer.parseInt(basic);
        return res;
    }

    public static int auxiliarConHoras(LocalTime hora) {
        int res = 0;
        String fecha = hora.toString();
        String[] partes = fecha.split(":");
        String basic = partes[0];
        res = Integer.parseInt(basic);
        return res;
    }

    @FXML
    public void botonCrearUnaReserva() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectorPistas.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Selección de pago");
        Image icono = new Image("\\resources\\icono.png");
        stage.getIcons().add(icono);
        stage.setResizable(false);

        Stage stage2 = (Stage) listView.getScene().getWindow();
        stage.show();
        stage2.close();
    }

    public void eliminarReserva(Booking reserva, int index) {
        try {
            Club club = Club.getInstance();
            club.removeBooking(reserva);
            listContainer.remove(index);
            listView.selectionModelProperty();
        } catch (Exception e) {
            ;
        }

    }
}
