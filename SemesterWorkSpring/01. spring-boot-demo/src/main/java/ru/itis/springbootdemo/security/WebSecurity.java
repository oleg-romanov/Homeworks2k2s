package ru.itis.springbootdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/signUp").permitAll()// доступна всем
                .antMatchers("/users").permitAll()
                .antMatchers("/categoriesList/search").permitAll()
                .antMatchers("/productsList/search").permitAll()
                .antMatchers("/files/**").permitAll()
                .antMatchers("/**").authenticated()
                .antMatchers("/profile").authenticated()
                .antMatchers("/categories").authenticated()
                //ПОКА ЧТО ЧИСТО ДЛЯ ТЕСТА ДОСТУПНО ДЛЯ АУТЕНТИФИЦИРОВАНЫХ, ЗАТЕМ ИЗМЕНИТЬ НА АДМИНА
                .antMatchers("/adminEditCategories/*").authenticated()
                .antMatchers("/addCategories").authenticated()
                .antMatchers("/adminCategories/*").authenticated()
                .antMatchers("/addProducts/*").authenticated()
                .antMatchers("/adminEditProducts/*").authenticated()
                .and()
                .formLogin() // описываем страницу входа
                .loginPage("/signIn")
                .usernameParameter("email")
                .defaultSuccessUrl("/profile", true)
                .failureUrl("/signIn?error")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost")
                .allowedMethods("*");
    }
}