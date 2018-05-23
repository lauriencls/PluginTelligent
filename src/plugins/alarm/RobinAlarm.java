package plugins.alarm;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.model.AlarmLoader;
import model.Alarm;

public class RobinAlarm extends AlarmLoader {

	@Override
	public void triggerAlarm(Alarm alarm) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, alarm.getMessage().getTitle() + " - " + alarm.getMessage().getBody(), "RAPPEL", JOptionPane.PLAIN_MESSAGE);
	}

}
