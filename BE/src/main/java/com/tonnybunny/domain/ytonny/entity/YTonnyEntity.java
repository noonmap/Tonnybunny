package com.tonnybunny.domain.ytonny.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class YTonnyEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "y_tonny_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity client;
	private Long helperSeq; // null 이 될수도 있으니

	private String title;
	private String content;

	private String startLangCode;
	private String endLangCode;

	private LocalDate estimateDate;
	private LocalTime estimateStartTime;
	private LocalTime estimateTime;
	private Integer estimatePrice;

	private String tonnySituCode;
	private String taskCode;
	private String taskStateCode;

	private Boolean isDeleted;

	// 헬퍼 신청 리스트
	@Builder.Default
	@OneToMany(mappedBy = "yTonny")
	private List<YTonnyApplyEntity> yTonnyApplyList = new ArrayList<>();

	// 헬퍼 견적서 리스트
	@Builder.Default
	@OneToMany(mappedBy = "yTonny")
	private List<YTonnyQuotationEntity> yTonnyQuotationList = new ArrayList<>();


	public void helperSeq(Long helperSeq) {
		this.helperSeq = helperSeq;
	}


	public void isDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public void yTonnyApplyList(List<YTonnyApplyEntity> yTonnyApplyList) {
		this.yTonnyApplyList = yTonnyApplyList;
	}

}
