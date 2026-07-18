package com.example.finance.repositary;

import com.example.finance.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserModel,Integer> {

    public UserModel findByEmailAndPassword(String tmail,String tpass);
}
