package plugins.ui;

import java.awt.Button;
import java.awt.Panel;

import core.UI.Body;
import core.UI.UserInterface;

public class PlugIntelligentBody extends Body {

	public PlugIntelligentBody(String uniqueName, UserInterface userInterface) {
		super(uniqueName, userInterface);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawBody() {
		// TODO Auto-generated method stub
		Panel pnl = new Panel();          // Panel is a container
		Button btn = new Button("Press"); // Button is a component
		pnl.add(btn);                     // The Panel container adds a Button component
	}

}
