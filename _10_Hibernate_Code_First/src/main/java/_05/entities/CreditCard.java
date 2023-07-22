package _05.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Month;
import java.time.Year;

@Entity
@DiscriminatorValue(value = "_05_credit_card")
public class CreditCard extends BillingDetail {

    private final static String TYPE = "CREDIT CARD";

    private String typeOfCard;

    private Month expirationMonth;

    private Year expirationYear;

    public CreditCard() {

    }

    public CreditCard(int number, User owner, String typeOfCard) {
        super(number, owner, TYPE);
        this.typeOfCard = typeOfCard;
    }

    public String getTypeOfCard() {
        return typeOfCard;
    }

    public void setTypeOfCard(String typeOfCard) {
        this.typeOfCard = typeOfCard;
    }

    public Month getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Month expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Year getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Year expirationYear) {
        this.expirationYear = expirationYear;
    }


}
