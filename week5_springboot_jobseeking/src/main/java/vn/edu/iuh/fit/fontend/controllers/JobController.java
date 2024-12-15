package vn.edu.iuh.fit.fontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.ICompanyRepository;
import vn.edu.iuh.fit.backend.repositories.IJobRepository;
import vn.edu.iuh.fit.backend.repositories.IJobSkillRepository;
import vn.edu.iuh.fit.backend.repositories.ISkillRepository;
import vn.edu.iuh.fit.backend.services.IJobService;
import vn.edu.iuh.fit.backend.services.ISkillService;

import java.util.ArrayList;
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

    @Autowired
    ICompanyRepository companyRepository;

    @Autowired
    ISkillService skillService;

    @Autowired
    ISkillRepository skillRepository;

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

    @GetMapping("/post-job")
    public String postJob(Model model) {
        String username = getUsernameFromSecurityContext();
        Company company = companyRepository.findCompanyByCompName(username);

        if (company == null) {
            return "redirect:/login";  // Nếu không tìm thấy công ty, chuyển hướng về trang login
        }

        // Lấy danh sách kỹ năng từ cơ sở dữ liệu
        List<Skill> allSkills = skillService.getAllSkills();

        model.addAttribute("company", company);
        model.addAttribute("skills", allSkills);
        model.addAttribute("job", new Job());
        return "company/job/post-job";
    }

    @PostMapping("/post-job")
    public String postJob(@ModelAttribute("job") Job job, @RequestParam List<Long> selectedSkillIds, Model model) {
        // Giả sử bạn lấy thông tin công ty từ người dùng đã đăng nhập
        Company company = companyRepository.findById(1L).orElseThrow(); // Thay 1L bằng ID của công ty đã đăng nhập

        job.setCompany(company); // Gán công ty cho job
        jobRepository.save(job); // Lưu job vào cơ sở dữ liệu

        // Lưu các kỹ năng liên quan đến job
        for (Long skillId : selectedSkillIds) {
            Skill skill = skillRepository.findById(skillId).orElseThrow(); // Lấy kỹ năng theo ID

            // Tạo đối tượng JobSkillId
            JobSkillId jobSkillId = new JobSkillId();
            jobSkillId.setJobId(job.getId()); // Gán jobId từ đối tượng Job
            jobSkillId.setSkillId(skill.getId()); // Gán skillId từ đối tượng Skill

            // Tạo đối tượng JobSkill
            JobSkill jobSkill = new JobSkill();
            jobSkill.setId(jobSkillId); // Gán JobSkillId vào JobSkill
            jobSkill.setJob(job);
            jobSkill.setSkill(skill);
            jobSkill.setSkillLevel((byte) 3); // Cấp độ kỹ năng mặc định, có thể thay đổi
            jobSkill.setMoreInfos("Some information about the skill"); // Thông tin bổ sung

            // Lưu đối tượng JobSkill
            jobService.saveJobSkill(jobSkill);
        }

        model.addAttribute("message", "Job posted successfully!");
        return "job-posted-success"; // Trang thông báo thành công
    }

    private String getUsernameFromSecurityContext() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

}
