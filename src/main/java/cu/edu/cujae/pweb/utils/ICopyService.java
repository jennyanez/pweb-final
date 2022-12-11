package cu.edu.cujae.pweb.utils;

import cu.edu.cujae.pweb.dto.CopyDto;

import java.util.List;

public interface ICopyService {
    List<CopyDto> getByBookId(Long bookId);
}
