package com.example.bank.repos;

import com.example.bank.models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ClientRepos extends CrudRepository<Client, Long> {

        List<Client> findByLastName(String lastName);

}
