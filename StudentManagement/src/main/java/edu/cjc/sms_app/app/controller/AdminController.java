package edu.cjc.sms_app.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cjc.sms_app.app.Model.Student;
import edu.cjc.sms_app.app.serviceI.StudentServiceI;

@Controller
public class AdminController {
	@Autowired
	StudentServiceI ssi;

	@RequestMapping("/")
	public String prelogin() {
		return "login";
		
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam("username")String username,@RequestParam("password")String password,Model m) {
		
		if(username.equals("admin") && password.equals("admin")) {
			List<Student>students=ssi.getAllStudents();
			m.addAttribute("data", students);
		return "adminscreen";
	}else {
		m.addAttribute("msg", "invalid User ?");
		return "login";
	}	
}
	@RequestMapping("/enroll_student")
	public String saveStudent(@ModelAttribute Student s,Model m) {
		ssi.saveStudentDetails(s);
		List<Student>students=ssi.getAllStudents();
		m.addAttribute("data", students);
		return "adminscreen";
	}
	
	@RequestMapping("/search")
	public String getBatchStudent(@RequestParam String batchNumber,Model m)
	{
		List<Student>result=ssi.searchStudentsByBatch(batchNumber);
		if(result.size()>0)
		{
			m.addAttribute("data", result);
		}else {
			List<Student>students=ssi.getAllStudents();
			m.addAttribute("data", students);
			m.addAttribute("message","No records are available for the batch"+batchNumber+"'....!");
		}
		return "adminscreen";
	}
	
	@RequestMapping("/fees")
	public String onFees(@RequestParam("studentId") int studentId,Model m) {
		Student st=ssi.getSingleStudent(studentId);
		m.addAttribute("st", st);
		return "fees";
		
	}
	
	@RequestMapping("/payfees")
	public String payfees(@RequestParam("studentId") int studentId,@RequestParam float ammount,Model m) {
		
		ssi.updateStudentFees(studentId,ammount);
		List<Student>students=ssi.getAllStudents();
				m.addAttribute("data", students);
				return "adminscreen";
	}
	
	@RequestMapping("/remove")
	public String removeStudent(@RequestParam("studentId") int studentId,Model m) {
		
		ssi.removeStudent(studentId);
		List<Student>students=ssi.getAllStudents();
				m.addAttribute("data", students);
				return "adminscreen";
	}
	@RequestMapping("/batch")
	public String onshiftbatch(@RequestParam("studentId")int studentId,Model m) {
		Student s=ssi.getSingleStudent(studentId);
		m.addAttribute("st", s);
		return "batch";
		
	}
	
	@RequestMapping("/shiftbatch")
	public String  updateBatch(@RequestParam("studentId")int studentId,@RequestParam("batchNumber") String batchNumber,Model m)
	{
		ssi.updateBatch(studentId, batchNumber);
		List<Student> list=ssi.getAllStudents();
		m.addAttribute("data", list);
		return "adminscreen";
	}
}
