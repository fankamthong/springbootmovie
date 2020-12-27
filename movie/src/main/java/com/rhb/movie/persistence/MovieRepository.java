package com.rhb.movie.persistence;

import org.springframework.stereotype.Repository;
import com.rhb.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	
}