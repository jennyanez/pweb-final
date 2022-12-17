package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.SanctionDto;
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
public class SanctionService implements ServiceImplementation {
    @Autowired
    private RestService restService;

    @Override
    public List<SanctionDto> getAll() {
        List<SanctionDto> sanctionDtoList = new ArrayList<>();
        try{
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<SanctionDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/sanctions/all", params, String.class).getBody();
            sanctionDtoList = apiRestMapper.mapList(response, SanctionDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sanctionDtoList;
    }

    @Override
    public SanctionDto getById(Long id) {
        SanctionDto sanctionDto = null;
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<SanctionDto> apiRestMapper = new ApiRestMapper<>();
            UriTemplate template = new UriTemplate("/sanctions/{id}");
            String uri = template.expand(id).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            sanctionDto = apiRestMapper.mapOne(response, SanctionDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sanctionDto;
    }

    @Override
    public void create(Object dto) {

    }

    @Override
    public void update(Object dto) {

    }

    @Override
    public void delete(Long id) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/sanctions/delete/{id}");
        String uri = template.expand(id).toString();
        String response = (String) restService.DELETE(uri, params, String.class).getBody();
        System.out.println(response);
    }
}
