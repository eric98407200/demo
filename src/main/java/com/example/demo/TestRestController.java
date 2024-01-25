package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Book;

/*/@RequestMapping 可使用在方法(控制處理請求)或類上
 *
 *方法 : 可依照類型處理請求(不設定method 則支援所有類型請求)
 *如 : @RequestMapping(value = "/1" ,method = RequestMethod.XXX) 
 *可使用	@PostMapping("/1")、@GetMapping("/1")、@PutMapping("/1")等等簡寫達到上方相同效果
 *
 *類 : 可提供階層概念 
 *如 : 若新增@RequestMapping("/api")則在URL上需再方法請求前新增/api/1(Restful)
 *
 *@PathVariable
 *@GetMapping("/rcbooks/{id}/{name}")URL順序可換{name:[a-z]+}可加入正規表達式
 *需獲取參數時寫法getOne(@PathVariable long bid)，先輸入(可自訂)@PathVariable("id")後可任意更改參數名，若無則須一致 
 * 
 *@RequestParam
 *使用方法與@PathVariable差不多
 * (@RequestParam(value="xxx", defaultValue="10") String xxx)
 */


@RestController
@RequestMapping("/v1")
public class TestRestController {
	
//	@Autowired
//	private Book book;
	
	@RequestMapping("/1")
	public String hey() {
//		System.out.println(book.getName() + book.getAuthor() + book.getDescript());
		
		return "this is spring boot RestController";
	}
	
	@GetMapping("/rcbooks/{id}/{name}")
	public Object getOne(@PathVariable long id,@PathVariable String name) {
		
//		Map<Object, String> map = new HashMap<>();
//		map.put("1", "book1"+id+name);
//		map.put("2", "book1"+id+name+id);
//		//設定檔取值
//		map.put("3", "book2_" + this.name);
//		map.put("4", "book2_" + this.author);
//		map.put("5", "book2_" + this.descript);
		
		return null;
	}
	
	@PostMapping("/rcbooks22")
	public Object post(@RequestParam String xxx,@RequestParam String yyy,@RequestParam String zzz) {
		Map<String, Object> book = new HashMap<>();
		book.put("xxx", xxx);
		book.put("yyy", yyy);
		book.put("zzz", zzz);
		return book;
	}
	
	@GetMapping("/rcbooks")
	public Object getPageSize(@RequestParam int page, @RequestParam int size) {
		Map<String, Object> book = new HashMap<>();
		book.put("xxx", "xxxx");
		book.put("yyy", "yyyy");
		book.put("zzz", "zzzz");
		Map<String, Object> book1 = new HashMap<>();
		book1.put("xxx", "xxxx1");
		book1.put("yyy", "yyyy1");
		book1.put("zzz", "zzzz1");
		
		List<Map> contents = new ArrayList<>();
		contents.add(book);
		contents.add(book1);
		
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("page", page);
		pageMap.put("size", size);
		pageMap.put("contents", contents);
		
		return pageMap;
	}
	
}
