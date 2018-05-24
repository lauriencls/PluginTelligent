package plugins.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import core.UI.Body;
import core.model.ModelLoader;
import model.Message;

public class RobinBody extends Body {
	
	JButton     sendMessage;
	JTextArea  messageBox;
    JTextArea   chatBox;
    JTextField  usernameChooser;
    JPanel 		mainPanel;
    JPanel 		southPanel;
    Body		pi;
    JCheckBox 	reminder;
    boolean 	remind = false;
	
	public RobinBody() {
		super();
		pi = this;
	}
	
	@Override
	protected void drawTextEntry() {
        southPanel = new JPanel();
        southPanel.setBackground(Color.WHITE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextArea();
        messageBox.setFont(new Font("Arial", Font.PLAIN, 15));
        messageBox.setLineWrap(true);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Ajouter Message");
        sendMessage.addActionListener(new sendMessageButtonListener());
        
        reminder = new JCheckBox();
        reminder.setAction(new AbstractAction("Rappel dans une heure"){
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				remind = reminder.isSelected();
			}
        	
        });
        

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);
        southPanel.add(reminder,right);

        mainPanel.add(BorderLayout.SOUTH, southPanel);
        this.userInterface.getFrame().add(mainPanel);
		
	}

	@Override
	protected void drawListMessages() {
		mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
		chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Arial", Font.PLAIN, 15));
        chatBox.setLineWrap(true);
        chatBox.setBackground(Color.GRAY);
        chatBox.setForeground(Color.WHITE);


        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);
	}

	@Override
	protected void drawChannelPanel() {
		
	}

	@Override
	public void addMessage(Message message) {
		DateTimeFormatter formatter =
			    DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
			                     .withLocale( Locale.FRANCE )
			                     .withZone( ZoneId.systemDefault() );
		chatBox.append( message.getBody() + "\nCréé le " + formatter.format(message.getCreatedTime())
                + "\n\n");
	}
	
	class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing
            } else {
            	Message m = new Message("title", messageBox.getText(), Instant.now());
                pi.addMessage(m);
                pi.getUserInterface().getAppli().getModelLoader().getRessourceByName("Alarm").add(pi.getUserInterface().getAppli().getAlarm().createAlarm(m, Instant.now().plusSeconds(3600), remind));
                pi.getUserInterface().getAppli().getModelLoader().save();
                messageBox.setText("");
            }
            messageBox.requestFocusInWindow();
        }
    }

	@Override
	protected void clearListMessages() {
		this.messageBox.setText("");
	}

}
