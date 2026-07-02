package egovframework.example.sample.board.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.sample.service.SampleDefaultVO;

@Controller
public class BoardController {

	@RequestMapping(value = "/mainList.do")
	public String list(ModelMap model) throws Exception {
		return "/board/mainList";
	}
	
	@RequestMapping(value = "/mgmt.do")
	public String mgmg(ModelMap model) throws Exception {  return "/board/mgmt";
	}
	
	@RequestMapping(value = "/view.do")
	public String view(ModelMap model) throws Exception {  return "/board/view";
	}
	
}
