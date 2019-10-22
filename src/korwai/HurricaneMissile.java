package korwai;

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
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.inventivetalent.particle.ParticleEffect;

public class HurricaneMissile implements Listener {
	
	private Main plugin;
	
	public HurricaneMissile(Main plugin) {
		this.plugin=plugin;
	}
	
	@EventHandler
	public void launch(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (e.getAction() == Action.LEFT_CLICK_AIR && p.getInventory().getItemInMainHand().getType()==Material.STICK) {
			
			new BukkitRunnable() {
				double t = 0.0;
				
				@Override
				public void run() {
					Location loc = p.getLocation();
					Vector direc = loc.getDirection().normalize();
					t=t+0.5;
					if (t==0.5)
						p.playSound(loc, Sound.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, 1, 0);
					
					double x = direc.getX()*t;
					double y = direc.getY()*t+1.5;
					double z = direc.getZ()*t;
					
					
					
					loc.add(x,y,z);
					if (t%2.0==1)
						p.playSound(loc, Sound.ENTITY_ENDERDRAGON_SHOOT, 1, 0);
					
					ParticleEffect.EXPLOSION_HUGE.send(Bukkit.getOnlinePlayers(), loc, 0,0,0,0,1); // This works apparently
					
					for (Entity en : loc.getChunk().getEntities()){
		                    if (en.getLocation().distance(loc) < 8.0){
		                    	if(en != p) {
			                        if(en.getType().isAlive()) {                                     
				                        Damageable d = (Damageable) en;
				                        d.damage(500, p);        
			                    
			                    }
		                   	}
		                }
	                }
					
					loc.subtract(x,y,z);
					
					if(t>30.0) {
						this.cancel();
					}
				}	
			}.runTaskTimer(plugin, 0, 1);	
		}
	}	

}