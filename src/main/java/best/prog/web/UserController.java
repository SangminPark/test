package best.prog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import best.prog.domain.User;
import best.prog.service.UserService;

@Controller
public class UserController {

    @Autowired UserService userService;

    @RequestMapping(value = "/user/new", method = RequestMethod.GET)
    public String createForm() {
        return "user/save";
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    public String create(User user) {

        userService.create(user);
        return "redirect:/users";
    }

    /**
     * 상품 수정 폼
     */
    @RequestMapping(value = "/user/{uid}/edit", method = RequestMethod.GET)
    public String updateUserForm(@PathVariable("uid") String uid, Model model) {

        User user = userService.findUser(uid);
        model.addAttribute("user", user);
        return "user/save";
    }

    /**
     * 상품 수정
     */
    @RequestMapping(value = "/user/{uid}/edit", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user) {

        userService.update(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/user/{uid}/view", method = RequestMethod.GET)
    public String view(@ModelAttribute("uid") String uid, Model model) {
      User user = userService.findUser(uid);
      model.addAttribute("user", user);
      return "user/view";
    }

    /**
     * 상품 목록
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model) {

        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

}
