package loader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import core.UI.Body;
import core.UI.Menu;
import core.UI.UserInterface;
import core.model.ModelLoader;
import model.Alarm;
import plugins.PlugIntelligent;
import plugins.alarm.PlugIntelligentAlarm;
import plugins.model.PlugIntelligentModelLoader;
import plugins.ui.PlugIntelligentBody;
import plugins.ui.PlugIntelligentMenu;
import plugins.ui.PlugIntelligentUI;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PluginsLoader {
    private static String filename = "src/ressource/config.json";
    private Map<String, PluginDescriptor> loaded = new HashMap<String, PluginDescriptor>();

    public PluginsLoader() throws ParseException, ClassNotFoundException {
    	JSONParser parser = new JSONParser();
    	try {
    		JSONObject obj = (JSONObject) parser.parse(new FileReader(filename));
        	JSONArray plugins = (JSONArray) obj.get("plugins");
        	System.out.println(obj);
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
        		String dName = (String) d;
            	dList.add(dName);
          	  }
        	  String classNamePack = type!=null ? "plugins."+type+"."+className : "plugins."+className ;
              Class<?> interf = Class.forName(classNamePack);
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
    
    public Map<String, PluginDescriptor> getAllPluginsByType(){
    	return this.loaded;
    }
    
    public Object getPlugin(PluginDescriptor plugin) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> c = Class.forName(plugin.getClassName());
        Object o = c.newInstance();
        if (plugin.getInterf().isAssignableFrom(c)){
            return o;
        }
        return null;
    }
    
 // The entry main() method
    public static void main(String[] args) {
    	try {
			PluginsLoader p = new PluginsLoader();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	UserInterface ui = new PlugIntelligentUI(); 
    	Body b = new PlugIntelligentBody("PlugIntelligentBody", ui);
    	ui.addBody(b);
    	Menu m = new PlugIntelligentMenu("PlugIntelligentMenu", ui);
    	ui.addMenu(m);
    	
    	Alarm a = new PlugIntelligentAlarm("PlugIntelligentAlarm");
    	
    	ModelLoader ml = new PlugIntelligentModelLoader("PlugIntelligentModelLoader");
    	
    	
    	PlugIntelligent pi = new PlugIntelligent(ui,ml,a);
    	
    	pi.run();
    }
}
