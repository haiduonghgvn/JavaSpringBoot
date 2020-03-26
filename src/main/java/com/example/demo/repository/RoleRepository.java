package com.example.demo.repository;

import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

    @Query(value = "select * from Role r , user_role ur  where ur.role_id =r.id and ur.user_id=?1" , nativeQuery = true)
    List<Role> findRoleByUserId(long userId);


}