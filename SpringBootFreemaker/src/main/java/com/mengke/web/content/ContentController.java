package com.mengke.web.content;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mengke.bean.Content;
import com.mengke.bean.Param;
import com.mengke.bean.Params;
import com.mengke.mapper.banner.IBannerMapper;
import com.mengke.mapper.content.IContentmapper;
@Controller
@RequestMapping("/contents")
public class ContentController {
	@Autowired
	private IContentmapper iContentmapper;
	
	/**
	 * 
	 * 查询内容<br/>
	 * 方法名：content<br/>
	 * 创建人：WXQ <br/>
	 * 时间：2017年11月8日-下午9:21:11 <br/>
	 * 手机:1564545646464<br/>
	 * @param params
	 * @return ModelAndView<br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	 */
	@RequestMapping("/content")
	public ModelAndView content(Params param){
		ModelAndView modelAndView = new ModelAndView();
		List<HashMap<String,Object>> contents=iContentmapper.findContent(param);
		modelAndView.addObject("contents", contents);
		modelAndView.setViewName("content/content");
		return modelAndView;
	}
}
