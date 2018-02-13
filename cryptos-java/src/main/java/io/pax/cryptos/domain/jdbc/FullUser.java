package io.pax.cryptos.domain.jdbc;

import io.pax.cryptos.domain.Wallet;

import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */
public class FullUser extends SimpleUser{
    List<Wallet> wallets;

    public FullUser(){}

    public FullUser(int id, String name) {
        super(id, name);
    }

    public FullUser(int id, String name,List<Wallet> wallets) {
        this.id=id;
        this.name = name;
        this.wallets=wallets;
    }

    @Override
    public List<Wallet> getWallets() {
        return this.wallets;
    }


    @Override
    public String toString()   {
        return this.name+" : " +this.wallets;

    }


    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }
}
