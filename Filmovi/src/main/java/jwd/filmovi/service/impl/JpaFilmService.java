package jwd.filmovi.service.impl;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jwd.filmovi.model.Film;
import jwd.filmovi.repository.FilmoviRepository;
import jwd.filmovi.service.FilmoviService;
@Service
public class JpaFilmService implements FilmoviService {

	@Autowired
	FilmoviRepository filmoviRepository;
	
	@Override
	public Film findOne(Integer id) {
		// TODO Auto-generated method stub
		return filmoviRepository.findOne(id);
	}

	

	@Override
	public Film save(Film film) {
		// TODO Auto-generated method stub
		return filmoviRepository.save(film);
	}

	@Override
	public Film delete(Integer id) {
		Film film = filmoviRepository.findOne(id);
		if(film==null){
			throw new IllegalArgumentException("tried to delete nonexistant film");
		}
		filmoviRepository.delete(film);
		return film;
	}




	@Override
	public Page<Film> findAll(int page) {
		// TODO Auto-generated method stub
		return filmoviRepository.findAll(new PageRequest(page, 10));
	}



	@Override
	public Page<Film> findByName(String name, int page) {
		// TODO Auto-generated method stub
		return filmoviRepository.findByName(name, new PageRequest(page, 10));
	}


	
}
