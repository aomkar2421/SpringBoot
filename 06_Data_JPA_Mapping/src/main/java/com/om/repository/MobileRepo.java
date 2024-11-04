package com.om.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.om.entity.Mobile;

public interface MobileRepo extends JpaRepository<Mobile, Integer> {

}
