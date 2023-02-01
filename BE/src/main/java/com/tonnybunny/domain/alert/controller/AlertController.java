package com.tonnybunny.domain.alert.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import com.tonnybunny.domain.alert.dto.AlertLogResponseDto;
import com.tonnybunny.domain.alert.dto.AlertSettingsDto;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import com.tonnybunny.domain.alert.entity.AlertSettingsEntity;
import com.tonnybunny.domain.alert.service.AlertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
@Api(tags = "알림 관련 API")
public class AlertController {

	private final AlertService alertService;


	/**
	 * MEMO : CREATE
	 * MARK : 계약이 성립되면 알람 생성
	 * ex) 즉시통역이 성립되었습니다. 오늘 통역예약이 있습니다. + 날짜, 시간 표기
	 *
	 * @param alertLogRequestDto : 대상 유저 seq, 카테고리 taskCode, 내용 content
	 * @return 생성 성공 여부
	 */
	@PostMapping("/log")
	@ApiOperation(value = "알림 생성 API", notes = "이벤트 발생 시, 알림 메세지를 생성한다.")
	public ResponseEntity<ResultDto<Boolean>> createAlertLog(@RequestBody AlertLogRequestDto alertLogRequestDto) {

		System.out.println("\ncreateAlertLog Controller !!");
		//		System.out.println("alertLogRequestDto : " + alertLogRequestDto);

		alertService.createAlertLog(alertLogRequestDto);
		//		System.out.println("isCreate : " + isCreate);

		//		System.out.println(ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess()));

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}


	/**
	 * MEMO : READ
	 * MARK : userSeq 에 따른 전체 알림 로그 목록을 반환 with pagination
	 *
	 * @param alertLogRequestDto : 대상 유저 seq, page, size
	 * @return 전체 알림 로그 목록
	 */
	@GetMapping("/log")
	@ApiOperation(value = "알림 목록 반환 API", notes = "userSeq 에 따라서 알림 목록을 반환한다.")
	public ResponseEntity<ResultDto<List<AlertLogResponseDto>>> getAlertLogList(AlertLogRequestDto alertLogRequestDto) {

		System.out.println("\ngetAlertLogList Controller !!");
		//		System.out.println("alertLogRequestDto : " + alertLogRequestDto);

		// service
		List<AlertLogEntity> alertLogList = alertService.getAlertLogList(alertLogRequestDto);
		//		System.out.println("alertLogList: " + alertLogList);

		// dto 로 변경
		List<AlertLogResponseDto> alertLogResponseDtoList = AlertLogResponseDto.fromEntityList(alertLogList);
		//		System.out.println("alertLogResponseDtoList : " + alertLogResponseDtoList);
		//		System.out.println(ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(alertLogResponseDtoList)));

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(alertLogResponseDtoList));

	}


	/**
	 * MARK : 알림 설정 조회
	 *
	 * @param userSeq : 대상 유저 seq
	 * @return
	 */
	@GetMapping("/settings/{userSeq}")
	@ApiOperation(value = "알림 설정 조회 API", notes = "사용자에 따른 알림 설정 값을 반환한다.")
	public ResponseEntity<ResultDto<AlertSettingsDto>> getAlertSettings(@PathVariable Long userSeq) {

		System.out.println("\ngetAlertSettings Controller !!");

		AlertSettingsEntity alertSettings = alertService.getAlertSettings(userSeq);
		AlertSettingsDto alertSettingsDto = AlertSettingsDto.fromEntity(alertSettings);
		System.out.println("alertSettings : " + alertSettings);
		System.out.println("alertSettingsDto : " + alertSettingsDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(alertSettingsDto));

	}


	/**
	 * MEMO : UPDATE
	 * MARK : 알림 읽음확인 수정
	 *
	 * @param alertLogSeq : 대상 알림 로그 seq
	 * @return 알림 읽음확인 수정 여부
	 */
	@PutMapping("/log/{alertLogSeq}")
	@ApiOperation(value = "알림 읽음 확인 수정 API", notes = "알림 읽었을 시, 읽었다고 표시 변경을 해준다.")
	public ResponseEntity<ResultDto<Boolean>> modifyAlertIsRead(@PathVariable Long alertLogSeq) {

		System.out.println("\nmodifyAlertIsRead Controller !!");

		alertService.modifyAlertIsRead(alertLogSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}


	/**
	 * MARK : 알림 설정 수정
	 *
	 * @param alertSettingsDto : 통역, 번역, 커뮤니티, 메시지(채팅) 알람
	 * @return 알림 설정 변경 여부
	 */
	@PutMapping("/settings")
	@ApiOperation(value = "알림 설정 수정 API", notes = "푸시 알림 설정을 변경한다.")
	public ResponseEntity<ResultDto<Boolean>> modifyAlertSettings(@RequestBody AlertSettingsDto alertSettingsDto) {

		System.out.println("\nmodifyAlertSettings Controller !!");
		System.out.println("alertSettingsDto : " + alertSettingsDto);

		alertService.modifyAlertSettings(alertSettingsDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}


	/**
	 * MEMO : DELETE
	 * MARK : 알림 삭제
	 *
	 * @param alertLogSeq : 대상 알림 로그 seq
	 * @return 알림 삭제 여부
	 */
	@DeleteMapping("/log/{alertLogSeq}")
	@ApiOperation(value = "알림 삭제 API", notes = "필요없는 알림을 삭제한다.")
	public ResponseEntity<ResultDto<Boolean>> deleteAlertLog(@PathVariable Long alertLogSeq) {

		System.out.println("\ndeleteAlertLog Controller !!");

		alertService.deleteAlertLog(alertLogSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}

}
