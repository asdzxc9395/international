package international.web;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.GsonBuilder;

import international.domain.Expense;
import international.service.ExpenseService;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

	@Autowired
	ExpenseService expenseService;

	@Autowired
	ServletContext servletContext;


	@GetMapping("form")
	public String form() throws Exception {
		return "/WEB-INF/jsp/expense/form.jsp";
	}
	
	@GetMapping("list")
	public String list(Model model) throws Exception {
		List<Expense> list = expenseService.list();
		model.addAttribute("list", list);
		return "/WEB-INF/jsp/expense/list.jsp";
	}
	@GetMapping("detail")
	public String detail(int no, Model model) throws Exception {
		
		model.addAttribute("expense", expenseService.get(no));
		return "/WEB-INF/jsp/expense/detail.jsp";
	}
	@GetMapping(value="data", produces = "application/json;charset=UTF-8")
	public String data(int no, Model model) throws Exception {
		
		Expense expense = expenseService.get(no);
		
		return new GsonBuilder().create().toJson(expense);	
		}

	@GetMapping("delete")
		public String delete(int no, Model model) throws Exception{
	    if (expenseService.delete(no) > 0) { // 삭제했다면,
	        return "redirect:list";
	      } else {
	        throw new Exception("삭제할 회원 번호가 유효하지 않습니다.");
	      }
		}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Expense expense, MultipartFile imageFile) throws Exception {
		if (imageFile.getSize() > 0) {
			String dirPath = servletContext.getRealPath("/upload/expense");
			String filename = UUID.randomUUID().toString();
			imageFile.transferTo(new File(dirPath + "/" + filename));
			expense.setReceipt(filename);
		}
		expenseService.add(expense);
		System.out.println(expense);

		return "redirect:../expense/list";
	}
	
	@PostMapping("update")
	public String update(Expense expense, MultipartFile imageFile) throws Exception {
		if (imageFile.getSize() > 0) {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡ");
			String dirPath = servletContext.getRealPath("/upload/expense");
			String filename = UUID.randomUUID().toString();
			System.out.println(filename + "\n" + dirPath);
			imageFile.transferTo(new File(dirPath + "/" + filename));
			expense.setReceipt(filename);
		}
		if (expenseService.update(expense) > 0) {
			return "redirect:list";
		} else {
			throw new Exception("변경할 게시물 번호가 유효하지 않습니다.");
		}
	}
//	@GetMapping("list")
//	public String list(Criteria cri, Model model) throws Exception{
//		List<Expense> expense = expenseService.listPage(cri);
//		model.addAttribute("list",expense);
//		PageMaker pageMaker = new PageMaker(cri);
//		int totalCount = expenseService.getTotalCount(cri);
//		pageMaker.setTotalCount(totalCount);
//		model.addAttribute("pageMaker", pageMaker);
//
//		return "/WEB-INF/jsp/expense/list.jsp";
//	}

//	@GetMapping("detail")
//	public String updateForm(int no, Model model) throws Exception {
//		model.addAttribute("expense", expenseService.get(no));
//		return "/WEB-INF/jsp/expense/detail.jsp";
//	}
//

//	@RequestMapping(value = "/downloadExcelFile", method =  RequestMethod.POST)
//	public String downloadExcelFile(Expense expense, Model model, HttpServletRequest req) throws Exception {
//		HttpSession session = req.getSession();
//		List<Expense> list = (List<Expense>) session.getAttribute("userNo");
//		System.out.println(list);
//		
//		SXSSFWorkbook workbook = expenseService.excelFileDownloadProcess(list);
//		model.addAttribute("locale", Locale.KOREA);
//		model.addAttribute("workbook", workbook);
//		model.addAttribute("workbookName", "기본정보");
//		return "excelDownloadView";
//	}
//	@RequestMapping(value = "/moveData", method =  RequestMethod.POST)
//	@ResponseBody
//	public String moveData(Expense expense, Model model, 
//			@RequestParam(value = "chBox[]") List<String> chArr	
//			, HttpServletRequest req) throws Exception {
//		List<Expense> list = new ArrayList<Expense>();
//		int userNum = 0;
//		for(String i : chArr) {  
//			userNum = Integer.parseInt(i);
//			expense = expenseService.get(userNum);
//			list.add(expense);
//		}  
//		System.out.println(list);
//		
//		HttpSession session = req.getSession();
//		session.setAttribute("userNo", list);
//		System.out.println(list);
//		return "1";
//	}

}

















