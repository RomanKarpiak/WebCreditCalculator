package com.example.bank.controllers;

import com.example.bank.models.Credit;
import com.example.bank.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller("/credit")
public class CreditController {

    @Autowired
    CreditService creditService;

    @RequestMapping("/credit")
    public String clients(Map<String, Object> model) {
        List<Credit> creditList = creditService.listAll();
        model.put("creditList", creditList);
        return "credit";
    }

    @RequestMapping("/credit/create")
    public String createCreditForm(Map<String, Object> model) {
        return "credit_create";
    }

    @RequestMapping("/credit/create/save")
    public String saveCredit(@ModelAttribute("credit") Credit credit) {
        creditService.save(credit);
        return "redirect:/credit";
    }

    @GetMapping("/credit/update")
    public String updateCreditForm(@RequestParam("id") Long id, Map<String, Object> model) {
        Credit credit = creditService.getCreditById(id);
        model.put("credit", credit);
        return "credit_update";
    }

    @PostMapping("/credit/update")
    public String updateCredit(@RequestParam("id") Long id, Credit credit) {
        creditService.save(credit);
        return "redirect:/credit";

    }

    @RequestMapping("/credit/delete")
    public String deleteCredit(@RequestParam("id") Long id, Credit credit) {
        creditService.deleteById(id);
        return "redirect:/credit";
    }

}
