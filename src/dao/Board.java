package dao;

public class Board {
  private String id,bd_title,bd_cont,bd_video,bd_pic,bd_date,s_cart,s_pic,bd_yn;
  private int bd_num,type_num,bd_view,bd_like,s_cnt,comments_count,s_price,stbuycnt;
private double s_score;
  //검색
  private String searchkeyWord;
  private String searchValue;
  

  
  public int getStbuycnt() {
	return stbuycnt;
}
public void setStbuycnt(int stbuycnt) {
	this.stbuycnt = stbuycnt;
}
public String getBd_yn() {
	return bd_yn;
}
public void setBd_yn(String bd_yn) {
	this.bd_yn = bd_yn;
}
public String getSearchValue() {
	return searchValue;
}
public void setSearchValue(String searchValue) {
	this.searchValue = searchValue;
}
public String getSearchkeyWord() {
	return searchkeyWord;
}
public void setSearchkeyWord(String searchkeyWord) {
	this.searchkeyWord = searchkeyWord;
}

public int getComments_count() {
	return comments_count;
}
public void setComments_count(int comments_count) {
	this.comments_count = comments_count;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getBd_title() {
	return bd_title;
}
public void setBd_title(String bd_title) {
	this.bd_title = bd_title;
}
public String getBd_cont() {
	return bd_cont;
}
public void setBd_cont(String bd_cont) {
	this.bd_cont = bd_cont;
}
public String getBd_video() {
	return bd_video;
}
public void setBd_video(String bd_video) {
	this.bd_video = bd_video;
}
public String getBd_pic() {
	return bd_pic;
}
public void setBd_pic(String bd_pic) {
	this.bd_pic = bd_pic;
}
public String getBd_date() {
	return bd_date;
}
public void setBd_date(String bd_date) {
	this.bd_date = bd_date;
}
public String getS_cart() {
	return s_cart;
}
public void setS_cart(String s_cart) {
	this.s_cart = s_cart;
}
public String getS_pic() {
	return s_pic;
}
public void setS_pic(String s_pic) {
	this.s_pic = s_pic;
}
public int getBd_num() {
	return bd_num;
}
public void setBd_num(int bd_num) {
	this.bd_num = bd_num;
}
public int getType_num() {
	return type_num;
}
public void setType_num(int type_num) {
	this.type_num = type_num;
}
public int getBd_view() {
	return bd_view;
}
public void setBd_view(int bd_view) {
	this.bd_view = bd_view;
}
public int getBd_like() {
	return bd_like;
}
public void setBd_like(int bd_like) {
	this.bd_like = bd_like;
}
public int getS_cnt() {
	return s_cnt;
}
public void setS_cnt(int s_cnt) {
	this.s_cnt = s_cnt;
}
public int getS_price() {
	return s_price;
}
public void setS_price(int s_price) {
	this.s_price = s_price;
}
public double getS_score() {
	return s_score;
}
public void setS_score(double s_score) {
	this.s_score = s_score;
}
  
	
  
  
}
