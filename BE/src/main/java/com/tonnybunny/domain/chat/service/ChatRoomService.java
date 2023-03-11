package com.tonnybunny.domain.chat.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tonnybunny.domain.chat.entity.ChatRoomEntity;
import com.tonnybunny.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ChatRoomService {

	private final ChatRoomRepository chatRoomRepository;

	private final ChatSocketTextHandler chatSocketTextHandler;


	/**
	 * userSeq1과 userSeq2가 참여한 채팅방 Seq를 생성합니다.
	 * 이미 존재하는 채팅방이면 기존의 채팅방 Seq를 반환합니다.
	 *
	 * @param userSeq        참여자 1 Seq
	 * @param anotherUserSeq 참여자 2 Seq
	 * @return 채팅방 Seq
	 */
	public ChatRoomEntity getChatRoomSeq(Long userSeq, Long anotherUserSeq) {
		Long userLessSeq = Math.min(userSeq, anotherUserSeq);
		Long userLargerSeq = Math.max(userSeq, anotherUserSeq);
		Optional<ChatRoomEntity> chatRoomOptional = chatRoomRepository.findByUserLessSeqAndUserLargerSeq(userLessSeq, userLargerSeq);
		ChatRoomEntity chatRoom;
		if (chatRoomOptional.isEmpty()) { // 만들어진 방이 없으면 방 번호 새로 생성
			String roomSeq = UUID.randomUUID().toString();
			chatRoom = ChatRoomEntity.builder().seq(roomSeq).userLessSeq(userLessSeq).userLargerSeq(userLargerSeq).build();
			chatRoomRepository.save(chatRoom);
		} else { // 이미 있으면 기존의 방 번호 반환
			chatRoom = chatRoomOptional.get();
		}
		return chatRoom;
	}


	public Long getAnotherUserSeq(ChatRoomEntity chatRoom, Long userSeq) {
		return chatRoom.getUserLessSeq() == userSeq ? chatRoom.getUserLargerSeq() : chatRoom.getUserLessSeq();
	}


	/**
	 * userSeq가 참여한 채팅방의 Seq 목록을 반환합니다.
	 *
	 * @param userSeq
	 * @return 채팅방 Seq 목록
	 */
	public List<ChatRoomEntity> getChatRoomList(Long userSeq) {
		return chatRoomRepository.findByUserLessSeqOrUserLargerSeq(userSeq, userSeq);
	}


	/**
	 * roomSeq 채팅방에서 userSeq가 아직 읽지 않은 채팅메세지 수를 반환합니다.
	 *
	 * @param roomSeq
	 * @param userSeq
	 * @return
	 */
	public Integer getNotReadCount(String roomSeq, Long userSeq) {
		return chatSocketTextHandler.getNotReadCount(roomSeq, userSeq);
	}


	/**
	 * roomSeq 채팅방의 최신 채팅 메세지를 반환합니다.
	 *
	 * @param roomSeq
	 * @return
	 */
	public String getRecentMessage(String roomSeq) {
		return chatSocketTextHandler.getRecentMessage(roomSeq);
	}


	public List<String> getPreviousChatLog(String roomSeq) {
		ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
		return chatSocketTextHandler.getPreviousChatLog(roomSeq).stream().map(chat -> {
			try {
				return objectMapper.writeValueAsString(chat);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toList());
	}


	public List<String> getChatAlertLog(Long userSeq) {
		return chatSocketTextHandler.getChatAlertLog(userSeq);
	}


	public Boolean deleteChatAlertLog(Long userSeq, String roomSeq, String alertLogSeq) {
		return chatSocketTextHandler.deleteChatAlertLog(userSeq.toString(), roomSeq, alertLogSeq);
	}


	public Boolean deleteChatAlertLogByRoomSeq(Long userSeq, String roomSeq) {
		return chatSocketTextHandler.deleteChatAlertLogByRoomSeq(userSeq.toString(), roomSeq);
	}

}
