package com.example.dlcsp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "mentor_messages")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class MentorMessageEntity {
    @Id
    private String id;
    @Column(name = "sort_order")
    private int position;
    private String sender;
    @Column(name = "message_text", length = 2000)
    private String messageText;
    @Column(name = "sent_time")
    private String sentTime;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "mentor_options", joinColumns = @JoinColumn(name = "message_id"))
    @OrderColumn(name = "option_index")
    @Column(length = 200)
    private List<String> options;
}