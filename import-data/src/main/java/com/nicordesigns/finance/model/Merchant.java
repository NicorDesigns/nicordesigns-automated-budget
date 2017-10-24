package com.nicordesigns.finance.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Merchant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    public String id;

    protected String normalizedPayeeName;
    protected String merchant;
    protected Integer sic;

    private Merchant() {
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
