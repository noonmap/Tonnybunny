package com.tonnybunny.domain.ytonny.repository;


import com.tonnybunny.domain.ytonny.entity.YTonnyApplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface YTonnyApplyRepository extends JpaRepository<YTonnyApplyEntity, Long> {

	// 예약통역 신청 목록 리스트 반환 By 예약통역 공고 seq
	Page<YTonnyApplyEntity> findByyTonnySeqOrderByCreatedAt(Long yTonnySeq, Pageable pageable);

	// 상세 조회한 공고가 헬퍼 자신이 신청한건지 아닌지
	//	Optional<Boolean> findByHelperSeq(Long isApplyHelper);

}
