import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BarChartSceneController {

    @FXML
    private RadioButton radioButton2022;

    @FXML
    private RadioButton radioButton2023;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private ToggleGroup toggleGroup;

    private Connection connection;

    private MainApp mainApp; // Reference to the MainApp

    public void initialize() {
        toggleGroup = new ToggleGroup();
        radioButton2022.setToggleGroup(toggleGroup);
        radioButton2023.setToggleGroup(toggleGroup);

        radioButton2022.setSelected(true);

        try {
            // Connect to the database
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/population_comp", "Neha", "Neha@123");

            // Load data for default year (2022)
            loadData("2022");

            // Add listener to toggle group to change data when year changes
            toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                String selectedYear = selectedRadioButton.getText();
                loadData(selectedYear);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to load data into the bar chart for a specific year
    private void loadData(String year) {
        String query = "SELECT country, `Population_" + year + "` FROM population_table";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            barChart.getData().clear();
            BarChart.Series<String, Number> series = new BarChart.Series<>();
            while (resultSet.next()) {
                String country = resultSet.getString("country");
                int population = resultSet.getInt("Population_" + year);
                series.getData().add(new BarChart.Data<>(country, population));
            }
            barChart.getData().add(series);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void showTableScene() {
        mainApp.showTableScene(); // Use the stored reference to MainApp to call the method
    }
}
