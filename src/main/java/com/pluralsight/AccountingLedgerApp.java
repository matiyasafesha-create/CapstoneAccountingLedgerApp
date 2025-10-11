package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
                        " P - Make Payment (Debit)\n" +
                        " L - Ledger\n" +
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
                break;
            case "D":
                System.out.println("Positive Deposits ");
                break;
            case "P":
                System.out.println("Negative Entries");
                break;
            case "R":
                System.out.println("Reports Screen");
                reports();
                break;
            case "H":
                mainmenuselector();
                break;


        }
        System.out.println("\nPress ENTER to continue...\n");
        ledgerscanner.nextLine();
        System.out.println();


    }

    public static void reports() {
        Scanner reportsscanner = new Scanner(System.in);
        System.out.println(
                "1 - Month to Date\n" +
                        "2 - Previous Month\n" +
                        "3 - Year to Date\n" +
                        "4 - Previous Year \n" +
                        "5 - Search by Vendor\n" +
                        "0 - Back to Ledger Option\n" +
                        "H - Return Home App\n");


        System.out.print("Enter Your Option Here: ");
        String reportsinput = reportsscanner.nextLine().toUpperCase();
        switch (reportsinput) {
            case "1":

                break;

            case "2":

                break;

            case "3":

                break;

            case "4":

                break;

            case "5":

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
            writer.write("Date|Time|Description|Amount"); ///HEADER???
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

        System.out.print("What is the Reason for the Deposit: ?");
        String description = adddeposit.nextLine();

        System.out.print("Who is the Source of this Deposit: ? ");
        String vendor = adddeposit.nextLine();

        System.out.print("What is the Amount:? ");
        float amount = adddeposit.nextFloat();


        transactions deposit = new transactions(date, time, description, vendor, amount);
        amount = Math.round(amount * 100.0f) / 100.0f;/// rounded the float here

        System.out.println("Deposit Added Successfully!");
        System.out.println("Current Time: " + time);
        System.out.println("Current Date: " + date);
        callingcsvtransaction(deposit);

    }

    ///  ===============================MAKE A PAYMENT============================================/////
    public static void makeapayment() {

        Scanner makeapayemtn = new Scanner(System.in);

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.print("What is the Reason for the Payment:?");
        String reasonunput = makeapayemtn.nextLine();

        System.out.print("WHo is the vendor:?");
        String vendorinput = makeapayemtn.nextLine();

        System.out.print("Enter the Amount you want to Debit:?");
        float debtinput = makeapayemtn.nextFloat();

        debtinput = Math.round(debtinput * 100.f) / 100.f;


        transactions payment = new transactions(date, time, reasonunput, vendorinput, debtinput);
        System.out.println("PAYMENT WAS SUCCESSFUL!");
        System.out.println("Time:" + time);
        System.out.println("Date:" + date);
        callingcsvtransaction(payment);

    }
                                     /// / ++++++++++++++++++++++++++++++++reader is complete+++++++++++++++++++++++++++++++++++++++++++++++++ /////
    public static HashMap<String, transactions> loadledger() {
        HashMap<String, transactions> ledger = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/transaction.csv"));
            String input;

            while ((input = bufferedReader.readLine()) != null) {
                String[] tokens = input.split("\\|");

                LocalDate date = LocalDate.parse(tokens[0]);
                LocalTime time = LocalTime.parse(tokens[1]);
                String description = tokens[2];
                String vendor = tokens[3];
                Float amount = Float.parseFloat(tokens[4]);

                ledger.put(description, new transactions(date, time, description, vendor, amount));

            }
                bufferedReader.close();

            }catch(IOException e) {
                e.printStackTrace();

            }
            return ledger;


        }
    }


























