package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
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
    /* ・リクエストパラメータで送られてくる従業員 ID を引数に(int 型に変換してから)渡し、
    employeeService のshowDetail()メソッドを呼ぶ。その結果従業員情報(Employee)が戻り値として返ってくるのでそれを受け取る。
    ・次の画⾯に表⽰するために request スコープに従業員情報を「employee」という名前を付けて格納する。
    ・「employee/detail.html」にフォワードする。
    ※フォームを引数で受け取ることで扶養⼈数を更新する際のリクエストパラメータが格納されるUpdateEmployeeForm オブジェクトが 
    Model オブジェクト(リクエストスコープ)に⾃動的に格納されます。 */
    @GetMapping(value = "/{id}/showDetail")
    public String showDetail(@RequestParam("id") String id, Model model, UpdateEmployeeForm form){
        Employee employee = employeeService.showDetail(Integer.parseInt(id));
        model.addAttribute("employee", employee);
        return "employee/detail";
    }
    
}
