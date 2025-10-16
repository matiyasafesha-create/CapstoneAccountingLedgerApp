package com.pluralsight;

import java.io.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AccountingLedgerApp {
    /// Run method is running the mainmenuselector that runs the program itself///
    public static void run() {
        System.out.println("=========  MoneyMind Finance Tracker ============ \n");
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
            case "D":   //LINE 199 Review the BuffWriter Callingalltransactions  LINE 182
                System.out.println(" +++++++++++++++++++++++++++ Deposit into your Account ++++++++++++++++++++++++++++++ ");
                adddedeposit();
                break;
            case "P":
                System.out.println(" -------------------------- Make A payment -------------------------------- ");
                makeapayment();     // LINE 229
                break;

            case "L":
                System.out.println(" ============================= Ledger =========================================== ");
                ledger();   /// LINE 66
                break;

            case "X":
                exitapplication(); // LINE 173
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
                System.out.println("===================== All the transactions ========================="); // REVIEW LINE 258 for the method LoadLedger and LINE 292 VIEW LEDGER///
                viewAllLedger();
                break;
            case "D":
                System.out.println("======================= POSITIVE Credit Entries ==============================");// REVIEW LINE 318
                System.out.println();
                ArrayList<transactions> positiveentry = loadledger();    // this loads all transactions in every case listed
                showcreditpositive(positiveentry);                       /// method has a real data
                break;
            case "P":
                System.out.println("========================= NEGATIVE Debit Entries=============================");// LINE 304
                System.out.println();
                ArrayList<transactions> negativeentery = loadledger();           // this loads all transactions in every case listed
                showdebitesnegative(negativeentery);                            ///Method has a real data
                break;
            case "R":
                System.out.println("============================ Reports Screen ============================");       // LINE 115
                reports();
                break;
            case "H":
                mainmenuselector();
                System.out.println("============================== Main Menu ======================================");   //LINE 26
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
            case "1":                          // LINE 334
                monthtodate();
                System.out.println("======================= Month To Date Transactions =============================== ");  // Line 338
                break;

            case "2":
                previousmonth();
                System.out.println("============================ Previous Month ======================================= ");  // LINE 357
                break;

            case "3":
                yeartodate();
                System.out.println(" ============================ Year To Date ========================================= "); // LINE 368
                break;

            case "4":
                System.out.println("============================== Previous Year ======================================= "); // Line 386
                previousyear();
                break;

            case "5":
                serachbyvendor();
                System.out.println(" ======================= SEARCH BY VENDOR ===============================");        ///LINE 404
                break;

            case "6":
                customsearch();
                System.out.println("======================= Custom Search by dates ============================");       // LINE 421
                break;

            case "0":
                ledger();
                System.out.println("============================= Ledger =======================================");
                break;
            case "H":
            case "7":
                mainmenuselector();
                System.out.println(" ================================= Main Menu ================================== ");
                break;
        }

    }


    public static void exitapplication() {
        System.out.println("You Have Exited the Application GOOD BYE ");
        System.exit(0);
    }
/// /Realized instead of rewriting the method again, and again I will just create one method and call it


    /// //++++++++++++++++++++++++++++METHOD FOR WRITING ON A FILE +++++++++++++++++++++++++///////

    public static void callingcsvtransaction(transactions t) {  /// t is how the method receive
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/transaction.csv", true)); //just add new transactions
            /// writer.write("Date|Time|Description|Amount"); ///HEADER???
            writer.newLine();
            writer.write(t.tocsv());               // writes to the CSV FILE calls the method .tocsv from class
            writer.close();                                 // saves and coses

        } catch (IOException e) { /// /cathes error in the program if any//
            System.out.println(" Unexpected Error Has Occurred ");   //
            e.printStackTrace(); // prints details on what exactly happened and why it cashed///

        }
    }

    /// PROMPT THE USER TO ADD WRITES A TRANSACTIONS //////
    ///
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


        transactions deposit = new transactions(date, time, description, vendor, +amount);   /// creates a transaction object
        amount = Math.round(amount * 100.0f) / 100.0f;/// rounded the float here

        System.out.println("Deposit Added Successfully!");
        System.out.println("Current Time: " + time);
        System.out.println("Current Date: " + date);
        callingcsvtransaction(deposit);

    }

    ///  ===============================MAKE A PAYMENT============================================/////
    /// PROMPTS THE USER TO DEBIT / NEGATIVE TRANSACTIONS ARE WRITTEN ////
    public static void makeapayment() {

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

    /////// READS TRANSACTIONS FROM CSV AND LOADS IT TO ARRAY LIST []/////
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
                double amount = Float.parseFloat(tokens[4]);


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
        for (transactions viewallitem : ledger) {
            System.out.println(viewallitem);
        }
    }

    public static void showdebitesnegative(ArrayList<transactions> ledger) {
        for (transactions debit : ledger) {
            if (debit.getamount() < 0) {
                displayalllistfromclass(debit);
            }
        }

        System.out.println("=============================================");
    }

    public static void showcreditpositive(ArrayList<transactions> ledger) {
        for (transactions credit : ledger) {
            if (credit.getamount() > 0) {
                displayalllistfromclass(credit);
            }
        }
        System.out.println("==============================================");

    }

    // Separation of concerns /// Smaller focused method
    public static void displayalllistfromclass(transactions t) {
        System.out.printf(t.getdate() + "|" + t.getTime() + "|" + t.getdiscription() + "| " +
                t.getvendor() + "|" + t.getamount() + "\n");

    }

    public static void monthtodate() {

        ArrayList<transactions> report = loadledger();             /// Loading the array to access it ///

        LocalDate todaysdate = LocalDate.now();                        ///LOCAL DATE. NOW ///
        LocalDate dayofmonth = todaysdate.withDayOfMonth(1);       // first day of the current month //


        ///  Check if Date is between the start of the month and the date ////
        for (transactions dateofmonth : report) {                    // goes each transaction
            LocalDate date = dateofmonth.getdate();
            if (!date.isBefore(dayofmonth) && !date.isAfter(todaysdate)) {   ///  loop through each transaction before  today until start of this month//
                displayalllistfromclass(dateofmonth);     // SHOW IT

            }
        }
    }

    /// ++++++++++++++++++++++++++++++++++++++++previous month +++++++++++++++++++++++++++++++++
    public static void previousmonth() {
        ArrayList<transactions> report = loadledger();
        LocalDate date = LocalDate.now();
        LocalDate starofthemonth = date.minusMonths(1).withDayOfMonth(1);
        LocalDate endofthemonth = starofthemonth.withDayOfMonth(starofthemonth.lengthOfMonth());

        for (transactions month : report) {
            LocalDate datee = month.getdate();     /// THIS WILL GO CHECK AND LOOP THROUGH THE CLASS

            if (!datee.isBefore(starofthemonth) && date.isAfter(endofthemonth)) {
                displayalllistfromclass(month);
            }
        }
    }


    public static void yeartodate() {
        ArrayList<transactions> report = loadledger();
        LocalDate todaysdate = LocalDate.now();
        LocalDate startoftheyear = todaysdate.withYear(1);   // Get January 1st of the current year //


        // SHow Transactions between january first till today////

        for (transactions yearcheck : report) {
            LocalDate yearr = yearcheck.getdate();
            if (!yearr.isBefore(startoftheyear) && !yearr.isAfter(todaysdate)) {
                displayalllistfromclass(yearcheck);
            }

        }
    }


    public static void previousyear() {
        ArrayList<transactions> report = loadledger();

        LocalDate todaysdate = LocalDate.now();

        LocalDate startofyear = todaysdate.minusYears(1).withDayOfYear(1);
        LocalDate endofyear = todaysdate.withDayOfYear(1).minusDays(1);

        for (transactions year : report) {
            LocalDate localDate = year.getdate();

            if (!localDate.isBefore(startofyear) && !localDate.isAfter(endofyear)) {
                displayalllistfromclass(year);
            }

        }
    }

    /// /++++++++++++++++++++++++++++++++++++++++++++search by vendor +++++++++++++++++++++++++++++++++++++++//////
    public static void serachbyvendor() {
        Scanner reports = new Scanner(System.in);
        ArrayList<transactions> ledger = loadledger();


        System.out.println("Enter the Vendor Name? ");
        String searchvendorinput = reports.nextLine();


        for (transactions search : ledger) {
            if (search.getvendor().toLowerCase().contains(searchvendorinput.toLowerCase())) {
                displayalllistfromclass(search);
            }
        }
    }

    public static void customsearch() {
        ArrayList<transactions> reports = loadledger();
        Scanner customscanner = new Scanner(System.in);


        System.out.print("Enter Start Date (yyyy-mm-dd):");
        String startdateinput = customscanner.nextLine();
        System.out.print("Enter End Date (yyyy-mm-dd):");
        String enddateinput = customscanner.nextLine();
        System.out.print("Enter the Description:");
        String descriptioninput = customscanner.nextLine();
        System.out.print("Enter Vendor:");
        String vendorinput = customscanner.nextLine();
        System.out.print("Enter Amount:");
        double amountinput = customscanner.nextFloat();

        LocalDate startdaate = null;                                   // LocalDate.parse(startdateinput);
        LocalDate enddate = null;                                      //LocalDate.parse(enddateinput);


        for (transactions search : reports) {
            LocalDate classdate = search.getdate();
            String description = search.getdiscription();
            String vendor = search.getvendor();
            double amount = search.getamount();

            if (!classdate.isBefore(startdaate) && !classdate.isAfter(enddate) &&
                    description.contains(descriptioninput) && vendor.contains(vendorinput)
                    && amount == (amountinput)) {
                displayalllistfromclass(search);//matching results shown
                System.out.println(search);  // move inside to show matching record only
            } else {
                System.out.println(" WE couldn't find a match please enter it again!! ");
            }
        }
    }

}




//2024-09-05   |07:32:18.944752300|      Freelance Project Payment     |Upwork|      1350.00























