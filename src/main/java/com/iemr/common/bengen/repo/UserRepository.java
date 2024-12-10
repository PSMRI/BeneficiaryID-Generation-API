package com.iemr.common.bengen.repo;

import org.springframework.data.repository.CrudRepository;

import com.iemr.common.bengen.data.user.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUserID(Long userID);

}
