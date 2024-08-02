package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.mvc;

@Repository
public interface mvcRepo extends CrudRepository<mvc, Integer>{

}
