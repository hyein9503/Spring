package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.BDao;
import com.javalec.spring_pjt_board.dto.BDto;

public class BReplyViewCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map=model.asMap();
		HttpServletRequest request= (HttpServletRequest) map.get("request");
		
		String bid =request.getParameter("bid");
		
		BDao dao= new BDao();
		//dao.reply_view();
		BDto dto=dao.reply_view(bid);
		
		model.addAttribute("reply_view",dto);
		
	}

}
