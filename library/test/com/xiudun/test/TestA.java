/**
 * 
 */
package com.xiudun.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import com.xiudun.service.BookService;
import com.xiudun.service.BookServiceImpl;
import com.xiudun.service.StudentService;
import com.xiudun.service.StudentServiceImpl;
import com.xiudun.vo.Book;
import com.xiudun.vo.Student;

/**
 * @author Administrator
 *
 */
class TestA {
	
	/**
	 * 查找学生
	 */
	@Test
	void testg() throws Exception {
		String a = "[],123";
		System.out.println(a.replace("[]", ""));
	}
	
	/**
	 * 
	 * TODO 在书包中添加图书id，编程字符串保存
	 * @throws FileNotFoundException 
	 */
	@Test
	void testf() throws Exception {
	    Student s = new Student();
	    s.getBag().add("a");
	    s.getBag().add("b");
	    
	    Properties pro = new Properties();
	    
	    OutputStream out = new FileOutputStream("c:/xiu/student/aa.txt");
	    
	    pro.setProperty("bag", s.getBag().toString());
	    
	    pro.store(out,"");
	    
	    
	}
	
	/**
	 * 
	 * TODO 测试更新方法
	 */
	@Test
	void testd() {
		Book book = new Book();
		book.setId("1");
		book.setBookname("mysql");
		book.setAuth("tom");
		book.setPrice(123.01);
		
		BookService bs = new BookServiceImpl();
		bs.update(book);
		
	}
	/**
	 * 
	 * TODO 删除指定的文件
	 */
	@Test
	void testc() {
		File file = new File("c:/xiu/aa.txt");
		if(file.exists())
			file.delete();
	}

	/**
	 * 
	 * TODO 测试保存方法
	 */

	@Test
	void testa() {
		Book book = new Book();
		book.setId("1");
		book.setBookname("java");
		book.setAuth("tom");
		book.setPrice(123.01);
		
		BookService bs = new BookServiceImpl();
		bs.saveBook(book);
		
	}
	/**
	 * 
	 * TODO 遍历目录的文件
	 */
	@Test
	void testb() {		
		File file = new File("e:/20181115/");
		//所有
		File[] files = file.listFiles();
		for(File f:files) {
			if(f.isDirectory()) {
				System.out.println("----->>文件夹:"+f);
			}
			if(f.isFile()) {
				System.out.println("=====>>文件:"+f);
			}
		}
	}
	

}



















