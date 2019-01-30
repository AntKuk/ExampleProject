package com.exampleproject.web.rest.service;

import com.exampleproject.model.shared.CompanyDto;
import com.exampleproject.web.rest.dao.Dao;
import com.exampleproject.web.rest.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service("companyService")
public class CompanyService implements ServiceDB<CompanyDto> {

    private final Dao<Company> companyDao;

    @Autowired
    public CompanyService(@Qualifier("companyDAO") Dao<Company> companyDao) {
        this.companyDao = companyDao;
    }

    /*
        public List<Company> getAllCompanies() {
            return companyDAO.getAllCompanies();
        }
    */
    public List<CompanyDto> getAll() {
        return fromDao(companyDao.getAllObjects());
    }
/*
    public CompanyDto getById(BigInteger id) {
        return null;
    }
*/
    public String getEntityName() {
        return companyDao.getEntityName();
    }

    @Override
    public void add(CompanyDto entity) {
        companyDao.add(fromDto(entity));
    }

    @Override
    public void deleteById(int id) {
        companyDao.deleteById(id);
    }

    @Override
    public void updateById(CompanyDto companyDto) {
        Company company = companyDao.getObject(BigInteger.valueOf(companyDto.getId()));
        company.setCompanyName(companyDto.getCompanyName());
        company.setAddress(companyDto.getAddress());
        company.setIec(companyDto.getIec());
        company.setTin(companyDto.getTin());
        company.setEmail(companyDto.getEmail());
        company.setTelNumber(companyDto.getTelNumber());

        companyDao.updateById(company);
    }

    private Company fromDto(CompanyDto companyDto) {
        Company companyDao = new Company();
        companyDao.setCompanyName(companyDto.getCompanyName());
        companyDao.setAddress(companyDto.getAddress());
        companyDao.setEmail(companyDto.getEmail());
        //companyDao.setId(BigInteger.valueOf(companyDto.getId()));
        companyDao.setEmail(companyDto.getEmail());
        companyDao.setIec(companyDto.getIec());
        companyDao.setTelNumber(companyDto.getTelNumber());
        companyDao.setTin(companyDto.getTin());

        return companyDao;
    }


    private List<CompanyDto> fromDao(List<Company> list) {
        List<CompanyDto> listDto = new ArrayList<>();
        for(Company company : list) {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setCompanyName(company.getCompanyName());
            companyDto.setAddress(company.getAddress());
            companyDto.setEmail(company.getEmail());
            companyDto.setId(company.getId().intValue());
            companyDto.setEmail(company.getEmail());
            companyDto.setIec(company.getIec());
            companyDto.setTelNumber(company.getTelNumber());
            companyDto.setTin(company.getTin());

            listDto.add(companyDto);
        }
        return listDto;
    }
}