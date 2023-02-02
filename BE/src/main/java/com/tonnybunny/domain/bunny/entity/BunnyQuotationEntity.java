package com.tonnybunny.domain.bunny.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BunnyQuotationEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bunny_quotation_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bunny_seq")
	private BunnyEntity bunny;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_seq")
	private UserEntity client;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "helper_seq")
	private UserEntity helper;

	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	private String title;
	private String content;

	private Integer totalPrice;

	private String bunnyQuotationStateCode;
	private String startLangCode;
	private String endLangCode;

	// 견적서
	@Builder.Default
	@OneToMany(mappedBy = "bunnyQuotation")
	private List<BunnyQuotationImageEntity> bunnyQuotationImageList = new ArrayList<>(); // 이미지 리스트

}
