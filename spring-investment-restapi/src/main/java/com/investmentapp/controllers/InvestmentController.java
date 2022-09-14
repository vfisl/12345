package com.investmentapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.investmentapp.model.Investment;
import com.investmentapp.service.IInvestmentService;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("investment-api")
public class InvestmentController {
	
	IInvestmentService investmentService;
	@Autowired
	public void setInvestmentService(IInvestmentService investmentService) {
		this.investmentService = investmentService;
	}
	@PostMapping("/investments")
	public ResponseEntity<Void> addInvestment(Investment investment) {
		investmentService.addInvestment(investment);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	@PutMapping("/investments")
	public ResponseEntity<String> updateInvestment(@RequestBody Investment investmentt) {
		investmentService.updateInvestment(investmentt);
		return ResponseEntity.accepted().body("Updated");
	}
	@DeleteMapping("/investments/{planId}")
	public ResponseEntity<Void> deleteInvestment(@PathVariable("planId")int planIdt) {
		investmentService.deleteInvestment(planIdt);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@GetMapping("/investments/type")
	public ResponseEntity<List<Investment>> getByType(@RequestParam("type")String type){
		List<Investment> investments=investmentService.getByType(type);
		return ResponseEntity.ok().body(investments);
	}
	@GetMapping("/investments/purpose/{purpose}")
	public ResponseEntity<List<Investment>> getByPurpose(@PathVariable("purpose") String purpose){
		List<Investment> investments=investmentService.getByPurpose(purpose);
		HttpHeaders headers=new HttpHeaders();
		headers.add("desc","Getting all investments by purpose");
		headers.add("info","Investments REST API by purpose");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(investments);
	}
	@GetMapping("/investments/risk/{risk}/term/{term}")
	public ResponseEntity<List<Investment>> getByRiskAndTerm(@PathVariable("risk")String risk,@PathVariable("term")int mterm){
		List<Investment> investments=investmentService.getByRiskAndTerm(risk, mterm);
		HttpHeaders headers=new HttpHeaders();
		headers.add("desc","All investments by risk and term");
		headers.add("info","Investments REST API by term and risk");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(investments);
		
	}
	@GetMapping("/investments/risk/{risk}")
	public ResponseEntity<List<Investment>> getByRisk(@PathVariable("risk")String risk){
		List<Investment> investments=investmentService.getByRisk(risk);
		HttpHeaders headers=new HttpHeaders();
		headers.add("desc","All investments by risk and term");
		headers.add("info","Investments REST API by term and risk");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(investments);
		
	}
@GetMapping("/investments")
	public ResponseEntity<List<Investment>> getAll(){
	List<Investment> investments=investmentService.getAll();
	HttpHeaders headers=new HttpHeaders();
	headers.add("desc","All investments");
	headers.add("info","Getting investments from db");
	ResponseEntity<List<Investment>> responseEntity=new ResponseEntity<>(investments,headers,HttpStatus.OK);
		return responseEntity;
	}

@GetMapping("/investments/planId/{id}")
	public ResponseEntity<Investment> getById(@PathVariable("id")int planId) {
	Investment investment=investmentService.getById(planId);
	HttpHeaders headers=new HttpHeaders();
	headers.add("desc","getting one investment by id");
	return ResponseEntity.ok().headers(headers).body(investment);
	}
@GetMapping("/investments/planId/{id}/amount/{amount}")
public ResponseEntity<Void> updateInvestment(@PathVariable("id")int planId,@PathVariable("amount")double amount) {
investmentService.updateInvestmentAmount(planId,amount);
return ResponseEntity.status(HttpStatus.ACCEPTED).build();
}
@GetMapping("/investments/plantypes")
public ResponseEntity<List<String>> getAllTypes(){
	List<String> planTypes=investmentService.getAllTypes();
	return ResponseEntity.ok().body(planTypes);
	}
}
