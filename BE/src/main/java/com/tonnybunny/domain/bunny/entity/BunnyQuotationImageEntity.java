package com.tonnybunny.domain.bunny.entity;


import com.tonnybunny.common.entity.CommonEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "bunny_quotation_image_table")
public class BunnyQuotationImageEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bunny_quotation_image_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bunny_quotation_seq")
	private BunnyQuotationEntity bunnyQuotation;

	private String imagePath;

}
