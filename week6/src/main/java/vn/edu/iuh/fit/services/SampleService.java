package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.repositories.SampleRepository;

@Service
public class SampleService {
    @Autowired
    private SampleRepository sampleRepository;
    public int calTotal(int a, int b) {
        return sampleRepository.calTotal(a, b);
    }
}
