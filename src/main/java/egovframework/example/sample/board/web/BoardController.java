package egovframework.example.sample.board.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String mgmt2(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {

		boardService.insertBoard(boardVO);
		return "redirect:/mainList.do";
	}

	@RequestMapping(value = "/mgmt.do", method = RequestMethod.GET)
	public String mgmt(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws Exception {
		Object userId = request.getSession().getAttribute("userId");
		Object userName = request.getSession().getAttribute("userName");

		if (userId == null || "".equals(userId.toString())) {
			redirectAttributes.addFlashAttribute("msg", "로그인 후 이용할 수 있습니다.");
			return "redirect:/mainList.do";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strToday = sdf.format(Calendar.getInstance().getTime());

		boardVO.setIndate(strToday);
		boardVO.setWriter(userId.toString());
		boardVO.setWriterNm(userName != null ? userName.toString() : "");

		String idx = request.getParameter("idx");
		if (idx != null && !"".equals(idx)) {
			boardVO.setIdx(idx);
			boardVO = boardService.selectBoard(boardVO);
		}

		model.addAttribute("boardVO", boardVO);
		return "/board/mgmt";
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

	@RequestMapping(value = "/view.do")
	public String view(@RequestParam("idx") String idx, ModelMap model) throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setIdx(idx);
		boardVO = boardService.selectBoard(boardVO);
		model.addAttribute("boardVO", boardVO);
		return "/board/view";
	}

	@RequestMapping(value = "/logout.do")
	public String logout(ModelMap model, HttpServletRequest request) throws Exception {
		request.getSession().invalidate();
		return "redirect:mainList.do";
	}



}
