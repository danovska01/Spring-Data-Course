package _05.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "_05_bank_account")
public class BankAccount extends BillingDetail {

    private final static String TYPE = "BANK ACCOUNT";

    private String bankName;
    private String SWIFTCode;

    public BankAccount() {

    }

    public BankAccount(int number, User owner, String SWIFTCode) {
        super(number, owner, "BANK ACCOUNT");
        this.SWIFTCode = SWIFTCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSWIFTCode() {
        return SWIFTCode;
    }

    public void setSWIFTCode(String SWIFTCode) {
        this.SWIFTCode = SWIFTCode;
    }
}
