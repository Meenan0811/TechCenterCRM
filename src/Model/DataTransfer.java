package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DataTransfer extends Repair{
    String oldDevice;

    public DataTransfer(int repairId, String device, int customerId, int partId, LocalDateTime createDate, String createdBy, LocalDateTime updateDate, String updateBy, LocalDate dueDate, String status, String assgnempl, String notes, String oldDevice) {
        super(repairId, device, customerId, partId, createDate, createdBy, updateDate, updateBy, dueDate, status, assgnempl, notes);
        this.oldDevice = oldDevice;
    }

    public DataTransfer(String device, String custName, String custPhone, LocalDateTime createDate, int repairId, LocalDate dueDate, String status, String assgnempl, String notes, String oldDevice, int customerId) {
        super(device, custName, custPhone, createDate, repairId, dueDate, status, assgnempl, notes, customerId);
        this.oldDevice = oldDevice;
    }

    public String getOldDevice() {
        return oldDevice;
    }

    public void setOldDevice(String oldDevice) {
        this.oldDevice = oldDevice;
    }
}
