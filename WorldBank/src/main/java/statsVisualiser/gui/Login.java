package statsVisualiser.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login implements ActionListener{
	
	JFrame frame = new JFrame();
	JTextField userField = new JTextField();
	JLabel userLabel = new JLabel("Username:");
	JPasswordField passwordField = new JPasswordField();
	JLabel passwordLabel = new JLabel("Password:");
	JButton loginButton = new JButton("Submit");
	
	JFrame loginframe = new JFrame();
	JFrame mainframe2 = new JFrame(); 
	
	HashMap<String,String> users = new HashMap<String,String>();
	
	//Extract usernames, passwords from csv file and store in hashmap
	public void readFile() {
		
	File file = new File("src\\users-passwords.csv");
	Scanner scan;
	try {
		scan = new Scanner(file);
		scan.useDelimiter(",|\n");
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
            String[] lineSplit = line.split("\\s*,\\s*");
            users.put(lineSplit[0], lineSplit[1]);
		}
		
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	}
	
	System.out.println("Usernames: "+ users.keySet());
	System.out.println("Passwords:" + users.values());
	
	}
	
	//Constructor for the login frame
	Login(JFrame mainframe){
		
		mainframe2 = mainframe;
		
		userLabel.setBounds(25,40,75,25);
		loginframe.add(userLabel);
		userField.setBounds(90,42,175,25);
		loginframe.add(userField);
		
		passwordLabel.setBounds(25,70,75,25);
		loginframe.add(passwordLabel);
		passwordField.setBounds(90,74,175,25);
		loginframe.add(passwordField);
		
		loginButton.setBounds(100,108,100,25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		loginframe.add(loginButton);
		
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.setSize(300,200);
		loginframe.setLayout(null);
		loginframe.setVisible(true);
		
	}

	//what to do if login button is pressed
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==loginButton) {
		
			String user = userField.getText();
			String password = String.valueOf(passwordField.getPassword());
			if(users.containsKey(user)) {
				if(users.get(user).equals(password)) {
					loginframe.dispose();
					mainframe2.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(frame, "Wrong password entered. Application will now terminate. Please restart to try again.");
					System.exit(0);
				}
			}
			else {
				JOptionPane.showMessageDialog(frame, "Username does not exist. Application will now terminate. Please restart to try again.");
				System.exit(0);
			}
		}
	}
	

	
}
