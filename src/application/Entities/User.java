package application.Entities;
/**
 * 
 * @author alex
 *
 */
public class User{

	private  	int 	id;
	private 	String 	name;
	private 	String 	password;
	/**
	 * 
	 * @param id
	 */
	public void setId(int id){
		this.id = id;
	}
	/**
	 * 
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	public int getId(){
		return this.id;
	}
	/**
	 * 
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * 
	 * @return
	 */
	public String getPassword(){
		return this.password;
	}

}