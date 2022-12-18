package com.example.firstproject.controller;

import com.example.firstproject.dto.CreateAcquiredBoard;
import com.example.firstproject.dto.AcquiredBoardResponse;
import com.example.firstproject.dto.UpdateAcquiredBoard;
import com.example.firstproject.service.AcquiredBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/acquired-board")
public class AcquiredBoardController {

    @Autowired
    AcquiredBoardService acquiredBoardService;

    @PostMapping()
    public ResponseEntity<AcquiredBoardResponse> saveAcquiredBoard(CreateAcquiredBoard dto) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(acquiredBoardService.save(dto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<AcquiredBoardResponse> getAcquiredBoardById(@PathVariable Long id) {
        return ResponseEntity.ok(acquiredBoardService.getById(id));
    }

    @GetMapping()
    public ResponseEntity<List<AcquiredBoardResponse>> getAcquiredBoardList() {
        return ResponseEntity.ok(acquiredBoardService.getAll());
    }

    @GetMapping("/image/{storeName}")
    public ResponseEntity<Resource> getImageByName(@PathVariable String storeName) throws MalformedURLException {
        return ResponseEntity.ok(acquiredBoardService.getImage(storeName));
    }

    @PutMapping()
    public ResponseEntity<AcquiredBoardResponse> updateAcquiredBoard(UpdateAcquiredBoard dto) throws IOException {
        return ResponseEntity.ok(acquiredBoardService.update(dto));
    }

    @DeleteMapping("/{id}")
    public void deleteAcquiredBoardById(@PathVariable Long id) {

        acquiredBoardService.deleteById(id);
    }

}
