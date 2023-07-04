import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TableSceneController {

    @FXML
    private TableView<PopulationData> tableView;

    @FXML
    private TableColumn<PopulationData, String> countryColumn;

    @FXML
    private TableColumn<PopulationData, String> regionColumn;

    @FXML
    private TableColumn<PopulationData, String> subregionColumn;

    @FXML
    private TableColumn<PopulationData, Integer> population2022Column;

    @FXML
    private TableColumn<PopulationData, Integer> population2023Column;

    @FXML
    private TableColumn<PopulationData, String> changeColumn;

    private Connection connection;
    private MainApp mainApp;

    public void initialize() {
        try {
            // Connect to the database
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/population_comp", "Neha", "Neha@123");

            // Fetch table data and populate TableView
            populateTableView();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void populateTableView() {
        String query = "SELECT * FROM population_table";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                // Retrieve data from the result set
                String country = resultSet.getString("country");
                String region = resultSet.getString("UN_Continental_region");
                String subregion = resultSet.getString("UN_statistical_subregion");
                int population2022 = resultSet.getInt("Population_2022");
                int population2023 = resultSet.getInt("Population_2023");
                String change = resultSet.getString("Population_Change");

                // Create a PopulationData object with the retrieved data
                PopulationData populationData = new PopulationData(country, region, subregion,
                        population2022, population2023, change);

                // Add the PopulationData object to the TableView
                tableView.getItems().add(populationData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showBarChartScene() {
        mainApp.showBarChartScene(); // Use the stored reference to MainApp to call the method
    }
}
