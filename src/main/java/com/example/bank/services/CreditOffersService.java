package com.example.bank.services;

import com.example.bank.models.Client;
import com.example.bank.models.CreditOffer;
import com.example.bank.models.LoanPaymentSummary;
import com.example.bank.models.Payment;
import com.example.bank.repos.CreditOfferRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditOffersService {
    @Autowired
    CreditOfferRepos creditOfferRepos;

    public List<CreditOffer> listAll() {
        return (List<CreditOffer>) creditOfferRepos.findAll();

    }

    public void saveCreditOffer(CreditOffer creditOffer) {
        creditOfferRepos.save(creditOffer);
    }

    public List<CreditOffer> findByClient(Client client) {
        if (client != null) {
            return creditOfferRepos.findByClient(client);
        } else {
            return (List<CreditOffer>) creditOfferRepos.findAll();
        }
    }

    public CreditOffer findById(Long id) {
        Optional<CreditOffer> creditOffer = creditOfferRepos.findById(id);
        return creditOffer.get();
    }

    public void deleteById(Long id) {
        creditOfferRepos.deleteById(id);
    }

    // TODO: monhtlyIR != null

    private double calcMonthlyPayment(double loanAmount, double monthlyInterestRate, int numberOfMonths)  {
        if (monthlyInterestRate == 0 && numberOfMonths != 0) {
            return loanAmount / numberOfMonths;
        } else {
            return (monthlyInterestRate * loanAmount) / (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfMonths));
        }
    }

    public List<Payment> showAmortizationTable(double principal, double rate, int numOfYears) {
        List<Payment> payments = new ArrayList<>();
        double monthsInterestRate = (rate / 12) / 100;
        int months = numOfYears * 12;
        double monthlyPayment = calcMonthlyPayment(principal, monthsInterestRate, months);
        double interestPaid, principalPaid, newBalance;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for (int month = 1; month <= months; month++) {
            interestPaid = principal * monthsInterestRate;
            principalPaid = monthlyPayment - interestPaid;
            newBalance = principal - principalPaid;

            payments.add(new Payment(
                    Integer.toString(month), // TODO: save date month
                    decimalFormat.format(principal),
                    decimalFormat.format(monthlyPayment),
                    decimalFormat.format(interestPaid),
                    decimalFormat.format(principalPaid),
                    decimalFormat.format(newBalance))
            );
            principal = newBalance;
        }
        return payments;
    }

    public LoanPaymentSummary showTotalPaymentSum(double principal, double rate, int numOfYears) {
        LoanPaymentSummary loanPaymentSummary = new LoanPaymentSummary();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double monthsInterestRate = (rate / 12) / 100;
        int months = numOfYears * 12;
        double monthlyPayment = calcMonthlyPayment(principal, monthsInterestRate, months);
        double totalInterestPaid = 0;
        double interestPaid, principalPaid, newBalance, totalPayments;
        totalPayments = monthlyPayment * months;
        loanPaymentSummary.setMonthlyPayment(decimalFormat.format(monthlyPayment));
        loanPaymentSummary.setTotalPayments(decimalFormat.format(totalPayments));
        for (int month = 1; month <= months; month++) {
            interestPaid = principal * monthsInterestRate;
            totalInterestPaid += interestPaid;
            principalPaid = monthlyPayment - interestPaid;
            newBalance = principal - principalPaid;
            principal = newBalance;
        }
        loanPaymentSummary.setTotalInterestPaid(decimalFormat.format(totalInterestPaid));
        return loanPaymentSummary;
    }

}
