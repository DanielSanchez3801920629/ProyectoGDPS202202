package com.chatbot.servicechatbot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatbot.servicechatbot.entity.Pqrs;

@Repository("PqrsRepository")
public interface RepositoryPqrs extends CrudRepository<Pqrs, Long>{

}
