package com.example.bank.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "credits")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double creditLimit;//?
    private double interestRate;//?BigDecimal

    @OneToMany(mappedBy = "credit", targetEntity = CreditOffer.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CreditOffer> creditOffers = new ArrayList<>();

    public Credit() {
    }

    public Credit(String name, double creditLimit, double interestRate) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public List<CreditOffer> getCreditOffers() {
        return creditOffers;
    }

    public void setCreditOffers(List<CreditOffer> creditOffers) {
        this.creditOffers = creditOffers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return Double.compare(credit.interestRate, interestRate) == 0 &&
                Objects.equals(id, credit.id) &&
                Objects.equals(name, credit.name) &&
                Objects.equals(creditLimit, credit.creditLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creditLimit, interestRate);
    }

    @Override
    public String toString() {
        return name + " - " + interestRate;

    }


}
