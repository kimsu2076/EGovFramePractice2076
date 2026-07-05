package egovframework.example.sample.board.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.example.sample.board.service.BoardService;
import egovframework.example.sample.board.service.BoardVO;

@Controller
public class BoardController {

	@Resource(name = "boardService")
	private BoardService boardService;

	@RequestMapping(value = "/mainList.do")
	public String list(ModelMap model) throws Exception {
		return "/board/mainList";
	}

	@RequestMapping(value = "/mgmt.do")
	public String mgmg(ModelMap model) throws Exception {
		return "/board/mgmt";
	}

	@RequestMapping(value = "/view.do")
	public String view(ModelMap model) throws Exception {
		return "/board/view";
	}

	@RequestMapping(value = "/login.do")
	public String login(@RequestParam("user_id") String user_id, @RequestParam("password") String password,
			ModelMap model, HttpServletRequest request) throws Exception {
		// HttpServletRequest request
		// String aa = request.getParameter("user_id");
		System.out.println("userid:" + user_id);
		System.out.println("password:" + password);
		
		BoardVO boardVO = new BoardVO();
		boardVO.serUserId(user_id);
		boardVO.setPassword(password);
		String user_name = boardService.selectLoginCheck(boardVO);
		
		if(user_name != null && !"".equals(user_name) ) {
			request.getSession().setAttribute("userId", user_id);
			request.getSession().setAttribute("userName", user_name);
		}else {
			request.getSession().setAttribute("userId", "");
			request.getSession().setAttribute("userName", "");
			model.addAttribute("msg","사용자 정보가 올바르지 않습니다.")
		}
		return "/board/mainList";
	}
}
