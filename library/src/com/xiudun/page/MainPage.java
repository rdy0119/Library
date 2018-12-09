/**
 * 
 */
package com.xiudun.page;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Administrator
 *
 */
public class MainPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
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
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("图书管理");
		frame.setBounds(100, 100, 874, 669);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTree tree = new JTree();
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//单击显示对应的页面
				if(tree.getLastSelectedPathComponent()!=null) {
					String param = tree.getLastSelectedPathComponent().toString();
					if("图书".equals(param)) {
						BookPage.main(null);
					}else if("学生".equals(param)) {
						StudentPage.main(null);
					}
				}
				
			}
		});
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("图书管理") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("功能操作");
						node_1.add(new DefaultMutableTreeNode("图书"));
						node_1.add(new DefaultMutableTreeNode("学生"));
					add(node_1);
				}
			}
		));
		tree.setBounds(14, 23, 319, 586);
		frame.getContentPane().add(tree);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Downloads\\001.jpg"));
		lblNewLabel.setBounds(362, 101, 480, 419);
		frame.getContentPane().add(lblNewLabel);
	}
}
