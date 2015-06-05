package com.pb.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pb.bean.MainBean;
import com.pb.dao.MainDao;

@Controller("/pbMain")
public class MainController {

	@RequestMapping(method = RequestMethod.POST, value="/sendBadgeLink.do")
	public ModelAndView sendBadgeLink(@ModelAttribute("mainBean")MainBean mainBean) {
		if(mainBean != null) {
			String key = MainDao.createBadgeLinkAndSendEmail(mainBean);
			try {
				new MainDao().saveFormAsProperty(key, mainBean);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("success");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/authenticate.do")
	public ModelAndView authenticate(@RequestParam("code") String code) throws Exception {
		ModelAndView mav = new ModelAndView();
		boolean result = new MainDao().validateAuthenticationCode(code);
		if(result) {
			mav.setViewName("success");
		} else {
			mav.setViewName("failure");
		}
		return mav;
	}
}
