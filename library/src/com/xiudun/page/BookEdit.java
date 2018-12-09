/**
 * 
 */
package com.xiudun.page;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.xiudun.action.BookAction;
import com.xiudun.vo.Book;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Administrator
 *
 */
public class BookEdit {

	public JFrame frame;
	private JTextField textField;
	private JTextField name;
	private JTextField author;
	private JTextField price;
	private JTextField id;
	public  Boolean flag;

	/**
	 * Launch the application.
	 */
	public static void main(String[] bookMessage) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookEdit window = new BookEdit(bookMessage);
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
	public BookEdit(String[] bookMessage) {
		initialize(bookMessage);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] bookMessage) {
		frame = new JFrame();
		frame.setTitle("图书修改");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("图书名称：");
		label.setBounds(26, 69, 73, 15);
		frame.getContentPane().add(label);
		
		name = new JTextField();
		name.setBounds(109, 66, 127, 21);
		frame.getContentPane().add(name);
		name.setColumns(10);
		name.setText(bookMessage[1]);
		
		JLabel label_1 = new JLabel("作  者：");
		label_1.setBounds(26, 107, 54, 15);
		frame.getContentPane().add(label_1);
		
		author = new JTextField();
		author.setBounds(109, 104, 127, 21);
		frame.getContentPane().add(author);
		author.setColumns(10);
		author.setText(bookMessage[2]);
		
		JLabel label_2 = new JLabel("单  价：");
		label_2.setBounds(26, 145, 54, 15);
		frame.getContentPane().add(label_2);
		
		price = new JTextField();
		price.setBounds(109, 135, 127, 21);
		frame.getContentPane().add(price);
		price.setColumns(10);
		price.setText(bookMessage[3]);
		
		JButton button = new JButton("保存");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book book=new Book();
				book.setId(id.getText());
				book.setBookname(name.getText());
				book.setAuth(author.getText());
				book.setPrice(Double.valueOf(price.getText()));
				
				BookAction action=new BookAction();		
				Boolean over=action.update(book);
				frame.setVisible(false);				
			}
		});
		button.setBounds(193, 182, 93, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		button_1.setBounds(341, 0, 93, 23);
		frame.getContentPane().add(button_1);
		
		JLabel lblid = new JLabel("id:");
		lblid.setBounds(26, 27, 54, 15);
		frame.getContentPane().add(lblid);
		
		id = new JTextField();
		id.setBounds(109, 24, 127, 21);
		frame.getContentPane().add(id);
		id.setColumns(10);
		id.setEditable(false);
		id.setText(bookMessage[0]);
	

		
	}
}
