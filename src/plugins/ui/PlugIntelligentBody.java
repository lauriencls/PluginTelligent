package plugins.ui;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.UI.Body;
import core.UI.UserInterface;
import model.Message;

public class PlugIntelligentBody extends Body implements ActionListener{

	private Label lblCount;    
	private TextField tfCount; 
	private Button btnCount; 
	
	public PlugIntelligentBody(String uniqueName, UserInterface userInterface) {
		super(uniqueName, userInterface);
	}
	
	@Override
   public void actionPerformed(ActionEvent evt) {
      //this.addMessage(new Message());
   }

	@Override
	protected void drawTextEntry() {
		tfCount = new TextField("", 30);
		this.userInterface.add(tfCount);
		 
		btnCount = new Button("Ajouter");   
		this.userInterface.add(btnCount);
		
		btnCount.addActionListener(this);
		
	}

	@Override
	protected void drawListMessages() {
		Label lab1 = new Label("Message 1");
		Dimension dDim = lab1.getSize();
		Rectangle fRect = this.userInterface.getBounds();
		lab1.setLocation(fRect.x + ((fRect.width - dDim.width) / 2),
                fRect.y + ((fRect.height - dDim.height) / 2));
		this.userInterface.add(lab1);
		
		Label lab2 = new Label("Message 2");
		dDim = lab2.getSize();
		fRect = this.userInterface.getBounds();
		lab2.setLocation(fRect.x + ((fRect.width - dDim.width) / 2),
                fRect.y + ((fRect.height - dDim.height) / 2));
		this.userInterface.add(lab2);
		
		Label lab3 = new Label("Message 3");
		dDim = lab3.getSize();
		fRect = this.userInterface.getBounds();
		lab3.setLocation(fRect.x + ((fRect.width - dDim.width) / 2),
                fRect.y + ((fRect.height - dDim.height) / 2));
		this.userInterface.add(lab3);
	}

	@Override
	protected void drawChannelPanel() {
		Panel pnl = new Panel();
		Label lab = new Label("Liste des channels");
		pnl.add(lab);
		
		this.userInterface.add(pnl);
		
	}

	@Override
	protected void addMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

}
