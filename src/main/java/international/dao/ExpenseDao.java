package international.dao;

import java.util.List;
import java.util.Map;

import international.domain.Criteria;
import international.domain.Expense;

public interface ExpenseDao {
	public int insert(Expense expense) throws Exception;
	
	public int update(Expense expense) throws Exception;
	
	public int delete(int no) throws Exception;
	
	public List<Expense> findAll() throws Exception;
	
	public Expense findByNo(int no) throws Exception;
	
	List<Expense> findByKeyword(Map<String, Object> params) throws Exception;
//
	List<Expense> listPage(Criteria cri) throws Exception;
	
	public int getTotalCount(Criteria cri) throws Exception;
	
}
