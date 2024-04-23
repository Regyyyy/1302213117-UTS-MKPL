package lib;

import java.lang.reflect.Constructor;

public class Salary {
    private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

    public Salary(int monthlySalary, int otherMonthlyIncome, int annualDeductible) {
        this.monthlySalary = monthlySalary;
        this.otherMonthlyIncome = otherMonthlyIncome;
        this.annualDeductible = annualDeductible;
    }

    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
    public int getMonthlySalary() {
        return monthlySalary;
    }

    public void setOtherMonthlyIncome(int otherMonthlyIncome) {
        this.otherMonthlyIncome = otherMonthlyIncome;
    }

    public int getOtherMonthlyIncome() {
        return otherMonthlyIncome;
    }

    public void setAnnualDeductible(int annualDeductible) {
        this.annualDeductible = annualDeductible;
    }

    public int getAnnualDeductible() {
        return annualDeductible;
    }
}
