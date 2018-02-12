package io.pax.cryptos.ws;

import io.pax.cryptos.dao.UserDao;
import io.pax.cryptos.dao.WalletDao;
import io.pax.cryptos.domain.FullUser;
import io.pax.cryptos.domain.FullWallet;
import io.pax.cryptos.domain.SimpleUser;
import io.pax.cryptos.domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserWs {

    @GET
    public List<User> getUsers() throws SQLException {
        UserDao dao = new UserDao();
        return dao.listUsers();
    }
@GET
@Path("{id}") //this is a pathparam
public User getUser(@PathParam("id") int userId) throws SQLException {
        return new UserDao().findUserWithWallets(userId);


}
    @POST
/*return future wallet with an id*/
    public FullUser createUser(FullUser user /* sent wallet has no id*/) {
         //String user1 = user.getName();
        if (user == null) {
            throw new NotAcceptableException("406 no user name sent");
        }
        if (user.getName().length() < 2) {
            throw new NotAcceptableException("406 : user name must have at least 2 letters ");

        }

        try {
            int id = new UserDao().createUser(user.getName());


            user.setId(id);
            return user;
        } catch (SQLException e) {

            throw new ServerErrorException("Database error, sorry",500);
        }

    }

}
