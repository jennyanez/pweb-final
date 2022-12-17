package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.pweb.dto.CopyDto;
import cu.edu.cujae.pweb.dto.LoanDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import cu.edu.cujae.pweb.utils.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class LoanService implements ServiceImplementation {
	
	 @Autowired
	 private RestService restService;

	@Override
	public List<LoanDto> getAll() {
		
		List<LoanDto> loanDtoList = new ArrayList<>();
		
		 try {
	            MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
	            ApiRestMapper<LoanDto> apiRestMapper = new ApiRestMapper<>();
	            String response = (String) restService.GET("/loanList/all", params,String.class).getBody();
	            loanDtoList = apiRestMapper.mapList(response, LoanDto.class);
	        }catch (IOException e){
	            e.printStackTrace();
	        }
		
		return loanDtoList;
	}

	@Override
	public LoanDto getById(Long id) {
		
		LoanDto loanDto = null;
			
		try {
			
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<LoanDto> apiRestMapper = new ApiRestMapper<>();
            UriTemplate template = new UriTemplate("/loanList/{id}");
            String uri = template.expand(id).toString();
            String response = (String) restService.GET(uri, params , String.class).getBody();
            loanDto = apiRestMapper.mapOne(response,LoanDto.class);
            
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return loanDto;
	}

	@Override
	public void create(Object dto) {
		LoanDto loanDto = (LoanDto) dto;
        String response = (String) restService.POST("/loanList/save",loanDto,String.class).getBody();
        System.out.println(response);
	}

	@Override
	public void update(Object dto) {
		LoanDto loanDto = (LoanDto) dto;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/loanList/update", params, loanDto, String.class).getBody();
        System.out.println(response);
	}

	@Override
	public void delete(Long id) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/loanList/delete/{id}");
        String uri = template.expand(id).toString();
        String response = (String) restService.DELETE(uri, params, String.class).getBody();
        System.out.println(response);
	}
	
	public List<Long> idCopies(){
		List<Long> idCopies = new ArrayList<>();		
		List<LoanDto> loans = getAll();
		
		for (int i = 0;i<loans.size();i++) {
			idCopies.add(loans.get(i).getCopy().getCopyId());
		}
			return idCopies;
	}
	
}
