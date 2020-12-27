package com.rhb.movie.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rhb.movie.entity.Movie;
import com.rhb.movie.exception.RecordNotFoundException;
import com.rhb.movie.service.MovieService;

/**
 * Controller for movie CRUD
 * @author fankamthong
 *
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
    MovieService service;
	
	/**
	 * Retrieve all movies
	 * @return list of Movie Response Entity 
	 */
	@GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
		
        List<Movie> list = service.getAllMovies();
        return new ResponseEntity<List<Movie>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	/**
	 * Retrieve movie by ID
	 * @param id
	 * @return movie Response Entity
	 * @throws RecordNotFoundException
	 */
	@GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) 
                                                    throws RecordNotFoundException {
        
		Movie movie = service.getMovieByID(id);
        return new ResponseEntity<Movie>(movie, new HttpHeaders(), HttpStatus.OK);
    }
	
	/**
	 * Insert or update movie entity
	 * @param movie
	 * @return movie response entity
	 * @throws RecordNotFoundException
	 */
	
	@PostMapping
    public ResponseEntity<Movie> createEmployee(@RequestBody Movie movie) 
													throws RecordNotFoundException {
		
		System.out.println("movie..ID....:" + movie.getId());
    	System.out.println("movie..Title....:" + movie.getTitle());
    	System.out.println("movie..Genre....:" + movie.getGenre());
		
        Movie newMovie = service.createOrUpdateMovie(movie);
        
        return new ResponseEntity<Movie>(newMovie, new HttpHeaders(), HttpStatus.OK);
    }

	/**
	 * Delete movie by ID
	 * @param id
	 * @return Http Status
	 * @throws RecordNotFoundException
	 */
	@DeleteMapping("/{id}")
    public HttpStatus deleteMovieById(@PathVariable("id") Long id) 
                                                    throws RecordNotFoundException {
		
        service.deleteMovieById(id);
        return HttpStatus.FORBIDDEN;
    }
}