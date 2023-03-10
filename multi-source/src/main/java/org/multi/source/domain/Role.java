package org.multi.source.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ms_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column
    private String roleName;

    @Column
    private String roleDesc;
}
