package vn.edu.iuh.fit.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class SampleRepository {

    public int calTotal(int a, int b) {
        return a + b;
    }
}
