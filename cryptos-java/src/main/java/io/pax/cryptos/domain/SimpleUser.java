package io.pax.cryptos.domain;

import java.util.List;

/**
 * Created by AELION on 08/02/2018.
 */
public class SimpleUser implements User{
    int id;
    String name;

    public SimpleUser() {
    }

    public SimpleUser(int id, String name) {
        this.name = name;
        this.id = id;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {return name;
    }

    @Override
    public List<Wallet> getWallets() {
        return null;
    }

    public void setId(int id) {
        this.id = id;
    }

}

