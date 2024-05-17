package com.runonground.model.dao;

import java.util.List;

import com.runonground.model.dto.FutsalMatch;
import com.runonground.model.dto.FutsalRecruitPost;

public interface FutsalDao {

	// 매칭 등록
	public void insertPost(FutsalMatch futsalMatch);

	// 매칭글 상세 보기
	public FutsalMatch readMatch(int id);
	
	// 풋살 인원 모집글 등록하기
	public void findMember(FutsalRecruitPost futsalRecruitPost);
	
	// 풋살 모집 글 전체 조회
	public List<FutsalRecruitPost> selectAllRecruit(String teamName);
	
	// 모집 글 하나 불러오기
	public FutsalRecruitPost selectOneRecruit(int id);
	
	// 모집 글 삭제하기
	public void deleteRecruit(int id);
	
	// 모집 글 수정하기
	public void updateRecruit(FutsalRecruitPost futsalRecruitPost);
}
