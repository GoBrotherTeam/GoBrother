package de.gobrother.player;

import io.gomint.entity.ChatType;
import io.gomint.entity.EntityPlayer;
import io.gomint.gui.Form;
import io.gomint.gui.FormListener;
import io.gomint.inventory.Inventory;
import io.gomint.math.Vector;
import io.gomint.permission.PermissionManager;
import io.gomint.server.entity.passive.EntityHuman;
import io.gomint.server.inventory.InventoryHolder;
import io.gomint.world.Gamemode;
import io.gomint.world.Particle;
import io.gomint.world.Sound;
import io.gomint.world.SoundData;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class JavaPlayer extends EntityHuman implements EntityPlayer, InventoryHolder {

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

}
