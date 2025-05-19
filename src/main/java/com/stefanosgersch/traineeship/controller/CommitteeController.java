package com.stefanosgersch.traineeship.controller;

import com.stefanosgersch.traineeship.service.committee.CommitteeMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/committee")
public class CommitteeController {

    private final CommitteeMemberService committeeMemberService;

    public CommitteeController(CommitteeMemberService committeeMemberService) {
        this.committeeMemberService = committeeMemberService;
    }

    @RequestMapping("/dashboard")
    public String getCommitteeDashboard() {
        return "committee/dashboard";
    }

    @RequestMapping("/applications")
    public String listTraineeshipApplications(Model model) {
        model.addAttribute("students", committeeMemberService.retrieveTraineeshipApplicants());
        return "committee/show_applications";
    }

    @RequestMapping("/applications/find_position")
    public String findPositions(String studentUsername, String strategy, Model model) {
        model.addAttribute("positions", committeeMemberService.retrievePositionsForApplicant(studentUsername, strategy));
        return "committee/show_positions";
    }

    @RequestMapping("/applications/find_position/assign_position")
    public String assignPosition(Long positionId, String studentUsername, Model model) {
        committeeMemberService.assignPosition(positionId, studentUsername);
        return "committee/dashboard";
    }

}
