package com.example.bank.repos;

import com.example.bank.models.Client;
import com.example.bank.models.CreditOffer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CreditOfferRepos extends CrudRepository<CreditOffer,Long> {
    List<CreditOffer> findByClient(Client client);
}
