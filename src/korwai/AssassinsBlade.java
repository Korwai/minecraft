package korwai;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.wrappers.EnumWrappers.Particle;

public class AssassinsBlade implements Listener {
	private int InvisDur = 600; // 20 ticks is 1 second
	private int SpeedLen = 600;
	private int JumpLen = 600;
	
	@EventHandler
	public void effect(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
			if(p.isSneaking() && p.getInventory().getItemInMainHand().getType()==Material.GOLD_SWORD) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, InvisDur, 0 , true), true);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, SpeedLen, 5 , true), true);
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, JumpLen, 3 , true), true);
				p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1, 0);
				for (int c = 0; c < 6; c++) 
					Bukkit.getServer().getWorld("LoliTest").playEffect(p.getLocation(),Effect.SMOKE, 1);

				p.getInventory().getItemInMainHand().addEnchantment(Enchantment.DAMAGE_ALL, 5);
				p.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "The shadows engulf you.");
				
			}
		}

			/*if(p instanceof EntityDamageByEntityEvent) {
				
				if(e.getDamager() == p) {
					  e.setDamage(e.getDamage() * 1.4);
					  p.removePotionEffect(PotionEffectType.INVISIBILITY);
					  p.getInventory().getItemInMainHand().removeEnchantment(Enchantment.DAMAGE_ALL);
					  p.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "You are no longer hidden!");
				}
		}*/
	}
	

