package com.nicordesigns.finance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Merchant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    public Long id;

    protected String normalizedPayeeName;
    protected String merchant;
    protected Integer sic;

    private Merchant() {
    }

    public Merchant(String normalizedPayeeName, String merchant, Integer sic) {
        this.normalizedPayeeName = normalizedPayeeName;
        this.merchant = merchant;
        this.sic = sic;
    }

    public String getNormalizedPayeeName() {
        return this.normalizedPayeeName;
    }

    public void setNormalizedPayeeName(String value) {
        this.normalizedPayeeName = value;
    }

    public String getMerchant() {
        return this.merchant;
    }

    public void setMerchant(String value) {
        this.merchant = value;
    }

    public Integer getSic() {
        return this.sic;
    }

    public void setSic(Integer value) {
        this.sic = value;
    }
}
