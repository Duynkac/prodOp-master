package productOptimiser;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    public SimpleStringProperty productName;
    public ObservableList<Component> productCompos;
    public ObservableList<Stock> stockData;
    public ObservableList<Constraint> constrData;

    public Product(String name, ObservableList<Component> compos){
        this.productName = new SimpleStringProperty(name);
        this.productCompos = compos;
        this.stockData = FXCollections.observableArrayList();
        this.constrData = FXCollections.observableArrayList();
    }

    public String getProdName() {
        return productName.get();
    }

    public void setProdName(String name) {
        productName.set(name);
    }

    public ObservableList<Component> getProductCompos() {
        return this.productCompos;
    }

    public void setProductCompos(ObservableList<Component> list) {
        productCompos = list;
        for (Component c : productCompos) {
            stockData.add(new Stock(c.getNum(), c.getID(), c.getGroup(), 0.0, 0.0, 0.0));
        }
        for (Component c : productCompos) {
            constrData.add(new Constraint(c.getNum(), c.getID(), c.getGroup(), 0, 0, 0.0, 0.0, 0, 0, 0));
        }
    }

    public ObservableList<String> getCompoNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        for (Component productCompo : productCompos) {
            names.add(productCompo.getID());
        }
        return names;
    }

    public ObservableList<Stock> getStockData() {
        return this.stockData;
    }

    public void setStockData(ObservableList<Stock> data) {
        stockData = data;
    }

    public ObservableList<Constraint> getConstrData() {return  this.constrData;}

    public void setConstrData(ObservableList<Constraint> data) { constrData = data;}
}
