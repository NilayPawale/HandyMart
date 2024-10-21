package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Login;
@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
	
	@Query("select l from Login l where username= :username and password_hash= :password_hash")
    public Optional<Login> getLogin(String username,String password_hash);
	
	@Query("select l from Login l where username= :username")
    public Optional<Login> getLoginByUser(String username);
	
	
	
	
	
//	@Query(
//	        value = "SELECT * FROM login l INNER JOIN vendor v ON l.login_id = v.login_id WHERE l.status_approve = 0",
//	        nativeQuery = true
//	    )
//	List<Long> findLoginIdsWithStatusZero();
}
