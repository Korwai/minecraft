package korwai;

import java.awt.Color;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.particle.ParticleEffect;

public class PaladinConsecrate implements Listener {
	

	private Main plugin;
	Random ran = new Random();
	
	public PaladinConsecrate(Main plugin) {
		this.plugin=plugin;
	}

	@EventHandler
	public void run(PlayerInteractEvent e) { //Add more particles to enhance the look xd
		Player p = e.getPlayer();
			if (e.getAction()==Action.LEFT_CLICK_AIR && p.getInventory().getItemInMainHand().getType()==Material.WOOD_SWORD) {
				new BukkitRunnable() {
				Location loc = p.getLocation();
				Location loc2 = p.getLocation();
				double t = 0.0;
				
				public void run() {
					int size = 5;
					for(int i = 0; i < 360;i++) {
						double angle = (i*Math.PI/180);
						t+=0.25;
						if (t==1) {
							p.playSound(loc, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
						} 
						double x =(size*Math.cos(angle));
						double y = 0.1;
						double z =(size*Math.sin(angle));
						
						
						int n = ran.nextInt(100);
						loc.add(x,y,z);
						if (t%1000==0)
							p.playSound(loc, Sound.ENTITY_IRONGOLEM_HURT, 1, 0);
						if(n>95) {
							ParticleEffect.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), loc, Color.YELLOW);
						}
						
							for (Entity en : loc.getChunk().getEntities()){
								if (en.getLocation().distance(loc2) < size){
			                    	if(en != p) {
				                        if(en.getType().isAlive()) {                                    
					                        Damageable d = (Damageable) en;
					                        d.damage(5.0, p);
				                    
				                    }
			                   	}
			                }
						
						}
							
						
						loc.subtract(x,y,z);
					}
					
					
					if(t>5000.0) {
						this.cancel();
					}
				}
			}.runTaskTimer(plugin, 0, 1);
		}		
	}
}
