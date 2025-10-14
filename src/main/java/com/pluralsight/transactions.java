package com.pluralsight;
import java.time.LocalDate;
import java.time.LocalTime;

public class transactions {

private LocalDate date ;
private LocalTime time;
private String description ;
private String vendor ;
private double amount;


public transactions (LocalDate date,LocalTime time, String discription, String vendor, double amount){
    this.date = date;
    this.time = time;
    this.description= discription;
    this.vendor = vendor;
    this.amount = amount;



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
        return description;
    }

    public void setdiscription(String discription) {
        this.description= discription;
    }

    public String getvendor() {
        return vendor;
    }

    public void setvendor(String vendor) {
        this.vendor = vendor;
    }

    public double getamount() {
        return amount;
    }

    public void setamount(double amount) {
        this.amount = amount;
    }

    // this is for transaction file for bufferdwriter to access the calss check and write it //


    public String tocsv(){
    return date + "|" + time + "|" + description+ "|" + vendor + "|" + amount;
    }
/// /need to learn more on this and what it does
    @Override
    public String toString() {
        return String.format(
                "DATE: %s |TIME: %s | DESCRIPTION: %s | VENDOR: %s |Amount: $%.2f",
                date, time, description, vendor, amount);
    }



}

/// /date|time|description|vendor|amount
/// 2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
/// 2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00
