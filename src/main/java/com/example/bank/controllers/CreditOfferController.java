package com.example.bank.controllers;

import com.example.bank.models.*;
import com.example.bank.services.ClientService;
import com.example.bank.services.CreditOffersService;
import com.example.bank.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller("/credit-offer")
public class CreditOfferController {

    @Autowired
    CreditOffersService creditOffersService;
    @Autowired
    ClientService clientService;
    @Autowired
    CreditService creditService;

    @GetMapping("/credit-offer")
    public String showAllOffers(Map<String, Object> model) {
        List<CreditOffer> creditOffers = creditOffersService.listAll();
        model.put("offers", creditOffers);
        return "credit_offer_list";
    }

    //clients/offers
    //home/clients
    //home/client?id={{id}}
    //home/offer?id={{id}}
    //home/client/offers?client_id={{id}}
    //home/client/5/offer/3
    // TODO: write Junit tests
    // TODO: make a separate loan processing page
    // TODO: write a method for calculating differentiated payment
    // TODO: make a homepage
    // TODO: add date in payment
    // TODO: use Hibernate Validator for establish validation rules


    @GetMapping("/credit-offer/client")
    public String showClientOffers(@RequestParam("id") Long id, Map<String, Object> model) {
        Client client = clientService.getClientById(id);
        List<CreditOffer> creditOffers = client.getCreditOffers();
        model.put("client", client);
        model.put("offers", creditOffers);
        return "credit_offer_client";
    }

    @GetMapping("/credit-offer/create")
    public String goToOfferCreation(@RequestParam("id") Long id, Map<String, Object> model) {
        List<Credit> credits = creditService.listAll();
        Client client = clientService.getClientById(id);
        model.put("client", client);
        model.put("credits", credits);
        return "credit_offer_create";
    }

    @RequestMapping(value = "/credit-offer/create/save", method = RequestMethod.POST)
    public String createNewOffer(@ModelAttribute("creditOffer") CreditOffer creditOffer, RedirectAttributes redirectAttr) {
        creditOffersService.saveCreditOffer(creditOffer);
        redirectAttr.addAttribute("id", creditOffer.getClient().getId());
        return "redirect:/credit-offer/client";
    }

    @RequestMapping("/credit-offer/delete")
    public String deleteOffer(@RequestParam("id") Long id, RedirectAttributes redirectAttr) {
        Client client = creditOffersService.findById(id).getClient();
        creditOffersService.deleteById(id);
        redirectAttr.addAttribute("id", client.getId());
        return "redirect:/credit-offer/client";
    }
    @GetMapping("/credit-offer/update")
    public String updateOfferForm(@RequestParam("id") Long id, Map<String, Object> model) {
        Client client = creditOffersService.findById(id).getClient();
        List<Credit> credits = creditService.listAll();
        model.put("client", client);
        model.put("credits",credits);
        model.put("offer",creditOffersService.findById(id));
        return "credit_offer_update";
    }

    @PostMapping("/credit-offer/update")
    public String updateOffer(@RequestParam("id") Long id, CreditOffer creditOffer,RedirectAttributes redirectAttr) {
       creditOffersService.saveCreditOffer(creditOffer);
        Client client = creditOffersService.findById(id).getClient();
        redirectAttr.addAttribute("id", client.getId());
        return "redirect:/credit-offer/client";
    }
    @GetMapping("/credit-offer/payments")
    public String showPaymentsSchedule(@RequestParam("id") Long id, Model model){
        CreditOffer creditOffer = creditOffersService.findById(id);
        double loanAmount = creditOffer.getLoanAmount();
        double interestRate = creditOffer.getCredit().getInterestRate();
        int loanTermYear = creditOffer.getLoanTermYear();
        Client client = creditOffer.getClient();
        List<Payment> payments = creditOffersService.showAmortizationTable(loanAmount,interestRate,loanTermYear);
        LoanPaymentSummary loanPaymentSummary = creditOffersService.showTotalPaymentSum(loanAmount,interestRate,loanTermYear);
        model.addAttribute("payments",payments);
        model.addAttribute("loanPaymentSummary",loanPaymentSummary);
        model.addAttribute("client",client);
        return "payments_schedule";

    }


}
