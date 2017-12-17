package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.BDao;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

	Map<String, Object> map = model.asMap(); //모델을 맵형태로 바
	HttpServletRequest request =(HttpServletRequest) map.get("request");
	
	String bname=request.getParameter("bname");
	String btitle=request.getParameter("btitle");
	String bcontent=request.getParameter("bcontent");
	
	BDao dao=new BDao();
	dao.write(bname, btitle,bcontent); //디비에 작성이됨
	
	
	
	
	
	
	
	
	}

}
