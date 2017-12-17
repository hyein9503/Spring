package com.javalec.spring_pjt_board.dto;

import java.sql.Timestamp;

public class BDto {//데이터에잇는 객체를 클래스로 옮기는 작
	
	int bid;
	String bname;
	String btitle;
	String bcontent;
	Timestamp bdate;
	int bhit;
	int bgroup;
	int bstep;
	int bindent;

	public BDto() {
		
	}
public BDto(int bid, String bname, String btitle,String bcontent, Timestamp bdate, int bhit, int bgroup, int bstep, int bindent ) {
		
	this.bid=bid;
	this.bname=bname;
	this.btitle=btitle;
	this.bcontent=bcontent;
	this.bdate=bdate;
	this.bhit=bhit;
	this.bgroup=bgroup;
	this.bstep=bstep;
	this.bindent=bindent;
	
	}

	public String getbcontent() {
	return bcontent;
}
public void setbcontent(String bcontent) {
	this.bcontent = bcontent;
}
	public int getbid() {
		return bid;
	}

	public void setbid(int bid) {
		this.bid = bid;
	}

	public String getbname() {
		return bname;
	}

	public void setbname(String bname) {
		this.bname = bname;
	}

	public String getbtitle() {
		return btitle;
	}
	

	public void setbtitle(String btitle) {
		this.btitle = btitle;
	}

	public Timestamp getbdate() {
		return bdate;
	}

	public void setbdate(Timestamp bdate) {
		this.bdate = bdate;
	}

	public int getbhit() {
		return bhit;
	}

	public void setbhit(int bhit) {
		this.bhit = bhit;
	}

	public int getbgroup() {
		return bgroup;
	}

	public void setbgroup(int bgroup) {
		this.bgroup = bgroup;
	}

	public int getbstep() {
		return bstep;
	}

	public void setbstep(int bstep) {
		this.bstep = bstep;
	}

	public int getbindent() {
		return bindent;
	}

	public void setbindent(int bindent) {
		this.bindent = bindent;
	}
}
