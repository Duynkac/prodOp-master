package productOptimiser;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Stock extends Component{

    public Double onHand;
    public Double atProvider;
    public Double cost;

    public Stock(Integer num, String iD, String group, Double onHand, Double atProvider, Double cost ) {
        super(num, iD, group);
        this.onHand = onHand;
        this.atProvider = atProvider;
        this.cost = cost;
    }

    public Double getOnHand() {
        return this.onHand;
    }

    public void setOnHand(Double onHand) {
        this.onHand = onHand;
    }

    public Double getAtProvider() {
        return this.atProvider;
    }

    public void setAtProvider(Double atProvider) {
        this.atProvider = atProvider;
    }

    public Double getCost() {
        return this.cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
