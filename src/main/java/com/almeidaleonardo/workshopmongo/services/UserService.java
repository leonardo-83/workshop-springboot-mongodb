package com.almeidaleonardo.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almeidaleonardo.workshopmongo.domain.User;
import com.almeidaleonardo.workshopmongo.dto.UserDTO;
import com.almeidaleonardo.workshopmongo.repository.UserRepository;
import com.almeidaleonardo.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById (String id) {
		Optional<User> user = repo.findById(id);
		if (user.isPresent()) {
			return user.get();
			
		}else {
		throw new ObjectNotFoundException("Objeto nao encontrado");	
		}
	}
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
