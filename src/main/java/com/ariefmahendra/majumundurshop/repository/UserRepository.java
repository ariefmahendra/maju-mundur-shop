package com.ariefmahendra.majumundurshop.repository;

import com.ariefmahendra.majumundurshop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

    @Query("select u from UserEntity u join PurchaseEntity p on u.id = p.user.id join PurchaseDetailEntity pd on p.id = pd.purchase.id join ProductEntity pr on pd.product.id = pr.id")
    List<UserEntity> findUserBoughtProducts();
}
