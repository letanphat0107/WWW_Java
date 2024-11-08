package vn.edu.iuh.fit.fontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.repositories.IJobRepository;
import vn.edu.iuh.fit.backend.services.IJobService;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private IJobService jobService;

    @Autowired
    private IJobRepository jobRepository;

    @GetMapping("/list")
    public String showJobsPaging(Model model, @RequestParam("size") Optional<Integer> size,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("companyId") Long companyId) {
        System.out.println("Requested Company ID: " + companyId);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(8);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

        // Gọi trực tiếp repository để kiểm tra
        Page<List<Job>> jobPage = jobRepository.findJobsByCompanyId_Paging(companyId, pageable);

        System.out.println("Job Page Content: " + jobPage.getContent());
        System.out.println("Total Pages: " + jobPage.getTotalPages());

        model.addAttribute("jobPage", jobPage);

        int totalPages = jobPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "company/job/jobs";
    }



    @GetMapping({""})
    public String showJobsNoPaging(Model model, @RequestParam("companyId") Long companyId) {
        System.out.println(companyId);
        List<Job> jobPage = jobService.getJobsByCompanyIda(companyId);
        model.addAttribute("jobPage", jobPage);
        System.out.println(jobPage.toString());
        return "company/job/jobs";
    }
}
