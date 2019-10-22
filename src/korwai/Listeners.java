package korwai;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.inventivetalent.particle.ParticleEffect;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.Particle;
import org.bukkit.event.entity.EntityDamageEvent;


public class Listeners implements org.bukkit.event.Listener {
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
			
		e.setJoinMessage(ChatColor.AQUA + "Welcome, " +p.getName() +".");
		p.sendMessage(ChatColor.DARK_PURPLE + "Remember, this is a no-bullying server");
		
	}
	
	@EventHandler
	public void quit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		e.setQuitMessage(ChatColor.AQUA + "Farewell, " +p.getName() +".");
	}
	
	@EventHandler
	public void speak(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		e.setFormat(ChatColor.GRAY + "<" + ChatColor.AQUA + "+" + ChatColor.GRAY + "> " + ChatColor.DARK_GRAY + p.getDisplayName() + ChatColor.GRAY + " >> " + ChatColor.WHITE + e.getMessage());
		//e.setMessage(ChatColor.GRAY + "<" + ChatColor.AQUA + "+" + ChatColor.GRAY + ">" + ChatColor.DARK_GRAY + p.getDisplayName() + e.getMessage());
	}
	
	
	
	
}
