package plugins.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.Alarm;
import core.model.Model;
import core.model.ModelLoader;
import model.Message;
import model.User;

public class PlugIntelligentModelLoader extends ModelLoader {
	private static String filename = "src/ressource/model.json";
	

	public PlugIntelligentModelLoader() {
		super();
	}

	@Override
	public void load() {
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader(filename));
			JSONArray JSONAlarms = (JSONArray) obj.get("Alarms");
			System.out.println(obj);
			for (Object a : JSONAlarms)
			{
				JSONObject jsonAlarm = (JSONObject) a;
				JSONObject jsonMessage = (JSONObject) jsonAlarm.get("message");
				Message message = new Message(jsonMessage.get("title").toString(), jsonMessage.get("body").toString(), Instant.parse(jsonMessage.get("createdTime").toString()));
				Alarm alarm = new Alarm(message, Instant.parse(jsonAlarm.get("triggerDateTime").toString()), (boolean) jsonAlarm.get("active"));
				alarms.add(alarm);
				messages.add(message);
			}
			JSONArray JSONUsers = (JSONArray) obj.get("Users");
			for (Object u : JSONUsers)
			{
				JSONObject jsonUser = (JSONObject) u;
				User user = new User(jsonUser.get("login").toString());
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Model> getRessourceByName(String className) {
		switch (className) {
		case "Alarm" :
			return alarms;
		case "Message" :
			return messages;
		case "User" :
			return users;
		default :
			return null;
		}
	}

	@Override
	public void save() {
		JSONObject json = new JSONObject();
		JSONArray JSONAlarms = new JSONArray();
		JSONArray JSONUsers = new JSONArray();
		for (Object a : alarms){
			Alarm alarm = (Alarm) a;
			JSONObject JSONAlarm = new JSONObject();
			JSONObject JSONMessage = new JSONObject();
			JSONMessage.put("title", alarm.getMessage().getTitle());
			JSONMessage.put("body", alarm.getMessage().getBody());
			JSONMessage.put("createdTime", alarm.getMessage().getCreatedTime().toString());
			JSONAlarm.put("message", JSONMessage);
			JSONAlarm.put("active", alarm.isActive());
			JSONAlarm.put("triggerDateTime", alarm.getTriggerDateTime().toString());
			JSONAlarms.add(JSONAlarm);
		}
		for (Object u : users){
			User user = (User) u;
			JSONObject JSONUser = new JSONObject();
			JSONUser.put("login", user.getLogin());
			JSONUsers.add(JSONUser);
		}
		//2018-05-23T18:30:00.00Z
		json.put("Alarms", JSONAlarms);
		json.put("Users", JSONUsers);


		try (FileWriter file = new FileWriter(filename)) {

			file.write(json.toJSONString());
			file.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args){
		PlugIntelligentModelLoader p = new PlugIntelligentModelLoader();
		p.load();
		p.save();
	}



}
