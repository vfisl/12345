package com.investmentapp.service;

import java.util.List;

import com.investmentapp.exception.PlanNotFoundException;
import com.investmentapp.model.Investment;

public interface IInvestmentService {
void addInvestment(Investment investment);
void updateInvestment(Investment investment)throws PlanNotFoundException;
void deleteInvestment(int planIdt);

List<Investment> getByType(String type)throws PlanNotFoundException;
List<Investment> getByPurpose(String purpose)throws PlanNotFoundException;
List<Investment> getByRiskAndTerm(String risk,int term)throws PlanNotFoundException;
List<Investment> getAll();

double calculateMaturity(Investment investment);
Investment getById(int planId)throws PlanNotFoundException;
void updateInvestmentAmount(int planId,double amount);
List<String> getAllTypes();
List<Investment> getByRisk(String risk);

}
