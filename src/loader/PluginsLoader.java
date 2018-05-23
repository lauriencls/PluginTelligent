package loader;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import core.Appli;
import core.UI.Body;
import core.UI.Menu;
import core.UI.UserInterface;
import core.model.AlarmLoader;
import core.model.ModelLoader;
import model.Alarm;


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

    private PluginsLoader(){
    	JSONParser parser = new JSONParser();
    	try {
    		JSONObject obj = null;
			try {
				obj = (JSONObject) parser.parse(new FileReader(filename));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	JSONArray plugins = (JSONArray) obj.get("plugins");
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
          		if(plugin.get("isMainApplication")!=null){
          			defaultUI=(String) plugin.get("ui");
          			defaultMenu=(String) plugin.get("menu");
          			defaultBody=(String) plugin.get("body");
          			defaultAlarm=(String) plugin.get("alarm");
        		    defaultModelLoader=(String) plugin.get("modeloader");
        		    defaultPlugin=pd;
          		}
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
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    //Holder
    private static class PluginsLoaderHolder
    {       
        private final static PluginsLoader instance = new PluginsLoader();
    }
 
    public static PluginsLoader getInstance()
    {
        return PluginsLoaderHolder.instance;
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
    
    public Map<String, PluginDescriptor> getAllPluginsByType(String type){
        Map<String, PluginDescriptor> byType = new HashMap<String, PluginDescriptor>();

      	for(Entry<String, PluginDescriptor> entry : loaded.entrySet()) {
      		if(entry.getValue().equals(type)){
      			byType.put(entry.getKey(), entry.getValue());
      		}
      	}
    	return byType;
    }
    
    public Map<String, PluginDescriptor> getAllPlugins(){
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
    public Appli initMainPlugin() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
    	Class<?> classMain = Class.forName("plugins."+defaultPlugin.getClassName());
            Object pluginMain = classMain.newInstance();
            ((Appli) pluginMain).run();
            return (Appli) pluginMain;
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

	public UserInterface getDefaultUI() {
  	    Class<?> classUI = null;
		try {
			classUI = Class.forName("plugins.ui."+defaultUI);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Object ui = null;
		try {
			ui = classUI.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	    Class<?> classB = null;
		try {
			classB = Class.forName("plugins.ui."+defaultBody);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Object body = null;
		try {
			body = classB.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ((UserInterface) ui).addBody((Body) body);
  	    Class<?> classM = null;
		try {
			classM = Class.forName("plugins.ui."+defaultMenu);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Object menu = null;
		try {
			menu = classM.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ((UserInterface) ui).addMenu((Menu) menu);
        ((Body) body).setUserInterface((UserInterface) ui);
		return (UserInterface) ui;
	}

	public ModelLoader getDefaultModelLoader() {
  	    Class<?> classML = null;
		try {
			classML = Class.forName("plugins.model."+defaultModelLoader);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Object modelLoader = null;
		try {
			modelLoader = classML.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (ModelLoader) modelLoader;
	}

	public AlarmLoader getDefaultAlarm() {
  	    Class<?> classA = null;
		try {
			classA = Class.forName("plugins.alarm."+defaultAlarm);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Object alarm = null;
		try {
			alarm = classA.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (AlarmLoader) alarm;
	}
}
