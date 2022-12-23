package com.example.firstproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor // 디폴트 생성자
@ToString
@Getter
@Setter
@Data
public class Lost extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String lng;

    @Column
    private String lat;

    @Column
    private String filename;

    @Column
    private String filepath;

    @Column
    private String locationDetail;

    @Setter
    @Column
    private String lostedDate;

}
