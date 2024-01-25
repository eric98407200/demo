package com.example.demo.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Notice;
import com.example.demo.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;

	//依照使用者查出全部待辦
	@GetMapping("/notice/{user}")
	public String AllNoticeList(@PathVariable String user, Model model) {
		List<Notice> noticeList = noticeService.getAllNoticeByUser(user);
		if(noticeList.isEmpty()) {
			noticeList = new ArrayList<>();
		}
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("user", user);
		return "notice";
	}

	//查出全部待辦	
	@GetMapping("/notice")
	public String getNotice(Model model) {
		List<Notice> noticeList = noticeService.findAll();
		model.addAttribute("noticeList", noticeList);
		return "notice";
	}
	
	//依照標題及基本條件查出單/多待辦	
	@PostMapping("/notice/search")
	public String getNotice(@RequestParam String searchStr, @RequestParam String user, Model model) {
		List<Notice> noticeList = new ArrayList<>();
		if(searchStr != "") {
			noticeList = noticeService.findNoticeByTitle(searchStr, user);
		}else {
			noticeList = noticeService.getAllNoticeByUser(user);
		}
		model.addAttribute("noticeList", noticeList);
		return "notice :: response";
	}
	
	//新增單筆Notice
	@PostMapping("/notice")
	public String add(@RequestParam String title, @RequestParam String content, @RequestParam String user, Model model) {
		//取得現在日期現在
        Date currentDate = new Date();
        //設定格式 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式轉換
        String cDateTime = dateFormat.format(currentDate);
        
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setCreateTime(cDateTime);
		notice.setUpdateTime(cDateTime);
		notice.setUser(user);
		notice.setStatus(1);
		
		Notice n = noticeService.add(notice);
		
		List<Notice> noticeList = noticeService.getAllNoticeByUser(user);
		model.addAttribute("noticeList", noticeList);
		return "notice :: response";
	}
	
	//依ID更新單筆Notice_status
	@PutMapping("/notice/{id}")
	public String updateById(@PathVariable Long id, @RequestParam int status, @RequestParam String user, Model model){
		//取得現在日期現在
		Date currentDate = new Date();
		//設定格式 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//格式轉換
		String updateTime = dateFormat.format(currentDate);
		//狀態變更
		status = status == 0 ? 1 : 0;
		//DB處裡
		noticeService.updateStatusById(id, status, updateTime, user);
		
		List<Notice> noticeList = noticeService.getAllNoticeByUser(user);
		model.addAttribute("noticeList", noticeList);
		return "notice :: response";		
	}
	
	//依ID刪除單筆Notice
	@DeleteMapping("/notice/{id}")
	public String deleteById(@PathVariable Long id, @RequestParam String user, Model model){
		
		int resultCode = 0; 
		String resultStr = ""; 
		
		try {
			resultCode = noticeService.deleteById(id, user);
			if(resultCode == 0) {
				resultStr = "刪除失敗 : 未找到此資料";
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultStr = "刪除異常" + e.getMessage();
		}
		
		System.out.println(resultStr);

		List<Notice> noticeList = noticeService.getAllNoticeByUser(user);
		model.addAttribute("noticeList", noticeList);
		return "notice :: response";	
	}
	
}
