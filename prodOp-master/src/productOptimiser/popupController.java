package productOptimiser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class popupController implements Initializable{

    public static ObservableList<Component> components = FXCollections.observableArrayList();
    public static Product product;

    public Button saveBut;
    public Button cancelBut;
    public Button addBut;

    public TableView compoTable;
    public TableColumn numColumn;
    public TableColumn idColumn;
    public TableColumn groupColumn;

    public TextField compoID;
    public TextField compoGroup;

    public TextField prodName;

    private static Stage stage;

    public static Product loadScene(String title, String fxmlFile, Stage primaryStage) throws IOException {

        product = new Product("", components);
        components = FXCollections.observableArrayList();
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
        return product;
    }

    public void popupSave(ActionEvent actionEvent) {
        product.setProdName(prodName.getText());
        product.setProductCompos(components);
        stage = (Stage) saveBut.getScene().getWindow();
        stage.close();
    }

    public void popupCancel(ActionEvent actionEvent) {
        stage = (Stage) cancelBut.getScene().getWindow();
        stage.close();
    }

    public void addComponent(ActionEvent actionEvent) {
        if (!compoID.getText().isBlank()) {
            components.add(new Component(components.size() + 1,compoID.getText(),compoGroup.getText()));
            compoID.clear();
            compoGroup.clear();
            compoTable.setItems(components);
        }
    }

    public void editCompoID(TableColumn.CellEditEvent<Component, String> editEvent) {
        ((Component) editEvent.getTableView().getItems().get(
                editEvent.getTablePosition().getRow())
                ).setID(editEvent.getNewValue());
    }

    public void editCompoGroup(TableColumn.CellEditEvent<Component, String> editEvent) {
        ((Component) editEvent.getTableView().getItems().get(
                editEvent.getTablePosition().getRow())
        ).setGroup(editEvent.getNewValue());
    }

    public void checkText(ActionEvent actionEvent) {
        if (!prodName.getText().isBlank()){
            saveBut.setDisable(false);
            addBut.setDisable(false);
            compoID.setDisable(false);
            compoGroup.setDisable(false);
            compoTable.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numColumn.setCellValueFactory(new PropertyValueFactory<Component,Integer>("num"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Component, String>("iD"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        groupColumn.setCellValueFactory((new PropertyValueFactory<Component, String>("group")));
        groupColumn.setCellFactory((TextFieldTableCell.forTableColumn()));
    }
}
