package com.fortunetree.basic.support.commons.business.crud.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fortunetree.basic.support.commons.business.crud.bo.SchBo;
import com.fortunetree.basic.support.commons.business.json.JsonClothProcessor;
import com.fortunetree.basic.support.commons.business.mybatis.query.condition.Condition;
import com.fortunetree.basic.support.commons.business.crud.pojo.BasePojo;
import com.fortunetree.basic.support.commons.business.crud.pojo.Grid;
import com.fortunetree.basic.support.commons.business.result.ResultMessage;
import com.fortunetree.basic.support.commons.business.crud.property.editor.DatePropertyEditor;
import com.fortunetree.basic.support.commons.business.result.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fortunetree.basic.support.commons.business.crud.dao.SchDao;

public class SchController<Pojo extends BasePojo, Bo extends SchBo<Pojo, ? extends SchDao<Pojo>>> {
	protected final Logger logger = LoggerFactory.getLogger(SchController.class);

	protected Bo bo;

	@Autowired
	private void setBo(Bo bo) {
		this.bo = bo;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new DatePropertyEditor(
				DatePropertyEditor.DATE_FORMAT));
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody
    ResultMessage handleException(Exception ex) {
		logger.error("Controller handleException",ex);
		return ResultMessage.error("操作失败，请返回重试！");
	}

	public Grid listGrid(Grid grid, List<Condition> appendConditions) {
		return new Grid(bo.list(grid, appendConditions));
	}

	public Grid listGrid(Grid grid, Condition appendCondition) {
		return new Grid(bo.list(grid, appendCondition));
	}

	public Grid listGrid(Grid grid) {
		return new Grid(bo.list(grid));
	}

	public ModelAndView list(Grid grid, List<Condition> appendConditions,
			JsonClothProcessor clothProcessor) {
		
		Grid listGrid =	this.listGrid(grid, appendConditions);
		listGrid.setGridMsg(grid.getGridMsg());
		listGrid.setSortname(grid.getSortname());
		listGrid.setSortorder(grid.getSortorder());
		
		return ResultUtil.success(listGrid,
				clothProcessor);
	}

	public ModelAndView list(Grid grid, Condition appendCondition,
			JsonClothProcessor clothProcessor) {
		
		Grid listGrid =this.listGrid(grid, appendCondition);
		listGrid.setGridMsg(grid.getGridMsg());
		listGrid.setSortname(grid.getSortname());
		listGrid.setSortorder(grid.getSortorder());
		
		return ResultUtil.success(listGrid,
				clothProcessor);
	}

	public ModelAndView list(Grid grid, JsonClothProcessor clothProcessor) {
		
		Grid listGrid =this.listGrid(grid);
		listGrid.setGridMsg(grid.getGridMsg());
		listGrid.setSortname(grid.getSortname());
		listGrid.setSortorder(grid.getSortorder());
		
		return ResultUtil.success(listGrid, clothProcessor);
	}

	public ModelAndView list(Grid grid, List<Condition> appendConditions) {
		
		Grid listGrid =this.listGrid(grid, appendConditions);
		listGrid.setGridMsg(grid.getGridMsg());
		listGrid.setSortname(grid.getSortname());
		listGrid.setSortorder(grid.getSortorder());
		
		return ResultUtil.success(listGrid);
	}

	public ModelAndView list(Grid grid, Condition appendCondition) {
		
		Grid listGrid =this.listGrid(grid, appendCondition);
		listGrid.setGridMsg(grid.getGridMsg());
		listGrid.setSortname(grid.getSortname());
		listGrid.setSortorder(grid.getSortorder());
		
		return ResultUtil.success(listGrid);
	}

	public ModelAndView list(Grid grid) {
		
		Grid listGrid =this.listGrid(grid);
		listGrid.setGridMsg(grid.getGridMsg());
		listGrid.setSortname(grid.getSortname());
		listGrid.setSortorder(grid.getSortorder());
		
		return ResultUtil.success(listGrid);
	}

}
