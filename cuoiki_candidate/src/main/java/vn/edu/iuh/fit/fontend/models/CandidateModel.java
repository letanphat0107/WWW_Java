package vn.edu.iuh.fit.fontend.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.backend.models.Candidate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Component
public class CandidateModel {
    @Autowired
    private RestTemplate restTemplate;

    public List<Candidate> getAllCandidates() {
        return restTemplate.getForObject("http://localhost:9999/api/candidate/list", List.class);
    }
}
