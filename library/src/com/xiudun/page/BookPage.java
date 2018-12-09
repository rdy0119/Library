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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.xiudun.action.BookAction;
import com.xiudun.util.XiudunTools;
import com.xiudun.vo.Book;

/**
 * @author Administrator
 *
 */
public class BookPage {

	private JFrame frame;
	private JTextField bookname;
	private JTextField auth;
	private JTextField price;
	public JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookPage window = new BookPage();
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
	public BookPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	BookAction bookAction = new BookAction();
	private JButton button_1;
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("图书管理");
		frame.setBounds(100, 100, 1121, 697);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("图书名称");
		label.setBounds(53, 50, 72, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("作者");
		label_1.setBounds(338, 50, 72, 18);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("单价");
		label_2.setBounds(570, 50, 72, 18);
		frame.getContentPane().add(label_2);
		
		bookname = new JTextField();
		bookname.setBounds(139, 47, 151, 24);
		frame.getContentPane().add(bookname);
		bookname.setColumns(10);
		
		auth = new JTextField();
		auth.setBounds(383, 47, 173, 24);
		frame.getContentPane().add(auth);
		auth.setColumns(10);
		
		price = new JTextField();
		price.setBounds(657, 47, 166, 24);
		frame.getContentPane().add(price);
		price.setColumns(10);
		
		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book book = new Book();
				book.setId(XiudunTools.getKey());
				book.setAuth(auth.getText());
				book.setBookname(bookname.getText());
				book.setPrice(Double.valueOf(price.getText().trim()));
				//调用方法
				bookAction.save(book);
				//显示
				table.setModel(new DefaultTableModel(
					bookAction.findAll(),
					new String[] {
						"id", "图书名称", "作者", "单价"
					}
				));
			}
		});
		button.setBounds(854, 46, 113, 27);
		frame.getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 119, 990, 147);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();

		table.setModel(new DefaultTableModel(
			bookAction.findAll(),
			new String[] {
				"id", "图书名称", "作者", "单价"
			}
		));
		scrollPane.setViewportView(table);
		
		button_1 = new JButton("关闭");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
			}
		});
		button_1.setBounds(982, 46, 113, 27);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("删除");

		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	              int[] rows=table.getSelectedRows(); 
	              for(int row:rows)
	              {
	            	String  id= (String) table.getValueAt(row, 0);
	            	System.out.println(id);
	            	bookAction.delete(id);
	              }
					table.setModel(new DefaultTableModel(
							bookAction.findAll(),
							new String[] {
								"id", "图书名称", "作者", "单价"
							}
						));				    
				
			}
		});
		button_2.setBounds(762, 287, 93, 23);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("修改");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				if(row>-1)
				{
					String  id= (String) table.getValueAt(row, 0);
					String  name=(String) table.getValueAt(row, 1);
					String  author=(String)table.getValueAt(row, 2);
					String  price=String.valueOf(table.getValueAt(row, 3));
					String[] book= {id,name,author,price};
					BookEdit bookEdit=new BookEdit(book);
					bookEdit.main(book);
				}
                
			}
		});
		button_3.setBounds(874, 287, 93, 23);
		frame.getContentPane().add(button_3);
		
		JButton btnNewButton = new JButton("刷新");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel(
						bookAction.findAll(),
						new String[] {
							"id", "图书名称", "作者", "单价"
						}
					));	
			}
		});
		btnNewButton.setBounds(982, 287, 93, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
