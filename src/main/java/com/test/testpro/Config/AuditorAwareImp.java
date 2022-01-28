package com.test.testpro.Config;

import com.test.testpro.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.util.ServletContextPropertyUtils;

import javax.servlet.ServletContext;
import java.util.Optional;

public class AuditorAwareImp implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
            if(SecurityContextHolder.getContext().getAuthentication() == null)
                return Optional.of("mastre@gmail.com");
            return Optional.of(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
    }//WE NEED TO FIND OUT WHAT THE USER IS LOGGED IN
}
