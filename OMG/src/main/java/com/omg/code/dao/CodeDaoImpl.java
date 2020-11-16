package com.omg.code.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omg.cmn.StringUtil;
import com.omg.code.domain.Code;

@Repository
public class CodeDaoImpl {
	final Logger  LOG = LoggerFactory.getLogger(this.getClass());
	
	private final String NAMESPACE = "com.sist.ehr.code";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 
	 * @param codeList :"PAGE_SIZE","BOARD_CONDITION"
	 * @return List<Code>
	 */
	public List<Code> doSelectList(String codeList){
		LOG.debug("==============");
		LOG.debug("=doSelectList=");
		LOG.debug("==============");
	
		String statement = NAMESPACE +".doSelectList";
		LOG.debug("=statement="+statement);
		LOG.debug("=codeList="+codeList);
		
		//Map param
		List<String> list = StringUtil.makeForeach(codeList,","); 
		Map<String, Object>  seachCodeMap = new HashedMap<String, Object>();
		seachCodeMap.put("codeList", list);
		
		List<Code> lists = this.sqlSessionTemplate.selectList(statement, seachCodeMap);
		
		for(Code vo:lists) {
			LOG.debug("=vo="+vo);
		}
		
		return lists;
	}
	
	public Code doSelectOne(Code code)
	{
		LOG.debug("===========================");
		LOG.debug("=doSelectOne=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doSelectOne";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=code : "+code);
		LOG.debug("===========================");
		
		Code outVO = this.sqlSessionTemplate.selectOne(statement, code);
		
		return outVO;
	}
	

   
	
	

	
	
	
	
	
	
	
	
}

