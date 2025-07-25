package ru.kpfu.itis.cmsforblogs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaNotificationMessage {
    private String consumerUsername;
    private Long newPostId;
}
