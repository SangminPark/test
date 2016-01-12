package best.prog.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import best.prog.domain.User;
import best.prog.service.UserService;

@Controller
public class UserController {

    @Autowired UserService userService;

    /**
     * 셍상 폼
     * @param model
     * @return
     */
    @RequestMapping(value = "/users/new", method = RequestMethod.GET)
    public String createForm(Model model) {
      model.addAttribute("user", new User());
      return "user/save";
    }

    /**
     * 생성
     * @param user
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/users/new", method = RequestMethod.POST)
    public String create(@Valid User user, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
        return "user/save";
      }
      try {
        userService.create(user);
      } catch(IllegalStateException ise) {
        bindingResult.rejectValue("userId", "error.user", ise.getMessage());
        return "user/save";
      }
      return "redirect:/users";
    }

    /**
     * 수정 폼
     */
    @RequestMapping(value = "/users/{uid}/edit", method = RequestMethod.GET)
    public String updateUserForm(@PathVariable("uid") String uid, Model model) {

      User user = userService.findUser(uid);
      model.addAttribute("user", user);
      return "user/save";
    }

    /**
     * 수정
     * @param user
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/users/{uid}", method = RequestMethod.PUT)
    public String updateUser(@Valid User user, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
        return "user/save";
      }
      userService.update(user);
      return "redirect:/users";
    }

    /**
     * 삭제
     * @param uid
     * @return
     */
    @RequestMapping(value = "/users/{uid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("uid") String uid) {

      userService.delete(uid);
      return "redirect:/users";
    }

    /**
     * 목록
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model) {

      List<User> users = userService.findUsers();
      model.addAttribute("users", users);
      return "user/list";
    }
    

}
