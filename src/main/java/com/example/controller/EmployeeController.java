package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /* 従業員⼀覧を出⼒する。
    ・employeeService の showList()メソッドを呼び出し、従業員⼀覧(List)を取得する。
    ・次の画⾯に表⽰するために request スコープに取得した従業員⼀覧を「employeeList」という名前を付けて格納する。
    ・「employee/list.html」にフォワードする。 */
    @GetMapping("/showList")
    public String showList(Model model){
        List<Employee> employeeList = employeeService.showList();
        model.addAttribute("employeeList", employeeList);
        return "employee/list";
    }
    
}
