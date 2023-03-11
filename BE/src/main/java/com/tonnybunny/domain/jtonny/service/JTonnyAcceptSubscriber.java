package com.tonnybunny.domain.jtonny.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonnybunny.domain.jtonny.dto.JTonnyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Log4j2
@RequiredArgsConstructor
@Service
public class JTonnyAcceptSubscriber implements MessageListener {

	private final ObjectMapper objectMapper;
	private final SimpMessagingTemplate template;


	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {

			String msg = new String(message.getBody());
			JTonnyDto jTonnyDto = objectMapper.readValue(msg, JTonnyDto.class);

			log.info("jTonnyDto = {}", jTonnyDto);

			String url;
			// clientSeq 로 요청, "/sub/jtonny/apply/17"
			url = "/sub/jtonny/accept/" + jTonnyDto.getClient().getSeq();
			template.convertAndSend(url, jTonnyDto);

			// helperSeq 로 요청, "/sub/jtonny/apply/34"
			url = "/sub/jtonny/accept/" + jTonnyDto.getHelper().getSeq();
			template.convertAndSend(url, jTonnyDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
