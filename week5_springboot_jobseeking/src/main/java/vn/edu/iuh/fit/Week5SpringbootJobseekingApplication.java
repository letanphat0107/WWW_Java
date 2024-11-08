package vn.edu.iuh.fit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week5SpringbootJobseekingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week5SpringbootJobseekingApplication.class, args);
    }

//    @Autowired
//    private ICandidateRepository candidateRepository;
//    @Autowired
//    private IAddressRepository addressRepository;
//    @Bean
//    CommandLineRunner initData(){
//        return args -> {
//            Random rnd =new Random();
//            for (int i = 1; i < 1000; i++) {
//                Address add = new Address(rnd.nextInt(1,1000)+"","Quang Trung","HCM",
//                        rnd.nextInt(70000,80000)+"", (short)CountryCode.VN.getNumeric());
//                addressRepository.save(add);
//                Candidate can=new Candidate("Name #"+i,
//                        LocalDate.of(1998,rnd.nextInt(1,13),rnd.nextInt(1,29)),
//                        add,
//                        rnd.nextLong(1111111111L,9999999999L)+"",
//                        "email_"+i+"@gmail.com");
//                candidateRepository.save(can);
//                System.out.println("Added: " +can);
//            }
//        };
//    }
}
