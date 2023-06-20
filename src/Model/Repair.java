package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Repair {
    private int repairId;
    private String device;
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
    private String status;
    private String assgnempl;

    public Repair(int repairId, String device, int customerId, int partId, LocalDateTime createDate, String createdBy, LocalDateTime updateDate, String updateBy, LocalDateTime dueDate, String status, String assgnempl, String notes) {
        this.repairId = repairId;
        this.device = device;
        this.customerId = customerId;
        this.partId = partId;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
        this.dueDate = dueDate;
        this.status = status;
        this.assgnempl = assgnempl;
        this.notes = notes;
    }



    public Repair(String device, String custName, String custPhone, LocalDateTime createDate, int repairId, LocalDateTime dueDate, String status, String assgnempl, String notes ){
        this.device = device;
        this.custName = custName;
        this.custPhone = custPhone;
        this.createDate = createDate;
        this.repairId = repairId;
        this.dueDate = dueDate;
        this.status = status;
        this.assgnempl = assgnempl;
        this.notes = notes;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
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

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssgnempl() {
        return assgnempl;
    }

    public void setAssgnempl(String assgnempl) {
        this.assgnempl = assgnempl;
    }
}
