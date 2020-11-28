package ru.aegorova.simbirsoftproject.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.aegorova.simbirsoftproject.security.Role;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(PasswordEncoder passwordEncoder
            , @Qualifier(value = "customUserDetailsService") UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/registration").permitAll()

                .antMatchers("/api/authors/getAll").authenticated()
                .antMatchers("/api/authors/getBooks/{authorId}").authenticated()
                .antMatchers("/api/authors/add").hasAuthority(Role.ADMIN.name())
                .antMatchers("/api/authors/delete/{id}").hasAuthority(Role.ADMIN.name())

                .antMatchers("/api/books/add").hasAuthority(Role.ADMIN.name())
                .antMatchers("/api/books/delete/{id}").hasAuthority(Role.ADMIN.name())
                .antMatchers("/api/books/addOrRemoveGenre").hasAuthority(Role.ADMIN.name())
                .antMatchers("/api/books/getByAuthor").authenticated()
                .antMatchers("/api/books/getByGenre/{genreId}").authenticated()

                .antMatchers("/api/genres/getAll").authenticated()
                .antMatchers("/api/genres/add").hasAuthority(Role.ADMIN.name())
                .antMatchers("/api/genres/delete/{genreId}").hasAuthority(Role.ADMIN.name())
                .antMatchers("/api/genres/statistic").authenticated()

                .antMatchers("/api/users/update").authenticated()
                .antMatchers("/api/users/getBooks").authenticated()
                .antMatchers("/api/users/addBook").authenticated()
                .antMatchers("/api/users/returnBook").authenticated();

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/main")
                .usernameParameter("login")
                .passwordParameter("password")
                .permitAll();
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
