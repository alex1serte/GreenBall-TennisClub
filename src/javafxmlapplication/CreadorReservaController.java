/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Booking;
import model.Club;
import model.Court;
import model.Member;

public class CreadorReservaController implements Initializable {

    static LocalDate fecha;
    static LocalTime tiempo;
    @FXML
    private DatePicker day;
    @FXML
    private GridPane grid;
    @FXML
    private Label slotSelected;
    @FXML
    private Label labelCol;

    private final LocalTime firstSlotStart = LocalTime.of(9, 0);
    private final Duration slotLength = Duration.ofMinutes(60);
    private final LocalTime lastSlotStart = LocalTime.of(21, 0);

    // se puede cambiar por codigo la pseudoclase activa de un nodo    
    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

    private List<TimeSlot> timeSlots = new ArrayList<>(); //Para varias columnas List<List<TimeSolt>>

    private ObjectProperty<TimeSlot> timeSlotSelected;

    private LocalDate daySelected;
    @FXML
    private Label courtLabel;
    @FXML
    private Button confirmButton;
    @FXML
    private Button backButton;
    @FXML
    private Button courtChangeButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courtLabel.setText("Pista: " + SelectorPistasController.numeroDePista);
        timeSlotSelected = new SimpleObjectProperty<>();

        //---------------------------------------------------------------------
        //cambia los SlotTime al cambiar de dia
        day.valueProperty().addListener((a, b, c) -> {
            setTimeSlotsGrid(c);
            String texto = c.getDayOfWeek().getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault());
            texto = texto.toUpperCase();
            labelCol.setText(texto);
            cargarReservas();
        });

        //---------------------------------------------------------------------
        //inicializa el DatePicker al dia actual
        day.setValue(LocalDate.now());

        //---------------------------------------------------------------------
        // pinta los SlotTime en el grid
        setTimeSlotsGrid(day.getValue());

        //---------------------------------------------------------------------
        // enlazamos timeSlotSelected con el label para mostrar la seleccion
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("E MMM d");
        timeSlotSelected.addListener((a, b, c) -> {
            if (c == null) {
                slotSelected.setText("");
            } else {
                slotSelected.setText(c.getDate().format(dayFormatter)
                        + "-"
                        + c.getStart().format(timeFormatter));
            }
        });
        cargarReservas();

    }

    private void setTimeSlotsGrid(LocalDate date) {
        //actualizamos la seleccion
        timeSlotSelected.setValue(null);

        //--------------------------------------------        
        //borramos los SlotTime del grid
        ObservableList<Node> children = grid.getChildren();
        for (TimeSlot timeSlot : timeSlots) {
            children.remove(timeSlot.getView());
        }
        timeSlots = new ArrayList<>();

        //----------------------------------------------------------------------------------
        // desde la hora de inicio y hasta la hora de fin creamos slotTime segun la duracion
        int slotIndex = 1;
        for (LocalDateTime startTime = date.atTime(firstSlotStart);
                !startTime.isAfter(date.atTime(lastSlotStart));
                startTime = startTime.plus(slotLength)) {

            //---------------------------------------------------------------------------------------
            // creamos el SlotTime, lo anyadimos a la lista de la columna y asignamos sus manejadores
            TimeSlot timeSlot = new TimeSlot(startTime, slotLength);
            timeSlots.add(timeSlot);
            registerHandlers(timeSlot);
            //-----------------------------------------------------------
            // lo anyadimos al grid en la posicion x= 1, y= slotIndex
            grid.add(timeSlot.getView(), 1, slotIndex);
            slotIndex++;
        }
    }

    private void registerHandlers(TimeSlot timeSlot) {

        timeSlot.getView().setOnMouseClicked((MouseEvent event) -> {
            //---------------------------------------------slot----------------------------
            //solamente puede estar seleccionado un slot dentro de la lista de slot
            timeSlots.forEach(slot -> {
                slot.setSelected(slot == timeSlot);
            });

            //----------------------------------------------------------------
            //actualizamos el label Dia-Hora, esto es ad hoc,  para mi diseño
            timeSlotSelected.setValue(timeSlot);
            //----------------------------------------------------------------
            // si es un doubleClik  vamos a mostrar una alerta y cambiar el estilo de la celda
            if (event.getClickCount() > 1) {
                crearDesdeBoton();

            }
        });
    }

    public class TimeSlot {

        private final LocalDateTime start;
        private final Duration duration;
        protected final Pane view;

        private final BooleanProperty selected = new SimpleBooleanProperty();

        public final BooleanProperty selectedProperty() {
            return selected;
        }

        public final boolean isSelected() {
            return selectedProperty().get();
        }

        public final void setSelected(boolean selected) {
            selectedProperty().set(selected);
        }

        public TimeSlot(LocalDateTime start, Duration duration) {
            this.start = start;
            this.duration = duration;
            view = new Pane();
            view.getStyleClass().add("time-slot");
            // ---------------------------------------------------------------
            // de esta manera cambiamos la apariencia del TimeSlot cuando los seleccionamos
            selectedProperty().addListener((obs, wasSelected, isSelected)
                    -> view.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, isSelected));

        }

        public LocalDateTime getStart() {
            return start;
        }

        public LocalTime getTime() {
            return start.toLocalTime();
        }

        public LocalDate getDate() {
            return start.toLocalDate();
        }

        public DayOfWeek getDayOfWeek() {
            return start.getDayOfWeek();
        }

        public Duration getDuration() {
            return duration;
        }

        public Node getView() {
            return view;
        }

    }

    private void cargarReservas() {
        try {
            Club club = Club.getInstance();
            LocalDate selectedDate = day.getValue();
            List<Booking> bookingList = club.getCourtBookings("Pista " + SelectorPistasController.numeroDePista, selectedDate);

            for (Booking reserva : bookingList) {
                for (TimeSlot slot : timeSlots) {

                    if (slot.getDate().equals(reserva.getMadeForDay()) && slot.getTime().equals(reserva.getFromTime())) {
                 

                        ObservableList<String> styles = slot.getView().getStyleClass();
                        styles.add("time-slot-reservado");
                        String usuario = "\t\t\t\tPista reservada por " + reserva.getMember().getNickName();
                        Label label = new Label(usuario);
                        GridPane.setColumnIndex(label, 1);
                        GridPane.setRowIndex(label, timeSlots.indexOf(slot) + 1);
                        grid.getChildren().add(label);
                        label.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-alignment: center;");

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void crearDesdeBoton() {
        try {
            TimeSlot seleccionado = timeSlotSelected.get();
            LocalTime tiempo = seleccionado.getTime();
            LocalDate laNoNoche = day.getValue();
            LocalDateTime fecha = seleccionado.getStart();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmarReserva.fxml"));
            String dia = formatearTextoDeFecha(laNoNoche.toString());
            ConfirmarReservaController.texto = "¿Desea confirmar la reserva efectuada para el día " + dia + ", a las " + tiempo + "h?";
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Confirmación de reserva");
            Image icono = new Image("\\resources\\icono.png");
        stage.getIcons().add(icono);
            stage.showAndWait();

            if (ConfirmarReservaController.confirmado) {
                Club club = Club.getInstance();
                Member miembro = club.getMemberByCredentials(RegistroController.user, RegistroController.password);
                List<Court> pistas = club.getCourts();
                for (Court pista : pistas) {
                  
                    if (pista.getName().equals("Pista " + SelectorPistasController.numeroDePista)) {
                        List<Booking> listaDeReservas = club.getUserBookings(InicioSesionController.loginUser.getNickName());

                        String tiempoString = tiempo.toString();
                        int twoPointsPosition = tiempoString.indexOf(":");
                        int actualTime = Integer.parseInt(tiempoString.substring(0, twoPointsPosition));

                        boolean oneHourBefore = false;
                        boolean twoHourBefore = false;
                        boolean oneHourAfter = false;
                        boolean twoHourAfter = false;
                        for (Booking reservation : listaDeReservas) {
                            int reservationTime = extraerHora(reservation);
                            if (reservationTime == actualTime - 1 && reservation.getCourt().equals(pista) && reservation.getMember().getNickName().equals(RegistroController.user)) {
                                oneHourBefore = true;
                            }
                            if (reservationTime == actualTime - 2 && reservation.getCourt().equals(pista) && reservation.getMember().getNickName().equals(RegistroController.user)) {
                                twoHourBefore = true;
                            }
                            if (reservationTime == actualTime + 1 && reservation.getCourt().equals(pista) && reservation.getMember().getNickName().equals(RegistroController.user)) {
                                oneHourAfter = true;
                            }
                            if (reservationTime == actualTime + 2 && reservation.getCourt().equals(pista) && reservation.getMember().getNickName().equals(RegistroController.user)) {
                                twoHourAfter = true;
                            }
                        }

                        int fechaActual = MisReservasController.auxiliarConFechas(LocalDate.now());
                        int fechaDeLaReserva = MisReservasController.auxiliarConFechas(seleccionado.getDate());
                        int horaActual = MisReservasController.auxiliarConHoras(LocalTime.now());
                        int horaDeLaReserva = MisReservasController.auxiliarConHoras(seleccionado.getTime());

                        int sumaDeValores = 0;
                        if (oneHourAfter) {
                            sumaDeValores++;
                        }
                        if (oneHourBefore) {
                            sumaDeValores++;
                        }
                        if (twoHourAfter) {
                            sumaDeValores++;
                        }
                        if (twoHourBefore) {
                            sumaDeValores++;
                        }
                        if (sumaDeValores >= 2 && pista.getName().equals("Pista " + SelectorPistasController.numeroDePista)) {
                            if (oneHourBefore && twoHourBefore) {
                                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ReservaNoPosible.fxml"));
                                Parent root2 = loader2.load();
                                Stage stage2 = new Stage();
                                Scene scene2 = new Scene(root2);
                                stage2.setScene(scene2);
                                stage2.initModality(Modality.APPLICATION_MODAL);
                                stage2.setResizable(false);
                                stage2.setTitle("Reserva no posible");
                                stage2.showAndWait();
                                ConfirmarReservaController.confirmado = false;
                                oneHourBefore = false;
                                twoHourBefore = false;
                                oneHourAfter = false;
                                twoHourAfter = false;
                                break;
                            } else if (oneHourAfter && twoHourAfter) {
                                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ReservaNoPosible.fxml"));
                                Parent root2 = loader2.load();
                                Stage stage2 = new Stage();
                                Scene scene2 = new Scene(root2);
                                stage2.setScene(scene2);
                                stage2.initModality(Modality.APPLICATION_MODAL);
                                stage2.setResizable(false);
                                stage2.setTitle("Reserva no posible");
                                
        stage2.getIcons().add(icono);
                                stage2.showAndWait();
                                ConfirmarReservaController.confirmado = false;
                                oneHourBefore = false;
                                twoHourBefore = false;
                                oneHourAfter = false;
                                twoHourAfter = false;
                                break;
                            } else if (oneHourBefore && oneHourAfter) {
                                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ReservaNoPosible.fxml"));
                                Parent root2 = loader2.load();
                                Stage stage2 = new Stage();
                                Scene scene2 = new Scene(root2);
                                stage2.setScene(scene2);
                                stage2.initModality(Modality.APPLICATION_MODAL);
                                stage2.setResizable(false);
                                stage2.setTitle("Reserva no posible");
                                stage2.showAndWait();
                                
        stage2.getIcons().add(icono);
                                ConfirmarReservaController.confirmado = false;
                                oneHourBefore = false;
                                twoHourBefore = false;
                                oneHourAfter = false;
                                twoHourAfter = false;
                                break;
                            } else if (horaActual == 20 && oneHourBefore == false && pista.getName().equals("Pista " + SelectorPistasController.numeroDePista)) {
                                if (Banco1Controller.confirmado == true && SeleccionDePagoController.pagarDespues == false) {
                                    Booking reserva = club.registerBooking(fecha, laNoNoche, tiempo, true, pista, miembro);
                                } else if (SeleccionDePagoController.pagarDespues == true && SeleccionDePagoController.pagarDespues == true) {
                                    Booking reserva = club.registerBooking(fecha, laNoNoche, tiempo, false, pista, miembro);
                                }
                                ConfirmarReservaController.confirmado = false;
                                SeleccionDePagoController.pagarDespues = false;
                                cargarReservas();
                                break;
                            } else if (horaActual == 10 && oneHourAfter == false && pista.getName().equals("Pista " + SelectorPistasController.numeroDePista)) {
                                if (Banco1Controller.confirmado == true && SeleccionDePagoController.pagarDespues == false) {
                                    Booking reserva = club.registerBooking(fecha, laNoNoche, tiempo, true, pista, miembro);
                                } else if (SeleccionDePagoController.pagarDespues == true && SeleccionDePagoController.pagarDespues == true) {
                                    Booking reserva = club.registerBooking(fecha, laNoNoche, tiempo, false, pista, miembro);
                                }
                                ConfirmarReservaController.confirmado = false;
                                SeleccionDePagoController.pagarDespues = false;
                                cargarReservas();
                                break;
                            } else {
                                if (Banco1Controller.confirmado == true && SeleccionDePagoController.pagarDespues == false) {
                                    Booking reserva = club.registerBooking(fecha, laNoNoche, tiempo, true, pista, miembro);
                                } else if (SeleccionDePagoController.pagarDespues == true  && SeleccionDePagoController.pagarDespues == true) {
                                    Booking reserva = club.registerBooking(fecha, laNoNoche, tiempo, false, pista, miembro);
                                }
                                ConfirmarReservaController.confirmado = false;
                                SeleccionDePagoController.pagarDespues = false;
                                cargarReservas();
                                break;
                            }
                        } else if (fechaActual > fechaDeLaReserva || (fechaActual == fechaDeLaReserva && horaActual > horaDeLaReserva)) {
                            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ReservaAnterior.fxml"));
                            Parent root2 = loader2.load();
                            Stage stage2 = new Stage();
                            Scene scene2 = new Scene(root2);
                            stage2.setScene(scene2);
                            stage2.initModality(Modality.APPLICATION_MODAL);
                            stage2.setResizable(false);
                            stage2.setTitle("Reserva no posible");
                          
        stage2.getIcons().add(icono);
                            stage2.showAndWait();
                            ConfirmarReservaController.confirmado = false;
                        } else {
                            if (Banco1Controller.confirmado == true && SeleccionDePagoController.pagarDespues == false) {
                                    Booking reserva = club.registerBooking(fecha, laNoNoche, tiempo, true, pista, miembro);
                                } else if (SeleccionDePagoController.pagarDespues == true && SeleccionDePagoController.pagarDespues == true) {
                                    Booking reserva = club.registerBooking(fecha, laNoNoche, tiempo, false, pista, miembro);
                                }
                                ConfirmarReservaController.confirmado = false;
                                SeleccionDePagoController.pagarDespues = false;
                                cargarReservas();
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cerrarVentana() {
        Stage stage = (Stage) grid.getScene().getWindow();
        stage.close();
    }

    private static String formatearTextoDeFecha(String fecha) {
        String res;
        String[] partes = fecha.split("-");
        int month = Integer.parseInt(partes[1]);
        switch (month) {
            case 1:
                partes[1] = "de enero de";
                break;
            case 2:
                partes[1] = "de febrero de";
                break;
            case 3:
                partes[1] = "de marzo de";
                break;
            case 4:
                partes[1] = "de abril de";
                break;
            case 5:
                partes[1] = "de mayo de";
                break;
            case 6:
                partes[1] = "de junio de";
                break;
            case 7:
                partes[1] = "de julio de";
                break;
            case 8:
                partes[1] = "de agostro de";
                break;
            case 9:
                partes[1] = "de septiembre de";
                break;
            case 10:
                partes[1] = "de octubre de";
                break;
            case 11:
                partes[1] = "de noviembre de";
                break;
            case 12:
                partes[1] = "de diciembre de";
                break;
        }
        res = partes[2] + " " + partes[1] + " " + partes[0];
        return res;
    }

    private static int extraerHora(Booking reserva) {
        LocalTime time = reserva.getFromTime();
        String tiempo = time.toString();
        int position = tiempo.indexOf(":");
        String horaString = tiempo.substring(0, position);
        int hora = Integer.parseInt(horaString);
        return hora;
    }
    
    
    @FXML
    public void cambiarPista() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectorPistas.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Selector de pistas");
        stage.setResizable(false);
        stage.showAndWait();
        Stage s = (Stage) grid.getScene().getWindow();
        s.close();
    }

}
