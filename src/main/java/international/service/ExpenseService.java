package international.service;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import international.domain.Criteria;
import international.domain.Expense;

public interface ExpenseService {

	void add(Expense expense) throws Exception;
	
	List<Expense> list() throws Exception;
	
	int delete(int no) throws Exception;
	
	int update(Expense expense) throws Exception;
	
	Expense get(int no) throws Exception;
	
	List<Expense> search(HashMap<String, Object> params) throws Exception;

	List<Expense> listPage(Criteria cri) throws Exception;
	
	public int getTotalCount(Criteria cri) throws Exception;
	
	//리스트를 간단한 엑셀 워크북 객체로 생성
	public SXSSFWorkbook makeSimpleFruitExcelWorkbook(List<Expense> list) throws Exception;
	
	//생성한 엑셀 워크북을 컨트롤레에서 받게해줄 메소드
	public SXSSFWorkbook excelFileDownloadProcess(List<Expense> list) throws Exception;
	
}