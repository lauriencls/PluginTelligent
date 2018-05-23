package core.UI;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import loader.PluginDescriptor;
import loader.PluginsLoader;

public abstract class Menu {
	private String uniqueName;
	protected UserInterface userInterface;

	public Menu() {
	}

	public Menu(String uniqueName) {
		super();
		this.uniqueName = uniqueName;
	}

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	
	public UserInterface getUserInterface() {
		return userInterface;
	}

	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}

	public void draw(){
		JFrame myframe = this.userInterface.getFrame();
		JMenuBar menubar = new JMenuBar();
		addMenuToMenuBar(menubar, "ui", "Plugins pour l'interface");
		addMenuToMenuBar(menubar, "alarm", "Plugins pour l'alarme");
		addMenuToMenuBar(menubar, "model", "Plugins pour les données");
		myframe.setJMenuBar(menubar);
		drawMenu();
	}

	public void addMenuToMenuBar(JMenuBar menubar, String typePlugin, String menuTitle) {
		JMenu menu;
		menu = new JMenu(menuTitle);
		for(Map.Entry<String,PluginDescriptor> pl:PluginsLoader.getInstance().getAllPluginsByType(typePlugin).entrySet()){	
			MenuClickHandler mch = new MenuClickHandler(pl.getValue().getNom());
			mch.setPluginDescriptor(pl.getValue());
			JMenuItem size = new JMenuItem(mch);
			menu.add(size);
		}
		menubar.add(menu);
	}
	
	public abstract void drawMenu();
	
	public class MenuClickHandler extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private PluginDescriptor pluginDescriptor;
		protected UserInterface userInterface;
		
		public PluginDescriptor getPluginDescriptor() {
			return pluginDescriptor;
		}

		public void setPluginDescriptor(PluginDescriptor pluginDescriptor) {
			this.pluginDescriptor = pluginDescriptor;
		}

		public MenuClickHandler(String title) {
	        super(title);
	    }

	    public void actionPerformed(ActionEvent e) {
	    	JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Chargement du plugin : " + this.pluginDescriptor.getNom(), "Chargement du plugin", JOptionPane.PLAIN_MESSAGE);
			PluginsLoader.getInstance().swapPlugins(pluginDescriptor);
	    }
	}
}
