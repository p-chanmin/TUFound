package com.example.firstproject.service;

import com.example.firstproject.dto.CreateAcquiredBoard;
import com.example.firstproject.dto.AcquiredBoardResponse;
import com.example.firstproject.dto.UpdateAcquiredBoard;
import com.example.firstproject.entity.AcquiredBoard;
import com.example.firstproject.entity.Member;
import com.example.firstproject.mapper.AcquiredBoardMapper;
import com.example.firstproject.repository.AcquiredBoardRepository;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AcquiredBoardService {

    private String fileDir = System.getProperty("user.dir") + "/img/";
    @Autowired
    AcquiredBoardMapper acquiredBoardMapper;
    @Autowired
    AcquiredBoardRepository acquiredBoardRepository;
    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public AcquiredBoardResponse save(CreateAcquiredBoard dto) throws IOException {

        String originalFilename = originalFilename(dto.getImgFile());
        String storeFilename = storeFile(dto.getImgFile());
        Member writer = memberRepository.findById(dto.getWriterId()).orElseThrow(EntityNotFoundException::new);

        AcquiredBoard acquiredBoard = acquiredBoardMapper.mapToEntity(dto, writer, originalFilename,storeFilename);
        AcquiredBoard savedAcquiredBoard = acquiredBoardRepository.save(acquiredBoard);

        return acquiredBoardMapper.mapToDto(savedAcquiredBoard);
    }

    @Transactional
    public void deleteById(Long id) {
        acquiredBoardRepository.delete(getEntity(id));
    }

    public List<AcquiredBoardResponse> getAll() {

        List<AcquiredBoardResponse> acquiredBoardResponseList = new ArrayList<>();
        List<AcquiredBoard> acquiredBoardList = acquiredBoardRepository.findAll();

        for (AcquiredBoard acquiredBoard : acquiredBoardList) {
            AcquiredBoardResponse acquiredBoardResponse = acquiredBoardMapper.mapToDto(acquiredBoard);
            acquiredBoardResponseList.add(acquiredBoardResponse);
        }

        return acquiredBoardResponseList;
    }

    public AcquiredBoardResponse getById(Long id) {
        AcquiredBoard acquiredBoard = getEntity(id);

        return acquiredBoardMapper.mapToDto(acquiredBoard);
    }

    public Resource getImage(String storeName) throws MalformedURLException {
        return new UrlResource("file:" + getFullPath(storeName));
    }

    @Transactional
    public AcquiredBoardResponse update(UpdateAcquiredBoard dto) throws IOException {

        AcquiredBoard acquiredBoard = getEntity(dto.getId());
        String originalFilename = acquiredBoard.getImgName();
        String storeFilename = acquiredBoard.getStoreName();
        if (!dto.getImgFile().isEmpty()) {
            originalFilename = originalFilename(dto.getImgFile());
            storeFilename = storeFile(dto.getImgFile());
        }

        acquiredBoard.update(dto, originalFilename, storeFilename);

        return acquiredBoardMapper.mapToDto(acquiredBoard);
    }

    public AcquiredBoard getEntity(Long id) {

        return acquiredBoardRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public String storeFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = originalFilename(multipartFile);
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return storeFileName;
    }

    public String  originalFilename(MultipartFile multipartFile) {
        return multipartFile.getOriginalFilename();
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    public long getCount() {
        return acquiredBoardRepository.countBy();
    }
}
