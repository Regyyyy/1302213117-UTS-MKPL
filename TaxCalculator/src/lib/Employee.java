package lib;

import java.util.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private Person employeeInfo;
	private Date dateJoined;
	private int monthWorkingInYear;
	private boolean isForeigner;
	private Salary employeeSalary;
	private Person spouse;
	private List<Person> children;

	private int grade1Salary = 3000000;
	private int grade2Salary = 5000000;
	private int grade3Salary = 7000000;
	private double foreignerSalaryRaise = 1.5;

	private int freeTaxSalary = 54000000;
	private int marriedSalaryRaise = 4500000;
	private int kidsSalaryRaise = 1500000;
	private double taxPercentage = 0.05;
	
	public Employee(String employeeId, Person employeeInfo, Date dateJoined, boolean isForeigner) {
		this.employeeId = employeeId;
		this.employeeInfo = employeeInfo;
		this.dateJoined = dateJoined;
		this.isForeigner = isForeigner;
		
		children = new LinkedList<Person>();
	}
	
	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			employeeSalary.setMonthlySalary(grade1Salary);
		}else if (grade == 2) {
			employeeSalary.setMonthlySalary(grade2Salary);
		}else if (grade == 3) {
			employeeSalary.setMonthlySalary(grade3Salary);
		}

		if (isForeigner) {
			employeeSalary.setMonthlySalary((int) (employeeSalary.getMonthlySalary() * foreignerSalaryRaise));
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.employeeSalary.setAnnualDeductible(deductible);
	}
	
	public void setAdditionalIncome(int income) {	
		this.employeeSalary.setOtherMonthlyIncome(income);
	}
	
	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}
	
	public void addChild(Person child) {
		children.add(child);
	}
	
	public int getAnnualIncomeTax() {
		
		LocalDate date = LocalDate.now();

		int tax = 0;
		int numberOfChildren = children.size();
		
		if (date.getYear() == dateJoined.getYear()) {
			monthWorkingInYear = date.getMonthValue() - dateJoined.getMonth() + 1;
		}else {
			monthWorkingInYear = 12;
		}
		
		if (monthWorkingInYear > 12) {
			System.err.println("More than 12 month working per year");
		}
		
		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}
		
		if (spouse.getIdNumber().equals("")) {
			tax = (int) Math.round(taxPercentage * (((employeeSalary.getMonthlySalary() + employeeSalary.getOtherMonthlyIncome()) * monthWorkingInYear) - employeeSalary.getAnnualDeductible() - (freeTaxSalary + marriedSalaryRaise + (numberOfChildren * kidsSalaryRaise))));
		}else {
			tax = (int) Math.round(taxPercentage * (((employeeSalary.getMonthlySalary() + employeeSalary.getOtherMonthlyIncome()) * monthWorkingInYear) - employeeSalary.getAnnualDeductible() - freeTaxSalary));
		}
		
		if (tax < 0) {
			return 0;
		}else {
			return tax;
		}
	}
}
