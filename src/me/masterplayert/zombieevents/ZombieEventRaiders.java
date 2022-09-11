package me.masterplayert.zombieevents;

import java.util.Iterator;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import org.bukkit.entity.Player;

public class ZombieEventRaiders{
	public static ItemStack[] weapons = {new ItemStack(Material.WOODEN_SWORD), new ItemStack(Material.STONE_SWORD), new ItemStack(Material.GOLDEN_SWORD), new ItemStack(Material.IRON_SWORD), new ItemStack(Material.DIAMOND_SWORD), new ItemStack(Material.NETHERITE_SWORD)};
	public static ItemStack[] helms = {new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.CHAINMAIL_HELMET), new ItemStack(Material.GOLDEN_HELMET), new ItemStack(Material.IRON_HELMET), new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.NETHERITE_HELMET)};
	public static ItemStack[] chestPlates = {new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.CHAINMAIL_CHESTPLATE), new ItemStack(Material.GOLDEN_CHESTPLATE), new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.NETHERITE_CHESTPLATE)};
	public static ItemStack[] leggings = {new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.CHAINMAIL_LEGGINGS), new ItemStack(Material.GOLDEN_LEGGINGS), new ItemStack(Material.IRON_LEGGINGS), new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.NETHERITE_LEGGINGS)};
	public static ItemStack[] boots = {new ItemStack(Material.LEATHER_BOOTS), new ItemStack(Material.CHAINMAIL_BOOTS), new ItemStack(Material.GOLDEN_BOOTS), new ItemStack(Material.IRON_BOOTS), new ItemStack(Material.DIAMOND_BOOTS), new ItemStack(Material.NETHERITE_BOOTS)};
	public static void spawnZombieRaider(Player player) {
		World world = player.getWorld();
		Location playerLoc = player.getLocation();
		Location newLoc = new Location(world, playerLoc.getX() + 2, playerLoc.getY(), playerLoc.getZ() + 2);
		Zombie raider = (Zombie) world.spawnEntity(newLoc, EntityType.ZOMBIE);
		NamespacedKey key = new NamespacedKey(ZombiesMain.getInstance(), "zombieevents-raider-id");
		PersistentDataContainer container = raider.getPersistentDataContainer();
		container.set(key, new UUIDDataType(), player.getUniqueId());
		container.set(key, PersistentDataType.INTEGER, 1);
		raider.getEquipment().setItemInMainHand(getWeaponryLevel(player, weapons));
		raider.getEquipment().setHelmet(getWeaponryLevel(player, helms));
		raider.getEquipment().setChestplate(getWeaponryLevel(player, chestPlates));
		raider.getEquipment().setLeggings(getWeaponryLevel(player, leggings));
		raider.getEquipment().setBoots(getWeaponryLevel(player, boots));
		addPotionEffect((LivingEntity)raider, ZombiePotionEffects.RAIDER_REGEN, 10000, 3);
	}
	
	public static void addPotionEffect(LivingEntity entityToEffect, ZombiePotionEffects type, int duration, int amplifier) {
		NamespacedKey key = new NamespacedKey(ZombiesMain.getInstance(), "zombieevents-raider-id");
		PersistentDataContainer container = entityToEffect.getPersistentDataContainer();
		if (container.has(key, PersistentDataType.INTEGER)) {
			if (type == ZombiePotionEffects.RAIDER_REGEN) {
				if (entityToEffect.getHealth() < entityToEffect.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
					if (amplifier == 1) {
						entityToEffect.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, entityToEffect.getLocation(), 10, 0.3, 1, 0.3);
						if (entityToEffect.getHealth() + 2 > entityToEffect.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
							entityToEffect.setHealth(entityToEffect.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
						}
						else {
							entityToEffect.setHealth(entityToEffect.getHealth() + 2);
						}
					}
					else if (amplifier == 2) {
						entityToEffect.getWorld().spawnParticle(Particle.HEART, entityToEffect.getLocation(), 10, 0.3, 1, 0.3);
						if (entityToEffect.getHealth() + 3 > entityToEffect.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
							entityToEffect.setHealth(entityToEffect.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
						}
						else {
							entityToEffect.setHealth(entityToEffect.getHealth() + 3);
						}
					}
					else if (amplifier == 3) {
						entityToEffect.getWorld().spawnParticle(Particle.DRIPPING_HONEY, entityToEffect.getLocation(), 10, 0.3, 1, 0.3);
						if (entityToEffect.getHealth() + 4 > entityToEffect.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
							entityToEffect.setHealth(entityToEffect.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
						}
						else {
							entityToEffect.setHealth(entityToEffect.getHealth() + 4);
						}
					}
				}
			}
		}
	}
	
	public static ItemStack getWeaponryLevel(Player player, ItemStack[] list) {
		NamespacedKey key = new NamespacedKey(ZombiesMain.getInstance(), "zombieevents-aggro-level");
		PersistentDataContainer container = player.getPersistentDataContainer();
		if(container.has(key, PersistentDataType.INTEGER)) {
			int aggro = container.get(key, PersistentDataType.INTEGER);
			Random random = new Random();
			int randomInt = random.nextInt(1000);
			switch(aggro) {
				case 1:  
					if (randomInt <= 500) {
						return list[0];
					}
					else {
						return null;
					}
				case 2:  
					if (randomInt <= 600) {
						randomInt = random.nextInt(2);
						return list[randomInt];
					}
					else {
						return list[0];
					}
				case 3:  
					if (randomInt <= 650) {
						randomInt = random.nextInt(2);
						return list[randomInt];
					}
					else {
						return list[1];
					}
				case 4:  
					if (randomInt <= 700) {
						randomInt = random.nextInt(2);
						return list[randomInt];
					}
					else {
						return list[2];
					}
				case 5:  
					if (randomInt <= 750) {
						randomInt = random.nextInt(2);
						return list[randomInt];
					}
					else {
						return list[3];
					}
				case 6:  
					if (randomInt <= 800) {
						randomInt = random.nextInt(2);
						return list[randomInt];
					}
					else {
						return list[4];
					}
				case 7:  
					if (randomInt <= 900) {
						randomInt = random.nextInt(2);
						return list[randomInt];
					}
					else {
						return list[5];
					}
			}
		}
		return null;
	}
	
	public static int getWorldDifficulty(World world) {
		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			return 4;
		}
		if (world.getDifficulty() == Difficulty.EASY) {
			return 3;
		}
		if (world.getDifficulty() == Difficulty.NORMAL) {
			return 2;
		}
		if (world.getDifficulty() == Difficulty.HARD) {
			return 1;
		}
		return -1;
	}
	
	public static void raidStartSound(Player player) {
		World world = player.getWorld();
		world.playSound(player, Sound.ENTITY_PARROT_IMITATE_ENDER_DRAGON, SoundCategory.MASTER, 1, 1);
	}
	
	public static void startRaid(Player player, EntityType entityType) {
		player.sendMessage("You have angered the Void!");
		raidStartSound(player);
		spawnnRaid(player);
	}
	
	public static void spawnnRaid(Player player) {
		NamespacedKey key = new NamespacedKey(ZombiesMain.getInstance(), "zombieevents-aggro-level");
		PersistentDataContainer container = player.getPersistentDataContainer();
		if(container.has(key, PersistentDataType.INTEGER)) {
			int aggro = container.get(key, PersistentDataType.INTEGER);
			Random random = new Random();
			int randomInt = random.nextInt(1);
			switch(aggro) {
				case 1:
					randomInt = random.nextInt(25);
					spawnWave(player, randomInt / getWorldDifficulty(player.getWorld()));
				case 2:
					randomInt = random.nextInt(50);
					spawnWave(player, randomInt / getWorldDifficulty(player.getWorld()));
				case 3:
					randomInt = random.nextInt(75);
					spawnWave(player, randomInt / getWorldDifficulty(player.getWorld()));
				case 4:
					randomInt = random.nextInt(100);
					spawnWave(player, randomInt / getWorldDifficulty(player.getWorld()));
				case 5:
					randomInt = random.nextInt(125);
					spawnWave(player, randomInt / getWorldDifficulty(player.getWorld()));
				case 6:
					randomInt = random.nextInt(150);
					spawnWave(player, randomInt / getWorldDifficulty(player.getWorld()));
				case 7:
					randomInt = random.nextInt(200);
					spawnWave(player, randomInt / getWorldDifficulty(player.getWorld()));
			}
		}
	}
	
	public static void giveRaidersRegen(LivingEntity entity) {
		Random random = new Random();
		NamespacedKey raiderKey = new NamespacedKey(ZombiesMain.getInstance(), "zombieevents-raider-id");
		PersistentDataContainer raiderContainer = entity.getPersistentDataContainer();
		if (raiderContainer.has(raiderKey, new UUIDDataType())) {
			Player player = Bukkit.getPlayer(raiderContainer.get(raiderKey, new UUIDDataType()));
			NamespacedKey key = new NamespacedKey(ZombiesMain.getInstance(), "zombieevents-aggro-level");
			PersistentDataContainer container = player.getPersistentDataContainer();
			if(container.has(key, PersistentDataType.INTEGER)) {
				int randomInt = random.nextInt((100 - (10 * container.get(key, PersistentDataType.INTEGER))) * getWorldDifficulty(player.getWorld()));
				if (randomInt < 30) {
					switch (getWorldDifficulty(player.getWorld())) {
						case 1:
							ZombieEventRaiders.addPotionEffect(entity, ZombiePotionEffects.RAIDER_REGEN, 10000, 3);
						case 2:
							ZombieEventRaiders.addPotionEffect(entity, ZombiePotionEffects.RAIDER_REGEN, 10000, 2);
						case 3:
							ZombieEventRaiders.addPotionEffect(entity, ZombiePotionEffects.RAIDER_REGEN, 10000, 1);
					}
				}
			}
		}
	}
	
	public static void bombRaiders(LivingEntity player, LivingEntity entity) {
		Random random = new Random();
		NamespacedKey key = new NamespacedKey(ZombiesMain.getInstance(), "zombieevents-aggro-level");
		PersistentDataContainer container = player.getPersistentDataContainer();
		if(container.has(key, PersistentDataType.INTEGER)) {
			int randomInt = random.nextInt((100 - (10 * container.get(key, PersistentDataType.INTEGER))) * getWorldDifficulty(player.getWorld()));
			if (randomInt < 15) {
				World world = player.getWorld();
				boolean flag = world.isGameRule("mobGriefing");
		        world.createExplosion(entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ(), 0, flag);
		        entity.damage(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			}
		}
	}
	
	public static void knockBackEnemy(LivingEntity attacker, LivingEntity enemy, double speed) {
		if (attacker != null && enemy != null) {
			NamespacedKey key = new NamespacedKey(ZombiesMain.getInstance(), "zombieevents-raider-id");
			PersistentDataContainer container = attacker.getPersistentDataContainer();
			if (container.has(key, PersistentDataType.INTEGER)) {
				Location attackerLoc = attacker.getLocation();
				Location enemyLoc = enemy.getLocation();
				Vector attackerVec = new Vector().setX(attackerLoc.getX()).setY(attackerLoc.getY()).setZ(attackerLoc.getZ());
				Vector enemyVec = new Vector().setX(enemyLoc.getX()).setY(enemyLoc.getY()).setZ(enemyLoc.getZ());
				enemy.setVelocity(enemy.getVelocity().add(enemyVec.subtract(attackerVec)).normalize().multiply(speed));
			}
		}
	}
	
	public static void spawnWave(Player player, int raiderAmount) {
		for (int i = 0; i < raiderAmount; i++) {
			ZombieEventRaiders.spawnZombieRaider(player);
		}
	}
	
    public static boolean hasAdvancement(Player player, String achname){
        Advancement ach = null;
        for (Iterator<Advancement> iter = Bukkit.getServer().advancementIterator(); iter.hasNext(); ) {
            Advancement adv = iter.next();
            if (adv.getKey().getKey().equalsIgnoreCase(achname)){
                ach = adv;
                break;
            }
        }
        AdvancementProgress prog = player.getAdvancementProgress(ach);
        return prog.isDone();
    }
}
