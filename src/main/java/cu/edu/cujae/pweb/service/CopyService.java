package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.CopyDto;

public interface CopyService {
	List<CopyDto> getCopies();
	CopyDto getCopyById(String copyId);
	void createCopy(CopyDto author);
	void updateCopy(CopyDto author);
	void deleteCopy(String copyId);
}
