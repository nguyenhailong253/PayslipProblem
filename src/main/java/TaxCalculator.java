/// author: Long Nguyen (long.nguyen@myob.com)

import java.util.HashMap;

public class TaxCalculator {
    /**
     * CONSTANTS
     */
    static final int NUM_MONTHS = 12;

    /**
     * PRIVATE FIELDS
     */
    private int annualIncome;
    // use wrapper class Integer instead of primitive int
    private HashMap<Integer, Integer> taxLevelBase = new HashMap<>();
    private HashMap<Integer, Integer>  taxDefaultDeduction = new HashMap<>();
    private HashMap<Integer, Double> taxRate = new HashMap<>();

    /**
     * CONSTRUCTOR
     */
    public TaxCalculator(int income) {
        annualIncome = income;
        generateTaxLevelBase();
        generateTaxDefaultDeduction();
        generateTaxRate();
    }

    /**
     *  INITIALISATION
     */

    /* Initialize values of starting tax value corresponding to its level */
    private void generateTaxLevelBase() {
        taxLevelBase.put(1, 0);
        taxLevelBase.put(2, 18201);
        taxLevelBase.put(3, 37001);
        taxLevelBase.put(4, 87001);
        taxLevelBase.put(5, 180001);
    }

    /* Initialize default deduction for each tax level */
    private void generateTaxDefaultDeduction() {
        taxDefaultDeduction.put(1, 0);
        taxDefaultDeduction.put(2, 0);
        taxDefaultDeduction.put(3, 3572);
        taxDefaultDeduction.put(4, 19822);
        taxDefaultDeduction.put(5, 54232);
    }

    /* Initialize tax rate for each level */
    private void generateTaxRate() {
        taxRate.put(1, 0.0);
        taxRate.put(2, 0.19);
        taxRate.put(3, 0.325);
        taxRate.put(4, 0.37);
        taxRate.put(5, 0.45);
    }

    /**
     * HELPER METHODS
     */

    /* Determine tax level */
    public int getTaxLevel() {
        int taxLevel = 0;
        Integer level;
        Integer baseValue;

        for (HashMap.Entry<Integer, Integer> entry: taxLevelBase.entrySet()) {
            // get current level and base value
            level = entry.getKey();
            baseValue = entry.getValue();

            // if income is larger than current base value, income is AT LEAST from this level
            if (annualIncome >= baseValue) {
                taxLevel = level;
            }
        }
        return taxLevel;
    }

    /* Determine default tax deduction */
    public int getDefaultDeduction(int taxLevel) {
        return taxDefaultDeduction.get(taxLevel);
    }

    /* Determine tax rate */
    public double getTaxRate(int taxLevel) {
        return taxRate.get(taxLevel);
    }

    /* Calculate income tax */
    public int calculateTax() {
        // obtain tax levels, default deduction, tax rate
        int taxLevel = getTaxLevel();
        int defaultDeduction = getDefaultDeduction(taxLevel);
        double rate = getTaxRate(taxLevel);

        // calculate tax
        int taxBase = taxLevelBase.get(taxLevel) - 1;
        int tax = (int) Math.round((defaultDeduction + (annualIncome - taxBase)*rate)/NUM_MONTHS);
        return tax;
    }
}
