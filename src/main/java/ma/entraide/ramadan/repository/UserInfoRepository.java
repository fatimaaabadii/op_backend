package ma.entraide.ramadan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.entraide.ramadan.entity.UserInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {

    Optional<UserInfo> findByEmail(String userName);
    Optional<UserInfo> findById(Long id);
    List<UserInfo> findAll();
	void deleteById(Long id);}

   
