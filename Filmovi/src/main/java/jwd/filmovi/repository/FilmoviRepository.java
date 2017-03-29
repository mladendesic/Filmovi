package jwd.filmovi.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.filmovi.model.Film;
@Repository
public interface FilmoviRepository extends PagingAndSortingRepository<Film, Integer> {


	Page<Film>findByName(String name, Pageable page);

}
