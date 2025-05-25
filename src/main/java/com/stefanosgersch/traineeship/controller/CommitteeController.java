package com.stefanosgersch.traineeship.controller;

import com.stefanosgersch.traineeship.service.committee.CommitteeMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("studentUsername", studentUsername);
        return "committee/show_positions";
    }

    @RequestMapping("/applications/find_position/assign_position")
    public String assignPosition(Long positionId, String studentUsername, Model model) {
        committeeMemberService.assignPosition(positionId, studentUsername);
        return "committee/dashboard";
    }

    @RequestMapping("/assign_supervisor")
    public String assignSupervisor(@RequestParam("position_id") Long positionId, 
                                 @RequestParam("strategy") String strategy, 
                                 Model model) {
        committeeMemberService.assignSupervisor(positionId, strategy);
        return "committee/dashboard";
    }

    @RequestMapping("/assigned_traineeships")
    public String listAssignedTraineeships(Model model) {
        model.addAttribute("assigned_positions", committeeMemberService.getTraineeshipPositionsInProgress());
        return "committee/assigned_traineeships";
    }
}
