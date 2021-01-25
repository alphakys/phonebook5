package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PhoneVo;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {
	
	//필드
	@Autowired
	private PhoneDao pd;
	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		
		List<PhoneVo> list = pd.getPhoneList();
		
		model.addAttribute("pList", list);
		
		
		return "list";
		
	}
	
	
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {		
		
		
		return "writeForm";
	}
	
	

	
	
	@RequestMapping(value="/insert", method={RequestMethod.GET, RequestMethod.POST})
	public String insert(@RequestParam ("name") String name, 
						 @RequestParam ("hp")	String hp,
						 @RequestParam ("company")	String company) {
		
		pd.insert(new PhoneVo(name, hp, company));
		
		
		return "redirect:/phone/list";
	}

	
	
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("personId") int personId) {
		
		pd.delete(personId);
		
		
		return "redirect:/phone/list";
	}
	
	
	
	@RequestMapping(value="/updateForm", method={RequestMethod.GET, RequestMethod.POST})
	public String updateForm(@RequestParam("personId") int id, Model model) {
		
		PhoneVo pv = pd.getPerson(id);
		
		model.addAttribute("updatePerson", pv);
		
		return "updateForm";
	}
	

	
	// modelAttribute를 쓰기 위해선 Vo의 setter의 네이밍과 파라미터의 이름이 동일해야한다. 
	@RequestMapping(value="/update", method={RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PhoneVo pVo) {
		
		pd.update(pVo);
				
		return "redirect:/phone/list";
					
	}
	
}
