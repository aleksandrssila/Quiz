package application.Entities;

public class Quiz {
	
	private int id;
	private String name;
	private int owner;
	private int status;
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
	 * @param owner
	 */
	public void setOwner(int owner){
		this.owner = owner;
	}
	/**
	 * 
	 * @param status
	 */
	public void setStatus(int status){
		this.status = status;
	}
	/**
	 * 
	 * @return
	 */
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
	public int getOwner(){
		return this.owner;
	}
	/**
	 * 
	 * @return
	 */
	public int getStatus(){
		return this.status;
	}
}
