package EndOfCourse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PAssign09 {

	public static void main(String[] args) throws IOException {

		double salaryInit = 0;
		double salaryFin = 0;

		try (
				// Create an input stream
				DataInputStream input = new DataInputStream(
						new BufferedInputStream(new FileInputStream("src/EndOfCourse/employeeData.dat")));

				// Create an output stream
				DataOutputStream output = new DataOutputStream(
						new BufferedOutputStream(new FileOutputStream("src/EndOfCourse/employeeDatProcessed.dat")));) {
			Employee employee;
			while (input.available() != 0) {
				employee = new Employee(input.readUTF(), input.readDouble(), input.readDouble());
				double salary = employee.getSalary();
				salaryInit += employee.getSalary();
				if (salary <= 30000) {
					if (employee.getYoS() <= 2) {
						employee.processRaise(3.00);

					} else {
						employee.processRaise(2.50);

					}
				} else if (salary <= 60000) {
					if (employee.getYoS() <= 6) {
						employee.processRaise(2.25);

					} else {
						employee.processRaise(2.00);

					}

				} else if (salary <= 80000) {
					if (employee.getYoS() <= 6) {
						employee.processRaise(1.75);
					} else {
						employee.processRaise(1.50);
					}
				} else if (salary <= 100000) {
					if (employee.getYoS() <= 6) {
						employee.processRaise(1.25);
					} else
						employee.processRaise(1.00);
				}

				salaryFin += employee.getSalary();
				output.writeUTF(employee.getNumEmployee());
				output.writeDouble(employee.getSalary());
				output.writeDouble(employee.getYoS());

			}
			System.out.printf("Total Salary Before: $%,.2f%n", salaryInit);
			System.out.printf("Total Salary Before: $%,.2f%n", salaryFin);
		} catch (FileNotFoundException e) {
			System.out.println("Your file was not found");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred during the write");
		}

	}
}

class Employee {
	private String numEmployee;
	private double salary;
	private double YoS;

	public Employee() {
		setNumEmployee("");
		setSalary(0.0);
		setYoS(1.0);
	}

	public Employee(String name, double salary, double YoS) {
		this.numEmployee = name;
		this.salary = salary;
		this.YoS = YoS;

	}

	private void setYoS(double yos) {
		this.YoS = yos;

	}

	public String getNumEmployee() {
		return numEmployee;
	}

	public double getSalary() {
		return salary;
	}

	public double getYoS() {
		return YoS;
	}

	private void setSalary(double currentSal) {
		this.salary = currentSal;
	}

	private void setNumEmployee(String Employee) {
		this.numEmployee = Employee;

	}

	public void processRaise(double raise) {
		this.setSalary(this.getSalary() * (1 + (raise / 100)));
	
	}

	public String toString() {
		return String.format("%s Salary: $%,.2f Years of Service: %.1f %n", getNumEmployee(), getSalary(), getYoS());

	}
}