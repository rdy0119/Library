/**
 * 
 */
package com.xiudun.action;

import com.xiudun.service.StudentService;
import com.xiudun.service.StudentServiceImpl;
import com.xiudun.vo.Book;
import com.xiudun.vo.Student;

/**
 * @author Administrator
 *  学生控制层
 */
public class StudentAction {

	private StudentService service = new StudentServiceImpl();
	
	//添加
	public void save(Student s) {
		service.saveStudent(s);
	}
	
	public void delete(String id)
	{
		service.deleteStudent(id);
	}
	//显示
	public Object[][] findAll(){
		return service.findAll();
	}
	
	//借书
	public void borrow(String sid,String bid) {
		service.borrow(sid, bid);
	}
	//借书
	public void returnB(String sid,String bid) {
		service.returnBook(sid, bid);
	}
	//查看明细
	public Object[][] detail(String sid){
		if(sid!=null)
			return service.findAllBag(sid);
		return null;
	}
	
	public void update(Student student) 
	{
		service.update(student);
	}
	
}

































