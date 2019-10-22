package korwai;

import java.awt.Color;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
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
import org.bukkit.util.Vector;
import org.inventivetalent.particle.ParticleEffect;

import com.comphenix.protocol.wrappers.EnumWrappers.Particle;

import net.minecraft.server.v1_10_R1.EnumParticle;

public class HurricaneWand implements Listener {
	
	
	@EventHandler
	public void beam(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction()==Action.LEFT_CLICK_AIR && p.getInventory().getItemInMainHand().getType()==Material.BONE) {
			
			for (int t = 0; t<60; t++) {
				Location loc = p.getLocation();
				Vector direc = loc.getDirection().normalize();
				if (t==1) {
					p.playSound(loc, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
				}
				double x = direc.getX()*t;
				double y = direc.getY()*t+1.5;
				double z = direc.getZ()*t;
				
				loc.add(x,y,z);
				ParticleEffect.REDSTONE.sendColor(Bukkit.getOnlinePlayers(), loc, Color.YELLOW); // This works apparently
				
				
				for (Entity en : loc.getChunk().getEntities()){
	                    if (en.getLocation().distance(loc) < 3.0){
	                    	if(en != p) {
		                        if(en.getType().isAlive()) {                                     
			                        Damageable d = (Damageable) en;
			                        d.damage(10.0, p);        
		                    
		                    }
	                   	}
	                }
                }
				
				loc.subtract(x,y,z);
				
			}
		}
	}	

}
