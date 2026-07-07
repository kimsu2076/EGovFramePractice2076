package egovframework.example.sample.board.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.example.sample.board.service.BoardService;
import egovframework.example.sample.board.service.BoardVO;

@Controller
public class BoardController {

	@Resource(name = "boardService")
	private BoardService boardService;

	@RequestMapping(value = "/mainList.do")
	public String list(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {

		List<?> list = boardService.selectBoardList(boardVO);
		model.addAttribute("resultList", list);

		int totCnt = boardService.selectBoardListTotCnt(boardVO);

		return "/board/mainList";
	}

	@RequestMapping(value = "/mgmt.do", method = RequestMethod.POST)
	public String mgmg2(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {

		boardService.insertBoard(boardVO);
		return "redirect:/mainList.do";
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
		boardVO.setUserId(user_id);
		boardVO.setPassword(password);
		String user_name = boardService.selectLoginCheck(boardVO);

		if (user_name != null && !"".equals(user_name)) {
			request.getSession().setAttribute("userId", user_id);
			request.getSession().setAttribute("userName", user_name);
		} else {
			request.getSession().setAttribute("userId", "");
			request.getSession().setAttribute("userName", "");
			model.addAttribute("msg", "사용자 정보가 올바르지 않습니다.");
		}
		return "redirect:mainList.do";
	}

	@RequestMapping(value = "/logout.do")
	public String logout(ModelMap model, HttpServletRequest request) throws Exception {
		request.getSession().invalidate();
		return "redirect:mainList.do";
	}

}
