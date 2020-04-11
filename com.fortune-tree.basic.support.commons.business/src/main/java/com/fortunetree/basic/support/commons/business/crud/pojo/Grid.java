package com.fortunetree.basic.support.commons.business.crud.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fortunetree.basic.support.commons.business.json.annotion.NotJsonData;
import com.fortunetree.basic.support.commons.business.mybatis.query.ConditionQuery;

import java.util.List;

@JsonIgnoreProperties(value = { "sortname", "sortorder" })
public class Grid extends Page {
	private Integer code;
	private String gridMsg;
	private String sortname;
	private String sortorder;

	public Grid() {
	}

	public Grid(Page page) {
		super(page);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getGridMsg() {
		return gridMsg;
	}

	public void setGridMsg(String gridMsg) {
		this.gridMsg = gridMsg;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	@NotJsonData
	public String getSortname() {
		return this.sortname;
	}

	@NotJsonData
	public String getSortorder() {
		return this.sortorder;
	}

	protected String buildSortname() {
		String ret = this.sortname;
		if (ret == null) {
			return null;
		}
		ret = ret.replaceAll("'", "");
		ret = ret.replaceAll("\\*", "");
		ret = ret.replaceAll("--", "");
		ret = ret.replaceAll(" ", "");
		ret = ret.replaceAll("\\(", "");
		ret = ret.replaceAll("\\)", "");
		if (ret.length() > 20) {
			ret = ret.substring(0, 20);
		}
		return ret;
	}

	protected String buildSortorder() {
		if (this.sortorder == null) {
			return null;
		}
		this.sortorder = this.sortorder.toLowerCase();
		if ("asc".equals(this.sortorder) || "desc".equals(this.sortorder)) {
			return this.sortorder;
		}
		return null;
	}

	public String buildOrderCol() {
		String name = buildSortname();
		if ((name == null) || ("".equals(name))) {
			return null;
		}
		StringBuilder ret = new StringBuilder();
		ret.append(name);
		String order = buildSortorder();
		if ((order != null) && (!"".equals(order))) {
			ret.append(" ");
			ret.append(order);
		}
		return ret.toString();
	}

	@Override
	public ConditionQuery buildConditionQuery() {
		ConditionQuery query = super.buildConditionQuery();
		String orderCol = buildOrderCol();
		if (null != orderCol) {
			query.addParam("orderCol", orderCol);
		}
		return query;
	}

	public void convert(int code, String msg){
		this.setCode(code);
		this.setGridMsg(msg);
	}

	public void convert(int code, String msg, List<?> rows, int count){
		this.setCode(code);
		this.setGridMsg(msg);
		this.setRows(rows);
		this.setTotal(count);
	}

}
