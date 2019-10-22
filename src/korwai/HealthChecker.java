package korwai;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class HealthChecker implements Listener {
	
	private Main plugin;
	
	public HealthChecker(Main plugin) {
		this.plugin=plugin;
	}
	
	public void healthCheck() {
		 new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			
			@Override
			public void run() {
				
				for (LivingEntity e : Bukkit.getServer().getWorld("LoliTest").getLivingEntities()) {
					double hpPercent = e.getHealth()/e.getMaxHealth();
					String name = "";
					
					name = name + ChatColor.GREEN + "[";
					
					double counter2 = 0.00;
					for (double counter = 0.1; counter < 1.0; counter+=0.1) {
						
						if(counter == 0.6) {
							name = name + " " +ChatColor.GREEN + e.getHealth() + " ";
						}
						
						if (counter2<hpPercent) {
							name = name + ChatColor.GREEN + "░";
						} else if (counter2>=hpPercent){
							name = name + ChatColor.RED + "░";
						}
						counter2 = counter + 0.01;
						
					}
					
					name = name + ChatColor.GREEN + "]";
					
					e.setCustomName(name);
					e.setCustomNameVisible(true);
				}
			}
		}.runTaskTimerAsynchronously(plugin, 0, 5);
		
	}

}
