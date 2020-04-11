package com.fortunetree.basic.support.commons.business.crud.property.editor;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import com.fortunetree.basic.support.commons.business.util.DateUtil;
import com.fortunetree.basic.support.commons.business.util.StringUtil;

/**
 * 对于需要转换为Date类型的属性，使用DateEditor进行处理
 */
public class DatePropertyEditor extends PropertyEditorSupport {
	public static final DateFormat DATE_FORMAT = DateUtil.DEFAULT_DATEFORMAT;
	public static final DateFormat TIME_FORMAT = DateUtil.FORMAT_YMDHMS;
	private DateFormat dateFormat;

	public DatePropertyEditor() {
	}

	public DatePropertyEditor(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * Parse the Date from the given text, using the specified DateFormat.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtil.isNullStr(text)) {
			setValue(null);
			return;
		}
		try {
			if (this.dateFormat != null) {
				setValue(this.dateFormat.parse(text));
			} else {
				if (text.contains(":")) {
					setValue(TIME_FORMAT.parse(text));
				} else {
					setValue(DATE_FORMAT.parse(text));
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		DateFormat dateFormat = this.dateFormat;
		if (dateFormat == null) {
			dateFormat = TIME_FORMAT;
		}
		return (value != null ? dateFormat.format(value) : "");
	}
}