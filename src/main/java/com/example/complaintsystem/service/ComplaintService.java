package com.example.complaintsystem.service;

import com.example.complaintsystem.entity.Complaint;
import com.example.complaintsystem.entity.User;
import com.example.complaintsystem.repository.ComplaintRepository;
import com.example.complaintsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public Complaint saveComplaint(Complaint complaint, String token) {

        token = token.substring(7);

        String email = jwtService.extractEmail(token);

        User user = userRepository.findByEmail(email);

        complaint.setUser(user);
        complaint.setStatus("PENDING");

        return complaintRepository.save(complaint);
    }
    public Complaint updateComplaintStatus(Integer id, Complaint updatedComplaint) {
        Complaint complaint = complaintRepository.findById(id).orElse(null);

        if (complaint != null) {
            complaint.setStatus(updatedComplaint.getStatus());
            return complaintRepository.save(complaint);
        }

        return null;
    }

    public void deleteComplaint(Integer id) {
        complaintRepository.deleteById(id);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }
}