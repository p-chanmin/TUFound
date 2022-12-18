package com.example.firstproject.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAcquiredBoard {

    private Long writerId;
    private String title;
    private String content;
    private String lat;
    private String lng;
    private String locationDetail;
    private String acquiredDate;
    private MultipartFile imgFile;

}
