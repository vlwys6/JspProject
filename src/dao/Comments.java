package dao;

public class Comments {
	private String id;
	private int bd_num;
	private int comm_num;
	private int type_num;
	private int comm_like;
	private String comm_date;
	private String comm_cont;
	private String comm_id;
	
	private String searchkeyWord;
	
	public String getSearchkeyWord() {
		return searchkeyWord;
	}
	public void setSearchkeyWord(String searchkeyWord) {
		this.searchkeyWord = searchkeyWord;
	}
	public String getComm_id() {
		return comm_id;
	}
	public void setComm_id(String comm_id) {
		this.comm_id = comm_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBd_num() {
		return bd_num;
	}
	public void setBd_num(int bd_num) {
		this.bd_num = bd_num;
	}
	public int getComm_num() {
		return comm_num;
	}
	public void setComm_num(int comm_num) {
		this.comm_num = comm_num;
	}
	public int getType_num() {
		return type_num;
	}
	public void setType_num(int type_num) {
		this.type_num = type_num;
	}
	public int getComm_like() {
		return comm_like;
	}
	public void setComm_like(int comm_like) {
		this.comm_like = comm_like;
	}
	public String getComm_date() {
		return comm_date;
	}
	public void setComm_date(String comm_date) {
		this.comm_date = comm_date;
	}
	public String getComm_cont() {
		return comm_cont;
	}
	public void setComm_cont(String comm_cont) {
		this.comm_cont = comm_cont;
	}
	
	
}
