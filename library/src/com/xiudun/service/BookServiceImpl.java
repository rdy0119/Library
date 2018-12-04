/**
 * 
 */
package com.xiudun.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.xiudun.util.XiudunTools;
import com.xiudun.vo.Book;
import com.xiudun.vo.Student;

/**
 * @author Administrator
 * 图书业务实现类
 */
public class BookServiceImpl implements BookService {
	//依赖关系:通过pro来加载文件或输出文件
	private Properties pro = new Properties();

	@Override
	public void saveBook(Book book) {
		//找到保存文件的文件夹: c:/xiu/book
		//文件名称必须不能重复		
		try(OutputStream out = new FileOutputStream("c:/xiu/book/"+XiudunTools.getDateTime()+".properties")){
			//数据添加到资源对象中
			pro.setProperty("id", book.getId());
			pro.setProperty("bookname",book.getBookname());
			pro.setProperty("auth",book.getAuth());
			pro.setProperty("price",String.valueOf(book.getPrice()));			
			//把book对象中的值，按照key-value的形式添加到对应的资源文件中保存
			pro.store(out, "book info");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBook(String id) {
		//依赖findById方法
		File file = findById(id);
		if(file!=null) {
			//删除文件
			file.delete();
		}
	}

	@Override
	public File findById(String id) {
		try {
			//查找文件
			File file = new File("c:/xiu/book");
			//获取所有的文件
			File[] files = file.listFiles();
			//必须把文件关闭后才可以删除
			for(File f:files) {
				//加载文件
				try(InputStream in = new FileInputStream(f)){
					pro.load(in);
					//判断id是否正确
					if(id.equals(pro.getProperty("id"))) {
						return f;
					}
				}catch(Exception e) {e.printStackTrace();}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Book book) {
		File file = findById(book.getId());
		if(file !=null) {
			//使用后及时关闭
			try(InputStream in = new FileInputStream(file);
				OutputStream out = new FileOutputStream(file)) {
				//资源文件加载
				pro.load(in);
				pro.setProperty("bookname",book.getBookname());
				pro.setProperty("auth",book.getAuth());
				pro.setProperty("price",String.valueOf(book.getPrice()));			
				//把book对象中的值，按照key-value的形式添加到对应的资源文件中保存
				pro.store(out, "book info");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Book> findAllList() {
		List<Book> list = null;
		//获取表中所有的数据
		File file = new File("c:/xiu/book/");
		File[] files = file.listFiles();
		if(files.length>0) {
			list = new ArrayList<>();
			//遍历
			for(File f:files) {
				//判断
				if(f.isFile()) {
					try(InputStream in = new FileInputStream(f)){
						//加载
						pro.load(in);
						//把对应的值添加到图书对象中
						Book book = new Book();
						book.setId(pro.getProperty("id"));
						book.setBookname(pro.getProperty("bookname"));
						book.setAuth(pro.getProperty("auth"));
						book.setPrice(Double.valueOf(pro.getProperty("price").trim()));
						//把每个对象添加到集合中
						list.add(book);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}				
		return list;
	}

	@Override
	public Object[][] findAll() {
		Object[][] array = null;
		//判断是否为null
		List<Book> list = findAllList();
		if(list != null) {
			//完成二维数组
			array = new Object[list.size()][4];
			for(int index=0;index<list.size();index++) {
				Book b = list.get(index);
				array[index][0] = b.getId();
				array[index][1] = b.getBookname();
				array[index][2] = b.getAuth();
				array[index][3] = b.getPrice();
			}			
		}
		return array;
	}

	@Override
	public Book findByIdBook(String id) {
		//依赖于findById
		File file = findById(id);
		if(file !=null) {
			try(InputStream in = new FileInputStream(file)){
				pro.load(in);
				Book book = new Book();
				book.setId(pro.getProperty("id"));
				book.setBookname(pro.getProperty("bookname"));
				book.setAuth(pro.getProperty("auth"));
				book.setPrice(Double.valueOf(pro.getProperty("price").trim()));
				return book;				
			}catch(Exception e) {e.printStackTrace();}
		}
		return null;
	}
}



































