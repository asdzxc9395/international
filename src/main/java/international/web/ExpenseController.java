package international.web;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import international.domain.Criteria;
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
	public String list(Criteria cri, Model model) throws Exception{
		List<Expense> size = expenseService.list();
		List<Expense> expense = expenseService.listPage(cri);
		model.addAttribute("list",expense);

		int usePrice = 0;
		int approvePrice = 0;
		int b = 0;
		for(int i = 0; i < expense.size(); i++) {
			if(expense.get(i).getUsePrice() == null) {
				b = 0;	
			} else {
				b = Integer.parseInt(expense.get(i).getUsePrice());
			}
			usePrice+=b;
		}
		for(int i = 0; i < expense.size(); i++) {
			if(expense.get(i).getApprovePrice() == null) {
				b = 0;	
			} else {
				b = Integer.parseInt(expense.get(i).getApprovePrice());
			}
			approvePrice+=b;
		}

		model.addAttribute("size", size.size());
		model.addAttribute("approvePrice", approvePrice);
		model.addAttribute("usePrice", usePrice);

		return "/WEB-INF/jsp/expense/list.jsp";
	}

	@GetMapping("detail")
	public String detail(Criteria cri,int no, Model model) throws Exception {
		List<Expense> size = expenseService.list();
		List<Expense> expense = expenseService.listPage(cri);
		model.addAttribute("list",expense);

		int usePrice = 0;
		int approvePrice = 0;
		int b = 0;
		for(int i = 0; i < expense.size(); i++) {
			if(expense.get(i).getUsePrice() == null) {
				b = 0;	
			} else {
				b = Integer.parseInt(expense.get(i).getUsePrice());
			}
			usePrice+=b;
		}
		for(int i = 0; i < expense.size(); i++) {
			if(expense.get(i).getApprovePrice() == null) {
				b = 0;	
			} else {
				b = Integer.parseInt(expense.get(i).getApprovePrice());
			}
			approvePrice+=b;
		}

		model.addAttribute("size", size.size());
		model.addAttribute("approvePrice", approvePrice);
		model.addAttribute("usePrice", usePrice);
		model.addAttribute("list",expense);
		model.addAttribute("expense", expenseService.get(no));
		return "/WEB-INF/jsp/expense/detail.jsp";
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

		return "redirect:../expense/detail?no=0";
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
			return "redirect:../expense/detail?no=0";
		} else {
			throw new Exception("변경할 게시물 번호가 유효하지 않습니다.");
		}
	}

	@GetMapping("search")
	public String search(Expense expense, Model model, Criteria cri
			, HttpServletRequest req) throws Exception {
		HashMap<String, Object> map = new HashMap<>();

		if (expense.getName().length() > 0) {
			map.put("name", expense.getName());
		}

		if (expense.getRegistrationDate() != null) {
			map.put("registrationDate", expense.getRegistrationDate().toString());
		}

		if (expense.getProcessStatus().length() > 0) {
			map.put("processStatus", expense.getProcessStatus());
		}

		List<Expense> list = expenseService.search(map);
		for(int i = 0; i < list.size(); i++) {
			if(list.size() > 5) {
				System.out.println(list.get(i));
				list.remove(i);
			}
		}
		Collections.reverse(list);
		
		//세션 저장
		HttpSession session = req.getSession();
		session.setAttribute("userNo", list);

		model.addAttribute("size", list.size());
		model.addAttribute("list", list);
		return "/WEB-INF/jsp/expense/search.jsp";
	}

	@RequestMapping(value = "/downloadExcelFile", method =  RequestMethod.POST)
	public String downloadExcelFile(Expense expense, Model model, Criteria cri) throws Exception {
		List<Expense> list = expenseService.listPage(cri);
		SXSSFWorkbook workbook = expenseService.excelFileDownloadProcess(list);
		model.addAttribute("locale", Locale.KOREA);
		model.addAttribute("workbook", workbook);
		model.addAttribute("workbookName", "기본정보");
		return "excelDownloadView";
	}
	// search검색시 나오는 정보를 다운받을 exceldownloads
	@RequestMapping(value = "/downloadExcelFile2", method =  RequestMethod.POST)
	public String downloadExcelFile2(Expense expense, Model model, Criteria cri
			, HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		List<Expense> list = (List<Expense>) session.getAttribute("userNo");
		SXSSFWorkbook workbook = expenseService.excelFileDownloadProcess(list);
		model.addAttribute("locale", Locale.KOREA);
		model.addAttribute("workbook", workbook);
		model.addAttribute("workbookName", "기본정보");
		return "excelDownloadView";
	}
}

















