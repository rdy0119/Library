/**
 * 
 */
package com.xiudun.service;

import java.io.File;
import java.util.List;

import com.xiudun.vo.Book;
import com.xiudun.vo.Student;

/**
 * @author Administrator
 * 创建学生接口
 */
public interface StudentService {
	
	/**
	 * 保存
	 */
	void saveStudent(Student Student);
	
	/**
	 * 删除：就是删除对应的资源文件
	 */
	void deleteStudent(String id);
	
	/**
	 * 获取资源文件
	 */
	File findById(String id);
	/**
	 * 获取资源文件
	 */
	Student findByIdStudent(String id);
	/**
	 * 更新
	 */
	void update(Student Student);
	/**
	 * 获取所有的数据
	 */
	List<Student> findAllList();
	
	/**
	 * 显示
	 */
	Object[][] findAll();
	
	/**
	 * 根据学生id查找对应的书包明细
	 */
	List<Book> findAllBook(String sid);
	
	/**
	 * 书包明细
	 */
	Object[][] findAllBag(String sid);
	
	/**
	 * 借书
	 */
	void borrow(String sid,String bid);
	/**
	 * 还书
	 */
	void returnBook(String sid,String bid);
}





































