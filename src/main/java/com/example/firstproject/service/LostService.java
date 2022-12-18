package com.example.firstproject.service;

import com.example.firstproject.dto.LostForm;
import com.example.firstproject.entity.Lost;
import com.example.firstproject.repository.LostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j //로깅
public class LostService {

    @Autowired//스프링부트가 미리 생성해놓은 객체를 가져다가 자동연결
    private LostRepository lostRepository;

    public Lost creates(LostForm lost, MultipartFile file) throws Exception {

        log.info(lost.toString());
        //System.out.println(form.toString());->로깅으로 대체

        //1. DTO를 Entity로 변환
        Lost article=lost.toEntity();
        log.info(article.toString());

        //2. Repository에 endtity를 db에저장

        //System.out.println(saved.toString());

        String projectPath=System.getProperty("user.dir")+"\\src\\main\\webapp";

        UUID uuid= UUID.randomUUID();

        String fileName=uuid+"_"+file.getOriginalFilename();

        File saveFile=new File(projectPath,fileName);

        file.transferTo(saveFile);

        article.setFilename(fileName);
        article.setFilepath("/webapp/"+fileName);


        Lost saved=lostRepository.save(article);
        log.info(saved.toString());

        return saved;
    }


    public List<Lost> show() {
        //1. id로 데이터를 가져옴 db에서 id값을 조회
        List<Lost> articleEntity= lostRepository.findAll();

        return articleEntity;
    }


    public Lost update(Long id,LostForm lost,MultipartFile file) throws Exception {
        //수정할 데이터 가져오기
        Lost articleEntity=lostRepository.findById(id).orElse(null);


        articleEntity.setId(lost.getId());
        articleEntity.setTitle(lost.getTitle());
        articleEntity.setContent(lost.getContent());
        articleEntity.setLocationDetail(lost.getLocationDetail());
        articleEntity.setLat(lost.getLat());
        articleEntity.setLng(lost.getLng());
        articleEntity.setLostedDate(lost.getLostedDate());

        if(!file.isEmpty()){
            String projectPath=System.getProperty("user.dir")+"\\src\\main\\webapp";

            UUID uuid= UUID.randomUUID();

            String fileName=uuid+"_"+file.getOriginalFilename();

            File saveFile=new File(projectPath,fileName);

            file.transferTo(saveFile);

            articleEntity.setFilename(fileName);  //articleEntity--> target으로 바꾸니깐 Time에러 사라짐
            articleEntity.setFilepath("/webapp/"+fileName);
        }



        return articleEntity;
    }


    public void delete(Long id, RedirectAttributes rttr) {
        log.info("삭제 요청");

        //1. 삭제 대상을 가져옴
        Lost target=lostRepository.findById(id).orElse(null);
        log.info(target.toString());

        //2. 대상을 삭제 한다
        if (target!=null){
            lostRepository.delete(target); //delete sql문 사용
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다."); //휘발성 데이터
        }
    }


    public Lost getById(Long id) {
        Lost target=lostRepository.findById(id).orElse(null);

        return target;
    }
}
