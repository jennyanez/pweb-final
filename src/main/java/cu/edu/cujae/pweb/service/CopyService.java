package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.BookDto;
import cu.edu.cujae.pweb.dto.CopyDto;
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
public class CopyService implements ServiceImplementation {

    @Autowired
    private RestService restService;
    
  

    @Override
    public List<CopyDto> getAll() {

        List<CopyDto>copyDtoList = new ArrayList<>();
        try {
            MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CopyDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/copies/all", params,String.class).getBody();
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

            UriTemplate template = new UriTemplate("copies/{id}");
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
        String response = (String) restService.POST("/copies/save",copyDto,String.class).getBody();
        System.out.println(response);
    }


    @Override
    public void update(Object copy) {
        CopyDto copyDto = (CopyDto) copy;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/copies/update", params, copyDto, String.class).getBody();
        System.out.println(response);
    }

    @Override
    public void delete(Long id) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/copies/delete/{id}");
        String uri = template.expand(id).toString();
        String response = (String) restService.DELETE(uri, params, String.class).getBody();
        System.out.println(response);
    }

}
