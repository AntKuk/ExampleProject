package com.exampleproject.web.rest.service;

import com.exampleproject.model.shared.BankDto;
import com.exampleproject.web.rest.dao.Dao;
import com.exampleproject.web.rest.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service("bankService")
public class BankService implements ServiceDB<BankDto> {
    private final Dao<Bank> bankDao;

    @Autowired
    public BankService(@Qualifier("bankDAO") Dao<Bank> bankDao) {
        this.bankDao = bankDao;
    }

    /*
        public List<Company> getAllCompanies() {
            return companyDAO.getAllCompanies();
        }
    */
    public List<BankDto> getAll() {
        return fromDao(bankDao.getAllObjects());
    }



    public  void add(BankDto bank) {
        bankDao.add(fromDto(bank));
    }

    @Override
    public void deleteById(int id) {
        bankDao.deleteById(id);
    }

    @Override
    public void updateById(BankDto bankDto) {
        Bank bank = bankDao.getObject(BigInteger.valueOf(bankDto.getId()));
        bank.setBankName(bankDto.getBankName());
        bank.setCity(bankDto.getCity());
        bank.setBic(bankDto.getBic());

        bankDao.updateById(bank);
    }


    public BankDto getById(BigInteger id) {
        return null;
    }

    public String getEntityName() {
        return bankDao.getEntityName();
    }


    private List<BankDto> fromDao(List<Bank> list) {
        List<BankDto> listDto = new ArrayList<>();
        for(Bank bank : list) {
            BankDto bankDto = new BankDto();
            bankDto.setId(bank.getId().intValue());
            bankDto.setBankName(bank.getBankName());
            bankDto.setBic(bank.getBic());
            bankDto.setCity(bank.getCity());

            listDto.add(bankDto);
        }
        return listDto;
    }

    private Bank fromDto(BankDto bankDto) {
        Bank bankDao = new Bank();
        //bankDao.setId(BigInteger.valueOf(bankDto.getId()));
        bankDao.setBankName(bankDto.getBankName());
        bankDao.setBic(bankDto.getBic());
        bankDao.setCity(bankDto.getCity());

        return bankDao;
    }

}
