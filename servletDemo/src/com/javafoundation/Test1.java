package com.javafoundation;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*int i=2112341234;
		System.out.println(i);
		byte a=125;
		long b=1234567890;*/
		
		int[] a = {1, 2};
		int[] b = a;
		b[1] = 5;
		System.out.println(a[1]);
		
		Employee employee=new Employee();
		employee.age=10;
		changeEmpoyee(employee);
		System.out.println(employee.age);
	}
	
	public static void changeEmpoyee(Employee emp){
		emp=new Employee();
		emp.age=12;
	}
}

class Employee{
	public int age;
}
