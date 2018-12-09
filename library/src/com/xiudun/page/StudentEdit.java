/**
 * 
 */
package com.xiudun.page;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.xiudun.action.StudentAction;
import com.xiudun.vo.Student;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Administrator
 *
 */
public class StudentEdit {

	private JFrame frame;
	private JComboBox sex ;
	private JTextField username;
	private JTextField age;
	private JTextField id;;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentEdit window = new StudentEdit(args);
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
	public StudentEdit(String[] args) {
		initialize(args);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] args) {
		frame = new JFrame();
		frame.setBounds(100, 100, 396, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("姓名");
		lblNewLabel.setBounds(11, 17, 72, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("性别");
		label.setBounds(11, 72, 72, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("年龄");
		label_1.setBounds(11, 130, 72, 18);
		frame.getContentPane().add(label_1);
		
		username = new JTextField();
		username.setText(args[1]);
		username.setBounds(42, 15, 98, 24);
		frame.getContentPane().add(username);
		username.setColumns(10);
		

		
		sex = new JComboBox();
		sex.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		if(args[2].equals("女"))
		{
			sex.setSelectedIndex(1);
		}
		sex.setBounds(42, 69, 98, 24);
		frame.getContentPane().add(sex);
		
		age = new JTextField();
		age.setText(args[3]);
		age.setBounds(42, 129, 98, 21);
		frame.getContentPane().add(age);
		age.setColumns(10);
		

		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(277, 174, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		id= new JTextField();
		id.setText(args[0]);
		id.setVisible(false);
		id.setBounds(217, 16, 66, 21);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		
		JButton btnNewButton = new JButton("保存");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentAction sa=new StudentAction();
				Student student=new Student();
				student.setId(id.getText());
				student.setUsername(username.getText());
				student.setAge(age.getText());
				student.setSex(sex.getSelectedItem().toString());
				sa.update(student);
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(142, 174, 93, 23);
		frame.getContentPane().add(btnNewButton);
		
	}

}
