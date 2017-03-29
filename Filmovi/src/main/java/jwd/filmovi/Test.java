package jwd.filmovi;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.filmovi.model.Film;
import jwd.filmovi.service.FilmoviService;
@Component
public class Test {
	
	@Autowired
	FilmoviService filmoviService;
	
	@PostConstruct
	public void ucitaj(){
		filmoviService.save(new Film("Rogue One"));
		filmoviService.save(new Film("The Bye Bye Man"));
		filmoviService.save(new Film("Jackie"));
		filmoviService.save(new Film("John Wick: Chapter Two"));
		filmoviService.save(new Film("Kong: Skull Island"));
		filmoviService.save(new Film("La reina de España"));
		filmoviService.save(new Film("Beauty and the Beast"));
		filmoviService.save(new Film("Logan"));
		filmoviService.save(new Film("On the Milky Road"));
		filmoviService.save(new Film("Nocturnal Animals"));
		filmoviService.save(new Film("The Founder"));
		filmoviService.save(new Film("American Pastoral"));
		filmoviService.save(new Film("Ballerina"));
	}

}
