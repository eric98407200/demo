package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * 若在@Controller底下要使用@RestController請求
 * 則需要在方法上加@ResponseBody(實體返回JSON格式)
 * */


@Controller
@RequestMapping("/v2")
public class TestController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/cbooks")
	@ResponseBody
	public Object books() {
		Map<Object, String> map = new HashMap<>();
		map.put("1", "book1");
		map.put("2", "book2");
		return map;
	}
}
