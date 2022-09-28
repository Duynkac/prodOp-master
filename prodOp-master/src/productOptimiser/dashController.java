package productOptimiser;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import static javafx.collections.FXCollections.observableArrayList;

public class dashController implements Initializable {

    public Button addBut;
    public Button editBut;
    public Button removeBut;
    public Button saveStockBut;
    public Button addLotBut;
    public Button saveConstrBut;

    public ObservableList<Product> products;
    public ObservableList<String> lotInfos;
    public ObservableList<Stock> stockData;
    public ObservableList<Constraint> constrData;
    public Product currentProd;

    public ListView<String> prodList;
    public ListView<String> lotList;

    public ChoiceBox compoChoice;
    public DatePicker expiryDate;
    public TextField onHandText;
    public TextField lotText;
    public TextField amountText;
    public TextField providerText;
    public TextField costText;

    public TableView stockTable;
    public TableColumn numStockCol;
    public TableColumn idStockCol;
    public TableColumn groupStockCol;
    public TableColumn onHandCol;
    public TableColumn providerCol;
    public TableColumn costCol;

    public ChoiceBox constrChoice;
    public Spinner minSpin;
    public Spinner maxSpin;
    public Spinner rankSpin;
    public Button processBut;
    public Spinner minCostSpin;
    public Spinner maxCostSpin;
    public Spinner minQtySpin;
    public Spinner maxQtySpin;

    public TableView constrTable;
    public TableColumn numConstrCol;
    public TableColumn idConstrCol;
    public TableColumn groupConstrCol;
    public TableColumn minPercentCol;
    public TableColumn maxPercentCol;
    public TableColumn minCostCol;
    public TableColumn maxCostCol;
    public TableColumn minQtyCol;
    public TableColumn maxQtyCol;
    public TableColumn rankCol;

    public TextField demandText;

    public void process(ActionEvent actionEvent) throws IOException {
        String test = bomController.loadBOM("Bill of Materials", "bom.fxml", Main.getPrimaryStage());
    }

    public void prodAdd(ActionEvent actionEvent) throws IOException {
        Product product = popupController.loadScene("Product Information", "prodPop.fxml", Main.getPrimaryStage());
        products.add(product);
        if (!product.getProdName().isBlank()) {
            prodList.getItems().add(product.getProdName());
        }
    }

    public void prodRemove(ActionEvent actionEvent) {

    }

    public void prodEdit(ActionEvent actionEvent) throws IOException {
    }

    public void addLot(ActionEvent actionEvent) {
        String lotInfo = "Lot " + lotText.getText() + " - " + amountText.getText() + " kg, expires on " + expiryDate.getValue();
        lotInfos.add(lotInfo);
        lotList.setItems(lotInfos);
    }

    public void saveStock(ActionEvent actionEvent) {
        int index = compoChoice.getSelectionModel().getSelectedIndex();
        stockData.get(index).setOnHand(Double.valueOf(onHandText.getText()));
        stockData.get(index).setAtProvider(Double.valueOf(providerText.getText()));
        stockData.get(index).setCost((Double.valueOf(costText.getText())));
        stockTable.setItems(stockData);
        stockTable.refresh();
        currentProd.setStockData(stockData);
    }

    public void saveConstraint(ActionEvent actionEvent) {
        int index = constrChoice.getSelectionModel().getSelectedIndex();
        int minPercent = (Integer) minSpin.getValue();
        int maxPercent = (Integer) maxSpin.getValue();
        double minCost = (Double) minCostSpin.getValue();
        double maxCost = (Double) maxCostSpin.getValue();
        int minQty = (Integer) minQtySpin.getValue();
        int maxQty = (Integer) maxQtySpin.getValue();
        int rank = (Integer) rankSpin.getValue();
        constrData.get(index).setMinPercent(minPercent);
        constrData.get(index).setMaxPercent(maxPercent);
        constrData.get(index).setMinCost(minCost);
        constrData.get(index).setMaxCost(maxCost);
        constrData.get(index).setMinQty(minQty);
        constrData.get(index).setMaxQty(maxQty);
        constrData.get(index).setRank(rank);
        constrTable.setItems(constrData);
        constrTable.refresh();
        currentProd.setConstrData(constrData);
    }

    public void clearStock() {
        onHandText.clear();
        lotText.clear();
        amountText.clear();
        providerText.clear();
        costText.clear();
        expiryDate.getEditor().clear();
        lotList.refresh();
    }

    public void disable() {
        onHandText.setDisable(true);
        lotText.setDisable(true);
        amountText.setDisable(true);
        providerText.setDisable(true);
        costText.setDisable(true);
        saveStockBut.setDisable(true);
        expiryDate.setDisable(true);
        addLotBut.setDisable(true);
        lotList.setDisable(true);
        minSpin.setDisable(true);
        maxSpin.setDisable(true);
        saveConstrBut.setDisable(true);
        minCostSpin.setDisable(true);
        maxCostSpin.setDisable(true);
        minQtySpin.setDisable(true);
        maxQtySpin.setDisable(true);
        rankSpin.setDisable(true);
    }

    public void enable() {
        processBut.setDisable(false);
    }

    //format cell of Stock table
    public Callback<TableColumn<Stock, Double>, TableCell<Stock, Double>> getCustomCellFactory(final String unit) {
        return new Callback<TableColumn<Stock, Double>, TableCell<Stock, Double>>() {
            @Override
            public TableCell<Stock, Double> call(TableColumn<Stock, Double> param) {
                TableCell<Stock, Double> cell = new TableCell<Stock, Double>() {
                    @Override
                    public void updateItem(final Double item, boolean empty) {
                        if (item != null) {
                            setText(String.valueOf(item) + " " + unit);
                        }
                    }
                };
                return cell;
            }
        };
    }

    //format cell of Constraint table
    public Callback<TableColumn<Constraint, Integer>, TableCell<Constraint, Integer>> getCustomCellFactory2(final String unit) {
        return new Callback<TableColumn<Constraint, Integer>, TableCell<Constraint, Integer>>() {
            @Override
            public TableCell<Constraint, Integer> call(TableColumn<Constraint, Integer> param) {
                TableCell<Constraint, Integer> cell = new TableCell<Constraint, Integer>() {
                    @Override
                    public void updateItem(final Integer item, boolean empty) {
                        if (item != null) {
                            setText(String.valueOf(item) + " " + unit);
                        }
                    }
                };
                return cell;
            }
        };
    }

    //set the value of each column in the tables
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        products = FXCollections.observableArrayList();
        lotInfos = FXCollections.observableArrayList();

        numStockCol.setCellValueFactory(new PropertyValueFactory<Stock,Integer>("num"));
        idStockCol.setCellValueFactory(new PropertyValueFactory<Stock, String>("iD"));
        groupStockCol.setCellValueFactory((new PropertyValueFactory<Stock, String>("group")));
        onHandCol.setCellValueFactory(new PropertyValueFactory<Stock, String>("onHand"));
        onHandCol.setCellFactory(getCustomCellFactory("kg"));
        providerCol.setCellValueFactory(new PropertyValueFactory<Stock, String>("atProvider"));
        providerCol.setCellFactory(getCustomCellFactory("kg"));
        costCol.setCellValueFactory(new PropertyValueFactory<Stock, String>("cost"));
        costCol.setCellFactory(getCustomCellFactory("/ kg"));

        minSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        maxSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        minCostSpin.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 1000.0));
        maxCostSpin.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 1000.0));
        minQtySpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
        maxQtySpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
        rankSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));

        numConstrCol.setCellValueFactory(new PropertyValueFactory<Constraint,Integer>("num"));
        idConstrCol.setCellValueFactory(new PropertyValueFactory<Constraint,Integer>("iD"));
        groupConstrCol.setCellValueFactory(new PropertyValueFactory<Constraint,Integer>("group"));
        minPercentCol.setCellValueFactory(new PropertyValueFactory<Constraint,Integer>("minPercent"));
        minPercentCol.setCellFactory(getCustomCellFactory2("%"));
        maxPercentCol.setCellValueFactory(new PropertyValueFactory<Constraint,Integer>("maxPercent"));
        maxPercentCol.setCellFactory(getCustomCellFactory2("%"));
        minCostCol.setCellValueFactory(new PropertyValueFactory<Constraint,String>("minCost"));
        maxCostCol.setCellValueFactory(new PropertyValueFactory<Constraint,String>("maxCost"));
        minQtyCol.setCellValueFactory(new PropertyValueFactory<Constraint,Integer>("minQty"));
        maxQtyCol.setCellValueFactory(new PropertyValueFactory<Constraint,Integer>("maxQty"));
        rankCol.setCellValueFactory(new PropertyValueFactory<Constraint,Integer>("rank"));

        //events when a product is selected in the listview
        prodList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                clearStock();
                disable();
                compoChoice.setDisable(false);
                stockTable.setDisable(false);
                constrChoice.setDisable(false);
                constrTable.setDisable(false);
                int index = prodList.getSelectionModel().getSelectedIndex();
                currentProd = products.get(index);
                stockData = currentProd.getStockData();
                constrData = currentProd.getConstrData();
                compoChoice.setItems(currentProd.getCompoNames());
                constrChoice.setItems(currentProd.getCompoNames());
                stockTable.refresh();
                stockTable.setItems(stockData);
                constrTable.refresh();
                constrTable.setItems(constrData);
            }
        });

        //events when we change components
        compoChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!compoChoice.getSelectionModel().isEmpty()) {
                    onHandText.setDisable(false);
                    lotText.setDisable(false);
                    amountText.setDisable(false);
                    providerText.setDisable(false);
                    costText.setDisable(false);
                    saveStockBut.setDisable(false);
                    expiryDate.setDisable(false);
                    addLotBut.setDisable(false);
                    lotList.setDisable(false);
                    clearStock();
                }
            }
        });
        //events when we change components
        constrChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!constrChoice.getSelectionModel().isEmpty()) {
                    minSpin.setDisable(false);
                    maxSpin.setDisable(false);
                    saveConstrBut.setDisable(false);
                    minCostSpin.setDisable(false);
                    maxCostSpin.setDisable(false);
                    minQtySpin.setDisable(false);
                    maxQtySpin.setDisable(false);
                    rankSpin.setDisable(false);
                }
            }
        });

        //format the text field to number only
        UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {

                if (t.isReplaced())
                    if(t.getText().matches("[^0-9]"))
                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));

                if (t.isAdded()) {
                    if (t.getControlText().contains(".")) {
                        if (t.getText().matches("[^0-9]")) {
                            t.setText("");
                        }
                    } else if (t.getText().matches("[^0-9.]")) {
                        t.setText("");
                    }
                }
                return t;
            }
        };
        costText.setTextFormatter(new TextFormatter<>(filter));
        onHandText.setTextFormatter(new TextFormatter<>(filter));
        providerText.setTextFormatter(new TextFormatter<>(filter));
        lotText.setTextFormatter(new TextFormatter<>(filter));
        amountText.setTextFormatter(new TextFormatter<>(filter));
        demandText.setTextFormatter(new TextFormatter<>(filter));
    }
}
