package org.multi.source.repository;


import org.multi.source.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface RoleDao extends JpaRepository<Role,Integer> {
}
