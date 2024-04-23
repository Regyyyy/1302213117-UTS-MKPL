package lib;

import java.util.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private Date dateJoined;

	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private boolean gender; //true = Laki-laki, false = Perempuan
	
	// Class salary
	private Salary employeeSalary;
	
	private Person spouse;

	private List<Person> children;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, Date dateJoined, boolean isForeigner, boolean gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.dateJoined = dateJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		children = new LinkedList<Person>();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			employeeSalary.setMonthlySalary(3000000);
			if (isForeigner) {
				employeeSalary.setMonthlySalary((int) (3000000 * 1.5));
			}
		}else if (grade == 2) {
			employeeSalary.setMonthlySalary(5000000);
			if (isForeigner) {
				employeeSalary.setMonthlySalary((int) (3000000 * 1.5));
			}
		}else if (grade == 3) {
			employeeSalary.setMonthlySalary(7000000);
			if (isForeigner) {
				employeeSalary.setMonthlySalary((int) (3000000 * 1.5));
			}
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
		
		return TaxFunction.calculateTax(employeeSalary.getMonthlySalary(), employeeSalary.getOtherMonthlyIncome(), monthWorkingInYear, employeeSalary.getAnnualDeductible(), spouse.getIdNumber().equals(""), children.size());
	}
}
