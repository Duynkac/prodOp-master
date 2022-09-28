package productOptimiser;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.awt.*;

public  class Component {
    public SimpleIntegerProperty compoNum;
    public SimpleStringProperty compoID;
    public SimpleStringProperty compoGroup;

    public Component(Integer num, String iD, String group) {
        this.compoNum = new SimpleIntegerProperty(num);
        this.compoID = new SimpleStringProperty(iD);
        this.compoGroup = new SimpleStringProperty(group);
    }

    public Integer getNum() {
        return compoNum.get();
    }

    public void setNum(Integer num) {
        compoNum.set(num);
    }

    public String getID() {
        return compoID.get();
    }
    public void setID(String iD) {
        compoID.set(iD);
    }

    public String getGroup() {
        return compoGroup.get();
    }
    public void setGroup(String group) {
        compoGroup.set(group);
    }

}
