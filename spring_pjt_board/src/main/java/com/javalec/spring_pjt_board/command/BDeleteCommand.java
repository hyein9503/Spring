package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.BDao;

public class BDeleteCommand implements BCommand {
//글 삭제 하는 
	
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map=model.asMap();
		HttpServletRequest request= (HttpServletRequest) map.get("request");
		
	String bid= request.getParameter("bid");
	
	BDao dao = new BDao();
	dao.delete(bid);
	
	
	
		
		
	}

}
