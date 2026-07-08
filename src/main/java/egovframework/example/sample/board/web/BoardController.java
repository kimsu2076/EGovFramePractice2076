package egovframework.example.sample.board.web;

import egovframework.example.sample.board.service.BoardService;
import egovframework.example.sample.board.service.BoardVO;
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

@Controller
public class BoardController {

    @Resource (name = "boardService")
    private BoardService boardService;

    @RequestMapping (value = "/mainList.do")
    public String list(@ModelAttribute ("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
        List<?> list = boardService.selectBoardList(boardVO);
        model.addAttribute("resultList", list);

        int totCnt = boardService.selectBoardListTotCnt(boardVO);

        return "/board/mainList";
    }

    @RequestMapping (value = "/mgmt.do", method = RequestMethod.POST)
    public String mgmt2(@ModelAttribute ("boardVO") BoardVO boardVO, 
    @RequestParam ("mode") String mode, ModelMap model) throws Exception {
        if ("add".equals(mode)) {
        	boardService.insertBoard(boardVO);
        }
        else if ("modify".equals(mode)) {
        	boardService.updateBoard(boardVO);
        }
        else if ("del".equals(mode)) {
        	boardService.deleteBoard(boardVO);
        }
        return "redirect:/mainList.do";
    }

    @RequestMapping (value = "/mgmt.do", method = RequestMethod.GET)
    public String mgmt(@ModelAttribute ("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        System.out.println("Today" + strToday);

        boardVO = boardService.selectBoard(boardVO);

        boardVO.setIndate(strToday);
        boardVO.setWriter(request.getSession().getAttribute("userId").toString());
        boardVO.setWriterNm(request.getSession().getAttribute("userName").toString());

        model.addAttribute("boardVO", boardVO);

        return "/board/mgmt";
    }

    @RequestMapping (value = "/login.do")
    public String login(@RequestParam ("user_id") String user_id, @RequestParam ("password") String password, ModelMap model, HttpServletRequest request) throws Exception {
        System.out.println("userid:" + user_id);
        System.out.println("password:" + password);

        BoardVO boardVO = new BoardVO();
        boardVO.setUserId(user_id);
        boardVO.setPassword(password);
        String user_name = boardService.selectLoginCheck(boardVO);

        if (user_name != null && !"".equals(user_name)) {

        	request.getSession().setAttribute("userId", user_id);
            request.getSession().setAttribute("userName", user_name);
        }
        else {
        	request.getSession().setAttribute("userId", "");
            request.getSession().setAttribute("userName", "");
            model.addAttribute("msg", "사용자 정보가 올바르지 않습니다.");
        }
        return "redirect:mainList.do";
    }

    @RequestMapping(value = "/view.do")
    public String view(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();  
        String strToday = sdf.format(c1.getTime());  
        System.out.println("Today" + strToday);

        boardVO = boardService.selectBoard(boardVO);  

        model.addAttribute("boardVO", boardVO);
        model.addAttribute("strToday", strToday);
    
        List<?> list = boardService.selectReplyList(boardVO);
        // resultlist에 list를 넣음  
        model.addAttribute("resultList", list);
        return "/board/view";
    }

    @RequestMapping (value = "/logout.do")
    public String logout(ModelMap model, HttpServletRequest request) throws Exception {
        request.getSession().invalidate();
        return "redirect:mainList.do";
    }
    
    @RequestMapping(value = "/reply.do",  method=RequestMethod.POST)
    public String reply(@ModelAttribute("boardVO")  BoardVO boardVO, ModelMap model) throws  Exception {
        boardService.insertReply(boardVO);
        return  "redirect:/view.do?idx="+boardVO.getIdx();
    }
}
