package jwd.filmovi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.filmovi.model.Film;
import jwd.filmovi.service.FilmoviService;

@RestController
@RequestMapping(value="/api/filmovi")
public class FilmoviController {

	@Autowired
	FilmoviService filmoviService;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<Film>>findAll(@RequestParam(defaultValue="0")int page, @RequestParam(value ="name",required=false)String name){
		
		
		Page<Film>filmovi;
		if(name!=null){
			
			filmovi=filmoviService.findByName(name, page);
		}else{
			
			filmovi=filmoviService.findAll(page);
		}
			HttpHeaders headers= new HttpHeaders();
			List<Film>retval=filmovi.getContent();
			int totalPages=filmovi.getTotalPages();
			headers.add("TotalPages", totalPages +"");
		return new ResponseEntity<>(retval,headers,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	ResponseEntity<Film>findOne(@PathVariable Integer id){
		Film film=filmoviService.findOne(id);
		return new ResponseEntity<>(film,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	ResponseEntity<Film>delete(@PathVariable Integer id){
		Film deleted=filmoviService.delete(id);
		return new ResponseEntity<>(deleted,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	ResponseEntity<Film>save(@RequestBody Film film){
		
		Film saved=filmoviService.save(film);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	ResponseEntity<Film>edit(@RequestBody Film film, @PathVariable Integer id){
		
		if(id!=film.getId()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			Film edited=filmoviService.save(film);
		
		return new ResponseEntity<>(edited,HttpStatus.OK);
	}
}
