package io.pax.cryptos.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import io.pax.cryptos.domain.SimpleUser;
import io.pax.cryptos.domain.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 08/02/2018.
 */

public class UserDao {

    JdbcConnector connector = new JdbcConnector();

    public List<User> listUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");

        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");

            users.add(new SimpleUser(id, name));

        }
        System.out.println(users);
        rs.close();
        stmt.close();
        conn.close();

        return users;

    }

    public int createUser(String name) throws SQLException {
        String query = "INSERT INTO user (name) VALUES (?)";
        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, name);

        stmt.executeUpdate();

        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        int result = keys.getInt(1);

        stmt.close();
        conn.close();

        return result;
    }



   public void deleteUser(int userId) throws SQLException{
      WalletDao walletDao = new WalletDao();
       walletDao.deleteAll(userId);
       String query = "DELETE  FROM user WHERE id=?";
       //System.out.println(query);


       Connection conn = this.connector.getConnection();
       PreparedStatement statement = conn.prepareStatement(query);
       statement.setInt(1,userId);
       statement.executeUpdate();
       statement.close();
       conn.close();

    }

     public List<User> findByName(String extract) throws SQLException {
String query = "SELECT * FROM user WHERE name LIKE ?";

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, extract +"%");
        ResultSet rs = stmt.executeQuery();

        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(new SimpleUser(rs.getInt("id"), rs.getString("name")));
            System.out.println(rs.getString("name"));
        }
        stmt.close();

        return users;
    }

    public void deleteByName(String exactName) throws SQLException {
 String query = "DELETE  FROM user WHERE name=?";
        //System.out.println(query);


        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,exactName);
        statement.executeUpdate();
        statement.close();
        conn.close();
    }

   public void updateUser(int userId, String newName) throws SQLException {
String query = "UPDATE user SET name = ? WHERE id = ? ";
        //System.out.println(query);


        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setInt(2,userId);
        statement.setString(1,newName);
        statement.executeUpdate();
        statement.close();
        conn.close();

    }




    public static void main(String[] args) throws SQLException {
       UserDao dao = new UserDao();
        dao.createUser("NN");
       // dao.updateUser(1,"lili");
       //  dao.deleteUser(5);
        //dao.updateWallet(5,"Ayoub");





    }}