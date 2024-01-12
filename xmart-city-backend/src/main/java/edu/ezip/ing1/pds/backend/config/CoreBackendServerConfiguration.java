package edu.ezip.ing1.pds.backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class CoreBackendServerConfiguration {
    private int listenPort;
    public CoreBackendServerConfiguration() {
    }

    public int getListenPort() {
        return listenPort;
    }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }

    @Override
    public String toString() {
        return "CoreBackendServerConfiguration{" +
                "listenPort=" + listenPort +
                '}';
    }
}
