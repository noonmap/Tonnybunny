package com.tonnybunny.domain.user.entity;


import com.tonnybunny.common.entity.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CertificateEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "certificate_seq")
	private Long seq;

	private String langCode;

	private String certName;
	private String content;

	private String isDeleted = "F";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "helper_info_seq")
	private HelperInfoEntity helperInfo;


	public CertificateEntity(String certName, String content, HelperInfoEntity helperInfo) {
		this.certName = certName;
		this.content = content;
		this.helperInfo = helperInfo;
	}


	public void deleteCertificate() {
		this.isDeleted = "T";
	}

}
