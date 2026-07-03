package egovframework.example.sample.board.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

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
			ModelMap model) throws Exception {
		// HttpServletRequest request
		// String aa = request.getParameter("user_id");
		System.out.println("userid:" + user_id);
		System.out.println("password:" + password);
		return "/board/mainList";
	}
}
