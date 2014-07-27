package cn.kane.service.impl;


import cn.kane.entity.MultiTypeEntity;
import cn.kane.service.IReturnMultiService;

public class ReturnMultiServiceImpl implements IReturnMultiService {

	public MultiTypeEntity getMultiTypeEntity() {
		MultiTypeEntity entity = new MultiTypeEntity() ;
		entity.setId(1L);
		entity.setName("name");
		return entity;
	}

}
