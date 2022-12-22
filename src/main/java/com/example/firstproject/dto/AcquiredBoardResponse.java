package com.example.firstproject.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcquiredBoardResponse {

    private Long id;
    private Long writerId;
    private String email;
    private String title;
    private String content;
    private String lat;
    private String lng;
    private String locationDetail;
    private String acquiredDate;
    private String imgName;
    private String storeName;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
