package core;
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

	public void setUserInterface() {	
		this.userInterface = PluginsLoader.getInstance().getDefaultUI();
		this.userInterface.setAppli(this);
	}

	public ModelLoader getModelLoader() {
		return modelLoader;
	}

	public void setModelLoader() {	
			this.modelLoader = PluginsLoader.getInstance().getDefaultModelLoader();
	}

	public AlarmLoader getAlarm() {
		return alarmLoader;
	}

	public void setAlarm() {	
			this.alarmLoader = PluginsLoader.getInstance().getDefaultAlarm();
	}

	public Appli(){
	}

    public void run(){
		this.setUserInterface();
		this.setModelLoader();
		this.setAlarm();
    	this.modelLoader.load();
    	//this.alarmLoader.load();
    	this.userInterface.display();
    }
}