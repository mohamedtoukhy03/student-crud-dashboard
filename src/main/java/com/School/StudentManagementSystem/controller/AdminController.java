package com.School.StudentManagementSystem.controller;

import com.School.StudentManagementSystem.entity.Admin;
import com.School.StudentManagementSystem.entity.Student;
import com.School.StudentManagementSystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private final StudentService studentService;

    public AdminController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String loginPage(Model model){
        model.addAttribute("admin", new Admin());
        return "loginPage";
    }

    @GetMapping("/adminPage")
    public String adminPage(Model model){
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "adminPage";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam int id){
        studentService.deleteById(id);
        return "redirect:/adminPage";   
    }

    @GetMapping("/updateForm")
    public String showUpdateForm(@RequestParam int id, Model model){
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "updatePage";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(
            @Valid @ModelAttribute("student") Student student,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "updatePage";
        }
        studentService.update(student);
        return "redirect:/adminPage";   
    }

    @GetMapping("/additionForm")
    public String showAdditionForm(Model model){
        model.addAttribute("student", new Student());
        return "additionPage";
    }

    @PostMapping("/addStudent")
    public String addStudent(
            @Valid @ModelAttribute("student") Student student,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "additionPage";
        }
        studentService.save(student);
        return "redirect:/adminPage";
    }
}
