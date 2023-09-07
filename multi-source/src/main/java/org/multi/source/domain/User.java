package org.multi.source.domain;

import lombok.Data;

import javax.persistence.*;

@Table(name = "ms_user")
@Data
@Entity
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name",columnDefinition = "varchar(50) COMMENT '用户名'")
    private String userName;

    @Column(name = "login_name")
    private String loginName;

    @Column(name = "age")
    private Integer age;



}
