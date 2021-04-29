package com.example.bank.models;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "credit_offers")
public class CreditOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_id")
    private Credit credit;

    private double loanAmount;
    private int loanTermYear;


    //    @DateTimeFormat(pattern = "yyyy/MM/dd")
    //private Date startDate;

    public CreditOffer() {
    }

    public CreditOffer(Client client, Credit credit, double loanAmount, int loanTermYear) {
        this.client = client;
        this.credit = credit;
        if (loanAmount <= credit.getCreditLimit()) {
            this.loanAmount = loanAmount;
        } else{this.loanAmount = credit.getCreditLimit();}
        this.loanTermYear = loanTermYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        if (loanAmount <= credit.getCreditLimit()) {
            this.loanAmount = loanAmount;
        } else{this.loanAmount = credit.getCreditLimit();}
    }

    public int getLoanTermYear() {
        return loanTermYear;
    }

    public void setLoanTermYear(int loanTermYear) {
        this.loanTermYear = loanTermYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditOffer that = (CreditOffer) o;
        return loanTermYear == that.loanTermYear &&
                Objects.equals(id, that.id) &&
                Objects.equals(client, that.client) &&
                Objects.equals(credit, that.credit) &&
                Objects.equals(loanAmount, that.loanAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, credit, loanAmount, loanTermYear);
    }
}
