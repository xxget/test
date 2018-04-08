package com.lding.wiqs.general.business.service.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.lding.wiqs.general.business.service.IService;

/**
 * 服务层标准抽象类
 * 实现单表的增删改查，分页
 * 2016-07-22
 * 
 * @author xxg
 *
 * @param <T>
 */
public abstract class BaseService<T> implements IService<T> {
	
	Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    /**
     * 插入一条数据
     */
    public int insert(T entity) {
        return mapper.insert(entity);
    }

    public int deleteByKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    public int updateAllByKey(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    public int updateNotNullByKey(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    public List<T> readByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public List<T> readAllRows(){
    	return mapper.selectAll();
    }

    public List<T> readByEqualNotNull(T entity) {
        Example example = new Example(entity.getClass());
        Example.Criteria criteria = example.createCriteria();
        
    	Field[] fields = entity.getClass().getDeclaredFields();
    	for(Field field:fields) {
    		field.setAccessible(true);
    		String name = field.getName();
    		Object value = null;
			try {
				value = field.get(entity);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
    		if(value != null ) {
    			criteria.andEqualTo(name, value);
    		}
    	}
    	return readByExample(example);
    }    

    public List<T> selectByExample(Object example,int page,int rows) {
        PageHelper.startPage(page, rows);
        return mapper.selectByExample(example);
    }
    
    public List<T> selectByEqualNotNull(T entity,int page,int rows) {
        Example example = new Example(entity.getClass());
        Example.Criteria criteria = example.createCriteria();
        
    	Field[] fields = entity.getClass().getDeclaredFields();
    	for(Field field:fields) {
    		field.setAccessible(true);
    		String name = field.getName();
    		Object value = null;
			try {
				value = field.get(entity);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
    		if(value != null ) {
    			if(value instanceof Integer) {
    				if( (Integer)value != 0 ) criteria.andEqualTo(name, value);
    			}else {
        			criteria.andEqualTo(name, value);
    			}
    		}
    	}
    	return selectByExample(example, page, rows);
    }
    
    public List<T> selectAllRows(int page,int rows){
    	PageHelper.startPage(page, rows);
    	return mapper.selectAll();
    }
    /**
     * 根据非空属性读取（值取相等）,按照指定列排序
     * @param entity
     * @return
     */
    public List<T> readByEqualNotNull(T entity,String orderColumn){
        Example example = new Example(entity.getClass());
        Example.Criteria criteria = example.createCriteria();
        
    	Field[] fields = entity.getClass().getDeclaredFields();
    	for(Field field:fields) {
    		field.setAccessible(true);
    		String name = field.getName();
    		Object value = null;
			try {
				value = field.get(entity);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
    		if(value != null ) {
    			criteria.andEqualTo(name, value);
    		}
    	}
    	String[] orderColumns = orderColumn.split(",");
    	for(int i=0;i<orderColumns.length;i++) {
    		if(orderColumns[i].trim().length()>1) {
    			example.orderBy(orderColumns[i].trim());
    		}
    	}
    	return readByExample(example);
    }

    /**
     * 根据非空属性分页读取
     * @param entity
     * @param page
     * @param rows
     * @return
     */

    public List<T> selectByEqualNotNull(T entity,String orderColumn,int page,int rows){
        Example example = new Example(entity.getClass());
        Example.Criteria criteria = example.createCriteria();
        
    	Field[] fields = entity.getClass().getDeclaredFields();
    	for(Field field:fields) {
    		field.setAccessible(true);
    		String name = field.getName();
    		Object value = null;
			try {
				value = field.get(entity);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
    		if(value != null ) {
    			criteria.andEqualTo(name, value);
    		}
    	}
    	String[] orderColumns = orderColumn.split(",");
    	for(int i=0;i<orderColumns.length;i++) {
    		if(orderColumns[i].trim().length()>1) {
    			example.orderBy(orderColumns[i].trim());
    		}
    	}
    	return selectByExample(example, page, rows);
    }
}
