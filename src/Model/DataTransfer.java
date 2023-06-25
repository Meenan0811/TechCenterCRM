package Model;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DataTransfer extends Repair{
    String oldDevice;

    public DataTransfer(int repairId, String device, int customerId, int partId, LocalDateTime createDate, String createdBy, LocalDateTime updateDate, String updateBy, LocalDate dueDate, String status, String assgnempl, String notes, String oldDevice, String type) {
        super(repairId, device, customerId, partId, createDate, createdBy, updateDate, updateBy, dueDate, status, assgnempl, notes, type);
        this.oldDevice = oldDevice;
    }

    public String getOldDevice() {
        return oldDevice;
    }

    public void setOldDevice(String oldDevice) {
        this.oldDevice = oldDevice;
    }
}
