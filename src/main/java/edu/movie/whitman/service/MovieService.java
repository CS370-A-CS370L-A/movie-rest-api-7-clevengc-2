package edu.movie.whitman.service;

import edu.movie.whitman.model.Movie;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MovieService {
    private final Map<Long, Movie> movies = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies.values());
    }

    public Movie getMovieById(Long id) {
        return movies.get(id);
    }

    public Movie addMovie(Movie movie) {
        long id = idCounter.incrementAndGet();
        movie.setId(id);
        movies.put(id, movie);
        return movie;
    }

    public Movie updateMovie(Long id, Movie movie) {
        if (!movies.containsKey(id)) return null;
        movie.setId(id);
        movies.put(id, movie);
        return movie;
    }

    public boolean deleteMovie(Long id) {
        return movies.remove(id) != null;
    }
}
