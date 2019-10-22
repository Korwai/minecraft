package korwai;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArcherMarkTarget implements Listener {
	
	public void proj(ProjectileHitEvent e) {
		Projectile p = e.getEntity();
			if(p instanceof Snowball) {
				Snowball sb = (Snowball) p;
				sb.getWorld().createExplosion(sb.getLocation(), 10);
				
			}
		
	}
	
	
		
}


