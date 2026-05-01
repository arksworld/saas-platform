package org.arksworld.saasPlatform.tenant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "shards")
@Data
public class Shard {

    @Id
    private String shardId;

    private String dbUrl;

    private int maxCapacity;

    private int currentLoad;

    private String status;
}