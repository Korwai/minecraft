package korwai;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.inventivetalent.particle.ParticleEffect;

public class FreezeBeam implements Listener {
	
	@EventHandler
	public void beam(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction()==Action.LEFT_CLICK_AIR && p.getInventory().getItemInMainHand().getType()==Material.REDSTONE_TORCH_ON) {
			
			for (int t = 0; t<60; t++) {
				Location loc = p.getLocation();
				Vector direc = loc.getDirection().normalize();
				if (t==1) {
					p.playSound(loc, Sound.ENTITY_EXPERIENCE_ORB_TOUCH, 1, 0);
				}
				double x = direc.getX()*t;
				double y = direc.getY()*t+1.5;
				double z = direc.getZ()*t;
				
				loc.add(x,y,z);
				ParticleEffect.SNOWBALL.send(Bukkit.getOnlinePlayers(), loc, 0,0,0,0,1); // This works apparently
				
				
				for (Entity en : loc.getChunk().getEntities()){
	                    if (en.getLocation().distance(loc) < 2.0){
	                    	if(en != p) {
		                        if(en.getType().isAlive()) {                                     
			                        Damageable d = (Damageable) en;
			                        d.damage(10.0, p);
			                        if (en instanceof LivingEntity) {
			                        	((LivingEntity) en).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 50));
			                        }
		                    
		                    }
	                   	}
	                }
                }
				
				loc.subtract(x,y,z);
				
			}
		}
	}

}
