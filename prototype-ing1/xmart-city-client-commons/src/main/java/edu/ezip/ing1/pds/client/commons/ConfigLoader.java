package edu.ezip.ing1.pds.client.commons;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.InputStream;

public class ConfigLoader {
    public static <T>  T loadConfig(final Class<T> classType, final String yamlFile) {
        Representer represent = new Representer();
        represent.getPropertyUtils().setSkipMissingProperties(true);
        final Yaml yaml = new Yaml(new Constructor(classType),represent);
        final InputStream nptStrm =
                ConfigLoader.class.getClassLoader().getResourceAsStream(yamlFile);
        final T t =  (T)yaml.load(nptStrm);
        return t;
    }
}
