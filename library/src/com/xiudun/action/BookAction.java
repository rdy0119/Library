/**
 * 
 */
package com.xiudun.action;

import com.xiudun.service.BookService;
import com.xiudun.service.BookServiceImpl;
import com.xiudun.vo.Book;

/**
 * @author Administrator
 *
 */
public class BookAction {
	private BookService bookService = new BookServiceImpl();
	
	//添加
	public void save(Book book) {
		bookService.saveBook(book);
	}
	//修改
	public void update(Book book) {
		bookService.update(book);
	}
	//删除
	public void delete(String id) {
		bookService.deleteBook(id);
	}
	//显示
	public Object[][] findAll(){
		return bookService.findAll();
	}
}




























