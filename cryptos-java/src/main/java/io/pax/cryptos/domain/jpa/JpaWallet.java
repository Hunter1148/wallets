package io.pax.cryptos.domain.jpa;

import io.pax.cryptos.domain.Line;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 13/02/2018.
 */

@Entity
public class JpaWallet implements Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    int id;
    String name;
    @Transient // dont wanna save in the database , it is a business attribute not a database item
            List<JpaLine> lines = new ArrayList<>();
//Liskov substitute principle for further reading
    @Override
    public int getId() {
        return this.id;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<? extends Line> getLines() {
        return lines;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLines(List<JpaLine> lines) {
        this.lines = lines;
    }
}
