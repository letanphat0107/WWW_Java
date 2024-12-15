package vn.edu.iuh.fit.backend.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/login", "/css/**", "/js/**").permitAll() // Cho phép truy cập trang login và các tài nguyên tĩnh
                .requestMatchers("/main").authenticated() // Yêu cầu đăng nhập để vào trang main
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/login") // Đặt URL cho trang đăng nhập
                        .defaultSuccessUrl("/main", true) // Chuyển hướng đến trang main khi đăng nhập thành công
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // Chuyển hướng về trang login khi đăng xuất
                        .permitAll());

        return http.build(); // Trả về đối tượng http đã được xây dựng
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("candidate1")
                        .password(passwordEncoder().encode("password1"))
                        .roles("CANDIDATE")
                        .build(),
                User.withUsername("company1")
                        .password(passwordEncoder().encode("password1"))
                        .roles("COMPANY")
                        .build()
        );
    }
}

