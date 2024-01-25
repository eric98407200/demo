package com.example.demo.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Notice;
import com.example.demo.service.NoticeService;

/**
 * CRUD 增刪改查 DEMO
 * --------------------------------------------------
 * C : Create (POST)
 * R : Read   (GET)
 * U : Update (PUT)
 * D : Delete (DELETE)
 */

@RestController
@RequestMapping("/api/v99")
public class NoticeApi {

	@Autowired
	private NoticeService noticeService;
	
	//取得全部Notice
	@GetMapping("/notice")
	public List<Notice> getAll(){
		return noticeService.findAll();
	}
	
	//依ID取得單筆Notice
	@GetMapping("/notice/{id}")
	public Optional<Notice> getOne(@PathVariable Long id){
		System.out.println(noticeService.findOne(id));
		return noticeService.findOne(id);
	}
	
	//新增單筆Notice
	@PostMapping("/notice")
	public Notice add(@RequestParam String title, @RequestParam String content, @RequestParam String user, @RequestParam int status) {
		//取得現在日期現在
        Date currentDate = new Date();
        //設定格式 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式轉換
        String cDateTime = dateFormat.format(currentDate);
        
		Notice notice = new Notice();
		notice.setId(0);
		notice.setTitle(title);
		notice.setContent(content);
		notice.setCreateTime(cDateTime);
		notice.setUpdateTime(cDateTime);
		notice.setUser(user);
		notice.setStatus(status);
		
		return noticeService.add(notice);
	}
	
	//依ID更新單筆Notice
	@PutMapping("/notice/{id}")
	public int updateById(@PathVariable Long id, @RequestParam String title, @RequestParam String content){
		//取得現在日期現在
        Date currentDate = new Date();
        //設定格式 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式轉換
        String updateTime = dateFormat.format(currentDate);
        
		return noticeService.updateById(id, title, content, updateTime);
	}
	
	//依ID更新單筆Notice_status
	@PutMapping("/notice/{id}/status")
	public int updateById(@PathVariable Long id, @RequestParam int status){
		//取得現在日期現在
		Date currentDate = new Date();
		//設定格式 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//格式轉換
		String updateTime = dateFormat.format(currentDate);
		
		return noticeService.updateStatusById(id, status, updateTime, "eric");
	}
	
	//依ID刪除單筆Notice
	@DeleteMapping("/notice/{id}")
	public String deleteById(@PathVariable Long id){
		
		int resultCode = 0; 
		String resultStr = "刪除成功 ID : " + id.toString();
		
		try {
			resultCode = noticeService.deleteById(id, "eric");
			if(resultCode == 0) {
				resultStr = "刪除失敗 : 未找到此筆資料";
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultStr = "刪除異常" + e.getMessage();
		}
		
		return resultStr;
	}
	
	
//	@PostMapping("/notices/by")
//	public List<Notice> findByAuthor(@RequestParam String author){
//		return noticeService.findNoticeByAuthor(author);
//	}
	
	
//	@PostMapping("/notices/AS")
//	public List<Notice> findBy(@RequestParam String content){
//		return noticeService.findNoticeByContentContaining(content);
//	}
	
	@PostMapping("/notice/JPQL")
	public List<Notice> findByJPQL(@RequestParam String str){
		return noticeService.findByJPQL(str);
	}
	
	@PostMapping("/notice/SQL")
	public List<Notice> findBySQL(@RequestParam int len){
		return noticeService.findBySQL(len);
	}
	
}
