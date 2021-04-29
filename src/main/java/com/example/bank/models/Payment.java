package com.example.bank.models;


public class Payment {

    //@DateTimeFormat(pattern = "yyyy/MM/dd")
    //private Date paymentDate;
    private String month;
    private String principal;
    private String  monthlyPayment;
    private String  interestPaid;
    private String  principalPaid;
    private String  newBalance;

    public Payment() {
    }

    public Payment(String month, String principal, String monthlyPayment, String interestPaid, String principalPaid, String newBalance) {
        this.month = month;
        this.principal = principal;
        this.monthlyPayment = monthlyPayment;
        this.interestPaid = interestPaid;
        this.principalPaid = principalPaid;
        this.newBalance = newBalance;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(String monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public String getInterestPaid() {
        return interestPaid;
    }

    public void setInterestPaid(String interestPaid) {
        this.interestPaid = interestPaid;
    }

    public String getPrincipalPaid() {
        return principalPaid;
    }

    public void setPrincipalPaid(String principalPaid) {
        this.principalPaid = principalPaid;
    }

    public String getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(String newBalance) {
        this.newBalance = newBalance;
    }

 }
