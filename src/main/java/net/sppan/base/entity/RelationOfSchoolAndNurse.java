package net.sppan.base.entity;

import net.sppan.base.entity.support.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tb_school_nurse_driver_bus")
public class RelationOfSchoolAndNurse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "school_id")
    private Integer schoolId;
    @Column(name = "nurse_id")
    private  Integer nurseId;
    @Column(name = "driver_id")
    private Integer driverId;
    @Column(name = "bus_id")
    private  Integer busId;

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

    @Override
    public String toString() {
        return "RelationOfSchoolAndNurse{" +
                "id=" + id +
                ", schoolId=" + schoolId +
                ", nurseId=" + nurseId +
                ", driverId=" + driverId +
                ", busId=" + busId +
                '}';
    }
}
