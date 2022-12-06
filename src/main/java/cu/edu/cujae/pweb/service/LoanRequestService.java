package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.utils.ServiceImplementation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRequestService implements ServiceImplementation {
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
