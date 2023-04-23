package com.sms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sms.entity.Student;
import com.sms.sevice.StudentService;
@Controller
public class StudentController {
	
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	
	@GetMapping("/students")
	public String listStudent(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
		
	}
	
	@GetMapping("/students/new")
	public String createStudent(Model model) {
		Student student =new Student();
		model.addAttribute("student", student);
		return "create_student";
		
	}
	
	@PostMapping("/students")
	public String studentSave(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		
		return "redirect:/students";
		
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudent(@PathVariable Long id ,Model model) {
		 
		model.addAttribute("student",studentService.getStudentById(id));
		return "edit_student";
		
	}
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id ,
			@ModelAttribute("student") Student student,
			Model model) {
		Student exitingStudent = studentService.getStudentById(id);
		exitingStudent.setId(id);
		exitingStudent.setFirstName(student.getFirstName());
		exitingStudent.setLastName(student.getLastName());
		exitingStudent.setEmail(student.getEmail());
		
		studentService.updateStudent(exitingStudent);
		return "redirect:/students";
		
		 
	}
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long Id) {
		studentService.deleteStudentById(Id);
		return "redirect:/students";
		
	}
}
