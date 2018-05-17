package loader;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import core.UI.AfficheurAbstrait;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PluginsLoader {
    private static String filename = "config.properties";
    private Properties p;

    public PluginsLoader() throws ParseException {
    	JSONParser parser = new JSONParser();

    	try {
        	JSONArray a = (JSONArray) parser.parse(new FileReader(filename));
      	  for (Object o : a)
      	  {
      		JSONObject plugin = (JSONObject) o;
      	  }
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
    
    public List<PluginDescriptor> getAllPluginsByType(String type, Class<?> interf){
    	ArrayList<PluginDescriptor> allPlugins = new ArrayList<>();
    	allPlugins.add(new PluginDescriptor("afficheur", "Un afficheur custom", "plugins.MonAfficheur", interf)); 
    	return allPlugins;
    }
    
    public Object getPlugin(PluginDescriptor plugin) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> c = Class.forName(plugin.getClassName());
        Object o = c.newInstance();
        if (plugin.getInterf().isAssignableFrom(c)){
            return o;
        }
        return null;
    }
}
