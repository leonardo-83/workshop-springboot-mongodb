package com.almeidaleonardo.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.almeidaleonardo.workshopmongo.domain.Post;
import com.almeidaleonardo.workshopmongo.domain.User;
import com.almeidaleonardo.workshopmongo.repository.PostRepository;
import com.almeidaleonardo.workshopmongo.repository.UserRepository;

@Configuration
public class instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Amelia", "maria@gmail.com");
		User alex = new User(null, "Joao da Silva", "joao@gmail.com");
		User bob = new User(null, "Mario Andrade", "mario@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para Sao Paulo, abraços!", maria);
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia!", "Acordei feliz hoje!", maria);
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
