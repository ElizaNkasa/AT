import interfa;
import scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ConnexionController {

    @FXML
    private Button clientButton, pharmacienButton, quitterButton;

    @FXML
    private void initialize() {
        clientButton.setOnAction(e -> changerScene("Client.fxml"));
        pharmacienButton.setOnAction(e -> changerScene("Pharmacien.fxml"));
        quitterButton.setOnAction(e -> System.exit(0));
    }

    private void changerScene(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/" + fxml));
            Stage stage = (Stage) clientButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
