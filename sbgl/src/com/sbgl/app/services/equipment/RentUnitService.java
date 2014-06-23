package com.sbgl.app.services.equipment;

import java.util.List;

import com.sbgl.app.entity.Rentunit;

public interface RentUnitService {
	public Rentunit findById(Integer id);
	
	public List<Rentunit> getAll();
}
