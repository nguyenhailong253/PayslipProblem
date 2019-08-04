/// author: Long Nguyen (long.nguyen@myob.com)

import java.util.Scanner;

public class PayslipGenerator {
    /**
     * PRIVATE FIELDS
     */
    private Payslip currentPayslip = new Payslip();

    /**
     * METHODS
     */
    public void collectInformation() {
        currentPayslip.getGivenNames();
        currentPayslip.getSurname();
        currentPayslip.getAnnualSalary();
        currentPayslip.getSuperRate();
        currentPayslip.getStartDate();
        currentPayslip.getEndDate();
    }

    public void processInformation() {
        currentPayslip.calculateGrossIncome();
        currentPayslip.calculateSuper();
        currentPayslip.calculateIncomeTax();
        currentPayslip.calculateNetIncome();
    }

    public void generatePayslip() {
        currentPayslip.displayHeading();
        currentPayslip.displayName();
        currentPayslip.displayPayPeriod();
        currentPayslip.displayGrossIncome();
        currentPayslip.displayIncomeTax();
        currentPayslip.displayNetIncome();
        currentPayslip.displaySuper();
    }

    public void run() {
        currentPayslip.displayGreetings();
        collectInformation();
        processInformation();
        generatePayslip();
        currentPayslip.displayThanks();
    }

    /**
     * MAIN
     */
    public static void main(String[] args) {
        PayslipGenerator generator = new PayslipGenerator();
        generator.run();
    }
}
