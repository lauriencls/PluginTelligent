package core;
import core.UI.Body;
import core.UI.Menu;
import core.UI.UserInterface;
import core.model.ModelLoader;
import loader.PluginsLoader;
import core.model.AlarmLoader;

public abstract class Appli {
	private UserInterface userInterface;
	private ModelLoader modelLoader;
	private AlarmLoader alarmLoader;
    
    public UserInterface getUserInterface() {
		return userInterface;
	}

    /*
     * Définit l'ui par défaut
     */
	public void setUserInterface() {	
		this.userInterface = PluginsLoader.getInstance().getDefaultUI();
		this.userInterface.setAppli(this);
		this.userInterface.setUserInterfaceToMenu();
	}
	
	/*
	 * Permet de redéfinir les plugins de type UI
	 */
	public void setUserInterface(Object o) {	
		if(o instanceof UserInterface){
			this.userInterface = (UserInterface) o;
		}else if(o instanceof Body){
			((Body) o).setUserInterface(userInterface);
			this.userInterface.addBody((Body) o);
			
		}else if(o instanceof Menu){
			this.userInterface.addMenu((Menu) o);
		}
		this.userInterface.setAppli(this);
		this.userInterface.setUserInterfaceToMenu();
	}

	public ModelLoader getModelLoader() {
		return modelLoader;
	}

	/*
	 * Définit le ModelLoader par défaut
	 */
	public void setModelLoader() {	
			this.modelLoader = PluginsLoader.getInstance().getDefaultModelLoader();
	}
	
	/*
	 * Permet de redéfinir les plugins de type ModelLoader
	 */
	public void setModelLoader(Object o) {
		this.modelLoader = (ModelLoader) o;
}

	public AlarmLoader getAlarm() {
		return alarmLoader;
	}

	public void setAlarm() {	
			this.alarmLoader = PluginsLoader.getInstance().getDefaultAlarm();
	}
	
	public void setAlarm(Object a) {	
		this.alarmLoader = (AlarmLoader) a;
	}

	public Appli(){
	}

	/*
	 * Recharge l'appli et les plugins
	 */
	public void reload(){
    	this.modelLoader.load();
    	//this.alarmLoader.load();
    	this.userInterface.reload();
    }

	/*
	 * Démarre l'appli 
	 */
    public void run(){
		this.setUserInterface();
		this.setModelLoader();
		this.setAlarm();
    	this.modelLoader.load();
    	//this.alarmLoader.load();
    	this.userInterface.display();
    }
}