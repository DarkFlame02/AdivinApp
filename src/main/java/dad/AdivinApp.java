package dad;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

public class AdivinApp extends Application {

    private Label numeroLabel;
    private TextField numeroText;
    private Button numeroButton;
    Random rd = new Random();
    int numeroAleatorio = rd.nextInt(100) + 1;
    int contador = 0;
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
    Alert warningAlert = new Alert(Alert.AlertType.WARNING);

    @Override
    public void start(Stage stage) throws Exception {

        numeroLabel = new Label();
        numeroLabel.setText("Introduce un numero del 1 al 100");

        numeroText = new TextField();
        numeroText.setPrefColumnCount(15);

        numeroButton = new Button();
        numeroButton.setText("Comprobar");
        numeroButton.setDefaultButton(true);
        numeroButton.setOnAction(this::onComprobar);

        HBox numeroLabelBox = new HBox();
        numeroLabelBox.setSpacing(7);
        numeroLabelBox.setFillHeight(false);
        numeroLabelBox.setAlignment(Pos.CENTER);
        numeroLabelBox.getChildren().addAll(numeroLabel);

        HBox numeroTextBox = new HBox();
        numeroTextBox.setSpacing(7);
        numeroTextBox.setFillHeight(false);
        numeroTextBox.setAlignment(Pos.CENTER);
        numeroTextBox.getChildren().addAll(numeroText);

        VBox root = new VBox();
        root.setSpacing(7);
        root.setFillWidth(false);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(numeroLabelBox,numeroTextBox,numeroButton);

        Scene scene = new Scene(root, 640, 480);

        stage.setTitle("Recogida de datos");
        stage.setScene(scene);
        stage.show();

    }

    private void onComprobar(ActionEvent e) {
        if (!numeroText.getText().matches("^\\d+$")){
            errorAlert.setTitle("AdivinApp");
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("El numero introducido no es valido");
            errorAlert.showAndWait();
        } else {
            int numero = Integer.valueOf(numeroText.getText());

            if (numero > 100 ) {
                errorAlert.setTitle("AdivinApp");
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("El numero introducido no es valido");
                errorAlert.showAndWait();
            } else if (numero == numeroAleatorio) {
                winAlert.setTitle("AdivinApp");
                winAlert.setHeaderText("Has ganado!");
                winAlert.setContentText("Solo has necesitado " + contador + " intentos");
                winAlert.showAndWait();
                contador = 1;
                numeroAleatorio = rd.nextInt(100) + 1;
            } else if (numero < numeroAleatorio) {
                warningAlert.setTitle("AdivinApp");
                warningAlert.setHeaderText("Has fallado!");
                warningAlert.setContentText("El numero es mayor que " + numero);
                warningAlert.showAndWait();
                contador++;
            } else if (numero > numeroAleatorio){
                warningAlert.setTitle("AdivinApp");
                warningAlert.setHeaderText("Has fallado!");
                warningAlert.setContentText("El numero es menor que " + numero);
                warningAlert.showAndWait();
                contador++;
            } else {
                errorAlert.setTitle("AdivinApp");
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("El numero introducido no es valido");
                errorAlert.showAndWait();
            }
        }

        numeroText.setText("");

    }

}
