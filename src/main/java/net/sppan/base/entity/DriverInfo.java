package net.sppan.base.entity;

import javax.persistence.Entity;
import java.io.File;
public class DriverInfo {
    private Integer id;
    private String name;
    private String phone;
    private Integer busId;
    private String busNumber;
    private String  driverImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getDriverImage() {
        return driverImage;
    }

    public void setDriverImage(String driverImage) {
        this.driverImage = driverImage;
    }


    public Integer getWorkUnitId() {
        return workUnitId;
    }

    public void setWorkUnitId(Integer workUnitId) {
        this.workUnitId = workUnitId;
    }

    public String getWorkUnitName() {
        return workUnitName;
    }

    public void setWorkUnitName(String workUnitName) {
        this.workUnitName = workUnitName;
    }

    private Integer workUnitId;
    private String workUnitName;

    @Override
    public String toString() {
        return "DriverInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", busId=" + busId +
                ", busNumber='" + busNumber + '\'' +
                ", driverImage='" + driverImage + '\'' +
                ", workUnitId=" + workUnitId +
                ", workUnitName='" + workUnitName + '\'' +
                '}';
    }
}
