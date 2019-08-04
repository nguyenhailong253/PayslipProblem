/// author: Long Nguyen (long.nguyen@myob.com)

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TaxCalculatorTest {

    @Test
    public void testGetTaxLevel() {
        TaxCalculator level1 = new TaxCalculator(18200);
        assertThat(level1.getTaxLevel(), is(equalTo(1)));

        TaxCalculator level2 = new TaxCalculator(36999);
        assertThat(level2.getTaxLevel(), is(equalTo(2)));

        TaxCalculator level3 = new TaxCalculator(37001);
        assertThat(level3.getTaxLevel(), is(equalTo(3)));

        TaxCalculator level4 = new TaxCalculator(179999);
        assertThat(level4.getTaxLevel(), is(equalTo(4)));

        TaxCalculator level5 = new TaxCalculator(1000000000);
        assertThat(level5.getTaxLevel(), is(equalTo(5)));
    }

    @Test
    public void testGetDefaultDeduction() {
        TaxCalculator level1 = new TaxCalculator(18200);
        assertThat(level1.getDefaultDeduction(1), is(equalTo(0)));

        TaxCalculator level2 = new TaxCalculator(36999);
        assertThat(level2.getDefaultDeduction(2), is(equalTo(0)));

        TaxCalculator level3 = new TaxCalculator(37001);
        assertThat(level3.getDefaultDeduction(3), is(equalTo(3572)));

        TaxCalculator level4 = new TaxCalculator(179999);
        assertThat(level4.getDefaultDeduction(4), is(equalTo(19822)));

        TaxCalculator level5 = new TaxCalculator(1000000000);
        assertThat(level5.getDefaultDeduction(5), is(equalTo(54232)));
    }

    @Test
    public void testGetTaxRate() {
        TaxCalculator level1 = new TaxCalculator(18200);
        assertThat(level1.getTaxRate(1), is(equalTo(0.0)));

        TaxCalculator level2 = new TaxCalculator(36999);
        assertThat(level2.getTaxRate(2), is(equalTo(0.19)));

        TaxCalculator level3 = new TaxCalculator(37001);
        assertThat(level3.getTaxRate(3), is(equalTo(0.325)));

        TaxCalculator level4 = new TaxCalculator(179999);
        assertThat(level4.getTaxRate(4), is(equalTo(0.37)));

        TaxCalculator level5 = new TaxCalculator(1000000000);
        assertThat(level5.getTaxRate(5), is(equalTo(0.45)));
    }

    @Test
    public void testCalculateTax() {
       TaxCalculator calculator = new TaxCalculator(60050);
       assertThat(calculator.calculateTax(), is(equalTo(922)));
    }
}
