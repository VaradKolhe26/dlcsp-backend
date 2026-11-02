package com.example.dlcsp.controller;

import com.example.dlcsp.dto.ApiResponse;
import com.example.dlcsp.model.MentorChatMessage;
import com.example.dlcsp.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mentor")
@RequiredArgsConstructor
public class MentorController {

    private final MentorService mentorService;

    @GetMapping("/history")
    public ApiResponse<List<MentorChatMessage>> history() {
        return ApiResponse.ok(mentorService.getHistory(), "Chat history fetched");
    }
}