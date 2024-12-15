package vn.edu.iuh.fit.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.services.IJobService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/jobs")
public class JobResource {
    @Autowired
    private IJobService jobService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Job>> findAll() {
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Job> getOne(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return ResponseEntity.ok(job);
    }
}
