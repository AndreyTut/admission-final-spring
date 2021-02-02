package com.study.my.finalspring.admission.controller;

import com.study.my.finalspring.admission.model.User;
import com.study.my.finalspring.admission.service.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class ImageController {

    private UserService userService;

    public ImageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/student/{email}/setimage")
    public String getForm(@PathVariable String email, Model model) {
        model.addAttribute("email", email);
        return "imageuploadform";
    }

    @PostMapping("/student/{email}/setimage")
    public String saveImage(@RequestParam MultipartFile image, @PathVariable String email, Model model) {
        User user = userService.saveDiplomaImage(image, email);
        model.addAttribute("student", user);
        model.addAttribute("diploma", user.getDiploma());
        return "redirect:/user/edit";
    }

    @GetMapping("/diplomaimage/{email}")
    public void getImage(@PathVariable String email, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        byte[] imageBytes = userService.getByEmail(email).getDiplomImage();
        if (imageBytes != null) {
            IOUtils.copy(new ByteArrayInputStream(imageBytes), response.getOutputStream());
        }
    }
}
