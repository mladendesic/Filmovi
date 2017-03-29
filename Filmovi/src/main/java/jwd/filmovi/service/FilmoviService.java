package jwd.filmovi.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jwd.filmovi.model.Film;

public interface FilmoviService {

	Film findOne(Integer id);
	Page<Film> findAll(int page);
	Film save(Film film);
	Film delete (Integer id);
	Page<Film> findByName(String name,int page);
}
