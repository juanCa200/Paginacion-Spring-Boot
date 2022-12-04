package com.spring.boot.service;

import com.spring.boot.dto.PublicacionDTO;
import com.spring.boot.dto.PublicacionRespuesta;
import com.spring.boot.entity.Publicacion;

public interface PublicacionService {
	
	public PublicacionRespuesta listar(int numeroDePaginas, int medidaDePaginas);
	public PublicacionDTO guardar(PublicacionDTO publicaciondto);
	public PublicacionDTO buscarPorId(Long id);
	public PublicacionDTO actualizar(PublicacionDTO publicaciondto);
	public PublicacionDTO MapearEntidad(Publicacion publicacion);
	public Publicacion MapearDTO(PublicacionDTO publicaciondto);
	public void eliminar(Long id);
}

