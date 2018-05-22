package core.UI;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Message;

public abstract class UserInterface {
	protected List<Body> bodies;
	protected JFrame frame;
	
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
		
		//populateMessage()
	}
		
	public void addBody(Body b){
		this.bodies.add(b);
	}
	
	public void addMenu(Menu m){
		this.menus.add(m);
	}
	
	public void populateMessage(List<Message> messages){
		for(Message m:
			messages){
			for(Body b:bodies){
				b.addMessage(m);
			}
		}
	}
	
}
