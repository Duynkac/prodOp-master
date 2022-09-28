package productOptimiser;

public class Constraint extends Component {

    public Integer minPercent;
    public Integer maxPercent;
    public Double minCost;
    public Double maxCost;
    public Integer minQty;
    public Integer maxQty;
    public Integer rank;


    public Constraint(Integer num, String iD, String group, Integer minPercent, Integer maxPercent, Double minCost,
                      Double maxCost, Integer minQty, Integer maxQty, Integer rank) {
        super(num, iD, group);
        this.minPercent = minPercent;
        this.maxPercent = maxPercent;
        this.minCost = minCost;
        this.maxCost = maxCost;
        this.minQty = minQty;
        this.maxQty = maxQty;
        this.rank = rank;
    }

    public Integer getMinPercent() {
        return this.minPercent;
    }

    public void setMinPercent(Integer minPercent) {
        this.minPercent = minPercent;
    }

    public Integer getMaxPercent() {
        return this.maxPercent;
    }

    public void setMaxPercent(Integer maxPercent) {
        this.maxPercent = maxPercent;
    }

    public Double getMinCost() {
        return this.minCost;
    }

    public void setMinCost(Double minCost) {
        this.minCost = minCost;
    }

    public Double getMaxCost() {
        return this.maxCost;
    }

    public void setMaxCost(Double maxCost) {
        this.maxCost = maxCost;
    }

    public Integer getMinQty() {
        return this.minQty;
    }

    public void setMinQty(Integer minQty) {
        this.minQty = minQty;
    }

    public Integer getMaxQty() {
        return this.maxQty;
    }

    public void setMaxQty(Integer maxQty) {
        this.maxQty = maxQty;
    }

    public Integer getRank() {
        return this.rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
