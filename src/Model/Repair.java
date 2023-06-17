package Model;

public class Repair {
    private int repairId;
    private int productId;
    private int customerId;
    private int partId;
    private String notes;

    public Repair(int repairId, int productId, int customerId, int partId, String notes) {
        this.repairId = repairId;
        this.productId = productId;
        this.customerId = customerId;
        this.partId = partId;
        this.notes = notes;
    }
}
