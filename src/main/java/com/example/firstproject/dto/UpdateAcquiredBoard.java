package com.example.firstproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAcquiredBoard {

    private Long id;
    private String title;
    private String content;
    private String lat;
    private String lng;
    private String locationDetail;
    private String acquiredDate;
    private MultipartFile imgFile;

}
