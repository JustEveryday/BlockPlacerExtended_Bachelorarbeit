package niklas.blockplacerextended;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockplacerExtended extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getCommand("MauerE").setExecutor(new WallPlacerExtended());
        getCommand("QuaderE").setExecutor(new CuboidPlacerExtended());
        getCommand("KreisE").setExecutor(new CirclePlacerExtended());;
        getCommand("ZylinderE").setExecutor(new CylinderPlacerExtended());
        getCommand("KugelE").setExecutor(new BallPlacerExtended());
        getCommand("PyramideE").setExecutor(new PyramidPlacerExtended());
        getCommand("test").setExecutor(new test());
        getCommand("test2").setExecutor(new test2());
        getCommand("test2").setExecutor(new BallPlacerExtended());
        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.CREATIVE);
    }

    @Override
    public void onDisable() {
    }
}
