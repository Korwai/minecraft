package korwai;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.inventivetalent.particle.ParticleEffect;

public class Main extends JavaPlugin implements Listener {
	
	Logger PluginLogger = Bukkit.getLogger();
	HealthChecker hp;
	//HurricaneMissile hm;
	PaladinConsecrate pc;
	
	@Override
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
		PluginLogger.info("My plugin is being started.");
		PluginLogger.warning("Enabling the plugin!");
		this.getLogger().log(Level.INFO, "Tutorial Enabled");
		
		hp = new HealthChecker(this);
		hp.healthCheck();
		
		getServer().getPluginManager().registerEvents(new Listeners(), this); 
		getServer().getPluginManager().registerEvents(new HurricaneWand(), this);
		getServer().getPluginManager().registerEvents(new FreezeBeam(), this);
		getServer().getPluginManager().registerEvents(new HurricaneMissile(this), this);
		getServer().getPluginManager().registerEvents(new ArcherMarkTarget(), this);
		getServer().getPluginManager().registerEvents(new BubbleLock(this), this);
		getServer().getPluginManager().registerEvents(new AssassinsBlade(), this);
		getServer().getPluginManager().registerEvents(new PaladinConsecrate(this), this);
		
	}
	
	@Override
	public void onDisable()
	{
		PluginLogger.info("My plugin is being disabled.");
		PluginLogger.severe("Plugin is dead :(");

	}
	
	
	@EventHandler
	public void mark(ProjectileHitEvent e) {
		Projectile p = e.getEntity();
		if (p instanceof Snowball) {
			Snowball sb = (Snowball) p;
			if(sb.getShooter() instanceof Player) {
				Player player = (Player) sb.getShooter();
				for (Entity en : sb.getLocation().getChunk().getEntities()){
					if (en.getLocation().distance(sb.getLocation()) < 4.0){
						Damageable d = (Damageable) en;
						ParticleEffect.EXPLOSION_LARGE.send(Bukkit.getOnlinePlayers(), sb.getLocation(), 0,0,0,0,1);
	                    d.damage(4, p);
	                    d.setGlowing(true);
	                    
					} else {
						ParticleEffect.EXPLOSION_LARGE.send(Bukkit.getOnlinePlayers(), sb.getLocation(), 0,0,0,0,1);
					}
                }
            }
		}
	}

}
