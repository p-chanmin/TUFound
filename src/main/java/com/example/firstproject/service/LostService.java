package com.example.firstproject.service;

import com.example.firstproject.dto.LostForm;
import com.example.firstproject.entity.Lost;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.LostRepository;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j //로깅
public class LostService {

    @Autowired//스프링부트가 미리 생성해놓은 객체를 가져다가 자동연결
    private LostRepository lostRepository;

    @Autowired
    MemberRepository memberRepository;

    public LostForm creates(LostForm lost, MultipartFile file) throws Exception {

        log.info(lost.toString());

        Lost article = lost.toEntity();
        log.info(article.toString());

        //writer 추가
        Member writer = memberRepository.findById(lost.getWriterId()).orElseThrow(EntityNotFoundException::new);

        String projectPath=System.getProperty("user.dir")+"\\src\\main\\webapp";

        UUID uuid= UUID.randomUUID();

        String fileName=uuid+"_"+file.getOriginalFilename();

        File saveFile=new File(projectPath,fileName);

        file.transferTo(saveFile);

        article.setFilename(fileName);
        article.setFilepath("/webapp/"+fileName);

        //writer 추가
        article.setWriter(writer);


        Lost saved=lostRepository.save(article);
        log.info(saved.toString());

        return LostForm.toDto(saved);
    }


    public void show(Model model) {

        List<Lost> articles= lostRepository.findAll();
        List<LostForm> forms = articles.stream().map(a->{
            return LostForm.toDto(a);
        }).collect(Collectors.toList());

        //2. 가져온 Article 묶음을 뷰로 전달!
        model.addAttribute("lostList",forms);
    }

    public void edit(Long id, Model model) {
        //수정할 데이터 가져오기
        Lost articleEntity=lostRepository.findById(id).orElse(null);

        //model 등록
        model.addAttribute("lost",articleEntity);
    }


    public Lost update(Long id,LostForm lost,MultipartFile file) throws Exception {
        //수정할 데이터 가져오기
        Lost articleEntity=lostRepository.findById(id).orElse(null);

        //writer 추가
        Member writer = memberRepository.findById(lost.getWriterId()).orElseThrow(EntityNotFoundException::new);
        articleEntity.setWriter(writer);

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

        lostRepository.save(articleEntity);


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


    public void getById(Long id, Model model) {


        Lost target=lostRepository.findById(id).orElse(null);

        model.addAttribute("lost",LostForm.toDto(target));
    }

    public long getCount(){
        return lostRepository.countBy();
    }
}
