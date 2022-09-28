package productOptimiser;

public class bom {

    public String compo;
    public double qtyWeight;
    public Integer percent;
    public double cost;


    public bom(String compo, double qtyWeight, Integer percent, double cost) {
        //super(num, iD, group);
        this.compo = compo;
        this.qtyWeight = qtyWeight;
        this.percent = percent;
        this.cost = cost;
    }

    public String getCompo() {
        return this.compo;
    }

    public void setCompo(String compo) {
        this.compo = compo;
    }

    public Double getQtyWeight() {
        return this.qtyWeight;
    }

    public void setQtyWeight(Double qtyWeight) {
        this.qtyWeight = qtyWeight;
    }

    public Integer getPercent() {
        return this.percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Double getCost() {
        return this.cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
