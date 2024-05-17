package com.runonground.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runonground.model.dto.FutsalMatch;
import com.runonground.model.dto.FutsalRecruitPost;
import com.runonground.model.service.FutsalService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/futsal")
public class FutsalRestController {

	private FutsalService futsalService;
	
	public FutsalRestController(FutsalService futsalService) {
		this.futsalService = futsalService;
	}
	
	// 매칭 등록
	@PostMapping("/match")
	@Operation(summary = "매칭 등록")
	public ResponseEntity<Void> regist(@RequestBody FutsalMatch futsalMatch){
		futsalService.regist(futsalMatch);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	// 매칭글 상세 보기
	@GetMapping("/{id}")
	@Operation(summary = "매칭글 상세 보기")
	public ResponseEntity<FutsalMatch> detail(@PathVariable("id") int id){
		FutsalMatch futsalMatch = futsalService.readMatch(id);
		
		if(futsalMatch != null)
			return new ResponseEntity<FutsalMatch>(futsalMatch, HttpStatus.OK);
		
		return new ResponseEntity<FutsalMatch>(HttpStatus.BAD_REQUEST);
	}
	
	// 풋살 인원 모집글 등록하기
	@PostMapping("/board")
	@Operation(summary = "풋살 인원 모집글 등록하기")
	public ResponseEntity<Void> findMember(@RequestBody FutsalRecruitPost futsalRecruitPost, HttpSession session){
		String writer = (String) session.getAttribute("nickName");
		String team = (String) session.getAttribute("favoriteTeam");
		
		futsalRecruitPost.setAuthorName(writer);
		futsalRecruitPost.setTeamName(team);
		
		futsalService.findMember(futsalRecruitPost);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	// 모집 글 전체 불러오기
	@GetMapping("/board")
	@Operation(summary = "전체 모집 글 불러오기")
	public ResponseEntity<List<FutsalRecruitPost>> selectAllRecruit(HttpSession session){
		String teamName = (String) session.getAttribute("favoriteTeam");
		
		List<FutsalRecruitPost> list = futsalService.selectAllRecruit(teamName);
		return new ResponseEntity<List<FutsalRecruitPost>>(list, HttpStatus.OK);
	}
	
	// 모집글 하나만 불러오기
	@GetMapping("/board/{id}")
	@Operation(summary = "모집 글 하나만 불러오기")
	public ResponseEntity<FutsalRecruitPost> selectOneRecruit(@PathVariable("id") int id){
		FutsalRecruitPost post = futsalService.selectOneRecruit(id);
		return new ResponseEntity<FutsalRecruitPost>(post, HttpStatus.OK);
	}
	
	// 모집글 삭제하기
	@DeleteMapping("/board/{id}")
	@Operation(summary = "모집 글 삭제하기")
	public ResponseEntity<Void> deleteRecruit(@PathVariable("id") int id){
		futsalService.deleteRecruit(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 모집 글 수정하기
	@PutMapping("/board/{id}")
	@Operation(summary = "모집 글 수정하기")
	public ResponseEntity<Void> updateRecruit(@PathVariable("id") int id, @RequestBody FutsalRecruitPost futsalRecruitPost){
		futsalRecruitPost.setRecruitmentId(id);
		
		futsalService.updateRecruit(futsalRecruitPost);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
