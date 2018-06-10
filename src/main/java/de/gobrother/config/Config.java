package de.gobrother.config;

import io.gomint.config.YamlConfig;
import lombok.Getter;

@Getter
public class Config extends YamlConfig {

    private int port = 25565;

}
