package zhangjie.entity;

import java.time.LocalDate;

public class BarChart {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int age;
	private LocalDate time;

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}

	private int monthSalesVolume;
	private int commoditySalesVolume;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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
