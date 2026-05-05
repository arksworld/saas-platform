package org.arksworld.saasPlatform.auth.kafka.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "processed_events")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedEvent {

    @Id
    @Column(name="eventId")
    private String eventId;
}