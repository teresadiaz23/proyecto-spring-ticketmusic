package com.salesianostriana.dam.ticketmusic.service.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T, ID, R extends JpaRepository<T, ID>> implements IBaseService<T, ID> {

	protected R repositorio;
	
	public BaseService(R repo) {
		this.repositorio = repo;
	}
	
	@Override
	public T save(T t) {
		return repositorio.save(t);
	}
	
	@Override
	public T findById(ID id) {
		return repositorio.findById(id).orElse(null);
	}
	
	@Override
	public List<T> findAll() {
		return repositorio.findAll();
	}
	
	@Override
	public T edit(T t) {
		return repositorio.save(t);
	}
	
	@Override
	public void delete(T t) {
		repositorio.delete(t);
	}
	
	@Override
	public void deleteById(ID id) {
		repositorio.deleteById(id);
	}
	
}
