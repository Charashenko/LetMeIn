package me.charashenko.letmein;

import java.sql.*;
import java.time.LocalDateTime;

public class Database {

    private String dbURL = "jdbc:mysql://localhost/";
    private String user = "root";
    private String password = "toor";
    private String dbName = "test";
    private Connection conn;

    public void createDatabase() {
        try {
            conn = DriverManager.getConnection(dbURL, user, password);
            conn.createStatement().executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
            conn.createStatement().executeUpdate("USE " + dbName);
            conn.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(playerName varchar(20) primary key," +
                    "passHash text," +
                    "lastLogin text)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            conn = DriverManager.getConnection(dbURL + dbName, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropDatabase() {
        try {
            conn.createStatement().executeUpdate("DROP DATABASE " + dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PlayerData getPlayerData(String playerName) {
        try {
            PlayerData playerData = null;
            ResultSet rs = conn.prepareStatement("SELECT * FROM users WHERE (playerName=\""
                    + playerName + "\")").executeQuery();
            while (rs.next()) {
                LocalDateTime ldt = LocalDateTime.parse(rs.getString("lastLogin"));
                playerData = new PlayerData(
                        playerName,
                        rs.getString("passHash"),
                        ldt);
            }
            return playerData;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writePlayerData(PlayerData playerData) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES (?,?,?)");
            ps.setString(1, playerData.getPlayerName());
            ps.setString(2, playerData.getPassHash());
            ps.setString(3, playerData.getLastLogin().toString());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayerData(PlayerData playerData) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE users SET passHash=?, lastLogin=? " +
                    "WHERE playerName=\"" + playerData.getPlayerName() + "\"");
            ps.setString(1, playerData.getPassHash());
            ps.setString(2, playerData.getLastLogin().toString());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
