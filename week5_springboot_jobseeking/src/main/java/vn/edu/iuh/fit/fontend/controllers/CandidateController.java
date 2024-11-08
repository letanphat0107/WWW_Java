package vn.edu.iuh.fit.fontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.models.Address;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.CandidateSkill;
import vn.edu.iuh.fit.backend.models.Experience;
import vn.edu.iuh.fit.backend.repositories.IAddressRepository;
import vn.edu.iuh.fit.backend.repositories.ICandidateRepository;
import vn.edu.iuh.fit.backend.services.ICandidateSkillService;
import vn.edu.iuh.fit.backend.services.IExperienceService;
import vn.edu.iuh.fit.backend.services.ISkillService;
import vn.edu.iuh.fit.backend.services.impl.CandidateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

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
}
