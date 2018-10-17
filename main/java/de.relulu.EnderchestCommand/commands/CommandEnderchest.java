package de.web.relulu.EnderchestCommand.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.web.relulu.EnderchestCommand.EnderchestMain;
import de.web.relulu.EnderchestCommand.exceptions.PlayerNotAvailableException;

/**
 * 
 * @author ReLuLu
 *
 */
public class CommandEnderchest implements CommandExecutor {
	
	private final EnderchestMain ema;
	
	/**
	 * Konstruktor mit Instanz der Main
	 * @param enderchestmain
	 */
	public CommandEnderchest(EnderchestMain enderchestmain) {
		this.ema = enderchestmain;
	}
	
    /**
     * Verarbeitet alle eingehenden Befehle, die diesem Plugin angehören.
     * @param CommandSender 	represents whatever is sending the command. This could be a Player, ConsoleCommandSender, or BlockCommandSender (a command block)
	 * @param Command 			represents what the command being called is. This will almost always be known ahead of time.
	 * @param label 			represents the exact first word of the command (excluding arguments) that was entered by the sender
	 * @param args 				is the remainder of the command statement (excluding the label) split up by spaces and put into an array.
     */
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	// als Konsolenbefehl unbrauchbar, deshalb nur für Spieler
    	if(sender instanceof Player) {
    		
    		Player player = (Player)sender;
    		
    		if(args.length < 1) { // Befehl ohne Parameter (/ec )
    			if(player.isOp() || player.hasPermission("enderchestcommand.own.see")) {
    				player.openInventory(player.getEnderChest());
    				return true;
    			}
    		}
    		if(args.length == 1) {
    			if(player.isOp() || player.hasPermission("enderchestcommand.other.see")) {
    				// exact sorgt für case insensitive
    				// player wird nicht überschrieben
    				// nur das Inv des anderen players wird genutzt
    				String targetplayername = null;
    				Player targetplayer = null;
    				try {
    					targetplayername = args[0];
    				} catch(ArrayIndexOutOfBoundsException e) {
    					ema.getLogger().warning("Missing argument on enderchestcommand... " + e);
    				}
					if(targetplayername != null) {
						targetplayer = Bukkit.getPlayerExact(targetplayername);
						
						// Exceptions vermeiden if possible...
						
						if(targetplayer == null) {
							try {
								throw new PlayerNotAvailableException(targetplayername);
							} catch (PlayerNotAvailableException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					player.openInventory(targetplayer.getEnderChest());
					//player.openInventory(Bukkit.getPlayerExact(args[0]).getEnderChest());
					return true;
    				
    			}
    		}
    		
    		
    		if(player.isOp()) {
    		//if(player.hasPermission("enderchestcmd.other.*")) {
        		if(args.length < 2) {
        			ema.getLogger().info("args.length < 2" + " -> " + args.length);
        			for(String s : args) {
        				ema.getLogger().info(s);
        			}
        		}
        		
        		if(args.length > 1) {
        			ema.getLogger().info("args.length > 1" + " -> " + args.length);
        			for(String s : args) {
        				ema.getLogger().info(s);
        			}
        		}
        		
        		ema.getLogger().info("args for-Schleife");
        		for(int i = 0; i < args.length; i++) {
        			ema.getLogger().info(args[i]);
        		}
        		
        		// enderchest <Leerzeichen> ergibt args.length = 0
        		// enderchest ReLuLu ergibt args.length = 1
        		// enderchest ReLuLu Leulu ergibt args.length = 2
        		
        		//if args.length == 0
        		// dann führe normalen own-code aus
        		//if args.length >= 1 
        		// dann führe den other-code aus
        		
        		//check auf permissions
        		
    			// öffne ein Inventar und hole dafür das Enderchest Inventar des Spielers
    			//player.openInventory(player.getEnderChest());
        		return false;
    		}
    	}
    	return true;
    }
	
}
