package tetofo.spring.tetofospringdashboard.Service.Impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class StaticUserDetailsService extends InMemoryUserDetailsManager {

    public StaticUserDetailsService() {

        super(User.withDefaultPasswordEncoder()
			.username("tetofo")
			.password("tetofo")
			.roles("USER")
			.build());
    }
    
}
