package com.exampleproject.web.rest.server;

import com.exampleproject.engine.DataBaseTest;
import com.exampleproject.model.shared.CompanyDto;
import com.exampleproject.model.shared.TestDto;
import com.exampleproject.web.rest.Company;
import com.exampleproject.web.rest.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestExample {

    private final ApplicationContext applicationContext;
    private final DataBaseTest dataBaseTest;
    private final CompanyDAO companyDAO;

    @Autowired
    public RestExample(ApplicationContext applicationContext, DataBaseTest dataBaseTest, CompanyDAO companyDAO) {
        this.applicationContext = applicationContext;
        this.dataBaseTest = dataBaseTest;
        this.companyDAO = companyDAO;
    }

    @RequestMapping("/test")
    public TestDto test() {
        TestDto dto = createDto();
        dto.setMessage("It's a test string from server");
        return dto;
    }

    @RequestMapping("/testDb")
    public boolean testDB() {
        try {
            dataBaseTest.tryIt();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @RequestMapping("/test/{someText}")
    public TestDto testAdditional(@PathVariable String someText) {
        TestDto dto = createDto();
        dto.setMessage("It's a test string from server and you've given me " + someText);
        return dto;
    }

    protected TestDto createDto() {
        return applicationContext.getBean(TestDto.class);
    }

    protected CompanyDto createMyDto() {
        return applicationContext.getBean(CompanyDto.class);
    }

    @RequestMapping("/company")
    public List<CompanyDto> getDto() {

        List<Company> listDB =  companyDAO.getAllObjects();
        List<CompanyDto> listDTO = new ArrayList<CompanyDto>();

        for(Company company : listDB) {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setCompanyName(company.getCompanyName());
            companyDto.setAddress(company.getAddress());
            companyDto.setEmail(company.getEmail());
            companyDto.setId(company.getId().intValue());
            companyDto.setEmail(company.getEmail());
            companyDto.setIec(company.getIec());
            companyDto.setTelNumber(company.getTelNumber());
            companyDto.setTin(company.getTin());

            listDTO.add(companyDto);
        }
        return listDTO;
    }

    @RequestMapping("/add")
    public void add(@RequestBody CompanyDto companyDto) {
        Company company = new Company();
        //company.setId(BigInteger.valueOf(companyDto.getId()));
        company.setCompanyName(companyDto.getCompanyName());
        company.setAddress(companyDto.getAddress());
        company.setIec(companyDto.getIec());
        company.setTin(companyDto.getTin());
        company.setEmail(companyDto.getEmail());
        company.setTelNumber(companyDto.getTelNumber());

        companyDAO.add(company);
    }

}
