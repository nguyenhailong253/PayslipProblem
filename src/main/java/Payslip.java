/// author: Long Nguyen (long.nguyen@myob.com)

import java.util.Arrays;
import java.util.Scanner;

public class Payslip {
    /**
     * CONSTANTS
     */
    static final int NUM_MONTHS = 12;
    static final float HUNDRED = 100.0f;
    static Scanner INPUT = new Scanner(System.in);
    static final String[] VALID_MONTHS = new String[] {
            "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    /**
     * PRIVATE FIELDS
     */
    private String givenNames;
    private String surname;
    private String startPeriod;
    private String endPeriod;
    private int annualIncome;
    private int grossIncome;
    private int incomeTax;
    private int netIncome;
    private int superValue;
    private float superRate;

    /**
     * USER INPUT
     */

    /* Get given names */
    public void getGivenNames() {
        String input = null;

        while (true) {
            // FIXME: 2019-08-04 : when using print instead of println, it wont work
            // FIXME: 2019-08-04 Whenever print after println, print does not work
            System.out.println("Please input your name: ");
            input = INPUT.nextLine();
            try {
                Integer.parseInt(input);
                System.out.println("Invalid name");
            } catch (Exception e) {
                break;
            }
        }

        givenNames = input;
    }

    /* Get surname */
    public void getSurname() {
        String input = null;

        while (true) {
            System.out.println("Please input your surname: ");
            input = INPUT.nextLine();
            try {
                Integer.parseInt(input);
                System.out.println("Invalid surname");
            } catch (Exception e) {
                break;
            }
        }

        surname = input;
    }

    /* Get annual salary */
    public void getAnnualSalary() {
        String input;

        do {
            System.out.println("Please enter your annual salary: ");
            input = INPUT.nextLine();
        } while (!isNumerical(input));

        annualIncome = Integer.parseInt(input);
    }

    /* Get super rate */
    public void getSuperRate() {
        String input;
        do {
            System.out.println("Please enter your super rate: ");
            input = INPUT.nextLine();
        } while (!isNumerical(input));

        superRate = Integer.parseInt(input)/HUNDRED;
    }

    /* Get payment start date */
    public void getStartDate() {
        String[] splittedDate;

        // Continuously ask for correct input
        do {
            System.out.println("Please enter your start date: ");
            startPeriod = INPUT.nextLine();
            splittedDate = startPeriod.split("\\s+"); // regex for white space
        } while (!isDateStandard(splittedDate));

        // if date is valid, check if date number is in standard format i.e 02, 12
        if (splittedDate[0].length() < 2) {
            startPeriod = standardiseDate(splittedDate[0]) + " " + splittedDate[1];
        }
    }

    /* Get payment end date */
    public void getEndDate() {
        String[] splittedDate;

        // Continuously ask for correct input
        do {
            System.out.println("Please enter your end date: ");
            endPeriod = INPUT.nextLine();
            splittedDate = endPeriod.split("\\s+"); // regex for white space
        } while (!isDateStandard(splittedDate));

        // if date is valid, check if date number is in standard format i.e 02, 12
        if (splittedDate[0].length() < 2) {
            endPeriod = standardiseDate(splittedDate[0]) + " " + splittedDate[1];
        }
    }

    /**
     * INPUT VALIDATION
     */

    /* Check if date is 1 number, then add a leading 0 */
    public boolean isDateStandard(String[] splittedDate) {
        if (splittedDate.length == 2) {
            if (isDateValid(splittedDate[0]) && isMonthValid(splittedDate[1])) {
                return true;
            } else {
                System.out.println("Invalid date format");
                return false;
            }
        } else {
            System.out.println("Invalid date format");
            return false;
        }
    }

    /* Check if month is valid (string and abbreviation) */
    public boolean isMonthValid(String date) {
        boolean valid = Arrays.stream(VALID_MONTHS).anyMatch(date::equals);
        if (!valid) {
            System.out.println("Invalid month");
        }
        return valid;
    }

    /* Check if date is valid (numerical and 2 digits) */
    public boolean isDateValid(String date) {
        if (date.length() > 2) {
            System.out.println("Date too long");
            return false;
        } else if (date == "0") {
            return  false;
        } else {
            return isNumerical(date);
        }
    }

    /* Check if input is numerical integer*/
    public boolean isNumerical(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input is not numerical");
            return false;
        }
    }

    /* Convert non-standard date format to standard. i.e 1 March -> 01 March */
    public String standardiseDate(String date) {
        String[] splittedDate = date.split("\\s+");
        splittedDate[0] = "0" + splittedDate[0];
        return String.join(" ", splittedDate);
    }

    /**
     * CALCULATIONS
     */

    /* Calculate gross income */
    public void calculateGrossIncome() {
        grossIncome = (int) Math.round(annualIncome/NUM_MONTHS);
    }

    /* Calculate income tax */
    public void calculateIncomeTax() {
        TaxCalculator taxCalculator = new TaxCalculator(annualIncome);
        incomeTax = taxCalculator.calculateTax();
    }

    /* Calculate net income */
    public void calculateNetIncome() {
        netIncome = grossIncome - incomeTax;
    }

    /* Calculate super */
    public void calculateSuper() {
        superValue = (int) Math.round(grossIncome * superRate);
    }

    /**
     * PAYSLIP GENERATION
     */

    /* Display payslip */
    public void displayPayslip() {
        displayHeading();
        displayName();
        displayPayPeriod();
        displayGrossIncome();
        displayIncomeTax();
        displayNetIncome();
        displaySuper();
        displayThanks();
    }

    /* Display name */
    public void displayName() {
        System.out.println("Name: " + givenNames + " " + surname);
    }

    /* Display pay period */
    public void displayPayPeriod() {
        System.out.println("Pay Period: " + startPeriod + " - " + endPeriod);
    }

    /* Display gross income */
    public void displayGrossIncome() {
        System.out.println("Gross Income: " + grossIncome);
    }

    /* Display income tax */
    public void displayIncomeTax() {
        System.out.println("Income Tax: " + incomeTax);
    }

    /* Display net income */
    public void displayNetIncome() {
        System.out.println("Net Income: " + netIncome);
    }

    /* Display super */
    public void displaySuper() {
        System.out.println("Super: " + superValue);
    }

    /* Display greetings */
    public void displayGreetings() {
        System.out.println("Welcome to the payslip generator\n");
    }

    /* Display heading */
    public void displayHeading() {
        System.out.println("\nYour payslip has been generated:\n");
    }

    /* Display thank you */
    public void displayThanks() {
        System.out.println("\nThank you for using MYOB!\n");
    }
}
