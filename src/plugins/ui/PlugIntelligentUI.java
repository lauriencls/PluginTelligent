package plugins.ui;

import java.awt.Button;
import java.awt.Panel;
import java.util.List;

import core.UI.Body;
import core.UI.Menu;
import core.UI.UserInterface;

public class PlugIntelligentUI extends UserInterface {

	public PlugIntelligentUI(List<Body> bodies, List<Menu> menus) {
		super(bodies, menus);
		// TODO Auto-generated constructor stub
	}
	
	public PlugIntelligentUI(){
		super();
	}

	@Override
	public void showBody() {
		Panel pnl = new Panel();          // Panel is a container
		Button btn = new Button("Press"); // Button is a component
		pnl.add(btn);  

	}

}
