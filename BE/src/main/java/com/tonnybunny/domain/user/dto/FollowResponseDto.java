package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.FollowEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * seq                  : 반환하는 유저의 키 값
 * followedUserSeq      : 팔로우한 유저의 키 값
 */
@Data
public class FollowResponseDto {

	private Long seq;
	private Long followedUserSeq;


	public static FollowResponseDto fromEntity(FollowEntity follow) {

		return new FollowResponseDto();
	}


	public static List<FollowResponseDto> fromEntityList(List<FollowEntity> followList) {
		List<FollowResponseDto> result = new ArrayList<>();

		for (FollowEntity follow : followList) {
			FollowResponseDto followResponseDto = fromEntity(follow);
			result.add(followResponseDto);
		}

		return result;

	}

}
