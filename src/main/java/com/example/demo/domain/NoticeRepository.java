package com.example.demo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/*
 * JPA 原生語句皆以findBy為開頭後面接條件 例: findByXXXStartLike 
 * 可使用各式@XXXX來定義SQL语句(更新)
 * */
public interface NoticeRepository extends JpaRepository<Notice, Long>{
	
	//@Transactional() timeout=秒、readOnly=boolean、@Modifying運行會要求加上@Transactional的TAG避免資料錯誤
	
	@Modifying
	@Query(value= "UPDATE Notice SET title = ?2, content = ?3, update_time = ?4  WHERE id = ?1", nativeQuery = true)
	public int updateById(Long id, String title, String content, String updateTime);
	
	@Modifying
	@Query(value= "UPDATE Notice SET status = ?2, update_time = ?3  WHERE id = ?1 and user = ?4", nativeQuery = true)
	public int updateStatusById(Long id, int status, String updateTime, String user);

	@Modifying
	@Query(value= "DELETE FROM Notice WHERE id = ?1 AND user = ?2", nativeQuery = true)
	public int runDeleteById(Long id, String user);
	
	//查詢----------------------------------------------------------------
	
	//List<Notice> findNoticeByContentStartingWith(String content);
	//List<Notice> findNoticeByContentEndingWith(String content);
	List<Notice> findNoticeByContentContaining(String content);
	
	@Query(value = "select * from notice where id = ?1", nativeQuery = true)
	Notice getNoticeById(Long id);
	
	@Query(value="select * from notice where user = ?1 and status != 4", nativeQuery = true)
	public List<Notice> getAllNoticeByUser(String string);
	
	@Query(value="select * from notice where title like %?1% and status != 4 and user = ?2",nativeQuery=true)
	List<Notice> findNoticeByTitle(String title, String user);
	
	List<Notice> findByTitleAndContent(String title, String content);
	 
	//JPQL、SQL自定義更新(使用:XXX或?數字來呼叫) PS:適用於不同資料庫語言
	@Query("SELECT n FROM Notice n where n.title LIKE %?1%")
	List<Notice> findByJPQL(String str);
	
	@Query(value = "SELECT * FROM Notice where length(title) > :len", nativeQuery = true)
	List<Notice> findBySQL(int len);

	 
}
