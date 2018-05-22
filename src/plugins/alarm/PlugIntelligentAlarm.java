package plugins.alarm;

import java.time.Instant;

import core.model.AlarmLoader;
import model.Alarm;
import model.Message;

public class PlugIntelligentAlarm extends AlarmLoader {

	public PlugIntelligentAlarm() {
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createAlarm(Message message, Instant triggerDateTime, boolean isActive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupAlarm(Message message, Instant triggerDateTime, boolean isActive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerAlarm(Instant triggerDateTime) {
		// TODO Auto-generated method stub
		
	}
	
	
	//TODO : Ajouter les méthodes dont aura besoin l'interface ou n'importe quel plugin qui utilisera l'Alarm qui sont
	//dans la classe Alarm
	
	//TODO : Ajouter la méthode de test dans une méthode main
	

}
