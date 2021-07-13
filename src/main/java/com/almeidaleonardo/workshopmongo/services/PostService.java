package com.almeidaleonardo.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almeidaleonardo.workshopmongo.domain.Post;
import com.almeidaleonardo.workshopmongo.repository.PostRepository;
import com.almeidaleonardo.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		if (post.isPresent()) {
			return post.get();
			
		}else {
			throw new ObjectNotFoundException("Objeto nao encontrado");
		}
	}
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullsearch(text, minDate, maxDate);
	}
}