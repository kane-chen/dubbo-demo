package cn.kane.service;

import cn.kane.entity.MultiTypeEntity;

public class ReturnMultiServiceImpl implements IReturnMultiService {

	public MultiTypeEntity getMultiTypeEntity() {
		MultiTypeEntity entity = new MultiTypeEntity() ;
		entity.setId(1L);
		entity.setName("name");
		return entity;
	}

}
