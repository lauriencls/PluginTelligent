package loader;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
    private PluginDescriptor defaultPlugin;
    private String defaultUI;
    private String defaultMenu;
    private String defaultBody;
    private String defaultAlarm;
    private String defaultModelLoader;


    public PluginsLoader() throws ParseException, ClassNotFoundException, IllegalAccessException, InstantiationException {
    	JSONParser parser = new JSONParser();
    	try {
    		JSONObject obj = (JSONObject) parser.parse(new FileReader(filename));
        	JSONArray plugins = (JSONArray) obj.get("plugins");
        	System.out.println(obj);
      	  for (Object p : plugins)
      	  {
      		JSONObject plugin = (JSONObject) p;
      		if(plugin.get("isMainApplication")!=null){
      			defaultUI=(String) plugin.get("ui");
      			defaultMenu=(String) plugin.get("menu");
      			defaultBody=(String) plugin.get("body");
      			defaultAlarm=(String) plugin.get("alarm");
    		    defaultModelLoader=(String) plugin.get("modeloader");
      		}
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
      	/*for(Entry<String, PluginDescriptor> entry : loaded.entrySet()) {
      	    PluginDescriptor value = entry.getValue();
      	    if(value.getDependencies()!=null){
	      	    for(String dependencieName : value.getDependencies()){
	      	    	Object o = loadPlugin(loaded.get(dependencieName));	
		      	}
      	    }
      	}*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getPlugin(String key, Class<?> plugin) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
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
    
    public Object loadPlugin(PluginDescriptor plugin) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
  	  	String classNamePack = plugin.getType()!=null ? "plugins."+plugin.getType()+"."+plugin.getClassName() : "plugins."+plugin.getClassName();
        Class<?> c = Class.forName(classNamePack);
        Object o = c.newInstance();
        if (plugin.getInterf().isAssignableFrom(c)){
            return o;
        }
        return null;
    }
    public void initMainPlugin() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
      	    Class<?> classA = Class.forName("plugins.alarm."+defaultAlarm);
            Object alarm = classA.getConstructor(Class.class).newInstance(defaultAlarm);

      	    Class<?> classMain = Class.forName("plugins."+defaultPlugin.getClassName());
            Object pluginMain = classMain.getConstructor(Class.class).newInstance();
            ((PlugIntelligent) pluginMain).run();
    }
    
 // The entry main() method
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ParseException {
    	PluginsLoader p = new PluginsLoader();
    	p.initMainPlugin();
    	/*UserInterface ui = new PlugIntelligentUI(); 
    	Body b = new PlugIntelligentBody("PlugIntelligentBody", ui);
    	ui.addBody(b);
    	Menu m = new PlugIntelligentMenu("PlugIntelligentMenu", ui);
    	ui.addMenu(m);
    	
    	/*Alarm a = new PlugIntelligentAlarm();
    	
    	ModelLoader ml = new PlugIntelligentModelLoader("PlugIntelligentModelLoader");
    	
    	
    	PlugIntelligent pi = new PlugIntelligent(ui,ml,a);
    	
    	pi.run();*/
    }

	public UserInterface getDefaultUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
  	    Class<?> classUI = Class.forName("plugins.ui."+defaultUI);
        Object ui = classUI.newInstance();
  	    Class<?> classB = Class.forName("plugins.ui."+defaultBody);
        Object body = classB.newInstance();
        ((UserInterface) ui).addBody((Body) body);
  	    Class<?> classM = Class.forName("plugins.ui."+defaultMenu);
        Object menu = classM.newInstance();
        ((UserInterface) ui).addMenu((Menu) menu);
		return (UserInterface) ui;
	}

	public ModelLoader getDefaultModelLoader() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
  	    Class<?> classML = Class.forName("model."+defaultModelLoader);
        Object modelLoader = classML.newInstance();
		return (ModelLoader) modelLoader;
	}
}
