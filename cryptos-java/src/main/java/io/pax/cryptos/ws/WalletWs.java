package io.pax.cryptos.ws;

import io.pax.cryptos.dao.WalletDao;
import io.pax.cryptos.domain.FullWallet;
import io.pax.cryptos.domain.SimpleUser;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by AELION on 06/02/2018.
 */
@Path("wallets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletWs {

    @GET
    public List<Wallet> getWallets() throws SQLException {
        WalletDao dao = new WalletDao();
        return dao.listWallets();
    }

    @POST
/*return future wallet with an id*/
    public FullWallet createWallet(FullWallet wallet /* sent wallet has no id*/) {
        User user = wallet.getUser();
        if (user == null) {
            throw new NotAcceptableException("406 no user id sent");
        }
        if (wallet.getName().length() < 2) {
            throw new NotAcceptableException("406 : wallet name must have at least 2 letters ");

        }

        try {
            int id = new WalletDao().createWallet(user.getId(),wallet.getName());
            User boundUser= wallet.getUser();
            SimpleUser simpleUser = new SimpleUser();
            return new FullWallet(id, wallet.getName(), (SimpleUser) wallet.getUser());
        } catch (SQLException e) {

           throw new ServerErrorException("Database error, sorry",500);
        }

    }

}
