package com.fortunetree.basic.support.commons.business.util;

import org.springframework.util.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("rawtypes")
public class CollectionUtil {

	public static boolean isEmpty(Collection collection) {
		return (null == collection || collection.isEmpty());
	}

	public static boolean isEmpty(Map map) {
		return (null == map || map.isEmpty());
	}

	public static boolean isNotEmpty(Collection collection) {
		return !isEmpty(collection);
	}

	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

	public static boolean isEmpty(Object[] objArray) {
		return (null == objArray || objArray.length == 0);
	}

	public static boolean isNotEmpty(Object[] objArray) {
		return !isEmpty(objArray);
	}

	/**
	 * 向集合中添加不为空的字符串
	 * 
	 * @param str
	 * @param list
	 */
	public static void addNotEmptyStr(String str, List<String> list) {
		if (StringUtil.isNullStr(str)) {
			return;
		}
		list.add(str);
	}

	/**
	 * 向集合中添加不为空的值
	 * 
	 * @param obj
	 * @param list
	 */
	public static void addNotEmptyVal(Object obj, List<Object> list) {
		if (null == obj) {
			return;
		}
		list.add(obj);
	}

	/**
	 * 判断字符窜是否在集合中
	 * 
	 * @param str
	 * @param list
	 * @return
	 */
	public static boolean strInList(String str, List<String> list) {
		if (CollectionUtil.isEmpty(list)) {
			return false;
		}
		if (null == str) {
			return false;
		}
		for (String listStr : list) {
			if (str.equals(listStr)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 数组转List
	 * @param objArray
	 * @param <D>
	 * @return
	 */
	public static <D> List<D> array2List(D[] objArray){
		Assert.isTrue(!CollectionUtil.isEmpty(objArray), "数组转换集合，参数不能为空");
		return Stream.of(objArray).collect(Collectors.toList());
	}

	public static <D> List<D> subList(List<D> list, int start, int end){
		Assert.isTrue(!CollectionUtil.isEmpty(list), "集合不能为空");
		AssertUtils.isFalse(start<0 || end<0 || end<start, "Index 错误，start cannot be larger than end ");
		AssertUtils.isFalse(end>=list.size(), "Index 错误，start cannot be larger than end ");
		List<Object> result = new ArrayList<>(16);
		IntStream.range(start, end).forEach(i->{
			Object object = new Object();
			object = (Object) list.get(i);
			result.add(object);
		});
		return (List<D>) result;
	}

	public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);

		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		@SuppressWarnings("unchecked")
		List<T> dest = (List<T>) in.readObject();
		return dest;
	}
}
