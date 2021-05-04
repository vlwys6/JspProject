package dao;

public class StBuy {

	private String id;
	private int bd_num;
	private int sbuy_num;
	private int type_num;
	private int s_cnt;
	private double s_score;
	private String buy_chk;
	private String Buy_Info;
	private String sbuy_id;
	private int buy_payprice;
	private String buy_date;
	private String buy_address;
	
	
	
	
	public String getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}
	public String getBuy_address() {
		return buy_address;
	}
	public void setBuy_address(String buy_address) {
		this.buy_address = buy_address;
	}
	public int getBuy_payprice() {
		return buy_payprice;
	}
	public void setBuy_payprice(int buy_payprice) {
		this.buy_payprice = buy_payprice;
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
	public int getSbuy_num() {
		return sbuy_num;
	}
	public void setSbuy_num(int sbuy_num) {
		this.sbuy_num = sbuy_num;
	}
	public int getType_num() {
		return type_num;
	}
	public void setType_num(int type_num) {
		this.type_num = type_num;
	}
	public int getS_cnt() {
		return s_cnt;
	}
	public void setS_cnt(int s_cnt) {
		this.s_cnt = s_cnt;
	}
	public double getS_score() {
		return s_score;
	}
	public void setS_score(double s_score) {
		this.s_score = s_score;
	}
	public String getBuy_chk() {
		return buy_chk;
	}
	public void setBuy_chk(String buy_chk) {
		this.buy_chk = buy_chk;
	}
	public String getBuy_Info() {
		return Buy_Info;
	}
	public void setBuy_Info(String buy_Info) {
		Buy_Info = buy_Info;
	}
	public String getSbuy_id() {
		return sbuy_id;
	}
	public void setSbuy_id(String sbuy_id) {
		this.sbuy_id = sbuy_id;
	}
	
	
}
