package core.UI;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.Appli;
import core.model.Model;
import model.Message;

public abstract class UserInterface {
	protected List<Body> bodies;
	protected JFrame frame;
	protected Appli appli;
	
	public UserInterface() {
		super();
		this.bodies = new ArrayList<>();
		this.menus = new ArrayList<>();
		this.frame = new JFrame();
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

	protected List<Menu> menus;
	
	public UserInterface(List<Body> bodies, List<Menu> menus) {
		super();
		this.bodies = bodies;
		this.menus = menus;
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
	
	public void setUserInterfaceToMenu() {
		for(Menu m:menus){
			m.setUserInterface(this);
		}
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
	
	public void display(){
		displayMenu();
		displayBody();
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		populateMessage(this.appli.getModelLoader().getRessourceByName("Message"));
	}
		
	public void addBody(Body b){
		this.bodies.clear();
		this.bodies.add(b);
	}
	
	public void addMenu(Menu m){
		this.menus.clear();
		this.menus.add(m);
	}
	
	public void populateMessage(List<Model> list){
		for(Model m:
			list){
			for(Body b:bodies){
				b.addMessage((Message) m);
			}
		}
	}

	public void reload() {
		this.frame.setVisible(false);
		
		this.frame = new JFrame();
		
		display();
		
	}
	
}
