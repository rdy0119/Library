/**
 * 
 */
package com.xiudun.page;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.xiudun.action.BookAction;
import com.xiudun.action.StudentAction;
import com.xiudun.util.XiudunTools;
import com.xiudun.vo.Student;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Administrator
 *
 */
public class StudentPage {

	private JFrame frame;
	private JTextField username;
	private JTextField age;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentPage window = new StudentPage();
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
	public StudentPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	StudentAction studentAction = new StudentAction();
	BookAction bookAction = new BookAction();
	private JTable table_2;
	private JTextField sid;
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("学生");
		frame.setBounds(100, 100, 1256, 719);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("姓名");
		lblNewLabel.setBounds(48, 17, 72, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("性别");
		label.setBounds(309, 17, 72, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("年龄");
		label_1.setBounds(507, 17, 72, 18);
		frame.getContentPane().add(label_1);
		
		username = new JTextField();
		username.setBounds(93, 14, 186, 24);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		age = new JTextField();
		age.setColumns(10);
		age.setBounds(570, 14, 186, 24);
		frame.getContentPane().add(age);
		
		sid = new JTextField();
		sid.setBounds(226, 62, 382, 24);
		frame.getContentPane().add(sid);
		sid.setColumns(10);
		
		JComboBox sex = new JComboBox();
		sex.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		sex.setBounds(358, 14, 117, 24);
		frame.getContentPane().add(sex);
		
		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student s = new Student();
				s.setId(XiudunTools.getKey());
				s.setUsername(username.getText());
				s.setSex(sex.getSelectedItem().toString());
				s.setAge(age.getText());
				//方法
				studentAction.save(s);
				//显示
				table.setModel(new DefaultTableModel(
						studentAction.findAll(),
					new String[] {
						"id", "姓名", "性别", "年龄", "书包"
					}
				));
			}
		});
		button.setBounds(809, 13, 113, 27);
		frame.getContentPane().add(button);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(55, 111, 1145, 171);
		frame.getContentPane().add(scrollPane_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//把选中的学生id添加到sid控件中
				sid.setText(table.getValueAt(table.getSelectedRow(), 0).toString());	
				table_1.setModel(new DefaultTableModel(
						studentAction.detail(sid.getText()),
						new String[] {
							"id", "图书名称", "作者", "单价"
						}
				));
			}
		});
		table.setModel(new DefaultTableModel(
				studentAction.findAll(),
			new String[] {
				"id", "姓名", "性别", "年龄", "书包"
			}
		));
		scrollPane_1.setViewportView(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(65, 307, 1156, 136);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		
		table_1.setModel(new DefaultTableModel(
			studentAction.detail(sid.getText()),
			new String[] {
				"id", "图书名称", "作者", "单价"
			}
		));
		scrollPane.setViewportView(table_1);
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
			}
		});
		button_1.setBounds(1092, 13, 113, 27);
		frame.getContentPane().add(button_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(65, 489, 1159, 154);
		frame.getContentPane().add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//双击事件
				if(e.getClickCount()==2) {
					studentAction.borrow(sid.getText(), 
							table_2.getValueAt(table_2.getSelectedRow(), 0).toString());
				}
			}
		});
		table_2.setModel(new DefaultTableModel(
			bookAction.findAll(),
			new String[] {
					"id", "图书名称", "作者", "单价"
			}
		));
		scrollPane_2.setViewportView(table_2);
		
		JLabel label_2 = new JLabel("学生信息");
		label_2.setBounds(48, 80, 72, 18);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("书包");
		label_3.setBounds(0, 284, 72, 18);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("图书馆");
		label_4.setBounds(0, 456, 72, 18);
		frame.getContentPane().add(label_4);
		
		
	}
}
