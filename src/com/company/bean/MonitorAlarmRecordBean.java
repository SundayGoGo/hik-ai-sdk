package com.company.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * (MonitorAlarmRecord)表实体类
 *
 * @author Tobin
 * @since 2022-05-01 15:32:58
 */
public class MonitorAlarmRecordBean implements Serializable {

    private static final long serialVersionUID = -35323091798796634L;



    private Date alarmTime;

    private String alarmType;

    private String channel;

    private String equipment;

    private String photo;

    private String base64;

    private  String file; // 源文件


    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "MonitorAlarmRecordBean{" +
                ", alarmTime=" + alarmTime +
                ", alarmType='" + alarmType + '\'' +
                ", channel='" + channel + '\'' +
                ", equipment='" + equipment + '\'' +
                ", photo='" + photo + '\'' +
                ", file='" + file + '\'' +
                '}';
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}

