package io.pax.cryptos.domain;

import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */
public interface Wallet {

    int getId();
    default User getUser(){
            return null;}

    String getName();
    List<? extends Line> getLines();
}
