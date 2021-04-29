package com.example.bank.models;

public class LoanPaymentSummary {

    private String monthlyPayment;
    private String totalInterestPaid;
    private String totalPayments;

    public LoanPaymentSummary() {
    }

    public LoanPaymentSummary(String monthlyPayment, String totalInterestPaid, String totalPayments) {
        this.monthlyPayment = monthlyPayment;
        this.totalInterestPaid = totalInterestPaid;
        this.totalPayments = totalPayments;
    }

    public String getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(String monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public String getTotalInterestPaid() {
        return totalInterestPaid;
    }

    public void setTotalInterestPaid(String totalInterestPaid) {
        this.totalInterestPaid = totalInterestPaid;
    }

    public String getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(String totalPayments) {
        this.totalPayments = totalPayments;
    }

}
