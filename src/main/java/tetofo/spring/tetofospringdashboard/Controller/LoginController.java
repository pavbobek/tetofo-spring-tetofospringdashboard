package tetofo.spring.tetofospringdashboard.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @GetMapping("/login")
	public String login() {
		return "login";
	}
	@PostMapping("/login") 
	public String token() {
		return "login";
	}
}
