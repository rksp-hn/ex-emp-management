package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;
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

        // ・Administrator ドメインをインスタンス化する
        Administrator administrator = new Administrator();

        // ・リクエストパラメータが⼊っているInsertAdministratorForm オブジェクトの中⾝を今インスタンス化した Administrator ドメインオブジェクトにコピーする
        BeanUtils.copyProperties(form, administrator);

        // ・administratorService の insert()メソッドを呼ぶ
        administratorService.insert(administrator);

        // ・「/」(ログイン画⾯)にリダイレクトする
        return "redirect:/";
    }
    
}
