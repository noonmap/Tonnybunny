package com.tonnybunny.domain.ytonny.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class YTonnyNotiEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "y_tonny_noti_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity client;
	private Long helperSeq;

	private String title;
	private LocalDate estimateDate;
	private LocalTime estimateStartTime;
	private LocalTime estimateTime;
	private Integer estimatePrice;
	private String content;

	private String startLangCode;
	private String endLangCode;
	private String tonnySituCode;
	private String taskCode;
	private String taskStateCode;

	// 헬퍼 신청 리스트
	@OneToMany(mappedBy = "yTonnyNoti")
	private List<YTonnyNotiHelperEntity> yTonnyNotiHelperList = new ArrayList<>();

	// 헬퍼 견적서 리스트
	@OneToMany(mappedBy = "yTonnyNoti")
	private List<YTonnyQuotationEntity> yTonnyQuotationList = new ArrayList<>();

}
