package com.example.firstproject.dto;

import com.example.firstproject.entity.Lost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class LostForm {

    private Long id;
    private String title;
    private String content;

    private String lat;

    private String lng;

    private String filename;

    private String filepath;

    private String locationDetail;

    private String lostedDate;



    public Lost toEntity() {

        return new Lost(id,title,content,lat,lng,filename,filepath,locationDetail,lostedDate);
    }
}
