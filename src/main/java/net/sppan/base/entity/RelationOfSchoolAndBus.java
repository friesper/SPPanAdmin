package net.sppan.base.entity;

import net.sppan.base.entity.support.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tb_school_bus")
public class RelationOfSchoolAndBus extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "school_id")
    private Integer schoolId;
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

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }
}
