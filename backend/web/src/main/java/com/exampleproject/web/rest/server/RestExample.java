package com.exampleproject.web.rest.server;

import com.exampleproject.model.shared.*;
import com.exampleproject.web.rest.dao.UserDao;
import com.exampleproject.web.rest.entity.Bank;
import com.exampleproject.web.rest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestExample {

    //private final ApplicationContext applicationContext;
    //private final DataBaseTest dataBaseTest;
    //private final CompanyDAO companyDAO;
    private final Map<String, ServiceDB<? extends BasicDto>> dtoMap;
    private final UserService userService;
    //private final BankAccountService bankAccountService;
/*
    @Autowired
    public RestExample(ApplicationContext applicationContext, DataBaseTest dataBaseTest, CompanyDAO companyDAO) {
        this.applicationContext = applicationContext;
        this.dataBaseTest = dataBaseTest;
        this.companyDAO = companyDAO;
    }
*/


    @Autowired
    public RestExample(List<ServiceDB<? extends BasicDto>> dtoList, UserService userService) {

        this.dtoMap = new HashMap<String, ServiceDB<? extends BasicDto>>(dtoList.size());

        for (ServiceDB<? extends BasicDto> dto : dtoList) {
            dtoMap.put(dto.getEntityName(), dto);
        }

        this.userService = userService;
    }
/*
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

*/
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



    @PostMapping(value = "/addCompany", consumes = "application/json")
    public void addCompany(@RequestBody CompanyDto entity) {
        ServiceDB<CompanyDto> service = (CompanyService) dtoMap.get("Company");
        service.add(entity);
    }

    @DeleteMapping(value = "/deleteCompany", consumes = "application/json")
    public void deleteCompany(@RequestBody Integer id) {
        ServiceDB<CompanyDto> service = (CompanyService) dtoMap.get("Company");
        service.deleteById(id);
    }

    @PostMapping(value = "/updateCompany", consumes = "application/json")
    public void updateCompanyById(@RequestBody CompanyDto companyDto) {
        ServiceDB<CompanyDto> service = (CompanyService) dtoMap.get("Company");
        service.updateById(companyDto);
    }



    @PostMapping(value = "/addBank", consumes = "application/json")
    public void addBank(@RequestBody BankDto entity) {
        ServiceDB<BankDto> service = (BankService) dtoMap.get("Bank");
        service.add(entity);
    }

    @DeleteMapping(value = "/deleteBank", consumes = "application/json")
    public void deleteBank(@RequestBody Integer id) {
        ServiceDB<BankDto> service = (BankService) dtoMap.get("Bank");
        service.deleteById(id);
    }

    @PostMapping(value = "/updateBank", consumes = "application/json")
    public void updateBankById(@RequestBody BankDto bankDto) {
        ServiceDB<BankDto> service = (BankService) dtoMap.get("Bank");
        service.updateById(bankDto);
    }




    @PostMapping(value = "/addTransact", consumes = "application/json")
    public void addTransact(@RequestBody TransactDto entity) {
        ServiceDB<TransactDto> service = (TransactService) dtoMap.get("Transact");
        service.add(entity);
    }

    @DeleteMapping(value = "/deleteTransact", consumes = "application/json")
    public void deleteTransact(@RequestBody Integer id) {
        ServiceDB<TransactDto> service = (TransactService) dtoMap.get("Transact");
        service.deleteById(id);
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public Boolean login(@RequestBody UserDto user) {
       boolean isUser = userService.isUser(user);

        /* if(user.getLogin().equals("admin") & user.getPwd() == "admin".hashCode()) {
            return true;
        }
        return false;*/
        return isUser;
    }



    @GetMapping(value = "/users", produces = "application/json")
    public List<UserDto> getUsers() {
        return userService.getAll();
    }

    @DeleteMapping(value = "/deleteUser", consumes = "application/json")
    public void deleteUser(@RequestBody Integer id) {
        userService.delete(id);
    }

    @PostMapping(value = "/addUser", consumes = "application/json")
    public Boolean addUser(@RequestBody UserDto user) {
        return userService.add(user);
    }


    @PostMapping(value = "/addAccount", consumes = "application/json")
    public void addAccount(@RequestBody BankAccDto entity) {
        ServiceDB<BankAccDto> service = (BankAccountService) dtoMap.get("BankAccount");
        service.add(entity);
    }

    @DeleteMapping(value = "/deleteAccount", consumes = "application/json")
    public void deleteAccount(@RequestBody Long id) {
        ServiceDB<BankAccDto> service = (BankAccountService) dtoMap.get("BankAccount");
        service.deleteById(id.intValue());
    }


    @PostMapping(value = "/getAccounts", consumes = "text/plain", produces = "application/json")
    public List<BankAccDto> getAccount(@RequestBody String companyName) {
        ServiceDB<BankAccDto> service = (BankAccountService) dtoMap.get("BankAccount");
        return ((BankAccountService) service).getByName(companyName);
    }

}
