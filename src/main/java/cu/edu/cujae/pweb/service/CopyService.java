package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.BookDto;
import cu.edu.cujae.pweb.dto.CopyDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.ICopyService;
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
public class CopyService implements ServiceImplementation, ICopyService {

    @Autowired
    private RestService restService;

    @Override
    public List<CopyDto> getAll() {

        List<CopyDto>copyDtoList = new ArrayList<>();
        try {
            MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CopyDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/copies/all", params,String.class).getBody();
            copyDtoList = apiRestMapper.mapList(response, CopyDto.class);
        }catch (IOException e){
            e.printStackTrace();
        }

        return copyDtoList;
    }

    @Override
    public CopyDto getById(Long id) {

        CopyDto copyDto = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CopyDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/copies/{id}");
            String uri = template.expand(id).toString();
            String response = (String) restService.GET(uri, params , String.class).getBody();
            copyDto = apiRestMapper.mapOne(response,CopyDto.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return copyDto;
    }

    @Override
    public void create(Object copy) {
        CopyDto copyDto = (CopyDto) copy;
        String response = (String) restService.POST("/api/v1/copies/save",copyDto,String.class).getBody();
        System.out.println(response);
    }


    @Override
    public void update(Object copy) {
        CopyDto copyDto = (CopyDto) copy;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/api/v1/copies/update", params, copyDto, String.class).getBody();
        System.out.println(response);
    }

    @Override
    public void delete(Long id) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/copies/delete/{id}");
        String uri = template.expand(id).toString();
        String response = (String) restService.DELETE(uri, params, String.class).getBody();
        System.out.println(response);
    }

    @Override
    public List<CopyDto> getByBookId(Long id) {
        List<CopyDto> copyDtoList = new ArrayList<>();
        try{
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CopyDto> apiRestMapper = new ApiRestMapper<>();
            UriTemplate template = new UriTemplate("/api/v1/copies/book/{id}");
            String uri = template.expand(id).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            copyDtoList = apiRestMapper.mapList(response, CopyDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return copyDtoList;
    }
    
    @Override
    public List<CopyDto> copyAvailable(List<Long> copiasPrestadas){
    	List<CopyDto> copies = getAll();
    	int i = 0;
    	int f;
    	
    	
    	if(!copiasPrestadas.isEmpty()) {
	    	for ( i = 0 ; i<copiasPrestadas.size() ; i++) {
	    		for  (f = 0 ; f<copies.size() ; f++) {
	    			
	    			if(copies.get(f).getCopyId() == copiasPrestadas.get(i)) {
	    				copies.remove(f);	    					
	        		}		
	    		}
	    	}	
    	}    	
    		return copies;   		    	
    }
}
