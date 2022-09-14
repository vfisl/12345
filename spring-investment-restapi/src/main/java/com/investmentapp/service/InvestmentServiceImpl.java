package com.investmentapp.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.investmentapp.exception.PlanNotFoundException;
import com.investmentapp.model.Investment;
import com.investmentapp.repositary.IInvestmentRepositary;
@Service
public class InvestmentServiceImpl implements IInvestmentService{
private IInvestmentRepositary investmentRepositary;
	
@Autowired
	public void setInvestmentRepositary(IInvestmentRepositary investmentRepositary) {
		this.investmentRepositary = investmentRepositary;
	}

@Override
public void addInvestment(Investment investment) {
	investmentRepositary.save(investment);
	
}

@Override
public void updateInvestment(Investment investment) throws PlanNotFoundException {
	investmentRepositary.save(investment);
	
}

@Override
public void deleteInvestment(int planIdt) {
	investmentRepositary.deleteById(planIdt);
	
}

@Override
public List<Investment> getByType(String type) {
	List<Investment> investments=investmentRepositary.findByType(type);
	if(investments.isEmpty())
		throw new PlanNotFoundException("Plan with this type not found");
	return investments;
}

@Override
public List<Investment> getByPurpose(String purpose) {
	List<Investment> investments=investmentRepositary.findByPurpose(purpose);
	if(investments.isEmpty())
		throw new PlanNotFoundException("Plan with this purpose not found");
	return investments;
}

@Override
public List<Investment> getByRiskAndTerm(String risk, int term) {
	List<Investment> investments=investmentRepositary.findByRiskAndTerm(risk, term);
	if(investments.isEmpty())
		throw new PlanNotFoundException("Plan with this term not found");
	return investments;
}

@Override
public List<Investment> getAll() {
	return investmentRepositary.findAll();
}

@Override
public double calculateMaturity(Investment investment) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public Investment getById(int planId) throws PlanNotFoundException {
	
	return investmentRepositary
			.findById(planId).orElseThrow(()->new PlanNotFoundException("Invalid Id"));
}

@Override
@Transactional
public void updateInvestmentAmount(int planId, double amount) {
	investmentRepositary.updateInvestmentAmount(planId, amount);
}

@Override
public List<String> getAllTypes() {
	return investmentRepositary.getAllTypes();
}

@Override
public List<Investment> getByRisk(String risk) {
	return investmentRepositary.findByRisk(risk);
}

}