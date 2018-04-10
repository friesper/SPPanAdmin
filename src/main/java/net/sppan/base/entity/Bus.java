package net.sppan.base.entity;


import net.sppan.base.entity.support.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tb_bus")
public class Bus extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "number")
    private String  number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
