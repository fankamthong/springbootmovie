package com.rhb.movie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhb.movie.entity.Movie;
import com.rhb.movie.exception.RecordNotFoundException;
import com.rhb.movie.persistence.MovieRepository;

/**
* Business layer to handle CRUD of movie records.
* @author Fan Kam Thong
*/
@Service
public class MovieService {

	@Autowired
	MovieRepository repository;
	
	/**
	 * Retrieve all movies
	 * @return list of Movie entities
	 */
	public List<Movie> getAllMovies(){
		
		List<Movie> employeeList = repository.findAll();
        
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<Movie>();
        }
	}
	
	/**
	 * Retrieve movie record by ID
	 * @param id
	 * @return Movie
	 * @throws RecordNotFoundException
	 */
	public Movie getMovieByID(Long id) throws RecordNotFoundException 
    {
        Optional<Movie> movie = repository.findById(id);
         
        if(movie.isPresent()) {
            return movie.get();
        } else {
            throw new RecordNotFoundException("No movie record exist for given id");
        }
    }
	
	/**
	 * Insert or update movie record
	 * @param movie
	 * @return
	 * @throws RecordNotFoundException
	 */
	public Movie createOrUpdateMovie(Movie movieEntity) throws RecordNotFoundException 
    {
        Optional<Movie> movie = repository.findById(movieEntity.getId());
         
        if(movie.isPresent()) 
        {
            Movie newEntity = movie.get();
            newEntity.setId(movieEntity.getId());
            newEntity.setTitle(movieEntity.getTitle());
            newEntity.setGenre(movieEntity.getGenre());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
        	
        	System.out.println("ID....:" + movieEntity.getId());
        	System.out.println("Title....:" + movieEntity.getTitle());
        	System.out.println("Genre....:" + movieEntity.getGenre());
        	movieEntity = repository.save(movieEntity);
             
            return movieEntity;
        }
    }
	
	/**
	 * Remove movie record by ID
	 * @param id
	 * @throws RecordNotFoundException
	 */
	public void deleteMovieById(Long id) throws RecordNotFoundException 
    {
        Optional<Movie> movie = repository.findById(id);
         
        if(movie.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No movie record exist for given id");
        }
    } 
}