package de.gobrother.player;

import io.gomint.entity.BossBar;
import io.gomint.entity.ChatType;
import io.gomint.entity.Entity;
import io.gomint.entity.EntityPlayer;
import io.gomint.entity.potion.PotionEffect;
import io.gomint.event.entity.EntityDamageEvent;
import io.gomint.gui.Form;
import io.gomint.gui.FormListener;
import io.gomint.inventory.ArmorInventory;
import io.gomint.inventory.Inventory;
import io.gomint.inventory.PlayerInventory;
import io.gomint.math.AxisAlignedBB;
import io.gomint.math.Location;
import io.gomint.math.Vector;
import io.gomint.math.Vector2;
import io.gomint.permission.PermissionManager;
import io.gomint.player.PlayerSkin;
import io.gomint.server.network.PlayerConnection;
import io.gomint.server.world.WorldAdapter;
import io.gomint.world.*;

import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class JavaPlayer implements EntityPlayer {

    @Override
    public void setGamemode(Gamemode gamemode) {

    }

    @Override
    public Gamemode getGamemode() {
        return null;
    }

    @Override
    public boolean isOp() {
        return false;
    }

    @Override
    public void hidePlayer(EntityPlayer entityPlayer) {

    }

    @Override
    public void showPlayer(EntityPlayer entityPlayer) {

    }

    @Override
    public boolean isHidden(EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public void openInventory(Inventory inventory) {

    }

    @Override
    public void sendMessage(String s) {

    }

    @Override
    public void sendMessage(ChatType chatType, String... strings) {

    }

    @Override
    public boolean hasPermission(String s) {
        return false;
    }

    @Override
    public int getViewDistance() {
        return 0;
    }

    @Override
    public void transfer(String s, int i) {

    }

    @Override
    public int getPing() {
        return 0;
    }

    @Override
    public <T> FormListener<T> showForm(Form form) {
        return null;
    }

    @Override
    public <T> FormListener<T> setSettingsForm(Form form) {
        return null;
    }

    @Override
    public void removeSettingsForm() {

    }

    @Override
    public PermissionManager getPermissionManager() {
        return null;
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public void disconnect(String s) {

    }

    @Override
    public int getXP() {
        return 0;
    }

    @Override
    public float getXPPercentage() {
        return 0;
    }

    @Override
    public void setXP(int i) {

    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public void setLevel(int i) {

    }

    @Override
    public void playSound(Vector vector, Sound sound, byte b, SoundData soundData) {

    }

    @Override
    public void playSound(Vector vector, Sound sound, byte b) {

    }

    @Override
    public void sendParticle(Vector vector, Particle particle) {

    }

    @Override
    public void setAllowFlight(boolean b) {

    }

    @Override
    public boolean getAllowFlight() {
        return false;
    }

    @Override
    public void setFlying(boolean b) {

    }

    @Override
    public boolean getFlying() {
        return false;
    }

    @Override
    public void sendTitle(String s, String s1, long l, long l1, long l2, TimeUnit timeUnit) {

    }

    @Override
    public void sendTitle(String s) {

    }

    @Override
    public void sendTitle(String s, String s1) {

    }

    @Override
    public void setGliding(boolean b) {

    }

    @Override
    public boolean isGliding() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public UUID getUUID() {
        return null;
    }

    @Override
    public PlayerSkin getSkin() {
        return null;
    }

    @Override
    public void setSkin(PlayerSkin playerSkin) {

    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public void setDisplayName(String s) {

    }

    @Override
    public String getXboxID() {
        return null;
    }

    @Override
    public PlayerInventory getInventory() {
        return null;
    }

    @Override
    public void setHunger(float v) {

    }

    @Override
    public float getHunger() {
        return 0;
    }

    @Override
    public void setSaturation(float v) {

    }

    @Override
    public float getSaturation() {
        return 0;
    }

    @Override
    public void setSneaking(boolean b) {

    }

    @Override
    public boolean isSneaking() {
        return false;
    }

    @Override
    public void setSprinting(boolean b) {

    }

    @Override
    public boolean isSprinting() {
        return false;
    }

    @Override
    public ArmorInventory getArmorInventory() {
        return null;
    }

    @Override
    public void setHealth(float v) {

    }

    @Override
    public float getHealth() {
        return 0;
    }

    @Override
    public void setMaxHealth(float v) {

    }

    @Override
    public float getMaxHealth() {
        return 0;
    }

    @Override
    public void setImmobile(boolean b) {

    }

    @Override
    public EntityDamageEvent.DamageSource getLastDamageSource() {
        return null;
    }

    @Override
    public Entity getLastDamageEntity() {
        return null;
    }

    @Override
    public void setAbsorptionHearts(float v) {

    }

    @Override
    public float getAbsorptionHearts() {
        return 0;
    }

    @Override
    public void addEffect(PotionEffect potionEffect, int i, long l, TimeUnit timeUnit) {

    }

    @Override
    public boolean hasEffect(PotionEffect potionEffect) {
        return false;
    }

    @Override
    public int getEffectAmplifier(PotionEffect potionEffect) {
        return 0;
    }

    @Override
    public void removeEffect(PotionEffect potionEffect) {

    }

    @Override
    public void removeAllEffects() {

    }

    @Override
    public float getMovementSpeed() {
        return 0;
    }

    @Override
    public void setMovementSpeed(float v) {

    }

    @Override
    public void attack(float v, EntityDamageEvent.DamageSource damageSource) {

    }

    @Override
    public void setBurning(long l, TimeUnit timeUnit) {

    }

    @Override
    public void extinguish() {

    }

    @Override
    public long getEntityId() {
        return 0;
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public float getPositionX() {
        return 0;
    }

    @Override
    public float getPositionY() {
        return 0;
    }

    @Override
    public float getPositionZ() {
        return 0;
    }

    @Override
    public float getPitch() {
        return 0;
    }

    @Override
    public float getYaw() {
        return 0;
    }

    @Override
    public void setVelocity(Vector vector) {

    }

    @Override
    public Vector getVelocity() {
        return null;
    }

    @Override
    public String getNameTag() {
        return null;
    }

    @Override
    public void setNameTag(String s) {

    }

    @Override
    public void setNameTagAlwaysVisible(boolean b) {

    }

    @Override
    public boolean isNameTagAlwaysVisible() {
        return false;
    }

    @Override
    public void setNameTagVisible(boolean b) {

    }

    @Override
    public boolean isNameTagVisible() {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return null;
    }

    @Override
    public boolean isOnGround() {
        return false;
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void spawn(Location location) {

    }

    @Override
    public void teleport(Location location) {

    }

    @Override
    public void despawn() {

    }

    @Override
    public Vector2 getDirectionVector() {
        return null;
    }

    @Override
    public void setAge(long l, TimeUnit timeUnit) {

    }

    @Override
    public void setTicking(boolean b) {

    }

    @Override
    public boolean isTicking() {
        return false;
    }

    @Override
    public BossBar getBossBar() {
        return null;
    }

    @Override
    public void setScale(float v) {

    }

    @Override
    public float getScale() {
        return 0;
    }

    @Override
    public void setHiddenByDefault(boolean b) {

    }

    @Override
    public void showFor(EntityPlayer entityPlayer) {

    }

    @Override
    public void hideFor(EntityPlayer entityPlayer) {

    }

    @Override
    public float getEyeHeight() {
        return 0;
    }
}
