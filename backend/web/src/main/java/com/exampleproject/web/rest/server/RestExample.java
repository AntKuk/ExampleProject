package com.exampleproject.web.rest.server;

import com.exampleproject.model.shared.BankDto;
import com.exampleproject.model.shared.BasicDto;
import com.exampleproject.model.shared.CompanyDto;
import com.exampleproject.model.shared.TestDto;
import com.exampleproject.web.rest.entity.Bank;
import com.exampleproject.web.rest.service.BankService;
import com.exampleproject.web.rest.service.CompanyService;
import com.exampleproject.web.rest.service.ServiceDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestExample {

    private final ApplicationContext applicationContext;
    //private final DataBaseTest dataBaseTest;
    //private final CompanyDAO companyDAO;
    private final Map<String, ServiceDB<? extends BasicDto>> dtoMap;
/*
    @Autowired
    public RestExample(ApplicationContext applicationContext, DataBaseTest dataBaseTest, CompanyDAO companyDAO) {
        this.applicationContext = applicationContext;
        this.dataBaseTest = dataBaseTest;
        this.companyDAO = companyDAO;
    }
*/


    @Autowired
    public RestExample(ApplicationContext applicationContext,  List<ServiceDB<? extends BasicDto>> dtoList) {
        this.applicationContext = applicationContext;
        this.dtoMap = new HashMap<String, ServiceDB<? extends BasicDto>>(dtoList.size());

        for (ServiceDB<? extends BasicDto> dto : dtoList) {
            dtoMap.put(dto.getEntityName(), dto);
        }
    }

    @RequestMapping("/test")
    public TestDto test() {
        TestDto dto = createDto();
        dto.setMessage("It's a test string from server");
        return dto;
    }
/*
    @RequestMapping("/testDb")
    public boolean testDB() {
        try {
            dataBaseTest.tryIt();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
*/
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
/*
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
*/

    @GetMapping(value = "/{entity}", produces = "application/json")
    public List<? extends BasicDto> getAll(@PathVariable("entity") String entity) {
        ServiceDB<? extends BasicDto> service = dtoMap.get(entity);
        return service.getAll();
    }

    @PostMapping(value = "/Bank", consumes = "application/json", produces = "application/json")
    public void addBank(@RequestBody BankDto entity) {
        ServiceDB<BankDto> service = (BankService) dtoMap.get(entity);
        service.add(entity);
    }

    @RequestMapping(value = "/addCompany", consumes = "application/json")
    public void addCompany(@RequestBody CompanyDto entity) {
        ServiceDB<CompanyDto> service = (CompanyService) dtoMap.get("Company");
        service.add(entity);
    }

}
