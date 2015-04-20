package br.ufes.inf.nemo.javahostel.application;

public class UnderAgeException extends Exception {
	private int age;
	
	public UnderAgeException(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
	
	
}
