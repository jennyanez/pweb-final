package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.ClientDto;
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
public class ClientService implements ServiceImplementation {
    @Autowired
    private RestService restService;
    @Override
    public List<ClientDto> getAll() {
        List<ClientDto> clientDtoList = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ClientDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/clients/all", params, String.class).getBody();
            clientDtoList = apiRestMapper.mapList(response, ClientDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientDtoList;
    }

    @Override
    public ClientDto getById(Long id) {
        ClientDto clientDto = null;
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ClientDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/clients/{id}");
            String uri = template.expand(id).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            clientDto = apiRestMapper.mapOne(response, ClientDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientDto;
    }

    @Override
    public void create(Object client) {
        ClientDto clientDto = (ClientDto) client;
        String response = (String) restService.POST("/api/v1/clients/save", clientDto, String.class).getBody();
        System.out.println(response);
    }

    @Override
    public void update(Object client) {
        ClientDto clientDto = (ClientDto) client;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/api/v1/clients/update", params, clientDto, String.class).getBody();
        System.out.println(response);
    }

    @Override
    public void delete(Long id) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/clients/delete/{id}");
        String uri = template.expand(id).toString();
        String response = (String) restService.DELETE(uri, params, String.class).getBody();
        System.out.println(response);
    }
}
