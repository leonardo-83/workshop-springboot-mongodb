package com.almeidaleonardo.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.almeidaleonardo.workshopmongo.domain.Post;
import com.almeidaleonardo.workshopmongo.domain.User;
import com.almeidaleonardo.workshopmongo.dto.AuthorDTO;
import com.almeidaleonardo.workshopmongo.dto.CommentDTO;
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
		User joao = new User(null, "Joao da Silva", "joao@gmail.com");
		User mario = new User(null, "Mario Andrade", "mario@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, joao, mario));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para Sao Paulo, abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Vai com Deus!",sdf.parse("01/11/1999"), new AuthorDTO(maria));
		CommentDTO c2 = new CommentDTO("Tamo junto!",sdf.parse("02/11/1999"), new AuthorDTO(joao));
		CommentDTO c3 = new CommentDTO("É isso ai!",sdf.parse("03/11/1999"), new AuthorDTO(maria));

		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));

		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
