package com.mengke.web.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mengke.bean.MkUser1;
import com.mengke.mapper.banner.IBannerMapper;

@Controller
@RequestMapping("/admin/exam")
public class ExamController {
	@Autowired
	private IBannerMapper bannerMapper;
	
	@RequestMapping("/{cookie}")
	public String exam(@PathVariable("cookie")String cookie){
		return "exam/"+cookie;
	}
	
	@ResponseBody
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public String add(MkUser1 mkUser1){
		int num = bannerMapper.addUser(mkUser1);
		if(num>0){
			return "success";
		}else {
			return "fail";
		}
	}
}
