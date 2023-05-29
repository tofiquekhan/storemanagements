package com.storesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storesystem.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
