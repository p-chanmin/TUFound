package com.example.firstproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "lost_id")
    private Long id;

    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;



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
