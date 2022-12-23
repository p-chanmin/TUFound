package com.example.firstproject.dto;

import com.example.firstproject.entity.Lost;
import com.example.firstproject.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class LostForm {

    private Long id;

    private Long writerId;

    private String username;
    private String title;
    private String content;

    private String lat;

    private String lng;

    private String filename;

    private String filepath;

    private String locationDetail;

    private String lostedDate;



    public static LostForm toDto(Lost lost){
        return new LostForm(
                lost.getId(),
                lost.getWriter().getId(),
                lost.getWriter().getUsername(),
                lost.getTitle(),
                lost.getContent(),
                lost.getLat(),
                lost.getLng(),
                lost.getFilename(),
                lost.getFilepath(),
                lost.getLocationDetail(),
                lost.getLostedDate()
        );
    }
    public  Lost toEntity(){
        return new Lost(id,
                Member.builder().id(writerId).username(username).build(),
                title,
                content,
                lat,
                lng,
                filename,
                filepath,
                locationDetail,
                lostedDate
        );
    }
}
