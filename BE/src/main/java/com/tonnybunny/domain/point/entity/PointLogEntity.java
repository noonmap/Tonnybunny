package com.tonnybunny.domain.point.entity;


import com.tonnybunny.common.entity.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor // @Builder와 같이 씀
@Builder
@Table(name = "point_log_table")
public class PointLogEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "point_log_seq")
	private Long seq;

	private int amount;

	private String typeCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user; // M:1 매핑

	//	@Column(name = "created_at", insertable = false, updatable = false)
	//	private LocalDateTime createdAt;

}