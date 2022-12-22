package com.example.firstproject.mapper;

import com.example.firstproject.dto.CreateAcquiredBoard;
import com.example.firstproject.dto.AcquiredBoardResponse;
import com.example.firstproject.entity.AcquiredBoard;
import com.example.firstproject.entity.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class AcquiredBoardMapper {

    public AcquiredBoard mapToEntity(CreateAcquiredBoard dto, MemberEntity writer, String originalFilename, String storeName) {
        return AcquiredBoard.builder()
                .writer(writer)
                .title(dto.getTitle())
                .content(dto.getContent())
                .lat(dto.getLat())
                .lng(dto.getLng())
                .locationDetail(dto.getLocationDetail())
                .acquiredDate(dto.getAcquiredDate())
                .imgName(originalFilename)
                .storeName(storeName)
                .build();
    }

    public AcquiredBoardResponse mapToDto(AcquiredBoard entity) {
        return AcquiredBoardResponse.builder()
                .id(entity.getId())
                .writerId(entity.getWriter().getId())
                .email(entity.getWriter().getEmail())
                .title(entity.getTitle())
                .content(entity.getContent())
                .lat(entity.getLat())
                .lng(entity.getLng())
                .locationDetail(entity.getLocationDetail())
                .acquiredDate(entity.getAcquiredDate())
                .imgName(entity.getImgName())
                .storeName(entity.getStoreName())
                .createAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .build();
    }

}
