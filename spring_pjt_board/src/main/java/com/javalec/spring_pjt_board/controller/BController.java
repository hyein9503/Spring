package com.javalec.spring_pjt_board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.spring_pjt_board.command.BCommand;
import com.javalec.spring_pjt_board.command.BContentCommand;
import com.javalec.spring_pjt_board.command.BDeleteCommand;
import com.javalec.spring_pjt_board.command.BListCommand;
import com.javalec.spring_pjt_board.command.BModifyCommand;
import com.javalec.spring_pjt_board.command.BReplyCommand;
import com.javalec.spring_pjt_board.command.BReplyViewCommand;
import com.javalec.spring_pjt_board.command.BWriteCommand;

@Controller
public class BController {

	BCommand command;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");
		
		command=new BListCommand();
		command.execute(model);
		
		return"list";
	}
	   
	@RequestMapping("/write_view") //글쓰는 폼 열 
	public String write_view(Model model) {
		System.out.println("writ e_view()");
		
		
		
		
		return "write_view";
	}
	
	@RequestMapping("/write")//글작성  
	public String write(HttpServletRequest request, Model model) {//request로 넘어온 거를 모델에 담
		System.out.println("write()"); 
		model.addAttribute("request",request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";//다 작성한후 다시 리스트로이동 
	}
	
	
	//글 클릭시 글 내용 보여야함
	@RequestMapping("content_view")
	
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		
		model.addAttribute("request",request);
		command= new BContentCommand();
		command.execute(model);
		return"content_view";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify()");
		
		model.addAttribute("request", request);
		command =new BModifyCommand();
		command.execute(model);
		
		
		return"redirect:list";
	}
	
	@RequestMapping("/reply_view")//	답변글보
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view()");
		
		model.addAttribute("request",request);
		
		command= new BReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
		
	}
	@RequestMapping("/reply")//답변하
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply()");
		
		model.addAttribute("request",request);
		command= new BReplyCommand();
		command.execute(model);
		
		return"redirect:list";
	}
	
	@RequestMapping("/delete")//답변하
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request",request);
		 
		command= new BDeleteCommand();	
		command.execute(model);
		
		return "redirect:list";
	}
}
