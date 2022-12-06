package cu.edu.cujae.pweb.utils;


import cu.edu.cujae.pweb.dto.XUserDto;

public interface IUserService {
    void deleteByUsername(String username);
    XUserDto getByUsername(String username);
}
