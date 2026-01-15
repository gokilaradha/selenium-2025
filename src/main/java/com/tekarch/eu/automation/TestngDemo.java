package com.tekarch.eu.automation;
import org.testng.annotations.Test;
public class TestngDemo {
	//_,[A-Z] ,[a-z] ,[0-9] -->default priority 
	@Test(priority = 1)
	public void demo1() {
		System.out.println("Running demo method 2");
	}
	@Test(priority = 2)
	public void demo() {
		System.out.println("Running demo method 1");
	}
	@Test(priority = 3)
	public void demo2() {
		System.out.println("Running demo method 4");
	}
	@Test(priority = 4)
	public void demo3() {
		System.out.println("Running demo method 3");
	}

}
