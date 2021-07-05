package me.charashenko.letmein;

import java.time.LocalDateTime;

public class PlayerData {

    private String playerName;
    private String passHash;
    private LocalDateTime lastLogin;

    public PlayerData(String playerName, String passHash, LocalDateTime lastLogin) {
        this.playerName = playerName;
        this.passHash = passHash;
        this.lastLogin = lastLogin;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPassHash() {
        return passHash;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "playerName='" + playerName + '\'' +
                ", passHash='" + passHash + '\'' +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
