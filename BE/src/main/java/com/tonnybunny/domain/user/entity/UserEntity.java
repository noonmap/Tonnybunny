package com.tonnybunny.domain.user.entity;


import com.tonnybunny.common.entity.CommonEntity;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import com.tonnybunny.domain.alert.entity.AlertSettingsEntity;
import com.tonnybunny.domain.bunny.entity.BunnyApplyEntity;
import com.tonnybunny.domain.bunny.entity.BunnyEntity;
import com.tonnybunny.domain.bunny.entity.BunnyQuotationEntity;
import com.tonnybunny.domain.point.entity.PointLogEntity;
import com.tonnybunny.domain.review.entity.ReviewEntity;
import com.tonnybunny.domain.schedule.entity.ScheduleEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyApplyEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyQuotationEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.tonnybunny.domain.user.dto.UserCodeEnum.헬퍼;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_table")
public class UserEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_seq")
	private Long seq;

	private String email;
	private String password;
	private String phoneNumber;
	private String nickName;

	private Long point = 0L;
	private Integer reportCount = 0;

	private String profileImagePath = File.separator + "noProfile.png";

	private String userCode;

	private Boolean isAgreement = true; // 약관 동의 여부(true)

	private Boolean isDeleted = false;
	@OneToMany(mappedBy = "user")
	private List<BlockEntity> blockUserList = new ArrayList<>(); // 차단한 유저
	@OneToMany(mappedBy = "user")
	private List<FollowEntity> followUserList = new ArrayList<>(); // 팔로잉 목록
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private HelperInfoEntity helperInfo;                            // 헬퍼 정보
	// 예약통역
	@OneToMany(mappedBy = "client")
	private List<YTonnyEntity> yTonnyNotiList = new ArrayList<>(); // 예약통역공고리스트
	@OneToMany(mappedBy = "helper")
	private List<YTonnyApplyEntity> yTonnyNotiHelperList = new ArrayList<>(); // 예약통역공고신청자리스트
	// 스케쥴
	@OneToMany(mappedBy = "user")
	private List<ScheduleEntity> scheduleList = new ArrayList<>(); // 고객 스케쥴 리스트

	//	@OneToMany(mappedBy = "client")
	//	private List<YTonnyHistoryEntity> yTonnyClientResultList = new ArrayList<>(); // 예약통역 결과 고객
	//
	//	@OneToMany(mappedBy = "helper")
	//	private List<YTonnyHistoryEntity> yTonnyHelperResultList = new ArrayList<>(); // 예약통역 결과 헬퍼
	//
	//	// 즉시통역
	//	@OneToMany(mappedBy = "client")
	//	private List<JTonnyHistoryEntity> jTonnyClientResultList = new ArrayList<>(); // 즉시통역 결과 고객
	//
	//	@OneToMany(mappedBy = "helper")
	//	private List<JTonnyHistoryEntity> jTonnyHelperResultList = new ArrayList<>(); // 즉시통역 결과 헬퍼
	// 포인트
	@OneToMany(mappedBy = "user")
	private List<PointLogEntity> pointLogList = new ArrayList<>(); // 포인트 사용내역 리스트
	// 알람
	@OneToMany(mappedBy = "user")
	private List<AlertLogEntity> alertLogList = new ArrayList<>(); // 알람 로그 리스트
	@OneToOne(mappedBy = "user")
	private AlertSettingsEntity alertSettings; // 알람 세팅 설정
	// 리뷰
	@OneToMany(mappedBy = "user")
	private List<ReviewEntity> reviewList = new ArrayList<>(); // 리뷰 리스트
	//	@OneToMany(mappedBy = "client")
	//	private List<ChatRoomEntity> clientChatRoomList = new ArrayList<>(); // 고객 쪽 채팅방 리스트

	// 채팅방
	//	@OneToMany(mappedBy = "helper")
	//	private List<ChatRoomEntity> helperChatRoomList = new ArrayList<>(); // 헬퍼 쪽 채팅방 리스트
	//	@OneToMany(mappedBy = "user")
	//	private List<ChatLogEntity> chatLogList = new ArrayList<>(); // 채팅 로그 리스트
	@OneToMany(mappedBy = "user")
	private List<BunnyEntity> bunnyNotiList = new ArrayList<>(); // 번역 공고 리스트

	// 번역 공고
	@OneToMany(mappedBy = "user")
	private List<BunnyApplyEntity> bunnyNotiHelperList = new ArrayList<>(); // 번역 공고 신청 헬퍼 리스트
	@OneToMany(mappedBy = "client")
	private List<BunnyQuotationEntity> clientBunnyQuotationList = new ArrayList<>(); // 고객 측 번역 요청 견적서 리스트

	// 번역 요청 견적서 관련
	@OneToMany(mappedBy = "helper")
	private List<BunnyQuotationEntity> helperBunnyQuotationList = new ArrayList<>(); // 헬퍼 측 번역 요청 견적서 리스트
	@OneToMany(mappedBy = "client")
	private List<YTonnyQuotationEntity> clientYTonnyQuatationList = new ArrayList<>(); // 고객 측 예약 통역 요청 견적서 리스트

	// 예약 통역 견적서 관련
	@OneToMany(mappedBy = "helper")
	private List<YTonnyQuotationEntity> helperYTonnyQuotationList = new ArrayList<>(); // 헬퍼 측 예약 통역 요청 견적서 리스트


	@Builder
	public UserEntity(String email, String password, String phoneNumber, String nickName, String userCode) {
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.nickName = nickName;
		this.userCode = userCode;
		this.profileImagePath = File.separator + "noProfile.png";
		this.point = 10000L;
	}


	public void setPoint(Long point) {
		this.point = point;
	}


	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}


	public void deleteUserInfo() {
		this.isDeleted = true;
	}


	public void updateProfileImage(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}


	public void updateNickName(String nickName) {
		this.nickName = nickName;
	}


	public void changeUserCode() {
		this.userCode = 헬퍼.getUserCode();
	}


	public void updatePassword(String password) {
		this.password = password;
	}

}
