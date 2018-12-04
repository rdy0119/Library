/**
 * 
 */
package com.xiudun.page;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.xiudun.action.LoginAction;

/**
 * @author Administrator
 *
 */
public class LoginPage {

	private JFrame frame;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	LoginAction loginAction = new LoginAction();
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("登录");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("用户名称");
		label.setBounds(40, 63, 72, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("用户密码");
		label_1.setBounds(40, 138, 72, 18);
		frame.getContentPane().add(label_1);
		
		username = new JTextField();
		username.setBounds(136, 60, 207, 24);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(136, 135, 207, 24);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		JButton button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//登录正确
				if(loginAction.login(username.getText(), password.getText())) {
					MainPage.main(null);
					//本页面隐藏
					frame.hide();
				}
			}
		});
		button.setBounds(76, 197, 113, 27);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("退出");
		button_1.setBounds(261, 197, 113, 27);
		frame.getContentPane().add(button_1);
	}
}
