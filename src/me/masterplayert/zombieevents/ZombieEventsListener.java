package me.masterplayert.zombieevents;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ZombieEventsListener implements Listener{
	@EventHandler
	public void addRaiderKnockback(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			if (!event.getEntity().isDead()) {
				LivingEntity entity = (LivingEntity)event.getDamager();
				LivingEntity player = (LivingEntity)event.getEntity();
				ZombieEventRaiders.knockBackEnemy(entity, player, 5);
				ZombieEventRaiders.bombRaiders(player, entity);
			}
		}
	}
	
	@EventHandler
	public void addRaiderPotionEffects(EntityDamageEvent event) {
		if (event.getEntity() instanceof LivingEntity) {
			if (!event.getEntity().isDead()) {
				ZombieEventRaiders.giveRaidersRegen((LivingEntity)event.getEntity());
			}
		}
	}
	
	@EventHandler
    public void playerAdvancementEvent(PlayerAdvancementDoneEvent event) {
		String name = event.getAdvancement().getKey().getKey();
		Player player = event.getPlayer();
		if (ZombieEventRaiders.hasAdvancement(player, name)) {
			NamespacedKey key = new NamespacedKey(ZombiesMain.getInstance(), "zombieevents-aggro-level");
			PersistentDataContainer container = player.getPersistentDataContainer();
			if (name.equalsIgnoreCase("story/upgrade_tools")) {
				player.sendMessage("The world has noticed you!");
				player.sendMessage("Aggro: " + container.get(key, PersistentDataType.INTEGER));
				if(!container.has(key, PersistentDataType.INTEGER)) {
				    //double foundValue = container.get(key, PersistentDataType.DOUBLE);\
					container.set(key, PersistentDataType.INTEGER, 1);
					player.sendMessage("Aggro Old: " + container.get(key, PersistentDataType.INTEGER));
				}
				ZombieEventRaiders.startRaid(player, EntityType.ZOMBIE);
			}
			if (name.equalsIgnoreCase("story/smelt_iron")) {
				player.sendMessage("The world has noticed you!");
				player.sendMessage("Aggro: " + container.get(key, PersistentDataType.INTEGER));
				if(!container.has(key, PersistentDataType.INTEGER)) {
				    //double foundValue = container.get(key, PersistentDataType.DOUBLE);\
					container.set(key, PersistentDataType.INTEGER, 2);
					player.sendMessage("Aggro Old: " + container.get(key, PersistentDataType.INTEGER));
				}
				else if(container.get(key, PersistentDataType.INTEGER) < 2) {
					container.set(key, PersistentDataType.INTEGER, 2);
					player.sendMessage("Aggro Old: " + container.get(key, PersistentDataType.INTEGER));
				}
				ZombieEventRaiders.startRaid(player, EntityType.ZOMBIE);
			}
			if (name.equalsIgnoreCase("story/enter_the_nether")) {
				player.sendMessage("The world has noticed you!");
				player.sendMessage("Aggro: " + container.get(key, PersistentDataType.INTEGER));
				if(!container.has(key, PersistentDataType.INTEGER)) {
				    //double foundValue = container.get(key, PersistentDataType.DOUBLE);\
					container.set(key, PersistentDataType.INTEGER, 3);
					player.sendMessage("Aggro Old: " + container.get(key, PersistentDataType.INTEGER));
				}
				else if(container.get(key, PersistentDataType.INTEGER) < 3) {
					container.set(key, PersistentDataType.INTEGER, 3);
					player.sendMessage("Aggro Old: " + container.get(key, PersistentDataType.INTEGER));
				}
				ZombieEventRaiders.startRaid(player, EntityType.ZOMBIE);
			}
			if (name.equalsIgnoreCase("story/enter_the_end")) {
				player.sendMessage("The world has noticed you!");
				player.sendMessage("Aggro: " + container.get(key, PersistentDataType.INTEGER));
				if(!container.has(key, PersistentDataType.INTEGER)) {
				    //double foundValue = container.get(key, PersistentDataType.DOUBLE);\
					container.set(key, PersistentDataType.INTEGER, 4);
					player.sendMessage("Aggro Old: " + container.get(key, PersistentDataType.INTEGER));
				}
				else if(container.get(key, PersistentDataType.INTEGER) < 4) {
					container.set(key, PersistentDataType.INTEGER, 4);
					player.sendMessage("Aggro Old: " + container.get(key, PersistentDataType.INTEGER));
				}
				ZombieEventRaiders.startRaid(player, EntityType.ZOMBIE);
			}
			if (name.equalsIgnoreCase("story/mine_diamond")) {
				player.sendMessage("The world has noticed you!");
				player.sendMessage("Aggro: " + container.get(key, PersistentDataType.INTEGER));
				if(!container.has(key, PersistentDataType.INTEGER)) {
				    //double foundValue = container.get(key, PersistentDataType.DOUBLE);\
					container.set(key, PersistentDataType.INTEGER, 5);
					player.sendMessage("Aggro Old: " + container.get(key, PersistentDataType.INTEGER));
				}
				else if(container.get(key, PersistentDataType.INTEGER) < 5) {
					container.set(key, PersistentDataType.INTEGER, 5);
					player.sendMessage("Aggro Old: " + container.get(key, PersistentDataType.INTEGER));
				}
				ZombieEventRaiders.startRaid(player, EntityType.ZOMBIE);
			}
			if (name.equalsIgnoreCase("end/kill_dragon")) {
				player.sendMessage("The world has noticed you!");
				player.sendMessage("Aggro: " + container.get(key, PersistentDataType.INTEGER));
				if(!container.has(key, PersistentDataType.INTEGER)) {
				    //double foundValue = container.get(key, PersistentDataType.DOUBLE);\
					container.set(key, PersistentDataType.INTEGER, 6);
					player.sendMessage("Aggro Old: " + container.get(key, PersistentDataType.INTEGER));
				}
				else if(container.get(key, PersistentDataType.INTEGER) < 6) {
					container.set(key, PersistentDataType.INTEGER, 6);
					player.sendMessage("Aggro Old: " + container.get(key, PersistentDataType.INTEGER));
				}
				ZombieEventRaiders.startRaid(player, EntityType.ZOMBIE);
			}
			if (name.equalsIgnoreCase("end/respawn_dragon")) {
				player.sendMessage("The world has noticed you!");
				player.sendMessage("Aggro: " + container.get(key, PersistentDataType.INTEGER));
				if(!container.has(key, PersistentDataType.INTEGER)) {
				    //double foundValue = container.get(key, PersistentDataType.DOUBLE);\
					container.set(key, PersistentDataType.INTEGER, 7);
					player.sendMessage("Aggro Old: " + container.get(key, PersistentDataType.INTEGER));
				}
				else if(container.get(key, PersistentDataType.INTEGER) < 7) {
					container.set(key, PersistentDataType.INTEGER, 7);
					player.sendMessage("Aggro Old: " + container.get(key, PersistentDataType.INTEGER));
				}
				ZombieEventRaiders.startRaid(player, EntityType.ZOMBIE);
			}
		}
	}
}
