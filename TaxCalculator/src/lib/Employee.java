package lib;

import java.util.Date;
import java.time.LocalDate;
import java.time.Month;
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
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == dateJoined.getYear()) {
			monthWorkingInYear = date.getMonthValue() - dateJoined.getMonth() + 1;
		}else {
			monthWorkingInYear = 12;
		}
		
		/**
		 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
		 * 
		 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
		 * 
		 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
		 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
		 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
		 * 
		 */

		int tax = 0;
		
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
		
		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}
		
		if (isMarried) {
			tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - (54000000 + 4500000 + (numberOfChildren * 1500000))));
		}else {
			tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - 54000000));
		}
		
		if (tax < 0) {
			return 0;
		}else {
			return tax;
		}

		return TaxFunction.calculateTax(employeeSalary.getMonthlySalary(), employeeSalary.getOtherMonthlyIncome(), monthWorkingInYear, employeeSalary.getAnnualDeductible(), spouse.getIdNumber().equals(""), children.size());
	}
}
