package my.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.entity.Company;
import my.repository.CompanyRepository;
import my.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	private List<Company> companies = new ArrayList<>();
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public Company addCompany(Company company) {
		Company saveCompany = companyRepository.save(company);
		return saveCompany;
	}

	public void delete(int id) {
		companyRepository.delete(id);
	}

	public Company getByName(String name) {
		return companyRepository.findByName(name);
	}

	public Company editCompany(Company company) {
		Company saveCompany = companyRepository.save(company);
		return saveCompany;
	}

	public Iterable<Company> getAll() {
		return companyRepository.findAll();
	}
	
	
	public Company getById(int id){
		return companyRepository.findOne(id);
	}
	public Iterable<Company> getAllChildren(Company parent){
		return companyRepository.findAllChildren(parent);
	}
	public Iterable<Company> getAllRoot(){
		return companyRepository.findAllRoot();
	}
	
	public List<Company> getTree(Iterable<Company> company){
		for (Company company2 : company) {
//			company2.setLevel(level);
			companies.add(company2);
			if (companyRepository.findAllChildren(company2) != null){
//				level +=1;
				getTree(companyRepository.findAllChildren(company2));
			}
		}
		return companies;

	}
	


}
