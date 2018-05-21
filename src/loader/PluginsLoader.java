package loader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import core.UI.Body;
import core.UI.Menu;
import core.UI.UserInterface;
import core.alarm.Alarm;
import core.model.ModelLoader;
import plugins.PlugIntelligent;
import plugins.alarm.PlugIntelligentAlarm;
import plugins.model.PlugIntelligentModelLoader;
import plugins.ui.PlugIntelligentBody;
import plugins.ui.PlugIntelligentMenu;
import plugins.ui.PlugIntelligentUI;


public class PluginsLoader {
    private static String filename = "config.properties";
    private Properties p;

    public PluginsLoader() {
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
        if (plugin.isAssignableFrom(c)){
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
    
 // The entry main() method
    public static void main(String[] args) {

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
