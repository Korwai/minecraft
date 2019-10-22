package korwai;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.particle.ParticleEffect;

public class BubbleLock implements org.bukkit.event.Listener {

	private Main plugin;
	
	public BubbleLock(Main plugin) {
		this.plugin=plugin;
	}
	
	@EventHandler
	public void run(PlayerInteractEvent e) {
		Player p = e.getPlayer();
			if (e.getAction()==Action.LEFT_CLICK_AIR && p.getInventory().getItemInMainHand().getType()==Material.SPECTRAL_ARROW) {
				new BukkitRunnable() {
				Location loc = p.getLocation();
				Location loc2 = loc;
				double t = 0.0;
				
				public void run() {
					t=t+0.05*Math.PI;
					for (double theta = 0; theta <= 2*Math.PI; theta=theta+Math.PI/32) {
						
						double x = Math.cos(theta)*t;
						double y = -2*Math.exp(0.1*t)*Math.sin(t);
						double z = Math.sin(theta)*t;
						
						double y2 = 3*Math.exp(-0.1*t) * Math.sin(t);
						
						loc.add(x,y,z);
						loc2.add(x,y2,z);
						ParticleEffect.CLOUD.send(Bukkit.getOnlinePlayers(), loc, 0,0,0,0,1); // This works apparently
						for (int c = 0; c < 3; c++) {
							ParticleEffect.WATER_SPLASH.send(Bukkit.getOnlinePlayers(), loc, 0,0,0,0,1);
							ParticleEffect.WATER_SPLASH.send(Bukkit.getOnlinePlayers(), loc2, 0,0,0,0,1); // This works apparently
						}
						
						loc.subtract(x,y,z);
						loc2.subtract(x,y2,z);
					}
					if(t>10.0) {
						this.cancel();
					}
				}
			}.runTaskTimer(plugin, 0, 1);
		}		
	}

}
