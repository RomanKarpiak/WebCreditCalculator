package com.example.bank.services;

import com.example.bank.models.Credit;
import com.example.bank.repos.CreditRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditService {

    @Autowired
    CreditRepos creditRepos;

    public List<Credit> listAll(){
        return (List<Credit>) creditRepos.findAll();
    }

    public void save(Credit credit){
        creditRepos.save(credit);
    }

    public Credit getCreditById(Long id){
        Optional<Credit> credit = creditRepos.findById(id);
        return credit.get();
    }

    public void deleteById(Long id){
        creditRepos.deleteById(id);
    }


}
