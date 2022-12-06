package cu.edu.cujae.pweb.service.implementation;

import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.pweb.service.CopyService;
import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.CopyDto;

@Service
public class CopyServiceImpl implements CopyService {
	
	@Override
	public List<CopyDto> getCopies(){
		List<CopyDto> copies = new ArrayList<>();
		
		
		//codigo
		
		
		return copies;
	}
	
	@Override
	public CopyDto getCopyById(int copyId) {
		//return getCopies().stream().filter(r -> r.getCopyId().equals(copyId)).findFirst().get();
		return null;
	}
	
	@Override
	public void createCopy(CopyDto author) {
		
	}
	
	@Override
	public void updateCopy(CopyDto author) {
		
	}

	@Override
	public void deleteCopy(int copyId) {

	}

}
