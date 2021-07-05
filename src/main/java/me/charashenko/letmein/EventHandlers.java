package me.charashenko.letmein;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.time.LocalDateTime;

public class EventHandlers implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        Database database = Letmein.getDatabase();
        PlayerData playerData = database.getPlayerData(player.getName());
        if (playerData == null) {
            playerData = new PlayerData(
                    player.getName(),
                    "testPassword",
                    LocalDateTime.now());
            database.writePlayerData(playerData);
        } else {
            playerData.setLastLogin(LocalDateTime.now());
            database.updatePlayerData(playerData);
        }

        System.out.println(playerData);
    }
}
