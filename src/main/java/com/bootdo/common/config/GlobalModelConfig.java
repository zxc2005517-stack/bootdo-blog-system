package com.bootdo.common.config;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelConfig {

    @ModelAttribute
    public void addCurrentUser(Model model) {
        try {
            UserDO user = ShiroUtils.getUser();
            if (user != null) {
                model.addAttribute("currentUser", user);
                model.addAttribute("currentUsername", user.getUsername());
            }
        } catch (Exception ignored) {
        }
    }
}