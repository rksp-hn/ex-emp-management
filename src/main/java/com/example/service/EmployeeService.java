package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    /* 従業員情報を全件取得する。
    ・employeeRepository の findAll()メソッドを呼ぶ。
    ・findAll()からの戻り値を呼び出し元に返す。 */
    public List<Employee> showList(){
            List<Employee> employee = employeeRepository.findAll();
            return employee;
    }
}
