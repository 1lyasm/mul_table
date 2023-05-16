import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.Registry;
import java.util.ArrayList;

class PracticeFrame extends JFrame {
	Users users;
	public PracticeFrame(Users users) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        	setSize(800, 600);
        	setTitle("Multiplication Table Practice");
        	setLocationRelativeTo(null);
        	setLayout(null);
        	setVisible(true);
		this.users = users;
	}
}

