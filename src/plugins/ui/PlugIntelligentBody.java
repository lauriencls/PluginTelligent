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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import core.UI.Body;
import core.model.ModelLoader;
import model.Message;

public class PlugIntelligentBody extends Body {
	
	JButton     sendMessage;
    JTextField  messageBox;
    JTextArea   chatBox;
    JTextField  usernameChooser;
    JPanel 		mainPanel;
    JPanel 		southPanel;
    Body		pi;
	
	public PlugIntelligentBody() {
		super();
		pi = this;
	}
	
	@Override
	protected void drawTextEntry() {

        southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Ajouter Message");
        sendMessage.addActionListener(new sendMessageButtonListener());
        

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

        mainPanel.add(BorderLayout.SOUTH, southPanel);
        this.userInterface.getFrame().add(mainPanel);
		
	}

	@Override
	protected void drawListMessages() {
		mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
		chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);

        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);
	}

	@Override
	protected void drawChannelPanel() {
		
	}

	@Override
	public void addMessage(Message message) {
		chatBox.append( message.getBody() + " créé le " + message.getCreatedTime().toString()
                + "\n");
	}
	
	class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing
            } else {
            	Message m = new Message("title", messageBox.getText(), Instant.now());
                pi.addMessage(m);
                ModelLoader.alarms.add(pi.getUserInterface().getAppli().getAlarm().createAlarm(m, Instant.now(), true));
                pi.getUserInterface().getAppli().getModelLoader().save();
                messageBox.setText("");
            }
            messageBox.requestFocusInWindow();
        }
    }

}
