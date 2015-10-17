package javaapplication5;

import java.util.*;
import java.io.*;

public class Lab4 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner inFile = new Scanner(new FileReader("C:/Users/czepeda/Desktop/salesdata.dat"));

        PrintWriter outFile = new PrintWriter("C:/Users/czepeda/Desktop/printer.dat");

        String EmpName, SalesAmtstr, quotamsg;
        double SalesAmt, Comm, TotalSales, TotalComm, quota, LowRate, HiRate;

//Variables
        TotalSales = 0.0;
        TotalComm = 0.0;
        quota = 500.00;
        LowRate = .03;
        HiRate = .05;

        outFile.println("\t\t\t" + "SALES COMMISSION REPORT");
        outFile.println();
        outFile.println("EMPLOYEE NAME" + "\t" + "SALES AMOUNT" + "\t\t" + "COMMISSION" + "\t\t" + "COMMENT");
        outFile.println();

//Begin Read Loop
        while (inFile.hasNext()) {
            EmpName = inFile.nextLine();
            SalesAmtstr = inFile.nextLine();

//try catch
            try {
                SalesAmt = Double.parseDouble(SalesAmtstr);
                if (SalesAmt <= quota) {
                    Comm = LowRate * SalesAmt;
                    quotamsg = "";
                } else {
                    Comm = LowRate * quota + HiRate * (SalesAmt - quota);
                    quotamsg = "Over Quota";
                }
                outFile.println(EmpName + "\t\t" + String.format("$%,.2f", SalesAmt) + "\t\t\t" + String.format("$%,.2f", Comm) + "\t\t" + quotamsg);
                TotalSales = TotalSales + SalesAmt;
                TotalComm = TotalComm + Comm;
            } catch (NumberFormatException e) {
                outFile.println(EmpName + "\t" + "**********Sales Amount Error**********");
            }
        } //end while

        outFile.println();
        outFile.println("FINAL TOTALS" + "\t" + String.format("$%,.2f", TotalSales) + "\t" + String.format("$%,.2f", TotalComm));
        inFile.close();
        outFile.close();
    }
}
