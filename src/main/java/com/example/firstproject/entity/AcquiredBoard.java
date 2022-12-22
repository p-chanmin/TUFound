package com.example.firstproject.entity;

import com.example.firstproject.dto.UpdateAcquiredBoard;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class AcquiredBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acquired_id")
    private Long id;

    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity writer;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false)
    private String content;

    @Setter
    @Column(nullable = false)
    private String lat;

    @Setter
    @Column(nullable = false)
    private String lng;

    @Setter
    @Column(nullable = false)
    private String locationDetail;

    @Setter
    @Column(nullable = false)
    private String acquiredDate;

    @Setter
    private String imgName;

    @Setter
    private String storeName;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public void update(UpdateAcquiredBoard dto, String imgName, String storeName) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.lat = dto.getLat();
        this.lng = dto.getLng();
        this.locationDetail = dto.getLocationDetail();
        this.acquiredDate = dto.getAcquiredDate();
        this.imgName = imgName;
        this.storeName = storeName;
    }
}
