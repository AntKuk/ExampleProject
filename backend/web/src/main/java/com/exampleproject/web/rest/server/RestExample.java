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

    private final Map<String, ServiceDB<? extends BasicDto>> dtoMap;
    private final UserService userService;

    @Autowired
    public RestExample(List<ServiceDB<? extends BasicDto>> dtoList, UserService userService) {
        this.dtoMap = new HashMap<String, ServiceDB<? extends BasicDto>>(dtoList.size());
        for (ServiceDB<? extends BasicDto> dto : dtoList) {
            dtoMap.put(dto.getEntityName(), dto);
        }
        this.userService = userService;
    }

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
    public LoginStatus login(@RequestBody UserDto user) {
       return userService.isUser(user);

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
