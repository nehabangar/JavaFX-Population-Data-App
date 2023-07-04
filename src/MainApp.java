import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Population Data");
        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

        // When the application starts, it directly shows the BarChartScene
        showBarChartScene();
    }

    // Method to show the TableView Scene
    public void showTableScene() {
        try {
            // Load the TableScene.fxml file using FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TableScene.fxml"));
            Parent root = loader.load();

            // Get the controller associated with the TableScene
            TableSceneController controller = loader.getController();
            controller.setMainApp(this);

            // Create a new Scene with the root (loaded from FXML) and set it in the primaryStage
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to show the BarChartView Scene
    public void showBarChartScene() {
        try {
            // Load the BarChartScene.fxml file using FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BarChartScene.fxml"));
            Parent root = loader.load();

            // Get the controller associated with the BarChartScene
            BarChartSceneController controller = loader.getController();
            controller.setMainApp(this);

            // Create a new Scene with the root (loaded from FXML) and set it in the primaryStage
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
