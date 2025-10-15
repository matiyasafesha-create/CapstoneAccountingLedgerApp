package com.pluralsight;

import java.io.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;





public class AccountingLedgerApp {

    public static void run() {
        System.out.println("========= Welcome to the Accounting Ledger App ============ \n");
        while (true) {
            mainmenuselector();
        }
    }

    public static void mainmenuselector() {

        Scanner appscanner = new Scanner(System.in);
        System.out.println(" How would you like to Continue? ");
        System.out.println(
                " D - Add A Deposit To Your Account\n" +  // done///
                        " P - Make Payment (Debit)\n" +              // done
                        " L - Ledger\n" +                   // done
                        " X - EXIT APP\n");                       ///done
        System.out.print("Enter Your option Here: ");
        String inputforhomescreeen = appscanner.nextLine().toUpperCase();

        switch (inputforhomescreeen) {
            case "D":
                System.out.println(" ======== Deposit into your Account ===== ");
                adddedeposit();
                break;
            case "P":
                System.out.println(" ++++++++++++ Make A payment ++++++++++++ ");
                makeapayment();
                break;

            case "L":
                ledger();
                break;

            case "X":
                exitapplication();
                break;


        }
        System.out.println("\nPress ENTER to continue...\n");
        appscanner.nextLine();
       System.out.println();


    }


    public static void ledger() {

        Scanner ledgerscanner = new Scanner(System.in);
        System.out.println(
                "A - Display All Transactions\n" +
                        "D - Display Only Positive entries (Deposits)\n" +
                        "P - Display Only Negative entries\n" +
                        "R - Go to Reports Screen\n" +
                        "H - Return to Home\n");



        System.out.print("Enter Your Option Here:");
        String ledgerinput = ledgerscanner.nextLine().toUpperCase();
        switch (ledgerinput) {
            case "A":
                System.out.println("=========Here are all the transactions==========");
                viewAllLedger();
                break;
            case "D":
                System.out.println("Credit Entries");
                System.out.println();
                ArrayList<transactions> positiveentry = loadledger();    // this loads all transactions in every case listed
                showcreditpositive(positiveentry);                       /// method has a real data


                break;
            case "P":
                System.out.println("Debit Entries");
                System.out.println();
                ArrayList<transactions> negativeentery = loadledger();           // this loads all transactions in every case listed
                showdebitesnegative(negativeentery);                                   ///Method has a real data


                break;
            case "R":
                System.out.println("Reports Screen");
                reports();
                break;
            case "H":
                mainmenuselector();
                break;


        }


    }

    public static void reports() {
        Scanner reportsscanner = new Scanner(System.in);
        System.out.println(
                "1 - Month to Date\n" +
                        "2 - Previous Month\n" +
                        "3 - Year to Date\n" +
                        "4 - Previous Year \n" +
                        "5 - Search by Vendor\n" +
                        "6 - Custom Search \n" +
                        "0 - Back to Ledger Option\n" +
                        "H - Return Home App\n");


        System.out.print("Enter Your Option Here: ");
        String reportsinput = reportsscanner.nextLine().toUpperCase();
        switch (reportsinput) {
            case "1":
                monthtodate();
                break;

            case "2":
                previousmonth();
                break;

            case "3":
                yeartodate();
                break;

            case "4":
                  previousyear();
                break;

            case "5":
                System.out.println(" ============= SEARCH BY VENDOR ==============");
                System.out.println("Enter the Vendor Name? ");
                String searchvendorinput = reportsscanner.nextLine();
                ArrayList<transactions> ledger = loadledger();  // this loads all transactions in every case listed
                serachbyvendor(ledger, searchvendorinput);
                break;

            case "6":
                System.out.println("======================= Custom Search by dates ============================");
                customsearch();
                break;

            case "0":
                ledger();
                break;
            case "H":
            case "7":
                mainmenuselector();
                break;
        }

        System.out.println("\nPress ENTER to continue...\n");
        reportsscanner.nextLine();
        System.out.println();


    }


    public static void exitapplication() {
        System.out.println("You Have Exited the Application GOOD BYE ");
        System.exit(0);
    }
/// /Realized instead of rewriting the method again and again i will just create one method and call it


    /// //++++++++++++++++++++++++++++METHOD FOR WRITING ON A FILE +++++++++++++++++++++++++///////

    public static void callingcsvtransaction(transactions t) {  /// t is how the method receive
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/transaction.csv", true));
            /// writer.write("Date|Time|Description|Amount"); ///HEADER???
            writer.newLine();
            writer.write(t.tocsv());
            writer.close();

        } catch (IOException e) {
            System.out.println(" Unexpected Error Has Occurred ");
            e.printStackTrace();

        }
    }

    /// ++++++++++++++++++++++++++++++++++++++++++ ADDING DEPOSIT ++++++++++++++++++++++++++++++++++++++++++++++
    public static void adddedeposit() {

        Scanner adddeposit = new Scanner(System.in);


        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.print("What is the Reason for the Deposit Enter a brief Description: ?");
        String description = adddeposit.nextLine();

        System.out.print("Enter is the Source/Vendor of the Deposit:? ");
        String vendor = adddeposit.nextLine();

        System.out.print("Enter is the Amount:? ");
        float amount = adddeposit.nextFloat();


        transactions deposit = new transactions(date, time, description, vendor, +amount);
        amount = Math.round(amount * 100.0f) / 100.0f;/// rounded the float here

        System.out.println("Deposit Added Successfully!");
        System.out.println("Current Time: " + time);
        System.out.println("Current Date: " + date);
        callingcsvtransaction(deposit);

    }

    ///  ===============================MAKE A PAYMENT============================================/////
    public static void makeapayment()  {

        Scanner makeapayemtn = new Scanner(System.in);

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.print("Enter the Reason for the Payment:?");
        String reasonunput = makeapayemtn.nextLine();

        System.out.print("Enter the vendor/recipient:?");
        String vendorinput = makeapayemtn.nextLine();

        System.out.print("Enter the Amount you want to Debit$:?");
        float debtinput = makeapayemtn.nextFloat();

        debtinput = Math.round(debtinput * 100.f) / 100.f;




        transactions payment = new transactions(date, time, reasonunput, vendorinput, -debtinput);
        System.out.println("PAYMENT WAS SUCCESSFUL!");
        System.out.println("Time:" + time);
        System.out.println("Date:" + date);
        System.out.println("Amount Paid" + debtinput);
        callingcsvtransaction(payment);

    }

    /// /////////// / ++++++++++++++++++++++++++++++++reader is complete+++++++++++++++++++++++++++++++++++++++++++++++++ /////
    public static ArrayList<transactions> loadledger() {
        ArrayList<transactions> ledger = new ArrayList<transactions>();


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/transaction.csv"));
            String input;

            bufferedReader.readLine();

            while ((input = bufferedReader.readLine()) != null) {
                String[] tokens = input.split("\\|");

                LocalDate date = LocalDate.parse(tokens[0]);
                LocalTime time = LocalTime.parse(tokens[1]);
                String description = tokens[2];
                String vendor = tokens[3];
                Float amount = Float.parseFloat(tokens[4]);


                ledger.add(new transactions(date, time, description, vendor, amount));

            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return ledger;


    }

    public static void viewAllLedger() {
        ArrayList<transactions> ledger = loadledger();

        System.out.println("======== ALL TRANSACTIONS ========");

        for (transactions viewallitem : ledger) {
            System.out.println(viewallitem);
        }

        System.out.println("==================================");
    }

    public static void showdebitesnegative(ArrayList<transactions> ledger) {
        for (transactions debit : ledger) {
            if (debit.getamount() < 0) {
                displayalllistfromclass(debit);
            }
        }


    }

    public static void showcreditpositive(ArrayList<transactions> ledger) {
        for (transactions credit : ledger) {
            if (credit.getamount() > 0) {
                displayalllistfromclass(credit);
            }
        }
    }

    public static void displayalllistfromclass(transactions t) {
        System.out.printf(t.getdate() + "|" + t.getTime() + "|" + t.getdiscription() + "| " +
                t.getvendor() + "|" + t.getamount() + "\n");

    }

    public static void monthtodate() {

        ArrayList<transactions> report = loadledger();             /// Loading the array to access it ///

        LocalDate todaysdate = LocalDate.now();                        ///LOCAL DATE. NOW ///
        LocalDate dayofmonth = todaysdate.withDayOfMonth(1);


             ///  Check if Date is between the start of the month and the date ////
        for (transactions dateofmonth : report) {
            LocalDate date = dateofmonth.getdate();
            if (!date.isBefore(dayofmonth) && !date.isAfter(todaysdate)){
                displayalllistfromclass(dateofmonth);     // SHOW IT

            }
        }
    }

    /// ++++++++++++++++++++++++++++++++++++++++previous month +++++++++++++++++++++++++++++++++
    public static void previousmonth(){
        ArrayList<transactions> report = loadledger();
        LocalDate date = LocalDate.now();
        LocalDate starofthemonth = date.minusMonths(1).withDayOfMonth(1);
        LocalDate endofthemonth = starofthemonth.withDayOfMonth(starofthemonth.lengthOfMonth());

        for (transactions month : report){
            LocalDate datee = month.getdate();     /// THIS WILL GO CHECK AND LOOP THROUGH THE CLASS

            if (!datee.isBefore(starofthemonth) && date.isAfter(starofthemonth) ){
                displayalllistfromclass(month);
            }
        }
    }


    public static void yeartodate(){
        ArrayList<transactions> report = loadledger();
        LocalDate todaysdate = LocalDate.now();
        LocalDate startoftheyear = todaysdate.withYear(1);   // Get January 1st of the current year //


        // SHow Transactions between january first till today////

        for(transactions yearcheck : report ){
            LocalDate yearr = yearcheck.getdate();
            if(!yearr.isBefore(startoftheyear) && !yearr.isAfter(todaysdate)){
                displayalllistfromclass(yearcheck);
            }

        }
    }


    public static void previousyear(){
        ArrayList<transactions> report = loadledger();

        LocalDate todaysdate = LocalDate.now();

        LocalDate startofyear = todaysdate.minusYears(1).withDayOfYear(1);
        LocalDate endofyear = todaysdate.withDayOfYear(1).minusDays(1);

        for(transactions year : report ){
            LocalDate localDate = year.getdate();

            if(!localDate.isBefore(startofyear) && !localDate.isAfter(endofyear)){
                displayalllistfromclass(year);
            }

        }
    }
////++++++++++++++++++++++++++++++++++++++++++++search by vendor +++++++++++++++++++++++++++++++++++++++//////
    public static void serachbyvendor(ArrayList<transactions> ledger, String serachbyvendorinput) {         // String Search by
        for (transactions search : ledger) {
            if (search.getvendor().toLowerCase().contains(serachbyvendorinput.toLowerCase())){
                displayalllistfromclass(search);
            }
        }
    }

    public static void customsearch(){
            ArrayList<transactions> reports = loadledger();
            Scanner customscanner = new Scanner(System.in);


        System.out.println("Enter Start Date (yyyy-mm-dd):");
        String startdateinput = customscanner.nextLine();
        System.out.println("Enter End Date (yyyy-mm-dd):");
        String enddateinput = customscanner.nextLine();



        LocalDate startdaate = LocalDate.parse(startdateinput);
        LocalDate enddate = LocalDate.parse(enddateinput);




    for (transactions search : reports){
        LocalDate classdate = search.getdate();

        if(!classdate.isBefore(startdaate) && !classdate.isAfter(enddate)){
            displayalllistfromclass(search);//matching results shown
            System.out.println(search);  // move inside to show matching record only
        }
        }




    }


}



























