package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.HelperInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * possibleLanguageList     : 언어공통코드가 담긴 리스트
 * certificateList          : 자격증 정보(언어공통코드, 자격증 이름, 내용)가 담긴 리스트
 *
 * oneLineIntroduction      : 한 줄 소개
 * introduction             : 본인소개
 * HelperInfoImageList      : 헬퍼 정보 이미지 경로가 담긴 리스트
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class HelperInfoRequestDto {

	/* 헬퍼 유저 회원가입 */

	private List<PossibleLanguageDto> possibleLanguageList = new ArrayList<>();

	private List<CertificateRequestDto> certificateList = new ArrayList<>();

	/* 헬퍼 유저 회원정보 수정 */
	private String oneLineIntroduction;
	private String introduction;
	private Integer unitPrice;
	private List<HelperInfoImageRequestDto> helperInfoImageReqeustDtoList = new ArrayList<>(); // 그럼 이거 쓸 일 없을듯
	private List<Long> deleteHelperInfoImageList = new ArrayList<>(); // 삭제될 이미지 리스트 시퀀스만 넘기기


	/**
	 * 1. 빌더패턴으로 작성
	 * 2. HelperInfoEntity 내부의 비즈니스 로직(팩토리 메소드)으로 인스턴스 생성 후 반환
	 */
	public HelperInfoEntity toEntity(HelperInfoRequestDto helperInfoRequestDto) {

		return (HelperInfoEntity) new Object();
	}

}
