package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Notice;
import com.example.demo.domain.NoticeRepository;


@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	//獲取全部Notice
	public List<Notice> findAll(){
		return noticeRepository.findAll();
	}
	
	//透過ID獲取單筆Notice
	public Optional<Notice> findOne(Long id){
		return noticeRepository.findById(id);
	}
	
	//透過ID獲取單筆Notice
	public Notice getNoticeById(Long id) {
		return noticeRepository.getNoticeById(id);
	}
	
	public List<Notice> getAllNoticeByUser(String user) {
		return noticeRepository.getAllNoticeByUser(user);
	}
	
	//新增單筆Notice
	public Notice add(Notice notice) {
		return noticeRepository.save(notice);
	}
	
	//依照條件獲取單/多筆Notice
	//透過JPA原生語句查詢
	public List<Notice> findNoticeByTitleOrAndContent(String title, String content){
		return noticeRepository.findByTitleAndContent(title, content);
	}
	
	//SQL依照條件更新單筆Notice
	//使用modify、delete時多個transaction可用@Transactional
	@Transactional
	public int updateById(Long id, String title, String content, String updateTime){
		return noticeRepository.updateById(id, title, content, updateTime);
	}
	
	//SQL依照條件刪除單筆Notice
	@Transactional
	public int deleteById(Long id, String user){
		return noticeRepository.runDeleteById(id, user);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int updateStatusById(Long id, int status, String updateTime, String user){
		return noticeRepository.updateStatusById(id, status, updateTime, user);
	}
	
	//依照條件獲取單/多筆Notice
	@Transactional(readOnly = true)
	public List<Notice> findNoticeByTitle(String title, String user){
		return noticeRepository.findNoticeByTitle(title, user);
	}
	
	//依照條件獲取單/多筆Notice
	public List<Notice> findNoticeByContentContaining(String content){
		return noticeRepository.findNoticeByContentContaining(content);
	}
	
	//JPQL自定義查詢(使用:XXX或?數字來呼叫)
	public List<Notice> findByJPQL(String str){
		return noticeRepository.findByJPQL(str);
	}
	
	//SQL自定義使用方式(使用:XXX或?數字來呼叫)
	public List<Notice> findBySQL(int len){
		return noticeRepository.findBySQL(len);
	}
}
