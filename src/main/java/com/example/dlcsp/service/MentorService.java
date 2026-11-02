package com.example.dlcsp.service;

import com.example.dlcsp.mapper.EntityMapper;
import com.example.dlcsp.model.MentorChatMessage;
import com.example.dlcsp.repository.MentorMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorMessageRepository mentor;

    public List<MentorChatMessage> getHistory() {
        return mentor.findAllByOrderByPositionAsc().stream()
                .map(EntityMapper::toMessage)
                .toList();
    }
}