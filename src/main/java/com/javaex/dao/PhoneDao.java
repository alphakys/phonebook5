package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PhoneVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;
	
	//전체 리스트 가져오기
	public List<PhoneVo> getPhoneList(){
		
		List<PhoneVo> pList = sqlSession.selectList("phonebook.selectList2");
						
		return pList;
	}
	
	public void insert(PhoneVo pVo) {
		
		sqlSession.insert("phonebook.insert", pVo);
		
		
	}
	
	
	public void delete(int personId) {
		sqlSession.delete("phonebook.delete", personId);
	}
	
	
	//1명 가져오기
	public PhoneVo getPerson(int personId) {
		
		PhoneVo pVo = sqlSession.selectOne("phonebook.selectOne", personId);
		
		return pVo;
	}
	
	
	public void update(PhoneVo pVo) {
		
		sqlSession.update("phonebook.update", pVo);
		
	}
	
	
	
	
	
}
