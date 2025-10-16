package com.pluralsight;
import java.time.LocalDate;
import java.time.LocalTime;

public class transactions {

private LocalDate date ;
private LocalTime time;
private String description ;
private String vendor ;
private double amount;

// constructor //  used to create a transactions object with all required information this key word assigns values passed //
/// it runs automatically when I create a new transactions object.

public transactions (LocalDate date,LocalTime time, String description, String vendor, double amount){
    this.date = date;
    this.time = time;
    this.description= description;
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

    // this is for transaction file for bufferdwriter to access the class check and write it //

///  method converts a transaction object into a single line of text separated by | single line of text
    public String tocsv(){
    return date + "|" + time + "|" + description+ "|" + vendor + "|" + amount;
    }
/// //////// This calles the csv and reads the files on the main method
    @Override
    public String toString() {     // this makes it readable string when object is printed it shows date time description vendor amount
        return String.format(             // instead of random memory/ nice readable line.
                "DATE: %s |TIME: %s | DESCRIPTION: %s | VENDOR: %s |Amount: $%.2f",
                date, time, description, vendor, amount);
    }



}

/// /date|time|description|vendor|amount
/// 2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
/// 2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00
