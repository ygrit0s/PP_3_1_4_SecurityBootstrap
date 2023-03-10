package habsida.ygrit0s.springboot_security.controller;

import habsida.ygrit0s.springboot_security.entity.User;
import habsida.ygrit0s.springboot_security.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin/users")
public class AdminController {

	private final UserService userService;
	private final RoleService roleService;


	public AdminController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@GetMapping()
	public String pageForAdmins(@AuthenticationPrincipal User admin, Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("admin", admin);
		model.addAttribute("userList", userService.userList());
		model.addAttribute("roleList", roleService.roleList());
		return "admin/users";
	}

	@PutMapping()
	public String updateUser(@ModelAttribute("user") User user) {
		userService.updateUser(user);
		return "redirect:/admin/users";
	}

	@DeleteMapping("/{id}")
	public String removeUser(@PathVariable("id") long id, Principal principal) {
		userService.removeUser(id, principal);
		return "redirect:/admin/users";
	}
}
