package tetofo.spring.tetofospringdashboard.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
