package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Repair {
    private int repairId;
    private int productId;
    private int customerId;
    private int partId;
    private String notes;
    private String custName;
    private String custPhone;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime dueDate;
    private String createdBy;
    private String updateBy;

    public Repair(int repairId, int productId, int customerId, int partId, LocalDateTime createDate, String createdBy, LocalDateTime updateDate, String updateBy, LocalDateTime dueDate, String notes) {
        this.repairId = repairId;
        this.productId = productId;
        this.customerId = customerId;
        this.partId = partId;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
        this.dueDate = dueDate;
        this.notes = notes;
    }

    public Repair(String custName, String custPhone, LocalDateTime createDate, int repairId, String notes){
        this.custName = custName;
        this.custPhone = custPhone;
        this.createDate = createDate;
        this.repairId = repairId;
        this.notes = notes;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }


}
