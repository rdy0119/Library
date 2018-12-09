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
public class StudentServiceImpl implements StudentService {
	//依赖关系:通过pro来加载文件或输出文件
	private Properties pro = new Properties();

	@Override
	public void saveStudent(Student student) {
		//找到保存文件的文件夹: c:/xiu/Student
		//文件名称必须不能重复		
		try(OutputStream out = new FileOutputStream("c:/xiu/student/"+XiudunTools.getDateTime()+".properties")){
			//数据添加到资源对象中
			pro.setProperty("id", student.getId());
			pro.setProperty("username",student.getUsername());
			pro.setProperty("sex",student.getSex());
			pro.setProperty("age",student.getAge());
			pro.setProperty("bag", student.getBag().toString());
			//把Student对象中的值，按照key-value的形式添加到对应的资源文件中保存
			pro.store(out, "Student info");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteStudent(String id) {
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
			File file = new File("c:/xiu/student");
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
	public void update(Student student) {
		File file = findById(student.getId());
		if(file !=null) {
			//使用后及时关闭
			try(InputStream in = new FileInputStream(file);
				OutputStream out = new FileOutputStream(file)) {
				//资源文件加载
				pro.load(in);
				pro.setProperty("username",student.getUsername());
				pro.setProperty("sex",student.getSex());
				pro.setProperty("age",student.getAge());
				//把Student对象中的值，按照key-value的形式添加到对应的资源文件中保存
				pro.store(out, "Student info");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Student> findAllList() {
		List<Student> list = null;
		//获取表中所有的数据
		File file = new File("c:/xiu/student/");
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
						Student student = new Student();
						student.setId(pro.getProperty("id"));
						student.setUsername(pro.getProperty("username"));
						student.setSex(pro.getProperty("sex"));
						student.setAge(pro.getProperty("age"));
						//把每个对象添加到集合中
						list.add(student);
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
		List<Student> list = findAllList();
		if(list != null) {
			//完成二维数组
			array = new Object[list.size()][5];
			for(int index=0;index<list.size();index++) {
				Student s = list.get(index);
				array[index][0] = s.getId();
				array[index][1] = s.getUsername();
				array[index][2] = s.getSex();
				array[index][3] = s.getAge();
				array[index][4] = s.getBag();
			}			
		}
		return array;
	}

	
	@Override
	public Student findByIdStudent(String id) {
		try {
			//查找文件
			File file = new File("c:/xiu/student");
			//获取所有的文件
			File[] files = file.listFiles();
			//必须把文件关闭后才可以删除
			for(File f:files) {
				//加载文件
				try(InputStream in = new FileInputStream(f)){
					pro.load(in);
					//判断id是否正确
					if(id.equals(pro.getProperty("id"))) {
						Student s = new Student();
						//添加学生信息
						//把地址还原为对象
						s.setAge(pro.getProperty("age"));
						s.setId(pro.getProperty("id"));
						s.setSex(pro.getProperty("sex"));
						s.setUsername(pro.getProperty("username"));
						return s;
					}
				}catch(Exception e) {e.printStackTrace();}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Book> findAllBook(String sid) {
		//获取书包的明细
		List<Book> list = null;
		try {
			//查找文件
			File file = new File("c:/xiu/student");
			//获取所有的文件
			File[] files = file.listFiles();
			//必须把文件关闭后才可以删除
			for(File f:files) {
				//加载文件
				try(InputStream in = new FileInputStream(f)){
					pro.load(in);
					//判断id是否正确bag=[a, b]
					if(sid.equals(pro.getProperty("id"))) {
						//找到学生后再初始化集合
						list = new ArrayList<>();
						//获取书包的字符串
						String bag = pro.getProperty("bag");
						//拆分
						//bag = bag.replaceAll("[","").replaceAll("]","");
						bag = bag.replace("[]", "");
						//split拆分数组
						String[] bookids = bag.split(",");
						BookService bookService = new BookServiceImpl();
						//使用bookService中的findById方法
						for(String bid:bookids) {
							Book b = bookService.findByIdBook(bid.trim());
							if(b!=null) {
								list.add(b);
							}
						}
						break;
					}
				}catch(Exception e) {e.printStackTrace();}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	@Override
	public void borrow(String sid, String bid) {
		//根据学生的id查找对应的数据文件(xxx.properties)
		File file = findById(sid);
		if(file !=null) {
			//找到了
			try(InputStream in = new FileInputStream(file);
				OutputStream out = new FileOutputStream(file)){
				pro.load(in);
				//实际就是更新原来的内容,为了保持原来的值
				String old = pro.getProperty("bag");
				if(old!=null) {
					pro.setProperty("bag", old+","+bid);
				}else {
					pro.setProperty("bag", bid);
				}
				pro.store(out, "");				
			}catch(Exception e) {e.printStackTrace();}
		}
		
	}
	
	@Override
	public Object[][] findAllBag(String sid) {	
		List<Book> list = findAllBook(sid);
		Object[][] array = null;
		if(list !=null) {
			array = new Object[list.size()][4];			
			for(int index=0;index<array.length;index++) {
				Book book = list.get(index);
				array[index][0] = book.getId();
				array[index][1] = book.getBookname();
				array[index][2] = book.getAuth();
				array[index][3] = book.getPrice();
			}		
		}
		return array;
	}

	/* (non-Javadoc)
	 * @see com.xiudun.service.StudentService#returnBook(java.lang.String, java.lang.String)
	 */
	@Override
	public void returnBook(String sid, String bid) {

		//根据学生的id查找对应的数据文件(xxx.properties)
		File file = findById(sid);
		if(file !=null) {
			//找到了
			try(InputStream in = new FileInputStream(file);
				OutputStream out = new FileOutputStream(file)){
				pro.load(in);
				//实际就是更新原来的内容,为了保持原来的值
				String old = pro.getProperty("bag");
				String[] booksId=old.split(",");
				for(int i=0; i<booksId.length;i++)
				{
					if(bid.equals(booksId[i]))
					{
						for(int j=i;j<booksId.length-1;j++)
						{
							booksId[j]=booksId[j+1];
						}
				    booksId[booksId.length-1]="";
					}
				}
				String newB="";
				for(String  id:booksId)
				{
					if(id!="")
						newB=id+",";
				}

				pro.setProperty("bag", newB);

				pro.store(out, "");				
			}catch(Exception e) {e.printStackTrace();}
		}
		
	
		
	}
}



































