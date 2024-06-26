package niklas.blockplacerextended;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BallPlacerExtended implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
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

            int r = 0;

            Material material;
            try {
                r = Integer.parseInt(strings[0]);
                material = Material.valueOf(strings[1].toUpperCase());
            } catch (NumberFormatException e) {
                System.out.println("Argumente sind ungültig");
                player.sendMessage("Argumente sind ungültig");
                e.printStackTrace();
                return false;
            }

            if (r <= 0) {
                System.out.println("Durch 0 als übergebener Wert kann die Struktur nicht vernünftig generiert werden");
                player.sendMessage("Durch 0 als übergebener Wert kann die Struktur nicht vernünftig generiert werden");
            }

            int yStart = (int) position.getY();
            int zStart = (int) position.getZ();
            int xStart = (int) position.getX();

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

            for(int x = xStart - r; x <= xStart + r; x++){
                for(int y = yStart - r; y <= yStart + r; y++){
                    for(int z = zStart - r; z <= zStart + r; z++){
                        if((x-xStart)*(x-xStart)+(y-yStart)*(y-yStart)+(z-zStart)*(z-zStart) < r*r){

                            Location blockLocation7 = position.clone();
                            blockLocation7.setX(x);
                            blockLocation7.setY(y);
                            blockLocation7.setZ(z);
                            world.getBlockAt(blockLocation7).setType(material);
                        }
                    }
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
