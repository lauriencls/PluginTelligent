package plugins.ui;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import core.UI.Menu;
import core.UI.UserInterface;
import core.model.ModelLoader;
import loader.PluginsLoader;
import loader.PluginDescriptor;

public class PlugIntelligentMenu extends Menu {

	public PlugIntelligentMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawMenu() {
		String menuTitle = "Actions";
		this.addMenuToMenuBar(menuTitle);
		this.addMenuItemToMenu(menuTitle, "Remettre à zéro les messages", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				userInterface.getAppli().getModelLoader().clearModel();
				userInterface.getAppli().getModelLoader().save();
				userInterface.reload();
			}
			
		});
	}

}
