package net.sppan.base.entity;


import com.alibaba.fastjson.annotation.JSONField;
import net.sppan.base.entity.support.BaseEntity;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tb_bus_status")
public class BusInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "bus_id")
    private  Integer busId;
    @Column(name = "bus_number")
    private String busNumber;
    @Column(name = "driver_name")
    private String driverName;
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "engine_hygiene")
    private String  engineHygiene;
    @Column(name = "air_filter")
    private String airFilter;
    @Column(name = "battery_health")
    private String batteryHealth;
    @Column(name = "medicine_box")
    private String medicineBox;
    @Column(name = "gps_monitoring")
    private String gpsMonitoring;
    @Column(name = "fire_extinguisher")
    private String fireExtinguisher;
    @Column(name = "escape_door")
    private String escapeDoor;
    @Column(name = "safety_hammer")
    private String safetyHammer;
    @Column(name = "oill_oil_level")
    private String oillOilLevel;
    @Column(name = "amount_of_antifreeze")
    private String  amountOfAntifreeze;
    @Column(name = "bake_fluid")
    private String bakeFluid;
    @Column(name = "belt_tightness")
    private String beltTightness;
    @Column(name = "tire_pressure_screws")
    private String tirePressureScrews;
    @Column(name = "lights")
    private String lights;
    @Column(name = "guide_board")
    private String guideBoard;
    @Column(name = "clutch")
    private String clutch;
    @Column(name = "brake")
    private String brake;
    @Column(name = "steering_wheel")
    private String steeringWheel;
    @Column (name = "instrument_panel")
    private String instrumentPanel;



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

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEngineHygiene() {
        return engineHygiene;
    }

    public void setEngineHygiene(String engineHygiene) {
        this.engineHygiene = engineHygiene;
    }

    public String getAirFilter() {
        return airFilter;
    }

    public void setAirFilter(String airFilter) {
        this.airFilter = airFilter;
    }

    public String getBatteryHealth() {
        return batteryHealth;
    }

    public void setBatteryHealth(String batteryHealth) {
        this.batteryHealth = batteryHealth;
    }

    public String getMedicineBox() {
        return medicineBox;
    }

    public void setMedicineBox(String medicineBox) {
        this.medicineBox = medicineBox;
    }

    public String getGpsMonitoring() {
        return gpsMonitoring;
    }

    public void setGpsMonitoring(String gpsMonitoring) {
        this.gpsMonitoring = gpsMonitoring;
    }

    public String getFireExtinguisher() {
        return fireExtinguisher;
    }

    public void setFireExtinguisher(String fireExtinguisher) {
        this.fireExtinguisher = fireExtinguisher;
    }

    public String getEscapeDoor() {
        return escapeDoor;
    }

    public void setEscapeDoor(String escapeDoor) {
        this.escapeDoor = escapeDoor;
    }

    public String getSafetyHammer() {
        return safetyHammer;
    }

    public void setSafetyHammer(String safetyHammer) {
        this.safetyHammer = safetyHammer;
    }

    public String getOillOilLevel() {
        return oillOilLevel;
    }

    public void setOillOilLevel(String oillOilLevel) {
        this.oillOilLevel = oillOilLevel;
    }

    public String getAmountOfAntifreeze() {
        return amountOfAntifreeze;
    }

    public void setAmountOfAntifreeze(String amountOfAntifreeze) {
        this.amountOfAntifreeze = amountOfAntifreeze;
    }

    public String getBakeFluid() {
        return bakeFluid;
    }

    public void setBakeFluid(String bakeFluid) {
        this.bakeFluid = bakeFluid;
    }

    public String getBeltTightness() {
        return beltTightness;
    }

    public void setBeltTightness(String beltTightness) {
        this.beltTightness = beltTightness;
    }

    public String getTirePressureScrews() {
        return tirePressureScrews;
    }

    public void setTirePressureScrews(String tirePressureScrews) {
        this.tirePressureScrews = tirePressureScrews;
    }

    public String getLights() {
        return lights;
    }

    public void setLights(String lights) {
        this.lights = lights;
    }

    public String getGuideBoard() {
        return guideBoard;
    }

    public void setGuideBoard(String guideBoard) {
        this.guideBoard = guideBoard;
    }

    public String getClutch() {
        return clutch;
    }

    public void setClutch(String clutch) {
        this.clutch = clutch;
    }

    public String getBrake() {
        return brake;
    }

    public void setBrake(String brake) {
        this.brake = brake;
    }

    public String getSteeringWheel() {
        return steeringWheel;
    }

    public void setSteeringWheel(String steeringWheel) {
        this.steeringWheel = steeringWheel;
    }

    public String getInstrumentPanel() {
        return instrumentPanel;
    }

    public void setInstrumentPanel(String instrumentPanel) {
        this.instrumentPanel = instrumentPanel;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }
}
