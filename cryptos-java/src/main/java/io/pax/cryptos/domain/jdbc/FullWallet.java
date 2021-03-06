package io.pax.cryptos.domain.jdbc;

import io.pax.cryptos.domain.User;

/**
 * Created by AELION on 12/02/2018.
 */
public class FullWallet extends SimpleWallet {
   SimpleUser user;

//empty constructor with no real sens
    // needed for java EE bindings technology
    public FullWallet() {
        super();
    }

    @Override
    public User getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

    public FullWallet(int id, String name, SimpleUser user) {
        super(id, name);
        this.user = user;
    }


}
