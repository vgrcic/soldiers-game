package com.grca.games.soldiers.support.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.grca.games.soldiers.model.Soldier;
import com.grca.games.soldiers.model.dto.SoldierDto;

@Service
public class SoldierConverter {
	
	public SoldierDto withoutUser(Soldier soldier) {
		if (soldier == null)
			return null;
		return new SoldierDto(soldier.getId(), soldier.getName(), null);
	}
	
	public Collection<SoldierDto> withoutUsers(Collection<Soldier> soldiers) {
		if (soldiers == null)
			return null;
		List<SoldierDto> dtos = new ArrayList<SoldierDto>();
		for (Soldier s : soldiers) {
			dtos.add(withoutUser(s));
		}
		return dtos;
	}

}
