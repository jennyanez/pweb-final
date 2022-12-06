package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.LoanDto;
import cu.edu.cujae.pweb.utils.ServiceImplementation;
import org.springframework.stereotype.Service;

@Service
public class LoanService implements ServiceImplementation {

	@Override
	public <T> List<T> getAll() {
		return null;
	}

	@Override
	public Object getById(Long id) {
		return null;
	}

	@Override
	public void create(Object dto) {

	}

	@Override
	public void update(Object dto) {

	}

	@Override
	public void delete(Long id) {

	}
}
