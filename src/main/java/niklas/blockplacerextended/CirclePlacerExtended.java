package niklas.blockplacerextended;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CirclePlacerExtended implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            System.out.println("test2");
            Player player = (Player) commandSender;
            World world = player.getWorld();
            Location position = player.getLocation();

            if (world == null || position == null) {
                System.out.println("Die Welt oder die Position ist null");
                player.sendMessage("Die Welt oder die Position ist null");
                return false;
            }

            if (strings.length != 2) {
                System.out.println("Falsche Anzahl an Argumenten");
                player.sendMessage("Falsche Anzahl an Argumenten");
                return false;
            }
            int r = 0;
            Material material;

            try {
                r = Integer.valueOf(strings[0]);
                material = Material.valueOf(strings[1].toUpperCase());
            } catch (NumberFormatException e) {
                System.out.println("Argumente sind ungültig");
                player.sendMessage("Argumente sind ungültig");
                e.printStackTrace();
                return false;
            }
            System.out.println("test3");

            if (r <= 0) {
                System.out.println("Durch 0 als übergebener Wert kann die Struktur nicht vernünftig generiert werden");
                player.sendMessage("Durch 0 als übergebener Wert kann die Struktur nicht vernünftig generiert werden");
            }

            double yStart = position.getY();
            double zStart = position.getZ();
            double xStart = position.getX();

            String directionName = getDirection(player.getLocation().getYaw());

            switch (directionName) {
                case "NORTH":
                    zStart = zStart + r + 5;
                    break;
                case "EAST":
                    xStart = xStart - r - 5;
                    break;
                case "SOUTH":
                    zStart = zStart - r - 5;
                    break;
                case "WEST":
                    xStart = xStart + r + 5;
                    break;
            }

            int x = 0;
            int y = r;
            int d = 3 - 2 * r;

            while (y >= x) {
                Location blockLocation0 = position.clone();
                blockLocation0.setX(xStart + x);
                blockLocation0.setY(yStart);
                blockLocation0.setZ(zStart + y);
                world.getBlockAt(blockLocation0).setType(material);


                Location blockLocation1 = position.clone();
                blockLocation1.setX(xStart + x);
                blockLocation1.setY(yStart);
                blockLocation1.setZ(zStart - y);
                world.getBlockAt(blockLocation1).setType(material);

                Location blockLocation2 = position.clone();
                blockLocation2.setX(xStart - x);
                blockLocation2.setY(yStart);
                blockLocation2.setZ(zStart + y);
                world.getBlockAt(blockLocation2).setType(material);


                Location blockLocation3 = position.clone();
                blockLocation3.setX(xStart - x);
                blockLocation3.setY(yStart);
                blockLocation3.setZ(zStart -y);
                world.getBlockAt(blockLocation3).setType(material);

                Location blockLocation4 = position.clone();
                blockLocation4.setX(xStart + y);
                blockLocation4.setY(yStart);
                blockLocation4.setZ(zStart + x);
                world.getBlockAt(blockLocation4).setType(material);

                Location blockLocation5 = position.clone();
                blockLocation5.setX(xStart + y);
                blockLocation5.setY(yStart);
                blockLocation5.setZ(zStart - x);
                world.getBlockAt(blockLocation5).setType(material);

                Location blockLocation6 = position.clone();
                blockLocation6.setX(xStart - y);
                blockLocation6.setY(yStart);
                blockLocation6.setZ(zStart + x);
                world.getBlockAt(blockLocation6).setType(material);

                Location blockLocation7 = position.clone();
                blockLocation7.setX(xStart - y);
                blockLocation7.setY(yStart);
                blockLocation7.setZ(zStart - x);
                world.getBlockAt(blockLocation7).setType(material);

                x++;

                if (d > 0) {
                    y--;
                    d = d + 4 * (x - y) + 10;
                } else {
                    d = d + 4 * x + 6;
                }
            }
            System.out.println("Die Struktur wurde gebaut");
            player.sendMessage("Die Struktur wurde gebaut");
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
