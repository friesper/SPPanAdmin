package net.sppan.base.entity;

import net.sppan.base.entity.support.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tb_nurse")
public class Nurse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private String phone;
    @Column(name = "work_unit_id")
    private Integer workUnitId;
    @Column(name = "work_unit_name")
    private String workUnitName;
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

    @Override
    public String toString() {
        return "nurse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
