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
    // **************************************************
    // Instance
    // **************************************************
    private static String filename = "src/ressource/config.json";
    // **************************************************
    // Map contenant les plugins du fichiers de config
    // **************************************************
    private Map<String, PluginDescriptor> loaded = new HashMap<String, PluginDescriptor>();
    // **************************************************
    // Plugins par défaut
    // **************************************************
    private PluginDescriptor defaultPlugin;
    private String defaultUI;
    private String defaultMenu;
    private String defaultBody;
    private String defaultAlarm;
    private String defaultModelLoader;
    
    /**
    * constructeur
    *
    * le fichier de configuration est chargé dans le constructeur
    */
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
    
    /**
    * Holder
    */
    private static class PluginsLoaderHolder
    {       
        private final static PluginsLoader instance = new PluginsLoader();
    }
    
    /**
    * Retourne une instance de la classe
    *
    * @return PluginsLoader
    */
    public static PluginsLoader getInstance()
    {
        return PluginsLoaderHolder.instance;
    }
    
    /**
    * Retourne un plugin en fonction de sa clé (nom de classe) et de sa classe
    *
    * @return Object
    */
    public Object getPlugin(String key, Class<?> plugin) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> c = Class.forName(key);
        Object o = c.newInstance();
        loaded.get(key);
        if (plugin.isAssignableFrom(c)){
            return o;
        }
        return null;
    }
    
    /**
    * Retourne la liste de tous les plugins qui sont du type passé en paramètre
    *
    * @return Map<String, PluginDescriptor>
    */
    public Map<String, PluginDescriptor> getAllPluginsByType(String type){
        Map<String, PluginDescriptor> byType = new HashMap<String, PluginDescriptor>();

      	for(Entry<String, PluginDescriptor> entry : loaded.entrySet()) {
      		if(entry.getValue().equals(type)){
      			byType.put(entry.getKey(), entry.getValue());
      		}
      	}
    	return byType;
    }
    
    /**
    * Retourne la liste de tous les plugins, soit la map loaded, chargé dans le constructeur
    *
    * @return Map<String, PluginDescriptor>
    */
    public Map<String, PluginDescriptor> getAllPlugins(){
    	return this.loaded;
    }
    
    /**
    * Charge un plugin, c'est à dire créer une instance et la retourne
    *
    * @return Object
    */
    public Object loadPlugin(PluginDescriptor plugin) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
  	  	String classNamePack = plugin.getType()!=null ? "plugins."+plugin.getType()+"."+plugin.getClassName() : "plugins."+plugin.getClassName();
        Class<?> c = Class.forName(classNamePack);
        Object o = c.newInstance();
        if (plugin.getInterf().isAssignableFrom(c)){
            return o;
        }
        return null;
    }
    
    /**
    * Chargement du plugin par défaut
    *
    * @return Appli
    */
    public Appli initMainPlugin(){
    	Class<?> classMain = null;
		try {
			classMain = Class.forName("plugins."+defaultPlugin.getClassName());
		} catch (ClassNotFoundException e) {
			System.out.println("Problème dans le nom de classe du plugin principal par défaut" +e.getMessage());
			e.printStackTrace();
		}
            Object pluginMain = null;
			try {
				pluginMain = classMain.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				System.out.println("Problème lors de l'instanciation du plugin principal par défaut" +e.getMessage());
				e.printStackTrace();
			}
            ((Appli) pluginMain).run();
            return (Appli) pluginMain;
    }
    
 // The entry main() method
    public static void main(String[] args) {
    	PluginsLoader p = new PluginsLoader();
    	p.initMainPlugin();
    }
    
    /**
    * Retourne le plugin d'ui par défaut après l'avoir chargé
    *
    * @return UserInterface
    */
	public UserInterface getDefaultUI() {
  	    Class<?> classUI = null;
		try {
			PluginDescriptor plui = loaded.get(defaultUI);
			classUI = Class.forName("plugins."+plui.getType()+"."+defaultUI);
		} catch (ClassNotFoundException e) {
			System.out.println("Problème dans le nom de classe du l'ui par défaut" +e.getMessage());
			e.printStackTrace();
		}
        Object ui = null;
		try {
			ui = classUI.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("Problème lors de l'instanciation de l'ui par défaut" +e.getMessage());
			e.printStackTrace();
		}
  	    Class<?> classB = null;
		try {
			PluginDescriptor plbody = loaded.get(defaultBody);
			classB = Class.forName("plugins."+plbody.getType()+"."+defaultBody);
		} catch (ClassNotFoundException e) {
			System.out.println("Problème dans le nom de classe du body par défaut" +e.getMessage());
			e.printStackTrace();
		}
        Object body = null;
		try {
			body = classB.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("Problème lors de l'instanciation du body par défaut" +e.getMessage());
			e.printStackTrace();
		}
        ((UserInterface) ui).addBody((Body) body);
  	    Class<?> classM = null;
		try {
			PluginDescriptor plmenu = loaded.get(defaultMenu);
			classM = Class.forName("plugins."+plmenu.getType()+"."+defaultMenu);
		} catch (ClassNotFoundException e) {
			System.out.println("Problème dans le nom de classe du menu par défaut" +e.getMessage());
			e.printStackTrace();
		}
        Object menu = null;
		try {
			menu = classM.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("Problème lors de l'instanciation du menu par défaut" +e.getMessage());
			e.printStackTrace();
		}
        ((UserInterface) ui).addMenu((Menu) menu);
        ((Body) body).setUserInterface((UserInterface) ui);
		return (UserInterface) ui;
	}
	
	/**
    * Retourne le plugin model loader par défaut après l'avoir chargé
    *
    * @return ModelLoader
    */
	public ModelLoader getDefaultModelLoader() {
  	    Class<?> classML = null;
		try {
			PluginDescriptor plmodeloader = loaded.get(defaultModelLoader);
			classML = Class.forName("plugins."+plmodeloader.getType()+"."+defaultModelLoader);
		} catch (ClassNotFoundException e) {
			System.out.println("Problème dans le nom de classe du model loader par défaut" +e.getMessage());
			e.printStackTrace();
		}
        Object modelLoader = null;
		try {
			modelLoader = classML.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("Problème lors de l'instanciation du model loader par défaut" +e.getMessage());
			e.printStackTrace();
		}
		return (ModelLoader) modelLoader;
	}

	/**
    * Retourne le plugin alarm loader par défaut après l'avoir chargé
    *
    * @return AlarmLoader
    */
	public AlarmLoader getDefaultAlarm() {
  	    Class<?> classA = null;
		try {
			PluginDescriptor plalarm = loaded.get(defaultAlarm);
			classA = Class.forName("plugins."+plalarm.getType()+"."+defaultAlarm);
		} catch (ClassNotFoundException e) {
			System.out.println("Problème dans le nom de classe de l'alarm par défaut" +e.getMessage());
			e.printStackTrace();
		}
        Object alarm = null;
		try {
			alarm = classA.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
			System.out.println("Problème lors de l'instanciation de l'alarm par défaut" +e.getMessage());
			e.printStackTrace();
		}
		return (AlarmLoader) alarm;
	}
}
