package com.study.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.springboot.dto.SimpleBbsDto;

@Repository //이 클래스틑 디비 처리를 목적으로 하는 빈으로 등록한다는 어노테이션을 지정한다
public class SimpleBbsDao implements ISimpleBbsDao {

	//JdbcTemplate 변수를 자동 주입으로 만든다.
	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<SimpleBbsDto> listDao(){
		System.out.println("listDao()");
		
		String query ="select * from simple_bbs order by id desc";
		List<SimpleBbsDto> dtos = template.query(
				query,new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
				
		return dtos;
	}
	
	@Override
	public SimpleBbsDto viewDao(String id) {
		System.out.println("viewDao()");
		
		String query = "select * from simple_bbs where id= " + id;
		SimpleBbsDto dto = template.queryForObject(
				query,new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
		return dto;			
	}
	
	@Override
	public int writerDao(final String writer,final String title,final String content) {
		System.out.println("writeDao()");
		
		String query =
				"insert into simple_bbs (id, writer, title, content) " + " values (simple_bbs.id,?,?,?)";
		
		return template.update(query,writer,title,content);	 
	}
	
	@Override
	public int deleteDao(final String id) {
		System.out.println("deleteDao()");
		
		String query="delete from simple_bbs where id = ?";
		return template.update(query,Integer.parseInt(id));		
	}

}
