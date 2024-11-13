package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @ModelAttribute
    public InsertAdministratorForm setUpInsertAdministratorForm() {
        return new InsertAdministratorForm();
    }
    // 「administrator/insert.html」にフォワードする処理 
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form){
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        return "redirect:/";
    }

    @GetMapping("/")
    // 「administrator/login.html」にフォワードする処理
    public String toLogin(LoginForm form){
        return "administrator/login";
    }
    @Autowired
    private HttpSession session;

    @PostMapping("/login")
    public String login(LoginForm form, Model model){
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
        if (administrator == null) {
            model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
            return "administrator/login";
        }
        session.setAttribute("administratorName", administrator.getName());
        return "redirect:/employee/showList";
    }
    
}
