package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.MatterDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import cu.edu.cujae.pweb.utils.ServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatterService implements ServiceImplementation {


    @Autowired
    private RestService restService;

    @Override
    public List<MatterDto> getAll() {
        List<MatterDto> matterDtoList = new ArrayList<>();
        try{
            MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<MatterDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/matters/all", params,String.class,CurrentUserUtils.getTokenBearer()).getBody();
            matterDtoList = apiRestMapper.mapList(response,MatterDto.class);
        }catch (IOException e){
            e.printStackTrace();

        }
        return matterDtoList;
    }

    @Override
    public MatterDto getById(Long id) {

        MatterDto matterDto = new MatterDto();

        try{
            MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<MatterDto> apiRestMapper = new ApiRestMapper<>();
            UriTemplate template = new UriTemplate("/api/v1/matters/{id}");
            String uri = template.expand(id).toString();
            String response = (String)restService.GET(uri,params,String.class,CurrentUserUtils.getTokenBearer()).getBody();
            matterDto = apiRestMapper.mapOne(response, MatterDto.class);
        }catch (IOException e){
            e.printStackTrace();

        }
        return matterDto;
    }

    @Override
    public String create(Object matter) {
        String msg = "";
        MatterDto matterDto = (MatterDto) matter;
        ResponseEntity response = restService.POST("/api/v1/matters/save",matterDto,String.class,CurrentUserUtils.getTokenBearer());
        if(response.getStatusCode().isError()){
            return msg = response.getBody().toString();
        }
        return msg;
    }

    @Override
    public String update(Object matter) {
        String msg = "";
        MatterDto matterDto = (MatterDto) matter;
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        ResponseEntity response = restService.PUT("/api/v1/matters/update",params,matterDto,String.class,CurrentUserUtils.getTokenBearer());
        if(response.getStatusCode().isError()){
            return msg = response.getBody().toString();
        }
        return msg;
    }

    @Override
    public String delete(Long id) {
        String msg = "";
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/matters/delete/{id}");
        String uri = template.expand(id).toString();
        ResponseEntity response = restService.DELETE(uri, params,String.class,CurrentUserUtils.getTokenBearer());
        if(response.getStatusCode().isError()){
            return msg = response.getBody().toString();
        }
        return msg;
    }
}
