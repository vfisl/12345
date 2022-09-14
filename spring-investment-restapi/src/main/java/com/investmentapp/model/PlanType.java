package com.investmentapp.model;

public enum PlanType {
	mutual("Mutual Fund"),
	ppf("Public Provident Fund"),
	ulip("Unit Limited investment Plan"),
	senior("senior Pension Scheme"),
	fd("Fixed Deposit");
public String type;

private PlanType(String type) {
	this.type = type;
}





	
}
