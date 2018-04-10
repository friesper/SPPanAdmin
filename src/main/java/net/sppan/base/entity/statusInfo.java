package net.sppan.base.entity;


import java.util.Date;

public class statusInfo {


        private Integer id;
        private Integer busId;
        private Integer driverId;
        private Integer nurseId;
        private String nurseName;
        private String driverName;
        private String busNumber;
        private Date takeTime;
        private String studentName;
        private String studentPhone;
        private Integer timeQuantum;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getBusId() {
            return busId;
        }

        public void setBusId(Integer busId) {
            this.busId = busId;
        }

        public Integer getDriverId() {
            return driverId;
        }

        public void setDriverId(Integer driverId) {
            this.driverId = driverId;
        }

        public Integer getNurseId() {
            return nurseId;
        }

        public void setNurseId(Integer nurseId) {
            this.nurseId = nurseId;
        }

        public String getNurseName() {
            return nurseName;
        }

        public void setNurseName(String nurseName) {
            this.nurseName = nurseName;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public Date getTakeTime() {
            return takeTime;
        }

        public void setTakeTime(Date takeTime) {
            this.takeTime = takeTime;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getStudentPhone() {
            return studentPhone;
        }

        public void setStudentPhone(String studentPhone) {
            this.studentPhone = studentPhone;
        }

        public Integer getTimeQuantum() {
            return timeQuantum;
        }

        public void setTimeQuantum(Integer timeQuantum) {
            this.timeQuantum = timeQuantum;
        }

}
