package net.sppan.base.entity;


import net.sppan.base.entity.support.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="tb_school_driver")
public class RlationOFSD extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "school_id")
    private Integer schoolId;
    @Column(name="driver_id")
    private Integer driverId;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RlationOFSD{" +
                "schoolId=" + schoolId +
                ", driverId=" + driverId +
                '}';
    }
}
