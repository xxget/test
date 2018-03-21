package com.lding.wiqs.modular.dict.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lding.wiqs.general.business.service.impl.BaseService;
import com.lding.wiqs.modular.dict.domain.Dict;
import com.lding.wiqs.modular.dict.service.DictService;

import tk.mybatis.mapper.entity.Example;

@Service("DictService")
public class DictServiceImpl extends BaseService<Dict> implements DictService {
	
	/**
	 * 根据字典类型查询字典信息
	 */
	@Override
	public List<Dict> readByType(String dictType) {
        Example example = new Example(Dict.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("dictType",dictType);
        example.orderBy("dictType").asc();
    	return readByExample(example);
	}
}
