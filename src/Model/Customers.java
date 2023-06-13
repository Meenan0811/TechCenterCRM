package Model;

import java.time.LocalDateTime;

public class Customers {

    private int custId;
    private String custName;
    private String custPhone;
    private String custStreet;
    private String custCity;
    private String custState;
    private int custZip;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime updateDate;
    private String updateBy;

    public Customers(int custId, String custName, String custPhone, String custStreet, String custCity, String custState, int custZip, LocalDateTime createDate, String createdBy, LocalDateTime updateDate, String updateBy) {
        this.custId = custId;
        this.custName = custName;
        this.custPhone = custPhone;
        this.custStreet = custStreet;
        this.custCity = custCity;
        this.custState = custState;
        this.custZip = custZip;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
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

    public String getCustStreet() {
        return custStreet;
    }

    public void setCustStreet(String custStreet) {
        this.custStreet = custStreet;
    }

    public String getCustCity() {
        return custCity;
    }

    public void setCustCity(String custCity) {
        this.custCity = custCity;
    }

    public String getCustState() {
        return custState;
    }

    public void setCustState(String custState) {
        this.custState = custState;
    }

    public int getCustZip() {
        return custZip;
    }

    public void setCustZip(int custZip) {
        this.custZip = custZip;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
