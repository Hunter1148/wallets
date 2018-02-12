package io.pax.cryptos.domain;

/**
 * Created by AELION on 06/02/2018.
 */
public class SimpleWallet implements Wallet {
    int id;
    String name;

    public SimpleWallet(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public SimpleWallet() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SimpleWallet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

