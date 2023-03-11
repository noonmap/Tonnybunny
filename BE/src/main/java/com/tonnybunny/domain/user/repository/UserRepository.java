package com.tonnybunny.domain.user.repository;


import com.tonnybunny.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByNickName(String nickName);
	Optional<UserEntity> findByPhoneNumber(String phoneNumber);

}
