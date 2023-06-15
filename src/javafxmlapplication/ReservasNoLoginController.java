/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Booking;
import model.Club;
import model.Court;
import model.Member;

public class ReservasNoLoginController implements Initializable {

    private LocalDate daySelected;
    private Label courtLabel;
    @FXML
    private Button backButton;
    @FXML
    private GridPane grid;
    @FXML
    private Label slotSelected;
    @FXML
    private HBox pista11;
    @FXML
    private HBox pista12;
    @FXML
    private HBox pista13;
    @FXML
    private HBox pista14;
    @FXML
    private HBox pista15;
    @FXML
    private HBox pista16;
    @FXML
    private HBox pista17;
    @FXML
    private HBox pista18;
    @FXML
    private HBox pista110;
    @FXML
    private HBox pista112;
    @FXML
    private HBox pista113;
    @FXML
    private HBox pista111;
    @FXML
    private Label texto11;
    @FXML
    private Label texto111;
    @FXML
    private Label texto112;
    @FXML
    private Label texto113;
    private Label texto114;
    private Label texto116;
    private Label texto115;
    private Label texto117;
    private Label texto118;
    private Label texto119;
    private Label texto1110;
    private Label texto1111;
    private Label texto1112;
    @FXML
    private HBox pista21;
    @FXML
    private HBox pista22;
    @FXML
    private HBox pista23;
    @FXML
    private HBox pista24;
    @FXML
    private HBox pista25;
    @FXML
    private HBox pista26;
    @FXML
    private HBox pista27;
    @FXML
    private HBox pista28;
    @FXML
    private HBox pista29;
    @FXML
    private HBox pista210;
    @FXML
    private HBox pista211;
    @FXML
    private HBox pista212;
    @FXML
    private HBox pista213;
    @FXML
    private HBox pista31;
    @FXML
    private HBox pista32;
    @FXML
    private HBox pista33;
    @FXML
    private HBox pista34;
    @FXML
    private HBox pista35;
    @FXML
    private HBox pista36;
    @FXML
    private HBox pista37;
    @FXML
    private HBox pista38;
    @FXML
    private HBox pista39;
    @FXML
    private HBox pista310;
    @FXML
    private HBox pista311;
    @FXML
    private HBox pista312;
    @FXML
    private HBox pista313;
    @FXML
    private HBox pista41;
    @FXML
    private HBox pista42;
    @FXML
    private HBox pista43;
    @FXML
    private HBox pista44;
    @FXML
    private HBox pista45;
    @FXML
    private HBox pista46;
    @FXML
    private HBox pista47;
    @FXML
    private HBox pista48;
    @FXML
    private HBox pista49;
    @FXML
    private HBox pista410;
    @FXML
    private HBox pista411;
    @FXML
    private HBox pista412;
    @FXML
    private HBox pista413;
    @FXML
    private HBox pista51;
    @FXML
    private HBox pista52;
    @FXML
    private HBox pista53;
    @FXML
    private HBox pista54;
    @FXML
    private HBox pista55;
    @FXML
    private HBox pista56;
    @FXML
    private HBox pista57;
    @FXML
    private HBox pista58;
    @FXML
    private HBox pista59;
    @FXML
    private HBox pista510;
    @FXML
    private HBox pista511;
    @FXML
    private HBox pista512;
    @FXML
    private HBox pista513;
    @FXML
    private HBox pista61;
    @FXML
    private HBox pista62;
    @FXML
    private HBox pista63;
    @FXML
    private HBox pista64;
    @FXML
    private HBox pista65;
    @FXML
    private HBox pista66;
    @FXML
    private HBox pista67;
    @FXML
    private HBox pista68;
    @FXML
    private HBox pista69;
    @FXML
    private HBox pista610;
    @FXML
    private HBox pista611;
    @FXML
    private HBox pista612;
    @FXML
    private HBox pista613;
    @FXML
    private HBox pista19;
    @FXML
    private Label texto12;
    @FXML
    private Label texto13;
    @FXML
    private Label texto14;
    @FXML
    private Label texto15;
    @FXML
    private Label texto16;
    @FXML
    private Label texto17;
    @FXML
    private Label texto18;
    @FXML
    private Label texto19;
    @FXML
    private Label texto110;
    @FXML
    private Label texto21;
    @FXML
    private Label texto22;
    @FXML
    private Label texto23;
    @FXML
    private Label texto24;
    @FXML
    private Label texto25;
    @FXML
    private Label texto26;
    @FXML
    private Label texto27;
    @FXML
    private Label texto28;
    @FXML
    private Label texto29;
    @FXML
    private Label texto210;
    @FXML
    private Label texto211;
    @FXML
    private Label texto212;
    @FXML
    private Label texto213;
    @FXML
    private Label texto31;
    @FXML
    private Label texto32;
    @FXML
    private Label texto33;
    @FXML
    private Label texto34;
    @FXML
    private Label texto35;
    @FXML
    private Label texto36;
    @FXML
    private Label texto37;
    @FXML
    private Label texto38;
    @FXML
    private Label texto39;
    @FXML
    private Label texto310;
    @FXML
    private Label texto311;
    @FXML
    private Label texto312;
    @FXML
    private Label texto313;
    @FXML
    private Label texto41;
    @FXML
    private Label texto42;
    @FXML
    private Label texto43;
    @FXML
    private Label texto44;
    @FXML
    private Label texto45;
    @FXML
    private Label texto46;
    @FXML
    private Label texto47;
    @FXML
    private Label texto48;
    @FXML
    private Label texto49;
    @FXML
    private Label texto410;
    @FXML
    private Label texto411;
    @FXML
    private Label texto412;
    @FXML
    private Label texto413;
    @FXML
    private Label texto51;
    @FXML
    private Label texto52;
    @FXML
    private Label texto53;
    @FXML
    private Label texto54;
    @FXML
    private Label texto55;
    @FXML
    private Label texto56;
    @FXML
    private Label texto57;
    @FXML
    private Label texto58;
    @FXML
    private Label texto59;
    @FXML
    private Label texto510;
    @FXML
    private Label texto511;
    @FXML
    private Label texto512;
    @FXML
    private Label texto513;
    @FXML
    private Label texto61;
    @FXML
    private Label texto62;
    @FXML
    private Label texto63;
    @FXML
    private Label texto64;
    @FXML
    private Label texto65;
    @FXML
    private Label texto66;
    @FXML
    private Label texto67;
    @FXML
    private Label texto68;
    @FXML
    private Label texto69;
    @FXML
    private Label texto610;
    @FXML
    private Label texto611;
    @FXML
    private Label texto612;
    @FXML
    private Label texto613;
    @FXML
    private BorderPane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Club club = Club.getInstance();
            List<Booking> reservas = club.getBookings();
            
            
            LocalDate today = LocalDate.now();
            List<Booking> reservasDeHoy = new ArrayList<>();
            for (Booking reserva : reservas) {
                LocalDate fechaDeLaReserva = reserva.getMadeForDay();
                if (today.equals(fechaDeLaReserva)) {
                  
                    reservasDeHoy.add(reserva);
                }
            }
            for (Booking reserva : reservasDeHoy) { colocarHora(reserva);}
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void cerrarVentana() {
        Stage stage = (Stage) grid.getScene().getWindow();
        stage.close();
    }

    private int extraerHora(LocalTime horaDeLaReserva) {
        String hora = horaDeLaReserva.toString();
        int pos = hora.indexOf(":");
        String horas = hora.substring(0, pos);
        return Integer.parseInt(horas);
    }

    public void colocarHora(Booking reserva) {
        int pista = Integer.parseInt(reserva.getCourt().getName().substring(reserva.getCourt().getName().length() - 1));
        int horaDeLaReserva = extraerHora(reserva.getFromTime());
        String nickname = "";
        switch (pista) {
            case 1:
                switch (horaDeLaReserva) {
                    case 9:
                        pista11.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto11.setText(nickname);
                        texto11.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 10:
                        pista12.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto12.setText(nickname);
                        texto12.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 11:
                        pista13.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto13.setText(nickname);
                        texto13.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 12:
                        pista14.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto14.setText(nickname);
                        texto14.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 13:
                        pista15.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto15.setText(nickname);
                        texto15.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 14:
                        pista16.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto16.setText(nickname);
                        texto16.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 15:
                        pista17.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto17.setText(nickname);
                        texto17.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 16:
                        pista18.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto18.setText(nickname);
                        texto18.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 17:
                        pista19.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto19.setText(nickname);
                        texto19.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 18:
                        pista110.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto110.setText(nickname);
                        texto110.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 19:
                        pista111.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto111.setText(nickname);
                        texto111.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 20:
                        pista112.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto112.setText(nickname);
                        texto112.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 21:
                        pista113.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto113.setText(nickname);
                        texto113.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                }
                break;
            case 2:
                switch (horaDeLaReserva) {
                    case 9:
                        pista21.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto21.setText(nickname);
                        texto21.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 10:
                        pista22.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto22.setText(nickname);
                        texto22.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 11:
                        pista23.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto23.setText(nickname);
                        texto23.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 12:
                        pista24.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto24.setText(nickname);
                        texto24.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 13:
                        pista25.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto25.setText(nickname);
                        texto25.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 14:
                        pista26.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto26.setText(nickname);
                        texto26.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 15:
                        pista27.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto27.setText(nickname);
                        texto27.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 16:
                        pista28.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto28.setText(nickname);
                        texto28.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 17:
                        pista29.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto29.setText(nickname);
                        texto29.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 18:
                        pista210.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto210.setText(nickname);
                        texto210.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 19:
                        pista211.setStyle("-fx-background-color:#006400; -fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto211.setText(nickname);
                        texto211.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 20:
                        pista212.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto212.setText(nickname);
                        texto212.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 21:
                        pista213.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto213.setText(nickname);
                        texto213.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                }
                break;
            case 3:
                switch (horaDeLaReserva) {
                    case 9:
                        pista31.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto31.setText(nickname);
                        texto31.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 10:
                        pista32.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto32.setText(nickname);
                        texto32.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 11:
                        pista33.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto33.setText(nickname);
                        texto33.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        // COMO 33?
                        break;
                    case 12:
                        pista34.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto34.setText(nickname);
                        texto34.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 13:
                        pista35.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto35.setText(nickname);
                        texto35.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 14:
                        pista36.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto36.setText(nickname);
                        texto36.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 15:
                        pista37.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto37.setText(nickname);
                        texto37.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 16:
                        pista38.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto38.setText(nickname);
                        texto38.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 17:
                        pista39.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto39.setText(nickname);
                        texto39.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 18:
                        pista310.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto310.setText(nickname);
                        texto310.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 19:
                        pista311.setStyle("-fx-background-color:#006400; -fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto311.setText(nickname);
                        texto311.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 20:
                        pista312.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto312.setText(nickname);
                        texto312.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 21:
                        pista313.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto313.setText(nickname);
                        texto313.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                }
                break;
            case 4:
                switch (horaDeLaReserva) {
                    case 9:
                        pista41.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto41.setText(nickname);
                        texto41.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 10:
                        pista42.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto42.setText(nickname);
                        texto42.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 11:
                        pista43.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto43.setText(nickname);
                        texto43.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 12:
                        pista44.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto44.setText(nickname);
                        texto44.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 13:
                        pista45.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto45.setText(nickname);
                        texto45.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 14:
                        pista46.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto46.setText(nickname);
                        texto46.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 15:
                        pista47.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto47.setText(nickname);
                        texto47.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 16:
                        pista48.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto48.setText(nickname);
                        texto48.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 17:
                        pista49.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto49.setText(nickname);
                        texto49.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 18:
                        pista410.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto410.setText(nickname);
                        texto410.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 19:
                        pista411.setStyle("-fx-background-color:#006400; -fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto411.setText(nickname);
                        texto411.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 20:
                        pista412.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto412.setText(nickname);
                        texto412.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 21:
                        pista413.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto413.setText(nickname);
                        texto413.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                }
                break;
            case 5:
                switch (horaDeLaReserva) {
                    case 9:
                        pista51.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto51.setText(nickname);
                        texto51.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 10:
                        pista52.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto52.setText(nickname);
                        texto52.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 11:
                        pista53.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto53.setText(nickname);
                        texto53.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 12:
                        pista54.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto54.setText(nickname);
                        texto54.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 13:
                        pista55.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto55.setText(nickname);
                        texto55.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 14:
                        pista56.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto56.setText(nickname);
                        texto56.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 15:
                        pista57.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto57.setText(nickname);
                        texto57.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 16:
                        pista58.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto58.setText(nickname);
                        texto58.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 17:
                        pista59.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto59.setText(nickname);
                        texto59.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 18:
                        pista510.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto510.setText(nickname);
                        texto510.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 19:
                        pista511.setStyle("-fx-background-color:#006400; -fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto511.setText(nickname);
                        texto511.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 20:
                        pista512.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto512.setText(nickname);
                        texto512.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 21:
                        pista513.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto513.setText(nickname);
                        texto513.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                }
                break;
            case 6:
                switch (horaDeLaReserva) {
                    case 9:
                        pista61.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto61.setText(nickname);
                        texto61.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 10:
                        pista62.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto62.setText(nickname);
                        texto62.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 11:
                        pista63.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto63.setText(nickname);
                        texto63.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 12:
                        pista64.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto64.setText(nickname);
                        texto64.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 13:
                        pista65.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto65.setText(nickname);
                        texto65.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 14:
                        pista66.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto66.setText(nickname);
                        texto66.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 15:
                        pista67.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto67.setText(nickname);
                        texto67.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 16:
                        pista68.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto68.setText(nickname);
                        texto68.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 17:
                        pista69.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto69.setText(nickname);
                        texto69.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 18:
                        pista610.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto610.setText(nickname);
                        texto610.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 19:
                        pista611.setStyle("-fx-background-color:#006400; -fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto611.setText(nickname);
                        texto611.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 20:
                        pista612.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto612.setText(nickname);
                        texto612.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                    case 21:
                        pista613.setStyle("-fx-background-color:#006400;-fx-border-color:white;");
                        nickname = reserva.getMember().getNickName();
                        texto613.setText(nickname);
                        texto613.setStyle("-fx-font: bold 8pt \"System\"; -fx-text-fill:white;");
                        break;
                }
                break;
        }
        pane.setStyle("-fx-background-color:white;");
    }
}
