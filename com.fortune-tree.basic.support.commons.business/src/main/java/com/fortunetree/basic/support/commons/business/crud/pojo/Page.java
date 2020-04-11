package com.fortunetree.basic.support.commons.business.crud.pojo;

import java.util.List;

import com.fortunetree.basic.support.commons.business.mybatis.query.ConditionQuery;
import com.fortunetree.basic.support.commons.business.json.annotion.JsonData;

public class Page extends QueryPojo {
	// 每页条数
	private int rp = 10;
	// 当前第几页
	private int page = 1;
	// 查询集合
	private List<?> rows;
	// 总记录数
	private int total;

	public Page() {
	}

	public Page(Page page) {
		this.rp = page.rp;
		this.rows = page.rows;
		this.page = page.page;
		this.total = page.total;
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@JsonData
	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getFirstResult() {
		return ((page - 1) * rp);
	}

	@Override
	public ConditionQuery buildConditionQuery() {
		ConditionQuery query = super.buildConditionQuery();
		query.addParam("firstResult", this.getFirstResult());
		query.addParam("rp", this.getRp());
		return query;
	}
}
