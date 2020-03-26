package com.example.demo.repository;

import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

    @Query(value = "select * from Role r , user_role ur  where ur.role_id =r.id and ur.user_id=?1")
    List<Role> findRoleByUserId(long userId);


}