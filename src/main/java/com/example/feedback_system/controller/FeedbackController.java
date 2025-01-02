package com.example.feedback_system.controller;

import com.example.feedback_system.model.Feedback;
import com.example.feedback_system.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;


    @GetMapping("/feedback")
    public String feedbackForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback";
    }

    @PostMapping("/submitFeedback")
    public String submitFeedback(@ModelAttribute("feedback") Feedback feedback) {
        feedbackRepository.save(feedback); // Only `comment` will be saved; `id` is auto-generated
        return "redirect:/viewFeedback";
    }



    @GetMapping("/viewFeedback")
    public String viewFeedback(Model model) {
        model.addAttribute("feedbacks", feedbackRepository.findAll());
        return "viewFeedback"; 
    }
}
