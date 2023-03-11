package com.tonnybunny.domain.alert.entity;


import com.tonnybunny.common.entity.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
@Table(name = "alert_settings_table")
public class AlertSettingsEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alert_settings_seq")
	private Long seq;

	private Boolean isAll;
	private Boolean isTonnyBunny;
	private Boolean isCommunity;
	private Boolean isChat;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user;


	public void update(Boolean isAll, Boolean isTonnyBunny, Boolean isCommunity, Boolean isChat) {
		this.isAll = isAll;
		this.isTonnyBunny = isTonnyBunny;
		this.isCommunity = isCommunity;
		this.isChat = isChat;
	}

}
