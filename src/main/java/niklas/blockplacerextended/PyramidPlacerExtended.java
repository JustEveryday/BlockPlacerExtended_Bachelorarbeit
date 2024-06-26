package niklas.blockplacerextended;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PyramidPlacerExtended implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        System.out.println("pyramide");
        if (commandSender instanceof Player) {
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

            int height;
            Material material;

            try {
                height = Integer.valueOf(strings[0]);
                material = Material.valueOf(strings[1].toUpperCase());
            } catch (NumberFormatException e) {
                System.out.println("Argumente sind ungültig");
                player.sendMessage("Argumente sind ungültig");
                e.printStackTrace();
                return false;
            }

            if (height <= 0) {
                System.out.println("Durch 0 als übergebener Wert kann die Struktur nicht vernünftig generiert werden");
                player.sendMessage("Durch 0 als übergebener Wert kann die Struktur nicht vernünftig generiert werden");
                return false;
            }

            double yStart = position.getY();
            double zStart = position.getZ();
            double xStart = position.getX();

            String directionName = getDirection(player.getLocation().getYaw());

            switch (directionName) {
                case "NORTH":
                    zStart = zStart - 5 - (double) height / 2;
                    for (int y = 0; y < height; y++) {
                        int size = height - y - 1;
                        for (int x = -size; x <= size; x++) {
                            for (int z = -size; z <= size; z++) {
                                Location blockLocation = position.clone();
                                blockLocation.setX(xStart + x);
                                blockLocation.setY(yStart + y);
                                blockLocation.setZ(zStart + z);
                                world.getBlockAt(blockLocation).setType(material);
                            }
                        }
                    }
                    break;
                case "EAST":
                    xStart = xStart + 5 + (double) height / 2;
                    for (int y = 0; y < height; y++) {
                        int size = height - y - 1;
                        for (int x = -size; x <= size; x++) {
                            for (int z = -size; z <= size; z++) {
                                Location blockLocation = position.clone();
                                blockLocation.setX(xStart + x);
                                blockLocation.setY(yStart + y);
                                blockLocation.setZ(zStart + z);
                                world.getBlockAt(blockLocation).setType(material);
                            }
                        }
                    }
                    break;
                case "SOUTH":
                    zStart = zStart + 5 + (double) height / 2;
                    for (int y = 0; y < height; y++) {
                        int size = height - y - 1;
                        for (int x = -size; x <= size; x++) {
                            for (int z = -size; z <= size; z++) {
                                Location blockLocation = position.clone();
                                blockLocation.setX(xStart + x);
                                blockLocation.setY(yStart + y);
                                blockLocation.setZ(zStart + z);
                                world.getBlockAt(blockLocation).setType(material);
                            }
                        }
                    }
                    break;
                case "WEST":
                    xStart = xStart - 5 - (double) height / 2;
                    for (int y = 0; y < height; y++) {
                        int size = height - y - 1;
                        for (int x = -size; x <= size; x++) {
                            for (int z = -size; z <= size; z++) {
                                Location blockLocation = position.clone();
                                blockLocation.setX(xStart + x);
                                blockLocation.setY(yStart + y);
                                blockLocation.setZ(zStart + z);
                                world.getBlockAt(blockLocation).setType(material);
                            }
                        }
                    }
                    break;
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
