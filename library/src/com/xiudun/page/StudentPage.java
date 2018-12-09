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
		frame.setTitle("学生及借还书管理");
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
		sid.setBounds(131, 272, 382, 24);
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
				username.setText("");
				age.setText("");
			}
		});
		button.setBounds(809, 13, 113, 27);
		frame.getContentPane().add(button);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(48, 62, 1145, 171);
		frame.getContentPane().add(scrollPane_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//把选中的学生id添加到sid控件中
				sid.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				Object[][] myBook=studentAction.detail(sid.getText());
				table_1.setModel(new DefaultTableModel(
						myBook,
						new String[] {
							"id", "图书名称", "作者", "单价"
						}
				));
				Object[][] allBook=bookAction.findAll();
				Object[][] otherBook=new Object[100][];
				int l=0;
				if(myBook.length>0)
				{
					System.out.println("============================mybooklength"+myBook.length);
					for(Object[] o:allBook)
					{
						int flag=0;
						for(Object[] m:myBook)
						{
							if(o[0].equals(m[0]))
								flag++;
						}
						if(flag==0)
						{
							otherBook[l]=o;
							l++;
						}
								
					}
					
				}else {
					otherBook=allBook;
				}
				System.out.println("======================="+otherBook.length);
				table_2.setModel(new DefaultTableModel(
						otherBook,
						new String[] {
							"id", "图书名称", "作者", "单价"
						}
				));
			}
		});
		Object[][] studentsInshort=studentAction.findAll();
		for(Object[] student:studentsInshort)
		{
			
			if(student[4].toString().equals("[]"))
			{
				student[4]="空";
			}
			else {
				student[4]="有借书记录";
			}
		}
		table.setModel(new DefaultTableModel(
				studentsInshort,
			new String[] {
				"id", "姓名", "性别", "年龄", "书包"
			}
		));
		scrollPane_1.setViewportView(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 306, 1166, 136);
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
		scrollPane_2.setBounds(48, 496, 1166, 154);
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
//		table_2.setModel(new DefaultTableModel(
//			bookAction.findAll(),
//			new String[] {
//			"id", "图书名称", "作者", "单价"
//		}
//		));
		scrollPane_2.setViewportView(table_2);
		
		JLabel lblNewLabel_1 = new JLabel("有借书记录不允许删除");
		lblNewLabel_1.setBounds(761, 247, 129, 15);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JLabel label_2 = new JLabel("学生ID");
		label_2.setBounds(48, 274, 72, 18);
		frame.getContentPane().add(label_2);
		
	   JLabel label_3 = new JLabel("书包");
		label_3.setBounds(10, 306, 72, 18);
		frame.getContentPane().add(label_3);
		
//		JLabel label_4 = new JLabel("图书馆");
//		label_4.setBounds(0, 540, 72, 18);
//		frame.getContentPane().add(label_4);
		
		JButton btnNewButton = new JButton("删除");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

	              int[] rows=table.getSelectedRows(); 
	              for(int row:rows)
	              {
	            	String  id= (String) table.getValueAt(row, 0);

	            	if(studentAction.detail(id).length>0)
	            	{
	            		lblNewLabel_1.setVisible(true);
	            	}else {
	            		studentAction.delete(id);
	            	}
	            	
	              }
	            Object[][] studentsInshort=studentAction.findAll();
	      		for(Object[] student:studentsInshort)
	      		{
	      			
	      			if(student[4].toString().equals("[]"))
	      			{
	      				student[4]="空";
	      			}
	      			else {
	      				student[4]="有借书记录";
	      			}
	      		}
				//显示
				table.setModel(new DefaultTableModel(
						studentsInshort,
					new String[] {
						"id", "姓名", "性别", "年龄", "书包"
					}
				));	
				sid.setText("");			
			}
		});
		btnNewButton.setBounds(900, 243, 93, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				String id= (String) table.getValueAt(row, 0);
				String name= (String) table.getValueAt(row,1);
				String sex= (String) table.getValueAt(row, 2);
				String age= (String) table.getValueAt(row, 3);
				String[] str= {id,name,sex,age};
				StudentEdit sEdit=new StudentEdit(str);
				sEdit.main(str);
			}
		});
		btnNewButton_1.setBounds(1021, 243, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("刷新");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_1.setVisible(false);
				Object[][] studentsInshort=studentAction.findAll();
				for(Object[] student:studentsInshort)
				{
					
					if(student[4].toString().equals("[]"))
					{
						student[4]="空";
					}
					else {
						student[4]="有借书记录";
					}
				}
				table.setModel(new DefaultTableModel(
						studentsInshort,
					new String[] {
						"id", "姓名", "性别", "年龄", "书包"
					}
				));
			}
		});
		btnNewButton_2.setBounds(1124, 243, 93, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel label_4 = new JLabel("可借图书");
		label_4.setBounds(0, 503, 54, 15);
		frame.getContentPane().add(label_4);
		
		JButton button_2 = new JButton("还书");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				studentAction.returnB(sid.getText(), 
				table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
				Object[][] myBook=studentAction.detail(sid.getText());
				table_1.setModel(new DefaultTableModel(
						myBook,
						new String[] {
							"id", "图书名称", "作者", "单价"
						}
				));
				Object[][] allBook=bookAction.findAll();
				Object[][] otherBook=new Object[100][];
				int l=0;
				if(myBook.length>0)
				{
					System.out.println("============================mybooklength"+myBook.length);
					for(Object[] o:allBook)
					{
						int flag=0;
						for(Object[] m:myBook)
						{
							if(o[0].equals(m[0]))
								flag++;
						}
						if(flag==0)
						{
							otherBook[l]=o;
							l++;
						}
								
					}
					
				}else {
					otherBook=allBook;
				}
				System.out.println("======================="+otherBook.length);
				table_2.setModel(new DefaultTableModel(
						otherBook,
						new String[] {
							"id", "图书名称", "作者", "单价"
						}
				));
			
				
			}
		});
		button_2.setBounds(1121, 452, 93, 23);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("借书");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentAction.borrow(sid.getText(), 
				table_2.getValueAt(table_2.getSelectedRow(), 0).toString());
				Object[][] myBook=studentAction.detail(sid.getText());
				table_1.setModel(new DefaultTableModel(
						myBook,
						new String[] {
							"id", "图书名称", "作者", "单价"
						}
				));
				Object[][] allBook=bookAction.findAll();
				Object[][] otherBook=new Object[100][];
				int l=0;
				if(myBook.length>0)
				{
					System.out.println("============================mybooklength"+myBook.length);
					for(Object[] o:allBook)
					{
						int flag=0;
						for(Object[] m:myBook)
						{
							if(o[0].equals(m[0]))
								flag++;
						}
						if(flag==0)
						{
							otherBook[l]=o;
							l++;
						}
								
					}
					
				}else {
					otherBook=allBook;
				}
				System.out.println("======================="+otherBook.length);
				table_2.setModel(new DefaultTableModel(
						otherBook,
						new String[] {
							"id", "图书名称", "作者", "单价"
						}
				));
			}
		});
		button_3.setBounds(1124, 658, 93, 23);
		frame.getContentPane().add(button_3);
		

		
	}
}
