package core.UI;

import java.util.List;

public abstract class UserInterface {
	private List<Body> bodies;
	private List<Menu> menus;
	
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
	
	protected abstract void displayMenu();
	
	protected abstract void displayBody();
	
	public void display(){
		displayMenu();
		displayBody();
	}
	
	public abstract void showBody();
	
}
