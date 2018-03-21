/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.lding.wiqs.general.business.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用接口
 */
@Service
public interface IService<T> {

	/**
	 * 主键读取
	 * @param key
	 * @return
	 */
    T selectByKey(Object key);

    /**
     * 新建/插入
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 主键删除
     * @param key
     * @return
     */
    int deleteByKey(Object key);

    /**
     * 主键更新所有属性
     * @param entity
     * @return
     */
    int updateAllByKey(T entity);

    /**
     * 主键更新非空属性
     * @param entity
     * @return
     */
    int updateNotNullByKey(T entity);
    
    /**
     * 读取所有记录
     * @return
     */
    List<T> readAllRows();
    

    /**
     * 自定义条件读取
     * @param example
     * @return
     */
    List<T> readByExample(Object example);

    /**
     * 根据非空属性读取（值取相等）,按照指定列排序
     * @param entity
     * @return
     */
    List<T> readByEqualNotNull(T entity,String orderColumn);

    /**
     * 根据非空属性读取（值取相等）
     * @param entity
     * @return
     */
    List<T> readByEqualNotNull(T entity);

    /**
     * 分页读取所有记录
     * @return
     */
    List<T> selectAllRows(int page,int rows);

    /**
     * 自定义条件分页读取
     * @param example
     * @param page
     * @param rows
     * @return
     */
    List<T> selectByExample(Object example,int page,int rows);
    
    /**
     * 根据非空属性分页读取
     * @param entity
     * @param page
     * @param rows
     * @return
     */
    List<T> selectByEqualNotNull(T entity,String orderColumn,int page,int rows);

    /**
     * 根据非空属性分页读取
     * @param entity
     * @param page
     * @param rows
     * @return
     */
    List<T> selectByEqualNotNull(T entity,int page,int rows);
    
}
