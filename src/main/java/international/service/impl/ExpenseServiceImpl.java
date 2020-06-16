package international.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import international.dao.ExpenseDao;
import international.domain.Criteria;
import international.domain.Expense;
import international.service.ExpenseService;

@Component
public class ExpenseServiceImpl implements ExpenseService{
	
	ExpenseDao expenseDao;
	
	public ExpenseServiceImpl(ExpenseDao expensenDao) {
		this.expenseDao = expensenDao;
	}
	@Override
	public void add(Expense expense) throws Exception {
		expenseDao.insert(expense);
	}

	@Override
	public List<Expense> list() throws Exception {
		return expenseDao.findAll();
	}

	@Override
	public int delete(int no) throws Exception {
		 return expenseDao.delete(no);
	}
	

	@Override
	public int update(Expense expense) throws Exception {
		return expenseDao.update(expense);
	}

	@Override
	public Expense get(int no) throws Exception {
		return expenseDao.findByNo(no);
	}
	
//	@Override
//	  public List<Expense> search(HashMap<String, Object> params) throws Exception {
//	    return expenseDao.findByKeyword(params);
//	  }

//	@Override
//	public List<Expense> listPage(Criteria cri) throws Exception {
//		return expenseDao.listPage(cri);
//	}
//
//	@Override
//	public int getTotalCount(Criteria cri) throws Exception {
//		return expenseDao.getTotalCount(cri);
//	}
	
//	@Override
//	public SXSSFWorkbook makeSimpleFruitExcelWorkbook(List<Expense> list) throws Exception {
//		SXSSFWorkbook workbook = new SXSSFWorkbook();
//        System.out.println("svimpl"+workbook);
//        System.out.println("svimpl"+list);
//        // 시트 생성
//        SXSSFSheet sheet = workbook.createSheet("기본정보");
//        
//        //시트 열 너비 설정
//        sheet.setColumnWidth(0, 1500);
//        sheet.setColumnWidth(0, 3000);
//        sheet.setColumnWidth(0, 3000);
//        sheet.setColumnWidth(0, 1500);
//        
//        // 헤더 행 생
//        Row headerRow = sheet.createRow(0);
//        // 해당 행의 첫번째 열 셀 생성
//        Cell headerCell = headerRow.createCell(0);
//        headerCell.setCellValue("번호");
//        // 해당 행의 두번째 열 셀 생성
//        headerCell = headerRow.createCell(1);
//        headerCell.setCellValue("한글이름");
//        // 해당 행의 세번째 열 셀 생성
//        headerCell = headerRow.createCell(2);
//        headerCell.setCellValue("주민등록번호");
//        // 해당 행의 네번째 열 셀 생성
//        headerCell = headerRow.createCell(3);
//        headerCell.setCellValue("생년월일");
//        headerCell = headerRow.createCell(4);
//        headerCell.setCellValue("성별");
//        headerCell = headerRow.createCell(5);
//        headerCell.setCellValue("결혼유무");
//        headerCell = headerRow.createCell(6);
//        headerCell.setCellValue("년차");
//        headerCell = headerRow.createCell(7);
//        headerCell.setCellValue("전화번호");
//        headerCell = headerRow.createCell(8);
//        headerCell.setCellValue("이메일");
//
//        
//        // 과일표 내용 행 및 셀 생성
//        Row bodyRow = null;
//        Cell bodyCell = null;
//        for(int i=0; i<list.size(); i++) {
//            Expense expense = list.get(i);
//            // 행 생성
//            bodyRow = sheet.createRow(i+1);
//            // 데이터 번호 표시
//            bodyCell = bodyRow.createCell(0);
//            bodyCell.setCellValue(i + 1);
//            // 데이터 이름 표시
//            bodyCell = bodyRow.createCell(1);
//            bodyCell.setCellValue(expense.getKorName());
//            // 데이터 가격 표시
//            bodyCell = bodyRow.createCell(2);
//            bodyCell.setCellValue(expense.getResidentNo());
//            // 데이터 수량 표시
//            bodyCell = bodyRow.createCell(3);
//            bodyCell.setCellValue(expense.getBirthDate());
//            bodyCell = bodyRow.createCell(4);
//            bodyCell.setCellValue(expense.getSex());
//            bodyCell = bodyRow.createCell(5);
//            bodyCell.setCellValue(expense.getMartialStatus());
//            bodyCell = bodyRow.createCell(6);
//            bodyCell.setCellValue(expense.getYear());
//            bodyCell = bodyRow.createCell(7);
//            bodyCell.setCellValue(expense.getNumber());
//            bodyCell = bodyRow.createCell(8);
//            bodyCell.setCellValue(expense.getEmail());
//        }
//        
//        System.out.println("헤더셀"+headerCell);
//        System.out.println("헤더로우"+headerRow);
//        System.out.println("바디셀"+bodyRow);
//        System.out.println("바디로우"+bodyCell);
//        return workbook;
//	}
//	
//	@Override
//	public SXSSFWorkbook excelFileDownloadProcess(List<Expense> list) throws Exception {
//		return this.makeSimpleFruitExcelWorkbook(list);
//		
//	}
	
}
