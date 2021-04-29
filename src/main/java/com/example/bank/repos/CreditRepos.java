package com.example.bank.repos;

import com.example.bank.models.Credit;
import org.springframework.data.repository.CrudRepository;

public interface CreditRepos extends CrudRepository<Credit,Long> {

}
