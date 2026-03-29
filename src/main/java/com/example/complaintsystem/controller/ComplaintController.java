package com.example.complaintsystem.controller;

import com.example.complaintsystem.entity.Complaint;
import com.example.complaintsystem.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    // CREATE COMPLAINT
    @PostMapping
    public Complaint createComplaint(
            @RequestBody Complaint complaint,
            @RequestHeader("Authorization") String token
    ) {
        return complaintService.saveComplaint(complaint, token);
    }
    // GET ALL COMPLAINTS
    @GetMapping
    public List<Complaint> getComplaints() {
        return complaintService.getAllComplaints();
    }

    @PutMapping("/{id}")
    public Complaint updateComplaintStatus(
            @PathVariable Integer id,
            @RequestBody Complaint complaint
    ) {
        return complaintService.updateComplaintStatus(id, complaint);
    }

    @DeleteMapping("/{id}")
    public String deleteComplaint(@PathVariable Integer id) {
        complaintService.deleteComplaint(id);
        return "Complaint deleted successfully";
    }

}