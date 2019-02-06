package com.exampleproject.web.rest.service;

import com.exampleproject.model.shared.BankAccDto;
import com.exampleproject.web.rest.dao.BankAccDAO;
import com.exampleproject.web.rest.dao.Dao;
import com.exampleproject.web.rest.entity.Bank;
import com.exampleproject.web.rest.entity.BankAccount;
import com.exampleproject.web.rest.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service("bankAccService")

public class BankAccountService implements ServiceDB<BankAccDto> {
    private final Dao<BankAccount> bankAccDao;
    private final Dao<Bank> bankDao;
    private final Dao<Company> companyDao;


    @Autowired
    public BankAccountService(@Qualifier("bankAccDAO") Dao<BankAccount> bankAccDao,
                              @Qualifier("bankDAO") Dao<Bank> bankDao,
                              @Qualifier("companyDAO") Dao<Company> companyDao) {
        this.bankAccDao = bankAccDao;
        this.bankDao = bankDao;
        this.companyDao = companyDao;
    }

    public List<BankAccDto> getAll() {
        return fromDao(bankAccDao.getAllObjects());
    }



    public  void add(BankAccDto bank) {
        bankAccDao.add(fromDto(bank));
    }

    @Override
    public void deleteById(int id) {
        bankAccDao.deleteById(id);
    }

    @Override
    public void updateById(BankAccDto bankAccDto) {
 /*       Bank bank = bankDao.getObject(BigInteger.valueOf(bankDto.getId()));
        bank.setBankName(bankDto.getBankName());
        bank.setCity(bankDto.getCity());
        bank.setBic(bankDto.getBic());

        bankDao.updateById(bank);*/
    }

    public List<BankAccDto> getByName(String name) {
        Integer id = companyDao.getByName(name).getId().intValue();
        return fromDao(bankAccDao.getAccsById(id));
    }


    public BankAccDto getById(BigInteger id) {
        return null;
    }

    public String getEntityName() {
        return bankAccDao.getEntityName();
    }


    private List<BankAccDto> fromDao(List<BankAccount> list) {
        List<BankAccDto> listDto = new ArrayList<>();
        for(BankAccount bankAcc : list) {
            BankAccDto bankAccDto = new BankAccDto();
            bankAccDto.setCorAcc(bankAcc.getCorAcc().longValue());
            bankAccDto.setBankName(bankDao.getObject(BigInteger.valueOf(bankAcc.getIdBank())).getBankName());
            bankAccDto.setComName(companyDao.getObject(BigInteger.valueOf(bankAcc.getIdCom())).getCompanyName());



            listDto.add(bankAccDto);
        }
        return listDto;
    }

    private BankAccount fromDto(BankAccDto bankAccDto) {
        BankAccount bankAccDao = new BankAccount();
        bankAccDao.setCorAcc(BigInteger.valueOf(bankAccDto.getCorAcc()));
        bankAccDao.setIdCom(companyDao.getByName(bankAccDto.getComName()).getId().intValue());
        bankAccDao.setIdBank(bankDao.getByName(bankAccDto.getBankName()).getId().intValue());

        return bankAccDao;
    }
}
