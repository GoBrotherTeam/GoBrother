package de.gobrother;

import de.gobrother.config.Config;
import de.gobrother.network.Server;
import de.gobrother.player.JavaPlayer;
import io.gomint.GoMint;
import io.gomint.config.InvalidConfigurationException;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerMoveEvent;
import io.gomint.plugin.Plugin;
import io.gomint.plugin.PluginName;
import io.gomint.plugin.Version;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@PluginName("GoBrother")
@Version(major = 1, minor = 0)
public class GoBrother extends Plugin {

    private Config config;
    private Server goBrotherServer;

    public final static String MCPC_VERSION = "1.12.2";
    public final static int MCPC_PROTOCOL   = 340;

    public Map<UUID, JavaPlayer> javaPlayer = new HashMap<>();

    @Override
    public void onInstall() {
        getLogger().info("Starte GoBrother v" + getVersion().toString());

        File goBrotherDir = new File("plugins/GoBrother/");
        goBrotherDir.mkdirs();

        File configFile = new File("plugins/GoBrother/config.cfg");

        this.config = new Config();

        try {
            this.config.init( configFile );
        } catch ( InvalidConfigurationException e ) {
            e.printStackTrace();
        }

        this.goBrotherServer = new Server(this, config.getPort());
        this.goBrotherServer.start();

    }

    @Override
    public void onUninstall() {
        this.goBrotherServer.stop();
    }

}
