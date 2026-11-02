package com.example.dlcsp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "simulation_scenarios")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class SimulationScenarioEntity {
    @Id
    private String id;
    private String type;
    private String title;
    private String difficulty;
    @Column(length = 1000)
    private String scenarioDescription;
    @Column(length = 500)
    private String mockSender;
    @Column(length = 2000)
    private String mockContent;
    @Column(length = 500)
    private String fakeLinkOrTarget;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "simulation_red_flags", joinColumns = @JoinColumn(name = "scenario_id"))
    @OrderColumn(name = "flag_index")
    @Column(length = 500)
    private List<String> redFlags;

    @Column(length = 2000)
    private String explanation;
    private String correctAction;
}