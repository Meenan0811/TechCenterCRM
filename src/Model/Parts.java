package Model;

public class Parts {

    private int partID;
    private String partName;
    private int qty;

    public Parts(int partID, String partName, int qty) {
        this.partID = partID;
        this.partName = partName;
        this.qty = qty;
    }

    public int getPartID() { return partID; }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
