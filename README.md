# Basic Payslip Kata
A console application that allows user to enter name, annual income, payment period and then generate a payslip.

## Requirements

    JDK 11.0.4
    JUnit 4.12
    
## Rules

- Pay period = per calendar month
- Gross income = annual salary / 12 months
- Income tax = based on the tax table provided below
- Net income = gross income - income tax
- Super = gross income x super rate
- All calculation results should be rounded to the whole dollar. 
If >= 50 cents round up to the next dollar increment, otherwise round down.

Tax information table

| Taxable Income     | Tax on this Income                         |
|--------------------|--------------------------------------------|
| $0 - $18,200       | Nil                                        |
| $18,201 - $37,000  | 19c for each $1 over $18,200               |
| $37,001 - $87,000  | $3,572 plus 32.5c for each $1 over $37,000 |
| $87,001 - $180,000 | $19,822 plus 37c for each $1 over $87,000  |
| $180,001 and over  | $54,232 plus 45c for each $1 over $180,000 |

## Design

### Classes Hierarchy

- PayslipGenerator
    - Payslip
        - TaxCalculator
        
PayslipGenerator contains instance of Payslip and uses its functions to gather, process information and display it.

Payslip contains instance of TaxCalculator and uses its functions to calculate income tax

### PayslipGenerator Class

Private fields
- currentPayslip (Payslip): instance of Payslip class

Methods
- collectInformation(): call methods from Payslip to get user's name, income, super rate, etc.
- processInformation(): call methods from Payslip to calculate income, super, tax, etc.
- generatePayslip(): Generate payslip with processed information
- run(): run application by calling 3 methods above

### Payslip Class

Constants
- NUM_MONTHS (int): number of months per year = 12
- HUNDRED (float): floating point of 100.0f
- VALID_MONTHS (String[]): Array of months' names
- INPUT (Scanner): instance of Scanner class, taking in System.in as param

Private fields
- givenNames (String)
- surname (String)
- startPeriod (String)
- endPeriod (String)
- annualIncome (int)
- grossIncome (int)
- incomeTax (int)
- netIncome (int)
- superValue (int)
- superRate (float)

Methods
- getGivenNames()
- getSurname()
- getAnnualSalary()
- getSuperRate()
- getStartDate()
- getEndDate()

- isDateStandard(String[] splittedDate): check if date follow standard format, i.e 01 March, 31 December
- isMonthValid(String date): check if month is correct string
- isDateValid(String date): check if date number is of 2 digits
- isNumerical(String input): check if a string is a number
- standardiseDate(String date): convert single-digit date to standard 2 digits

- calculateGrossIncome()
- calculateIncomeTax()
- calculateNetIncome()
- calculateSuper()

- displayPayslip()
- displayName()
- displayPayPeriod()
- displayGrossIncome()
- displayIncomeTax()
- displayNetIncome()
- displaySuper()
- displayGreetings()
- displayHeading()
- displayThanks()

### TaxCalculator Class

Constants
- NUM_MONTHS (int): number of months per year = 12

Private fields
- annualIncome (int): user input income
- taxLevelBase (HashMap<Integer, Integer>): hashmap with key = tax level (1 to 5 according to table), value = lower range value of each tax level


    {
        1: 0,
        2: 182001,
        3: 37001,
        4: 87001,
        5: 180001
    }
    
- taxDefaultDeduction (HashMap<Integer, Integer>): hashmap with key = tax level and value = default deduction money for each level


    {
        1: 0,
        2: 0,
        3: 3572,
        4: 19822,
        5: 54232
    } 

- taxRate (HashMap<Integer, Double>): hashmap with key = tax level and value = tax rate corresponding to tax level


    {
        1: 0.0,
        2: 0.19,
        3: 0.325,
        4: 0.37,
        5: 0.45
    }

Methods
- constructor(int income): initialise all private fields

- generateTaxLevelBase(): initilise taxLevelBase private field

- generateTaxDefaultDeduction(): initialise taxDefaultDeduction

- generateTaxRate(): initialise taxRate private field

- getTaxLevel(): based on annual income, return suitable tax level

- getDefaultDeduction(int taxLevel): based on tax level, determine the default deduction

- getTaxRate(int taxLevel): based on tax level, determine tax rate

- calculateTax(): calculate tax using income, tax rate, default deduction

## Demo

    Welcome to the payslip generator
    
    Please input your name: Long
    Please input your surname: Nguyen
    Please enter your annual salary: 60050
    Please enter your super rate: 9
    Please enter your start date: 1 March
    Please enter your end date: 31 March
    
    Your payslip has been generated:
    
    Name: Long Nguyen
    Pay Period: 01 March - 31 March
    Gross Income: 5004
    Income Tax: 922
    Net Income: 4082
    Super: 450
    
    Thank you for using MYOB!
    





