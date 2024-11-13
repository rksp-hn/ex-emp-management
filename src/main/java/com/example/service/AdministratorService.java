package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    // 管理者情報を挿⼊する。administratorRepository の insert()メソッドを呼ぶ処理を記述する
    public void insert(Administrator administrator){
        administratorRepository.insert(administrator);
    }
    /* ログイン処理をする。
    administratorRepository のfindByMailAddressAndPassword ()メソッドを呼ぶ処理を記述する。
    戻ってきた管理者情報をそのまま呼び出し元に返す。 */
    public Administrator login(String mailAddress, String password){
        Administrator administrator = administratorRepository.findByMailAddressAndPassword(mailAddress, password);
        return administrator ;
    }
}
