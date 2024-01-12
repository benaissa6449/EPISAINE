package edu.ezip.ing1.pds.client.commons;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class ConfigLoader {
    public static <T>  T loadConfig(final Class<T> classType, final String yamlFile) {
        final Yaml yaml = new Yaml(new Constructor(classType));
        final InputStream nptStrm =
                ConfigLoader.class.getClassLoader().getResourceAsStream(yamlFile);
        final T t =  (T)yaml.load(nptStrm);
        return t;
    }

}
