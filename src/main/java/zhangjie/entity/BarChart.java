package zhangjie.entity;

import java.time.LocalDate;

public class BarChart {
	private String name;
	private int age;
	private LocalDate time;
	private int monthSalesVolume;
	private int commoditySalesVolume;
	private int evaluationNumber;

	public int getEvaluationNumber() {
		return evaluationNumber;
	}

	public void setEvaluationNumber(int evaluationNumber) {
		this.evaluationNumber = evaluationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}

	public int getMonthSalesVolume() {
		return monthSalesVolume;
	}

	public void setMonthSalesVolume(int monthSalesVolume) {
		this.monthSalesVolume = monthSalesVolume;
	}

	public int getCommoditySalesVolume() {
		return commoditySalesVolume;
	}

	public void setCommoditySalesVolume(int commoditySalesVolume) {
		this.commoditySalesVolume = commoditySalesVolume;
	}
}
