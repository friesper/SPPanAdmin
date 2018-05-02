package net.sppan.base.entity;

public class ReqBus {
    private Integer id;
    private String  number;
    private Integer schoolId;

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

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "ReqBus{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", schoolId=" + schoolId +
                '}';
    }
}
