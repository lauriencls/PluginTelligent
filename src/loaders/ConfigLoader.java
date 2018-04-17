package loaders;

import core.interfaces.AfficheurAbstrait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static String filename = "config.properties";
    private Properties p;

    public ConfigLoader() {
         p = new Properties();
        try {
            p.load(new FileInputStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object donnePlugin(String key, Class<?> plugin) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> c = Class.forName(p.getProperty(key));
        Object o = c.newInstance();
        if (AfficheurAbstrait.class.isAssignableFrom(c)){
            return o;
        }
        return null;
    }
}
