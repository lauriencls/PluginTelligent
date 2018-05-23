package plugins.ui;

import java.awt.Button;
import java.awt.Panel;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.json.simple.parser.ParseException;

import core.UI.Body;
import core.UI.Menu;
import core.UI.UserInterface;
import loader.PluginsLoader;

public class PlugIntelligentUI extends UserInterface {

	public PlugIntelligentUI(List<Body> bodies, List<Menu> menus) {
		super(bodies, menus);
	}
	
	public PlugIntelligentUI(){
		super();
	}

}
