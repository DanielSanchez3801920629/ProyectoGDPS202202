package com.chatbot.servicechatbot.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chatbot.servicechatbot.entity.Pqrs;
import com.chatbot.servicechatbot.repository.RepositoryPqrs;

@Service("pqrsServiceImpl")
@Transactional
public class PqrsServiceImpl implements PqrsService{
	
	@Autowired
	@Qualifier("PqrsRepository")
	private RepositoryPqrs repositoryPqrs;

	@Override
	public Pqrs addPqrs(Pqrs pqrs) {
		return repositoryPqrs.save(pqrs);
	}
	
	
	

}
