package plugins.model;

import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Alarm;
import core.model.Model;
import core.model.ModelLoader;
import loader.PluginDescriptor;
import model.Message;

public class PlugIntelligentModelLoader extends ModelLoader {
	private static String filename = "src/ressource/model.json";
    	

	public PlugIntelligentModelLoader(String uniqueName) {
		super(uniqueName);
	}

	@Override
	public void load() {
		JSONParser parser = new JSONParser();
    	try {
    		JSONObject obj = (JSONObject) parser.parse(new FileReader(filename));
        	JSONArray alarms = (JSONArray) obj.get("Alarms");
        	System.out.println(obj);
        	for (Object a : alarms)
        	  {
        		JSONObject jsonAlarm = (JSONObject) a;
        		JSONObject jsonMessage = (JSONObject) jsonAlarm.get("message");
        		Message message = new Message(jsonMessage.get("title").toString(), jsonMessage.get("body").toString(), Instant.parse(jsonMessage.get("createdTime").toString()));
        		Alarm alarm = new Alarm(message, Instant.parse(jsonMessage.get("createdTime").toString()), (boolean) jsonMessage.get("active"));
        		
        	  }
    	} catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<Model> getRessourceByName(String className) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(List<Model> objects) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		PlugIntelligentModelLoader p = new PlugIntelligentModelLoader("Alarm");
		p.load();
	}
	
	

}
