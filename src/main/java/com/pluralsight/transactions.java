package com.pluralsight;
import java.time.LocalDate;
import java.time.LocalTime;

public class transactions {

private LocalDate date ;
private LocalTime time;
private String discription ;
private String vendor ;
private double ammount;


public transactions (LocalDate date,LocalTime time, String discription, String vendor, double amount){
    this.date = date;
    this.time = time;
    this.discription = discription;
    this.vendor = vendor;
    this.ammount = amount;



}

    public LocalDate getdate() {
        return date;
    }

    public void setdate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void settime(LocalTime time) {
        this.time = time;
    }

    public String getdiscription() {
        return discription;
    }

    public void setdiscription(String discription) {
        this.discription = discription;
    }

    public String getvendor() {
        return vendor;
    }

    public void setvendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    // this is for transaction file for bufferdwriter to access the calss check and write it //


    public String tocsv(){
    return date + "|" + time + "|" + discription + "|" + vendor + "|" + ammount;
    }


}

/// /date|time|description|vendor|amount
/// 2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
/// 2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00
