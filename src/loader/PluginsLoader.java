package loader;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PluginsLoader {
    private static String filename = "config.json";
    private Map<String, PluginDescriptor> loaded = new HashMap<String, PluginDescriptor>();

    public PluginsLoader() throws ParseException, ClassNotFoundException {
    	JSONParser parser = new JSONParser();
    	try {
        	JSONArray plugins = (JSONArray) parser.parse(new FileReader(filename));
      	  for (Object p : plugins)
      	  {
      		JSONObject plugin = (JSONObject) p;
      		String type = (String) plugin.get("type");
      		String name = (String) plugin.get("name");
      		String className = (String) plugin.get("className");
      		List<String> dList = new ArrayList<String>();
      		JSONArray dependencies = (JSONArray) plugin.get("dependencies");
        	  for (Object d : dependencies)
          	  {
        		  JSONObject dName = (JSONObject) d;
            		String bvbname = (String) dName.get("name");
            		dList.add(bvbname);
          	  }
              Class<?> interf = Class.forName(className);
        		PluginDescriptor pd = new PluginDescriptor(type, name, className, interf, dList);
        		loaded.put(className, pd);
      	  }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object donnePlugin(String key, Class<?> plugin) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> c = Class.forName(key);
        Object o = c.newInstance();
        loaded.get(key);
        if (plugin.isAssignableFrom(c)){
            return o;
        }
        return null;
    }
    
    public List<PluginDescriptor> getAllPluginsByType(String type, Class<?> interf){
    	ArrayList<PluginDescriptor> allPlugins = new ArrayList<>();
    	allPlugins.add(new PluginDescriptor("afficheur", "Un afficheur custom", "plugins.MonAfficheur", interf, new ArrayList<String>())); 
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
