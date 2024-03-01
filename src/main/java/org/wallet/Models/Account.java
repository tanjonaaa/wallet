package org.wallet.Models;

import org.wallet.Annotations.Column;
import org.wallet.Annotations.CustomType;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;
import org.wallet.Models.Types.AccountType;

import java.util.Objects;

@Model(table = "account")
public class Account {
    @Column(name = "account_id")
    @Id
    private String accountId;
    @Column(name = "name")
    private String name;
    @Column(name = "currency_id")
    private String currencyId;
    @Column(name = "account_type")
    @CustomType(type_class = "AccountType")
    private AccountType accountType;

    public Account() {
    }

    public Account(String accountId, String name, String currencyId, AccountType accountType) {
        this.accountId = accountId;
        this.name = name;
        this.currencyId = currencyId;
        this.accountType = accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", name='" + name + '\'' +
                ", currencyId='" + currencyId + '\'' +
                ", accountType=" + accountType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId.equals(account.accountId) &&
                name.equals(account.name) &&
                currencyId.equals(account.currencyId) &&
                accountType == account.accountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, name, currencyId, accountType);
    }
}
