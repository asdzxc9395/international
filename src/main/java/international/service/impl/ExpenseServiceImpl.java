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
	
	@Override
	  public List<Expense> search(HashMap<String, Object> params) throws Exception {
	    return expenseDao.findByKeyword(params);
	  }

	@Override
	public List<Expense> listPage(Criteria cri) throws Exception {
		return expenseDao.listPage(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) throws Exception {
		return expenseDao.getTotalCount(cri);
	}
	
	@Override
	public SXSSFWorkbook makeSimpleFruitExcelWorkbook(List<Expense> list) throws Exception {
		SXSSFWorkbook workbook = new SXSSFWorkbook();
        // 시트 생성
        SXSSFSheet sheet = workbook.createSheet("기본정보");
        
        //시트 열 너비 설정
        sheet.setColumnWidth(0, 1500);
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(0, 1500);
        
        // 헤더 행 생
        Row headerRow = sheet.createRow(0);
        // 해당 행의 첫번째 열 셀 생성
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("번호");
        // 해당 행의 두번째 열 셀 생성
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("사용일");
        // 해당 행의 세번째 열 셀 생성
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("사용내역");
        // 해당 행의 네번째 열 셀 생성
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("사용금액");
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("승인금액");
        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("처리상태");
        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("등록일");

        
        // 과일표 내용 행 및 셀 생성
        Row bodyRow = null;
        Cell bodyCell = null;
        for(int i=0; i<list.size(); i++) {
            Expense expense = list.get(i);
            // 행 생성
            bodyRow = sheet.createRow(i+1);
            // 데이터 번호 표시
            bodyCell = bodyRow.createCell(0);
            bodyCell.setCellValue(i + 1);
            // 데이터 이름 표시
            bodyCell = bodyRow.createCell(1);
            bodyCell.setCellValue(expense.getUseDate().toString());
            // 데이터 가격 표시
            bodyCell = bodyRow.createCell(2);
            bodyCell.setCellValue(expense.getName());
            // 데이터 수량 표시
            bodyCell = bodyRow.createCell(3);
            bodyCell.setCellValue(expense.getUsePrice());
            bodyCell = bodyRow.createCell(4);
            bodyCell.setCellValue(expense.getApprovePrice());
            bodyCell = bodyRow.createCell(5);
            bodyCell.setCellValue(expense.getProcessStatus());
            bodyCell = bodyRow.createCell(6);
            bodyCell.setCellValue(expense.getRegistrationDate().toString());
        }
        return workbook;
	}
	
	@Override
	public SXSSFWorkbook excelFileDownloadProcess(List<Expense> list) throws Exception {
		return this.makeSimpleFruitExcelWorkbook(list);
		
	}
	
}
