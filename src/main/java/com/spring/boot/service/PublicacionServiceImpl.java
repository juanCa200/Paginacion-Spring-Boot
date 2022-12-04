package com.spring.boot.service;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.PublicacionDTO;
import com.spring.boot.dto.PublicacionRespuesta;
import com.spring.boot.entity.Publicacion;
import com.spring.boot.excepciones.ResourceNotFoundException;
import com.spring.boot.repository.PublicacionRepository;

@Service
public class PublicacionServiceImpl implements PublicacionService{
	
	@Autowired
	public PublicacionRepository repositorio;

	@Autowired
	public ModelMapper mapear;
	
	@Override
	public PublicacionRespuesta listar(int numeroDePaginas, int medidaDePaginas) {
		Pageable pageable = PageRequest.of(numeroDePaginas, medidaDePaginas);
		Page<Publicacion> publicacion = repositorio.findAll(pageable);
		List<Publicacion> listadepublicaciones = publicacion.getContent();
		List<PublicacionDTO> publicaciondto =  listadepublicaciones
				.stream().map(this::MapearEntidad).collect(Collectors.toList());
		
		PublicacionRespuesta publicacionrespuesta = new PublicacionRespuesta();
		publicacionrespuesta.setContenido(publicaciondto); 
		publicacionrespuesta.setNumeroPagina(publicacion.getNumber());
		publicacionrespuesta.setMedidaPagina(publicacion.getSize());
		publicacionrespuesta.setTotalElementos(publicacion.getTotalElements());
		publicacionrespuesta.setTotalPaginas(publicacion.getTotalPages());
		publicacionrespuesta.setUltima(publicacion.isLast());
		return publicacionrespuesta; 
	}
	@Override
	public PublicacionDTO guardar(PublicacionDTO publicaciondto){
		Publicacion publicacion = MapearDTO(publicaciondto);
		Publicacion guardar = repositorio.save(publicacion);
		return MapearEntidad(guardar);
	}
	@Override
	public PublicacionDTO buscarPorId(Long id){
		Publicacion publicacion = repositorio.findById(id).orElseThrow(()-> new ResourceNotFoundException("Publicacion","id",id));
		return MapearEntidad(publicacion);
	}

	@Override
	public PublicacionDTO actualizar(PublicacionDTO publicaciondto) {
		Publicacion publicacion = MapearDTO(publicaciondto);
		Publicacion guardar = repositorio.save(publicacion);
		return MapearEntidad(guardar);
	}

	@Override
	public void eliminar(Long id) {
        repositorio.deleteById(id);		
	}

	@Override
	public PublicacionDTO MapearEntidad(Publicacion publicacion){
		PublicacionDTO publicaciondto = mapear.map(publicacion, PublicacionDTO.class);
		return publicaciondto;
	}

	@Override
	public Publicacion MapearDTO(PublicacionDTO publicaciondto) {
		Publicacion publicacion = mapear.map(publicaciondto, Publicacion.class);
		return publicacion;
	}
	
}
