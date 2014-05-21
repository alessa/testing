package app.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "T_TRANSACTION")
public class Transaction {


    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "PAYER_ACCOUNT", nullable = false)
    private String payerAccountNumber;

    @Column(name = "BENEFICIARY_ACCOUNT", nullable = false)
    private String beneficiaryAccountNumber;

    @Column(name = "MONEY_AMOUNT", nullable = false)
    private BigDecimal moneyAmount;

    @Column(name = "DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayerAccountNumber() {
        return payerAccountNumber;
    }

    public void setPayerAccountNumber(String payerAccountNumber) {
        this.payerAccountNumber = payerAccountNumber;
    }

    public String getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                ", payerAccountNumber='" + payerAccountNumber + '\'' +
                ", beneficiaryAccountNumber='" + beneficiaryAccountNumber + '\'' +
                ", moneyAmount=" + moneyAmount +
                ", date=" + date +
                '}';
    }
}
