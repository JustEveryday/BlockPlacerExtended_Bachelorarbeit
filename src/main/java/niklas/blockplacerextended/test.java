package niklas.blockplacerextended;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class test implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        System.out.println("bester test");
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            World world = player.getWorld();
            Location position = player.getLocation();

            if (world == null || position == null) {
                System.out.println("Die Welt oder die Position ist null");
                player.sendMessage("Die Welt oder die Position ist null");
                return false;
            }

            if (strings.length != 4) {
                System.out.println("Falsche Anzahl an Argumenten");
                player.sendMessage("Falsche Anzahl an Argumenten");
                return false;
            }

            int height = 0;
            int width = 0;
            int lenght = 0;
            Material material;
            try {
                height = Integer.parseInt(strings[0]);
                width = Integer.parseInt(strings[1]);
                lenght = Integer.parseInt(strings[2]);
                material = Material.valueOf(strings[3].toUpperCase());
            } catch (NumberFormatException e) {
                System.out.println("Argumente sind ungültig");
                player.sendMessage("Argumente sind ungültig");
                e.printStackTrace();
                return false;
            }

            if (height <= 0 || width <= 0 || lenght <= 0) {
                System.out.println("Durch 0 als übergebener Wert kann die Struktur nicht vernünftig generiert werden");
                player.sendMessage("Durch 0 als übergebener Wert kann die Struktur nicht vernünftig generiert werden");
            }

            double yStart = position.getY();
            double zStart = position.getZ();
            double xStart = position.getX();

            String directionName = getDirection(player.getLocation().getYaw());

            switch (directionName) {
                case "NORTH":
                    player.sendMessage("Norden");
                    zStart = zStart + 10;
                    break;
                case "EAST":
                    player.sendMessage("Osten < W");
                    xStart = xStart - 10;
                    break;
                case "SOUTH":
                    player.sendMessage("Süden");
                    zStart = zStart - 10;
                    break;
                case "WEST":
                    player.sendMessage("Westen < O");
                    xStart = xStart + 10;
                    break;
            }
            System.out.println();

            for (int y = 0; y < height; y++) {
                for (int z = 0; z < width; z++) {
                    for (int x = 0; x < lenght; x++) {
                        Location blockLocation = position.clone();
                        blockLocation.setX(xStart + x);
                        blockLocation.setY(yStart + y);
                        blockLocation.setZ(zStart + z);
                        world.getBlockAt(blockLocation).setType(material);


                    }
                }
            }
        }
        return true;
    }
    private String getDirection(float yaw) {
        yaw = (yaw % 360 + 360) % 360;
        if (yaw >= 315 || yaw < 45) {
            return "NORTH";
        } else if (yaw >= 45 && yaw < 135) {
            return "EAST";
        } else if (yaw >= 135 && yaw < 225) {
            return "SOUTH";
        } else {
            return "WEST";
        }
    }
}
