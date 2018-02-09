package io.pax.cryptos.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import io.pax.cryptos.domain.SimpleWallet;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */
public class WalletDao {

    JdbcConnector connector = new JdbcConnector();

    public List<Wallet> listWallets() throws SQLException {

        List<Wallet> wallets = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM wallet");

        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");

            wallets.add(new SimpleWallet(id, name));

        }

        rs.close();
        stmt.close();
        conn.close();

        return wallets;

    }




    public int  createWallet(int userId, String name) throws SQLException {
        String query = "INSERT INTO wallet (name,user_id) VALUES (?,?)";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statements = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
    statements.setString (1,name);
    statements.setInt(2, userId);
        statements.executeUpdate();


      //  int rows = statements.executeUpdate(query);
   // if (rows!= 1){
        //throw new SQLException("something wring happpened with "+query);
    //}
    ResultSet keys = statements.getGeneratedKeys();
    keys.next();


int id = keys.getInt(1);

        statements.close();
        conn.close();
        return id;




    }
    public void deleteWallet(int walletId) throws SQLException {

        String query = "DELETE  FROM wallet WHERE id=?";
        //System.out.println(query);


        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,walletId);
        statement.executeUpdate();
        statement.close();
        conn.close();

    }

    public List<Wallet> findByName(String extract) throws SQLException {
        String query = "SELECT * FROM wallet WHERE name LIKE ?";

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, extract +"%");
        ResultSet rs = stmt.executeQuery();

        List<Wallet> wallets = new ArrayList<>();
        while (rs.next()) {
            wallets.add(new SimpleWallet(rs.getInt("id"), rs.getString("name")));
            System.out.println(rs.getString("name"));
        }
        stmt.close();

        return wallets;

    }


    public void deleteByName(String name) throws SQLException {
        String query = "DELETE  FROM wallet WHERE name=?";
        //System.out.println(query);


        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,name);
        statement.executeUpdate();
        statement.close();
        conn.close();
    }


    /**
     *
     * @param walletId the id of the wallet
     * @param newName the new name
     */
    public void updateWallet(int walletId, String newName) throws SQLException {
        String query = "UPDATE wallet SET name = ? WHERE id = ? ";
        //System.out.println(query);


        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setInt(2,walletId);
        statement.setString(1,newName);
        statement.executeUpdate();
        statement.close();
        conn.close();

    }




    public void deleteAll(int userId) throws SQLException { // Delete all wallets from users

        String query = "DELETE FROM wallet WHERE user_id=?";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }
    public static void main(String[] args) throws SQLException {
        WalletDao dao = new WalletDao();
       // dao.createWallet(2,"somecool");
        //dao.deleteWallet(28);
      // dao.deleteByName("Yellow");
        dao.updateWallet(5,"Ayoub123");





    }
}
