package core;
import java.util.List;

import org.json.simple.parser.ParseException;

import core.UI.UserInterface;
import core.model.ModelLoader;
import loader.PluginDescriptor;
import loader.PluginsLoader;
import model.Alarm;

public abstract class Appli {
	private UserInterface userInterface;
	private ModelLoader modelLoader;
	private Alarm alarm;
	private PluginsLoader pluginsLoader;
    
    public UserInterface getUserInterface() {
		return userInterface;
	}

	public void setUserInterface() {
		try {
			this.userInterface = pluginsLoader.getDefaultUI();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	public ModelLoader getModelLoader() {
		return modelLoader;
	}

	public void setModelLoader(ModelLoader modelLoader) {
		try {
			this.modelLoader = pluginsLoader.getDefaultModelLoader();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}

	public Appli(){
		try {
			pluginsLoader = new PluginsLoader();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void run(){
    	this.modelLoader.load();
    	this.alarm.load();
    	this.userInterface.display();
    }

}
