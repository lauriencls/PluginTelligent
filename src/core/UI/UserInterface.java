package core.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.Appli;
import core.model.Model;
import model.Message;

public abstract class UserInterface {
	private static final int height = 700;
	private static final int width = 900;
	protected List<Body> bodies;
	protected JFrame frame;
	protected Appli appli;
	protected List<Menu> menus;
	protected String title = "Plateforme de plugins";
	
	public UserInterface() {
		super();
		this.bodies = new ArrayList<>();
		this.menus = new ArrayList<>();
		this.frame = new JFrame();
	}
	
	public UserInterface(List<Body> bodies, List<Menu> menus) {
		super();
		this.bodies = bodies;
		this.menus = menus;
	}
	
	private void displayMenu() {
		for(Menu m:menus){
			m.draw();
		}
	}

	private void displayBody() {
		for(Body b:bodies){
			b.draw();
		}
	}
	
	/*
	 * Permet d'afficher l'interface utilisateur
	 */
	public void display(){
		displayMenu();
		displayBody();
		frame.setTitle(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		populateMessage(this.appli.getModelLoader().getRessourceByName("Message"));
	}
	
	private void populateMessage(List<Model> list){
		for(Body b:bodies){
			b.clearListMessages();
			for(Model m:
				list){
				b.addMessage((Message) m);
			}
		}
	}

	/*
	 * Permet de recharger l'interface utilisateur
	 */
	public void reload() {
		this.frame.setVisible(false);
		
		this.frame = new JFrame();
		
		display();
	}

	/*
	 * S'ajoute lui même au menu
	 */
	public void setUserInterfaceToMenu() {
		for(Menu m:menus){
			m.setUserInterface(this);
		}
	}
	
	public List<Body> getBodies() {
		return bodies;
	}
	public void setBodies(List<Body> bodies) {
		this.bodies = bodies;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	
	public void addBody(Body b){
		this.bodies.clear();
		this.bodies.add(b);
	}
	
	public void addMenu(Menu m){
		this.menus.clear();
		this.menus.add(m);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public Appli getAppli() {
		return appli;
	}
	
	public void setAppli(Appli appli) {
		this.appli = appli;
		
	}
}
