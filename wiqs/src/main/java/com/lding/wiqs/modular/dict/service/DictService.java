package com.lding.wiqs.modular.dict.service;

import java.util.List;

import com.lding.wiqs.general.business.service.IService;
import com.lding.wiqs.modular.dict.domain.Dict;

public interface DictService extends IService<Dict> {
	/**
	 * <p>Title: readByType</p>  
	 * <p>Description: 根据字典类型查询字典信息</p>  
	 * @param dictType
	 * @return
	 */
	List<Dict> readByType(String dictType);

}
