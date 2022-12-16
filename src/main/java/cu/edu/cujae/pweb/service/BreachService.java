package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.BreachDto;
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
public class BreachService implements ServiceImplementation {
    @Autowired
    private RestService restService;

    @Override
    public void create(Object breach) {


    }

    @Override
    public void update(Object breach) {

    }

    @Override
    public void delete(Long id) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/breaches/delete/{id}");
        String uri = template.expand(id).toString();
        String response = (String) restService.DELETE(uri, params, String.class).getBody();
        System.out.println(response);
    }

    @Override
    public BreachDto getById(Long id) {
        BreachDto breachDto = null;
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<BreachDto> apiRestMapper = new ApiRestMapper<>();
            UriTemplate template = new UriTemplate("/breaches/{id}");
            String uri = template.expand(id).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            breachDto = apiRestMapper.mapOne(response, BreachDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return breachDto;
    }

    @Override
    public List<BreachDto> getAll() {
        List<BreachDto> breachDtoList = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<BreachDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/breaches/all", params, String.class).getBody();
            breachDtoList = apiRestMapper.mapList(response, BreachDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return breachDtoList;
    }

}
