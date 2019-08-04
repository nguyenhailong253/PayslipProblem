/// author: Long Nguyen (long.nguyen@myob.com)

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class PayslipTest {

    @Test
    public void testIsNumerical() {
        Payslip ps = new Payslip();

        assertThat(ps.isNumerical("1"), is(equalTo(true)));
        assertThat(ps.isNumerical("hello"), is(equalTo(false)));
    }

    @Test
    public void testIsDateValid() {
        Payslip ps = new Payslip();

        assertThat(ps.isDateValid("10000000"), is(equalTo(false)));
        assertThat(ps.isDateValid("0"), is(equalTo(false)));
        assertThat(ps.isDateValid("9"), is(equalTo(true)));
        assertThat(ps.isDateValid("31"), is(equalTo(true)));
    }

    @Test
    public void testIsMonthValid() {
        Payslip ps = new Payslip();

        assertThat(ps.isMonthValid("Dec"), is(equalTo(false)));
        assertThat(ps.isMonthValid("December"), is(equalTo(true)));
        assertThat(ps.isMonthValid("Hello"), is(equalTo(false)));
        assertThat(ps.isMonthValid("12"), is(equalTo(false)));
        assertThat(ps.isMonthValid("12 March"), is(equalTo(false)));
    }

    @Test
    public void testIsDateStandard() {
        Payslip ps = new Payslip();
        String[] case1 = new String[] {"1", "November"};
        String[] case2 = new String[] {"31", "March"};
        String[] case3 = new String[] {"0", "February"};
        String[] case4 = new String[] {"16", "Oct"};
        String[] case5 = new String[] {"16212", "Oct"};

        assertThat(ps.isDateStandard(case1), is(equalTo(true)));
        assertThat(ps.isDateStandard(case2), is(equalTo(true)));
        assertThat(ps.isDateStandard(case3), is(equalTo(false)));
        assertThat(ps.isDateStandard(case4), is(equalTo(false)));
        assertThat(ps.isDateStandard(case5), is(equalTo(false)));
    }
}
