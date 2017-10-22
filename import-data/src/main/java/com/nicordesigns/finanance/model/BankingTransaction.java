package com.nicordesigns.finanance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
public class BankingTransaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    protected long id;


    protected String institutionTransactionId;
    protected String checkNumber;
    protected String payeeName;
    protected String memo;
    protected String type;

    protected Calendar postedDate;
    protected BigDecimal amount;
    protected Categorization categorization;

    private BankingTransaction() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getInstitutionTransactionId() {
        return this.institutionTransactionId;
    }

    public void setInstitutionTransactionId(String value) {
        this.institutionTransactionId = value;
    }

    public String getCheckNumber() {
        return this.checkNumber;
    }

    public void setCheckNumber(String value) {
        this.checkNumber = value;
    }

    public String getPayeeName() {
        return this.payeeName;
    }

    public void setPayeeName(String value) {
        this.payeeName = value;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String value) {
        this.memo = value;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public Calendar getPostedDate() {
        return this.postedDate;
    }

    public void setPostedDate(Calendar value) {
        this.postedDate = value;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    public Categorization getCategorization() {
        return this.categorization;
    }

    public void setCategorization(Categorization value) {
        this.categorization = value;
    }
}

