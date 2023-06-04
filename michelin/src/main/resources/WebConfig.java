@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                //"/webjars/**",
                "/images/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        //"classpath:/META-INF/resources/webjars/",
                        "classpath:/static/images/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/js/**", "/css/**", "/images/**" ,"/pressiplus", "/public/**", "/index", "/", "/login").permitAll();
        http.authorizeRequests()
                .antMatchers("/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/**").hasAnyRole("ADMIN")
                /*.and()
                .formLogin()  //login configuration
                    .loginPage("/login")
                    .failureUrl("/login-error")
                    .loginProcessingUrl("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(myAuthenticationSuccessHandler())
                .and()
                .logout()    //logout configuration
                .logoutUrl("/logout")
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .and()
                    .rememberMe()
                        .tokenRepository(persistentTokenRepository())
                        .tokenValiditySeconds(7 * 24 * 60 * 60) // expires in 7 days
                .and()
                    .exceptionHandling() //exception handling configuration
                .accessDeniedHandler(accessDeniedHandler());*/
    }

}