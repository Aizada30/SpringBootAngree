package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.User;
import peaksoft.service.UserService;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping
    public String getAll(Model model){
       model.addAttribute("users",userService.getAllUsers());
        return "getAllUsers";
    }

    @GetMapping("/new")
    public String createUser(Model model){
        model.addAttribute("user",new User());
        return "newUser";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/api/getAllUsers";
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable Long userId, Model model){
        model.addAttribute("user",userService.getUserById(userId));
        return "updateUser";
    }

    @PostMapping("{userId}/update")
    public String updateUser(@PathVariable Long userId,
                             @ModelAttribute("user") User user){
        userService.updateUser(userId,user);
        return "redirect:/api/users";
    }
}
