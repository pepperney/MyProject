package cn.com.learn.dynamicProxy;

/**
 * IUser的实现类
 * @Description 
 * @author niepei
 * @date 2017年2月8日 上午10:02:47 
 * @version V1.3.1
 */
public class UserImpl implements IUser{

	
	private String username = null;
	
	public UserImpl(){
		
	}
	
	public UserImpl(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return username;
	}
	
	@Override
	public void getUser() {
		System.out.println("this is getUser() method");
		
	}

	@Override
	public void addUser() {
		System.out.println("this is addUser() method");
		
	}

	@Override
	public void updateUser() {
		System.out.println("this is updateUser() method");
		
	}

	@Override
	public void deleteUser() {
		System.out.println("this is deleteUser() method");
		
	}

}
