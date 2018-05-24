package core.UI;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
	protected JMenuBar menubar;
	private Map<String,JMenu> menus;

	public Menu() {
		this.menus = new HashMap<>();
	}

	public Menu(String uniqueName) {
		super();
		this.uniqueName = uniqueName;
	}

	/*
	 * Permet de construire le menu de base
	 */
	public void draw(){
		JFrame myframe = this.userInterface.getFrame();
		menubar = new JMenuBar();
		addMenuToMenuBar("ui", "Plugins pour l'interface");
		addMenuToMenuBar("alarm", "Plugins pour l'alarme");
		addMenuToMenuBar("model", "Plugins pour les données");
		myframe.setJMenuBar(menubar);
		drawMenu();
	}
	
	/*
	 * Permet d'ajouter un élément au Menu
	 */
	protected void addMenuItemToMenu(String menuTitle, String menuItemTitle, AbstractAction action) {
		JMenu menu;
		action.putValue(AbstractAction.NAME, menuItemTitle);
		menu = this.menus.get(menuTitle);
		if(menu != null){
			JMenuItem size = new JMenuItem(action);
			menu.add(size);
		}
	}
	
	/*
	 * Permet d'ajouter un menu à la bar des menus
	 */
	protected void addMenuToMenuBar(String menuTitle) {
		JMenu menu;
		menu = new JMenu(menuTitle);
		menubar.add(menu);
		this.menus.put(menuTitle, menu);
	}

	/*
	 * Permet d'ajouter un élément au Menu
	 */
	private void addMenuToMenuBar(String typePlugin, String menuTitle) {
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
}
