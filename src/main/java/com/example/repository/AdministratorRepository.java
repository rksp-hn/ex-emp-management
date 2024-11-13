package com.example.repository;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

@Repository
public class AdministratorRepository {

    private static final RowMapper<Administrator>ADMINISTRATOR_ROW_MAPPER = (rs,i)->{
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };
    @Autowired
    private NamedParameterJdbcTemplate template;

    // 管理者情報を挿⼊する。
    public void insert(Administrator administrator){
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        String insertSql = "INSERT INTO administrators(name,mail_address,password) VALUES(:name,:mailAddress,:password)";
        template.update(insertSql,param);
    }
    /* メールアドレスとパスワードから管理者情報を取得する(1 件も存在しない場合は null を返す※)。 */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password){
        String sql = "SELECT id,name,mailAddress,password FROM administrators WHERE mail_address=:mailAddress";
        SqlParameterSource param = new MapSqlParameterSource()
        .addValue("mail_address", mailAddress)
        .addValue("password", password);
        Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
        return administrator;

        /*List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
        if (administratorList.size() == 0) {
            return null;
        }
        return administratorList.get(0);*/
    }

}
