package core;
import java.util.List;

import core.UI.UserInterface;
import core.alarm.Alarm;
import core.model.ModelLoader;
import loader.PluginDescriptor;

public abstract class Appli {
	private UserInterface userInterface;
	private ModelLoader modelLoader;
	private Alarm alarm;

    
    public UserInterface getUserInterface() {
		return userInterface;
	}

	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}

	public ModelLoader getModelLoader() {
		return modelLoader;
	}

	public void setModelLoader(ModelLoader modelLoader) {
		this.modelLoader = modelLoader;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}

	public Appli(UserInterface userInterface, ModelLoader modelLoader, Alarm alarm) {
		super();
		this.userInterface = userInterface;
		this.modelLoader = modelLoader;
		this.alarm = alarm;
	}

    public void run(){
    	this.modelLoader.load();
    	this.alarm.load();
    	this.userInterface.display();
    }

}
