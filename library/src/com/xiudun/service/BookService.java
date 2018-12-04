/**
 * 
 */
package com.xiudun.service;

import java.io.File;
import java.util.List;

import com.xiudun.vo.Book;

/**
 * @author Administrator
 * 创建图书接口
 */
public interface BookService {
	
	/**
	 * 保存
	 */
	void saveBook(Book book);
	
	/**
	 * 删除：就是删除对应的资源文件
	 */
	void deleteBook(String id);
	
	/**
	 * 获取资源文件
	 */
	File findById(String id);
	/**
	 * 获取图书对象
	 */
	Book findByIdBook(String id);
	/**
	 * 更新
	 */
	void update(Book book);
	/**
	 * 获取所有的数据
	 */
	List<Book> findAllList();
	
	/**
	 * 显示
	 */
	Object[][] findAll();
	
}





































