package io.pax.cryptos.domain.jdbc;

import io.pax.cryptos.domain.Line;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<? extends Line> getLines() {
        return new ArrayList<>();
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

