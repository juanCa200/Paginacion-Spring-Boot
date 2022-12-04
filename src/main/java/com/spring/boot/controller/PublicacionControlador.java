package com.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dto.PublicacionDTO;
import com.spring.boot.dto.PublicacionRespuesta;
import com.spring.boot.service.PublicacionServiceImpl;

@RestController
@RequestMapping("/api/publicacion")
public class PublicacionControlador {

	@Autowired
	public PublicacionServiceImpl service;

	@GetMapping
	public ResponseEntity<PublicacionRespuesta> listar(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int numeroDePaginas,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int medidaDePaginas) {
		return new ResponseEntity<>(service.listar(numeroDePaginas,medidaDePaginas), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PublicacionDTO> guardar(@RequestBody PublicacionDTO publciaciondto) {
		return new ResponseEntity<>(service.guardar(publciaciondto), HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<PublicacionDTO> buscarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.buscarPorId(id), HttpStatus.NOT_FOUND);
	}
	@PutMapping("/{id}")
	public ResponseEntity<PublicacionDTO> actualizar(@PathVariable("id") Long id,
			@RequestBody PublicacionDTO publicaciondto) {
		PublicacionDTO publicacion = service.buscarPorId(id);
		publicacion.setTitulo(publicaciondto.getTitulo());
		publicacion.setDescripcion(publicaciondto.getDescripcion());
		publicacion.setContenido(publicaciondto.getContenido());
		return new ResponseEntity<>(service.guardar(publicacion), HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
		service.eliminar(id);
		return new ResponseEntity<>("Publicacion Eliminada", HttpStatus.OK);
	}

}