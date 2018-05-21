package core.UI;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

public abstract class UserInterface extends Frame {
	protected List<Body> bodies;
	
	public UserInterface() {
		super();
		this.bodies = new ArrayList<>();
		this.menus = new ArrayList<>();
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
	}
	
	public abstract void showBody();
	
	public void addBody(Body b){
		this.bodies.add(b);
	}
	
	public void addMenu(Menu m){
		this.menus.add(m);
	}
	
}
