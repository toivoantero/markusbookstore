package com.example.markusbookstore.domain;

import org.springframework.data.repository.CrudRepository;

import com.example.markusbookstore.domain.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	AppUser findByUsername(String username);
}
