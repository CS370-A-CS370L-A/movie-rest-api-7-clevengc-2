package edu.movie.whitman.service;

import edu.movie.whitman.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService();
    }

    @Test
    void testAddAndGetMovie() {
        Movie movie = new Movie(null, "Inception", "Christopher Nolan", 2010);
        Movie saved = movieService.addMovie(movie);
        assertNotNull(saved.getId());
        Movie found = movieService.getMovieById(saved.getId());
        assertEquals("Inception", found.getTitle());
    }

    @Test
    void testGetAllMovies() {
        movieService.addMovie(new Movie(null, "Movie1", "Director1", 2000));
        movieService.addMovie(new Movie(null, "Movie2", "Director2", 2001));
        List<Movie> movies = movieService.getAllMovies();
        assertEquals(2, movies.size());
    }

    @Test
    void testUpdateMovie() {
        Movie movie = movieService.addMovie(new Movie(null, "Old", "Dir", 1999));
        Movie updated = new Movie(null, "New", "Dir2", 2000);
        Movie result = movieService.updateMovie(movie.getId(), updated);
        assertEquals("New", result.getTitle());
        assertEquals(movie.getId(), result.getId());
    }

    @Test
    void testDeleteMovie() {
        Movie movie = movieService.addMovie(new Movie(null, "ToDelete", "Dir", 1998));
        boolean deleted = movieService.deleteMovie(movie.getId());
        assertTrue(deleted);
        assertNull(movieService.getMovieById(movie.getId()));
    }
}
