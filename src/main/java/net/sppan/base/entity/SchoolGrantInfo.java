package net.sppan.base.entity;

import net.sppan.base.entity.support.BaseEntity;

import javax.persistence.Entity;

public class SchoolGrantInfo  {
    public SchoolGrantInfo(RelationOfSchoolAndNurse relationOfSchoolAndNurse){
        this.id=relationOfSchoolAndNurse.getId();
        this.busId=relationOfSchoolAndNurse.getBusId();
        this.driverId=relationOfSchoolAndNurse.getDriverId();
        this.nurseId=relationOfSchoolAndNurse.getNurseId();
        this.schoolId=relationOfSchoolAndNurse.getSchoolId();
    }

    private Integer id;
    private Integer schoolId;
    private  Integer nurseId;
    private Integer driverId;
    private  Integer busId;
    private  String driverName;
    private String busNumber;
    private String nurseName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getNurseId() {
        return nurseId;
    }

    public void setNurseId(Integer nurseId) {
        this.nurseId = nurseId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    @Override
    public String toString() {
        return "SchoolGrantInfo{" +
                "id=" + id +
                ", schoolId=" + schoolId +
                ", nurseId=" + nurseId +
                ", driverId=" + driverId +
                ", busId=" + busId +
                ", driverName='" + driverName + '\'' +
                ", busNumber='" + busNumber + '\'' +
                ", nurseName='" + nurseName + '\'' +
                '}';
    }
}
