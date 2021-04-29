package com.example.bank.services;

import com.example.bank.models.Client;
import com.example.bank.repos.ClientRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepos clientRepos;

    public List<Client> listAll() {
        return (List<Client>) clientRepos.findAll();
    }

    public void saveClient(Client client) {
        clientRepos.save(client);
    }

    public Client getClientById(Long id) {
        Optional<Client> client = clientRepos.findById(id);
        return client.get();
    }

    public void deleteById(Long id) {
        clientRepos.deleteById(id);
    }

    public List<Client> findByLastName(String lastName){
        if(lastName != null){
            return clientRepos.findByLastName(lastName);
        } else {
            return (List<Client>) clientRepos.findAll();
        }
    }

}
