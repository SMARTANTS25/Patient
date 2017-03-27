package com.pifss.patient.utils;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by SMARTANS on 3/27/2017.
 */
@Table(name ="Hospital")
public class Hospital extends Model {

    public Hospital(String hospitalName, String extraInfo, String email) {


        this.hospitalName = hospitalName;
        this.extraInfo = extraInfo;
        this.email = email;
    }




//     tvHospitalTitle.setText(hospital.getHospitalName());
//        tvHospitalDescription.setText(hospital.getExtraInfo());
//        tvHospitalEmail.setText(hospital.getEmail());
    public Hospital(String email, String address, String extraInfo, String fax, String hospitalName, String phone, String webSite, String workingHours, String type, String hospitalLang, String hospitalAlt, String hospitalId) {
        this.email = email;
        this.address = address;
        this.extraInfo = extraInfo;
        this.fax = fax;
        this.hospitalName = hospitalName;
        this.phone = phone;
        this.webSite = webSite;
        this.workingHours = workingHours;
        this.type = type;
        this.hospitalLang = hospitalLang;
        this.hospitalAlt = hospitalAlt;
        this.hospitalId = hospitalId;
    }

    @Column(name = "email")
private
String email;

    @Column(name = "address")
    private
    String address;

    @Column(name = "extraInfo")
    private
    String extraInfo;

    @Column(name = "fax")
    private
    String fax;

    @Column(name = "hospitalName")
    private
    String hospitalName;

    @Column(name = "phone")
    private
    String phone;

    @Column(name = "webSite")
    private
    String webSite;


    @Column(name = "workingHours")
    private
    String workingHours;

    @Column(name = "type")
    private
    String type;

    @Column(name = "hospitalLang")
    private
    String hospitalLang;


    @Column(name = "hospitalAlt")
    private
    String hospitalAlt;

    @Column(name = "hospitalId")
    private
    String hospitalId;

    public Hospital(String s) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHospitalLang() {
        return hospitalLang;
    }

    public void setHospitalLang(String hospitalLang) {
        this.hospitalLang = hospitalLang;
    }

    public String getHospitalAlt() {
        return hospitalAlt;
    }

    public void setHospitalAlt(String hospitalAlt) {
        this.hospitalAlt = hospitalAlt;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

//
//    {
//        "address": "bayan",
//            "email": "a@whatever",
//            "extraInfo": "a@whatever",
//            "fax": "222",
//            "hospitalAlt": "22.2",
//            "hospitalId": 125,
//            "hospitalLang": "1.23",
//            "hospitalName": "test1",
//            "logoUrl": "loho",
//            "phone": "111",
//            "specialityId": 33,
//            "type": "mynon",
//            "webSite": "web1",
//            "workingHours": "24"
//    }
}
