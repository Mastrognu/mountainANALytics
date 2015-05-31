package it.polimi.awt.db.service;

import it.polimi.awt.db.domain.Mountain;
import it.polimi.awt.db.repository.MountainRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MountainServiceImplementation implements MountainService {

	@Autowired
	private MountainRepository mountainRepository;

	@Override
	public List<Mountain> findAll() {
		return mountainRepository.findAll();
	}

	@Override
	public Mountain find(int mountainID) {
		return mountainRepository.find(mountainID);
	}

}
