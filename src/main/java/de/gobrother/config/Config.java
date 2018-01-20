package de.gobrother.config;

import com.blackypaw.simpleconfig.SimpleConfig;
import lombok.Getter;

@Getter
public class Config extends SimpleConfig {

    private int port = 25565;

}
