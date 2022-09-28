package productOptimiser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class bomController implements Initializable {

    public Button exportBut;

    private static Stage stage;
    public TableColumn bomCompoCol;
    public TableColumn bomQtyCol;
    public TableColumn bomProporCol;
    public TableColumn bomCostCol;

    public TableView<bom> bomTable;
    public TableColumn<bom, String> compo;
    public TableColumn<bom, Double> qtyWeight;
    public TableColumn<bom, Integer> percent;
    public TableColumn<bom, Double> cost;


    public static String loadBOM(String title, String fxmlFile, Stage primaryStage) throws IOException {

        String bom = "";
        FXMLLoader loader = new FXMLLoader(popupController.class.getResource(fxmlFile));
        Scene newScene = new Scene(loader.load());
        Stage inputStage = new Stage();
        inputStage.initModality(Modality.WINDOW_MODAL);
        inputStage.setTitle(title);
        inputStage.initOwner(primaryStage);
        inputStage.setScene(newScene);
        inputStage.setResizable(false);
        stage = inputStage;
        stage.showAndWait();
        return bom;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bomCompoCol.setCellValueFactory(new PropertyValueFactory<>("compo"));
        bomQtyCol.setCellValueFactory(new PropertyValueFactory<>("qtyWeight"));
        bomProporCol.setCellValueFactory(new PropertyValueFactory<>("percent"));
        bomCostCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        bomTable.setItems(bomData);
    }

    private ObservableList<bom> bomData = FXCollections.observableArrayList(
            //for 10 bags
            new bom("CORGB01",60, 30, 0),
            new bom("CORRB01",50, 25, 0),
            new bom("OAKRB01",20, 10, 6),
            new bom("SPINB01",70, 35, 5)

            );
}
