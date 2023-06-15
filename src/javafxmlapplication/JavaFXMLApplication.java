/*

TRABAJO IPC REAlIZADO POR JORDI PUIG, ALEJANDRO ISERTE Y JERONI ALTUR. :)


*/
package javafxmlapplication;

import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Booking;
import model.Club;

public class JavaFXMLApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("MisReservas.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("CreadorReserva.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Green Ball - Tennis Club");
        Image icono = new Image("\\resources\\icono.png");
        stage.getIcons().add(icono);
        try {
            Club club = Club.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
