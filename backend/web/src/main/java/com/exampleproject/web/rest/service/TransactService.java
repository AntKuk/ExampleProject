package com.exampleproject.web.rest.service;

import com.exampleproject.model.shared.TransactDto;
import com.exampleproject.web.rest.dao.Dao;
import com.exampleproject.web.rest.entity.BankAccount;
import com.exampleproject.web.rest.entity.Company;
import com.exampleproject.web.rest.entity.Transact;
import com.google.gwt.i18n.client.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("transactService")
public class TransactService implements ServiceDB<TransactDto> {
    private final Dao<Transact> transactDao;
    private final Dao<Company> companyDao;
    private final Dao<BankAccount> bankAccountDao;



    @Autowired
    public TransactService(@Qualifier("transactionDAO") Dao<Transact> transactDao,
                           @Qualifier("companyDAO") Dao<Company> companyDao,
                           @Qualifier("bankAccDAO") Dao<BankAccount> bankAccountDao) {
        this.transactDao = transactDao;
        this.companyDao = companyDao;
        this.bankAccountDao = bankAccountDao;
    }


    public List<TransactDto> getAll() {
        return fromDao(transactDao.getAllObjects());
    }



    public  void add(TransactDto transact) {
        transactDao.add(fromDto(transact));
    }

    @Override
    public void deleteById(int id) {
        transactDao.deleteById(id);
    }

    @Override
    public void updateById(TransactDto transactDto) {

    }

/*
    public TransactDto getById(BigInteger id) {
        return null;
    }
*/
    public String getEntityName() {
        return transactDao.getEntityName();
    }


    private List<TransactDto> fromDao(List<Transact> list) {
        List<TransactDto> listDto = new ArrayList<>();
        for(Transact transact : list) {
            TransactDto transactDto = new TransactDto();
            transactDto.setId(transact.getId().intValue());
            transactDto.setTranDate(new SimpleDateFormat("MM-dd-yyyy").format(transact.getTranDate()));
            transactDto.setSeller(transact.getIdSeller().getCompanyName());
            transactDto.setCustomer(transact.getIdCustomer().getCompanyName());
            transactDto.setTotal(transact.getTotal());
            transactDto.setSellerAcc(transact.getSellerAcc().getCorAcc().intValue());
            transactDto.setCustomerAcc(transact.getCustomerAcc().getCorAcc().intValue());


            listDto.add(transactDto);
        }
        return listDto;
    }

    private Transact fromDto(TransactDto transactDto) {
        Transact transactDao = new Transact();
        //bankDao.setId(BigInteger.valueOf(bankDto.getId()));

        transactDao.setIdCustomer(companyDao.getByName(transactDto.getCustomer()));
        transactDao.setIdSeller(companyDao.getByName(transactDto.getSeller()));
        transactDao.setTotal(transactDto.getTotal());
        transactDao.setTranDate(new Date());
        transactDao.setSellerAcc(bankAccountDao.getObject(BigInteger.valueOf(transactDto.getSellerAcc())));
        transactDao.setCustomerAcc(bankAccountDao.getObject(BigInteger.valueOf(transactDto.getCustomerAcc())));

        //transactDao.setSellerAcc(bankAccountDao.getByName(Integer.toString(companyDao.getByName(transactDto.getSeller()).getId().intValue())));
        //transactDao.setCustomerAcc(bankAccountDao.getByName(Integer.toString(companyDao.getByName(transactDto.getCustomer()).getId().intValue())));
        return transactDao;
    }
}
