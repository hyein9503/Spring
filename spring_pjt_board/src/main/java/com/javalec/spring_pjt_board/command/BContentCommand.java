package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.BDao;
import com.javalec.spring_pjt_board.dto.BDto;

public class BContentCommand implements BCommand { //게시판에서 리스트의 내용 클릭 시 보이는 콘텐츠 내용 

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String,Object> map= model.asMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		String bid= request.getParameter("bid");//클릭한 글의 내용을 bId를 통해 뽑아옴 
	
	BDao dao= new BDao();
	BDto dto= dao.contentView(bid); 
	
	model.addAttribute("content_view",dto);
	
	
	}

}
