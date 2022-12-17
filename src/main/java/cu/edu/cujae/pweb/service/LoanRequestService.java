package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.LoanRequestDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import cu.edu.cujae.pweb.utils.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanRequestService implements ServiceImplementation {
	
	@Autowired
	private RestService restService;
	
    @Override
    public List<LoanRequestDto> getAll() {
    	List<LoanRequestDto> loanRequestDtoList = new ArrayList<>();
    	try {
    		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<LoanRequestDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/loanRequestList/all", params, String.class).getBody();
            loanRequestDtoList = apiRestMapper.mapList(response, LoanRequestDto.class);
    	}catch(IOException e) {
    		e.printStackTrace();
    		
    	}
        return loanRequestDtoList;
    }
    

    @Override
    public LoanRequestDto getById(Long id) {
    	
    	LoanRequestDto loanRequestDto = null;
    	
    	try {
    		
    		 MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
             ApiRestMapper<LoanRequestDto> apiRestMapper = new ApiRestMapper<>();

             UriTemplate template = new UriTemplate("/loanRequestList/{id}");
             String uri = template.expand(id).toString();
             String response = (String) restService.GET(uri, params , String.class).getBody();
             loanRequestDto = apiRestMapper.mapOne(response,LoanRequestDto.class);
             
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
        return loanRequestDto;
    }
   

    @Override
    public void create(Object dto) {
    	LoanRequestDto loanRequestDto = (LoanRequestDto) dto;
    	String response = (String) restService.POST("/loanRequestList/save",loanRequestDto,String.class).getBody();
        System.out.println(response);
    }
  

    @Override
    public void update(Object dto) {
    	LoanRequestDto loanRequestDto = (LoanRequestDto) dto;
    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/loanRequestList/update", params, loanRequestDto, String.class).getBody();
        System.out.println(response);
    }

    @Override
    public void delete(Long id) {
    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/loanRequestList/delete/{id}");
        String uri = template.expand(id).toString();
        String response = (String) restService.DELETE(uri, params, String.class).getBody();
        System.out.println(response);
    }
}
