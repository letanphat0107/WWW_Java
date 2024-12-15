package vn.edu.iuh.fit.fontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.IAddressRepository;
import vn.edu.iuh.fit.backend.repositories.ICandidateRepository;
import vn.edu.iuh.fit.backend.repositories.ICompanyRepository;
import vn.edu.iuh.fit.backend.repositories.IJobRepository;
import vn.edu.iuh.fit.backend.services.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private ICandidateService candidateService;

    @Autowired
    public ICandidateRepository candidateRepository;

    @Autowired
    public IAddressRepository addressRepository;

    @Autowired
    private ISkillService skillService;

    @Autowired
    private ICandidateSkillService candidateSkillService;

    @Autowired
    private IExperienceService experienceService;

    @Autowired
    private EmailService emailService;
    @Autowired
    private  ICompanyService companyService;
    @Autowired
    private ICompanyRepository companyRepository;
    @Autowired IJobService jobService;
    @Autowired
    private IJobRepository jobRepository;

    @GetMapping("/list")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/candidates";
    }

    @GetMapping("/list_paging")
    public String showCandidateListPaging(Model model, @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size")Optional<Integer> size) {
        int currentPage = page.orElse(1); // default page number is 1 (the first page) or get the page number from the request
        int pageSize = size.orElse(10); // default page size is 10 or get the page size from the request

        Page<Candidate> candidatePage = candidateService.findAll(currentPage - 1, pageSize, "id", "asc");
        model.addAttribute("candidatePage", candidatePage);

        System.out.println(candidatePage.getContent().stream().toList());

        int totalPages = candidatePage.getTotalPages(); // get the total number of pages
        if(totalPages>0) {
            List<Integer> pageNumbers= IntStream.rangeClosed(1, totalPages) // create a list of page numbers from 1 to totalPages
                    .boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidates/candidates-paging";
    }

    @GetMapping("form-add-candidate")
    public ModelAndView showFormAddCandidate(Model model) {
        ModelAndView mav = new ModelAndView("candidates/add-candidate");
        Candidate candidate = new Candidate();
        candidate.setCandidateSkills(new ArrayList<>());
        Experience experience = new Experience();
        candidate.setAddress(new Address());
        mav.addObject("candidate", candidate);
        mav.addObject("address", candidate.getAddress());
        mav.addObject("countries", CountryCode.values());
        mav.addObject("skills", skillService.getAllSkills());
        mav.addObject("experience", experience);
        return mav;
    }

    @PostMapping("/add")
    public String addCandidate(@ModelAttribute("candidate") Candidate candidate,
                               @ModelAttribute("address") Address address,
                               @ModelAttribute("experience") Experience experience) {
        // Khởi tạo danh sách nếu candidateSkills là null
        if (candidate.getCandidateSkills() == null) {
            candidate.setCandidateSkills(new ArrayList<>());
        }
        candidate.getCandidateSkills().removeIf(Objects::isNull);

        addressRepository.save(address);
        candidate.setAddress(address);
        candidateRepository.save(candidate);

        Candidate canbyEmail = candidateRepository.findByEmail(candidate.getEmail());
        System.out.println(candidate.getCandidateSkills());

        // Lưu từng CandidateSkill
        for (CandidateSkill candidateSkill : candidate.getCandidateSkills()) {
            if (candidateSkill.getSkill() != null && candidateSkill.getSkillLevel() != null) { // Kiểm tra null trước khi lưu
                candidateSkill.setCan(canbyEmail);
                candidateSkillService.save(candidateSkill);
            }
        }

        experience.setCan(canbyEmail);
        experienceService.save(experience);

        return "redirect:/candidates/list_paging";
    }



    @GetMapping("form-update-candidate/{id}")
    public ModelAndView showFormEditCandidate(Model model, @PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("candidates/update-candidates");
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if(candidate.isPresent()) {
            mav.addObject("candidate", candidate.get());
            mav.addObject("address", candidate.get().getAddress());
            mav.addObject("countries", CountryCode.values());
        }
        return mav;
    }

    @PostMapping("edit")
    public String editCandidate(@ModelAttribute("candidate") Candidate candidate
            , @ModelAttribute("address") Address address) {
        addressRepository.save(address);
        candidate.setAddress(address);
        candidateRepository.save(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("delete/{id}")
    public String deleteCandidate(@PathVariable("id") Long id) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid candidate Id:" + id));
        candidateRepository.delete(candidate);
        return "redirect:/candidates/list_paging";
    }

    @GetMapping("/find-candidates")
    public String findCandidates(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // Lấy công ty đang đăng nhập
        Company company = companyRepository.findCompanyByCompName(userDetails.getUsername());

        // Lấy danh sách công việc mà công ty đã đăng
        List<Job> jobs = jobRepository.findJobByCompanyId(company.getId());

        // Lấy danh sách kỹ năng cần tìm từ công việc
        Set<Long> requiredSkillIds = new HashSet<>();
        for (Job job : jobs) {
            for (JobSkill jobSkill : job.getJobSkills()) {
                requiredSkillIds.add(jobSkill.getSkill().getId());
            }
        }

        // Tìm các ứng viên có kỹ năng phù hợp
        List<Candidate> candidates = candidateService.findCandidatesBySkills(requiredSkillIds);

        // Gửi thông tin đến view
        model.addAttribute("candidates", candidates);
        model.addAttribute("company", company);
        return "candidates/find-candidates";
    }

    @PostMapping("/send-invites")
    public String sendInvites(@RequestParam List<Long> candidateIds, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        // Lấy công ty đang đăng nhập
        Company company = companyRepository.findCompanyByCompName(userDetails.getUsername());

        // Gửi email cho từng ứng viên
        for (Long candidateId : candidateIds) {
            Candidate candidate = candidateService.getCandidate(candidateId);
            emailService.sendInvitationEmail(candidate.getEmail(), company.getCompName());
        }

        model.addAttribute("message", "Đã gửi email mời đến các ứng viên.");
        return "redirect:/company/find-candidates";
    }


    @GetMapping("/job-suggestions")
    public String getJobSuggestions(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Lấy thông tin ứng viên từ tên đăng nhập
        Candidate candidate = candidateRepository.findCandidateByFullName(userDetails.getUsername());
        if (candidate == null) {
            model.addAttribute("error", "Không tìm thấy thông tin ứng viên.");
            return "candidates/error"; // Trang lỗi nếu không tìm thấy ứng viên
        }

        // Lấy danh sách kỹ năng của ứng viên
        List<Skill> candidateSkills = candidateService.getSkillsForCandidate(candidate.getId());

        // Lấy các công việc phù hợp với kỹ năng của ứng viên
        List<Job> suggestedJobs = jobService.findJobsBySkills(candidateSkills);

        model.addAttribute("suggestedJobs", suggestedJobs);
        return "candidates/job-suggestions"; // Chuyển đến trang hiển thị công việc gợi ý
    }

    @GetMapping("/skill-suggestions")
    public String suggestSkills(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Candidate candidate = candidateRepository.findCandidateByFullName(userDetails.getUsername());
        if (candidate == null) {
            return "error"; // Xử lý lỗi nếu ứng viên không tồn tại
        }

        // Lấy danh sách các kỹ năng mà ứng viên đã có
        List<Skill> existingSkills = candidateService.getSkillsForCandidate(candidate.getId());

        // Lấy tất cả các kỹ năng trong hệ thống
        List<Skill> allSkills = skillService.getAllSkills();

        // Lọc các kỹ năng mà ứng viên chưa có
        List<Skill> suggestedSkills = allSkills.stream()
                .filter(skill -> !existingSkills.contains(skill))
                .collect(Collectors.toList());

        model.addAttribute("suggestedSkills", suggestedSkills);
        return "candidates/skill-suggestions";
    }
}
