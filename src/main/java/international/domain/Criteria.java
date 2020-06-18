package international.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class Criteria {
	private int page;
	private int perPageNum;
	private String searchType;
	private String nameType;
	private String regType;
	private String proType;
	private String keyword;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 5;
		this.searchType = null;
		this.keyword = null;
	}
	
	public String makeQuery() {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", this.perPageNum);
				
		if (searchType!=null) {
			uriComponentsBuilder
					.queryParam("searchType", this.searchType)
					.queryParam("keyword", this.keyword);
		}
		if (nameType != null) {
			uriComponentsBuilder
				.queryParam("nameType", this.getNameType())
				.queryParam("keyword", this.getKeyword());
		}
		if (regType != null) {
			uriComponentsBuilder
				.queryParam("regType", this.getRegType())
				.queryParam("keyword", this.getKeyword());
		}
		if (proType != null) {
			uriComponentsBuilder
				.queryParam("proType", this.getProType())
				.queryParam("keyword", this.getKeyword());
		}
		return uriComponentsBuilder.build().encode().toString();
	}
    
	//pageStart를 반환
	public int getPageStart() {
		return (this.page - 1)*perPageNum;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
		}else {
			this.page = page;
		}
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <=0 || perPageNum > 100) {
			this.perPageNum = 5;
		}else {
			this.perPageNum = perPageNum;
		}
	}
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getNameType() {
		return nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	public String getRegType() {
		return regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", searchType=" + searchType + ", nameType="
				+ nameType + ", regType=" + regType + ", proType=" + proType + ", keyword=" + keyword + "]";
	}
	

}
