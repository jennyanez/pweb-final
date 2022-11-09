package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.CopyDto;

@Service
public class CopyServiceImpl implements CopyService{
	
	@Override
	public List<CopyDto> getCopies(){
		List<CopyDto> copies = new ArrayList<>();
		
		
		//codigo
		
		
		return copies;
	}
	
	@Override
	public CopyDto getCopyById(String copyId) {
		return getCopies().stream().filter(r -> r.getCopyId().equals(copyId)).findFirst().get();
	}
	
	@Override
	public void createCopy(CopyDto author) {
		
	}
	
	@Override
	public void updateCopy(CopyDto author) {
		
	}
	
	@Override
	public void deleteCopy(String copyId) {
		
	}
}
