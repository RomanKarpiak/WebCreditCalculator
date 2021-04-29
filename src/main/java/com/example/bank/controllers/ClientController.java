package com.example.bank.controllers;

import com.example.bank.models.Client;
import com.example.bank.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//This annotation indicates that our class is a Spring controller
//and can send and receive HTTP requests

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/")//The annotation that process the getClientById request
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }


    //     TODO(rkarpiak): add validation and error handling
//     TODO(rkarpiak): check if the user with the same passport id or email
    @RequestMapping("/client-manager")
    public String clients(Map<String, Object> model) {
        List<Client> clientList = clientService.listAll();
        model.put("clientList", clientList);
        return "client_manager";
    }

    @RequestMapping("/client-manager/create")
    public String createClientForm(Map<String, Object> model) {
        return "client_create";
    }

    @RequestMapping(value = "/client-manager/create/save", method = RequestMethod.POST)
    public String saveClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/client-manager";
    }

    @GetMapping("/client-manager/update")
    public String updateClientForm(@RequestParam("id") Long id, Map<String, Object> model) {
        Client client = clientService.getClientById(id);
        model.put("client", client);
        return "client_update";
    }

    @PostMapping("/client-manager/update")
    public String updateClient(@RequestParam("id") Long id, Client client) {
        clientService.saveClient(client);
        return "redirect:/client-manager";
    }

    @RequestMapping("/client-manager/delete")
    public String deleteClient(@RequestParam("id") Long id) {
        clientService.deleteById(id);
        return "redirect:/client-manager";
    }

    // TODO(karpiak) handle search errors
    @PostMapping("/client-manager/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) throws NullPointerException {
        Iterable<Client> clientList = new ArrayList<>();
        if (filter != null && !filter.isEmpty()) {
            clientList = clientService.findByLastName(filter);
        } else if (filter.isEmpty()) {
            clientList = clientService.listAll();
        }
        if (!clientList.iterator().hasNext()) {
            model.put("filter", filter);
            return "client_not_found";
        }
        model.put("clientList", clientList);
        return "client_manager";

    }


}







