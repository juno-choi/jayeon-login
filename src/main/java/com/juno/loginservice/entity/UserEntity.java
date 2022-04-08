package com.juno.loginservice.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "users")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true, length = 10)
    private String userId;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 150)
    private String pw;

    @Column(nullable = false, length = 10)
    private String address1;
    @Column(nullable = false, length = 50)
    private String address2;
    @Column(nullable = false, length = 50)
    private String address3;

    @Column(nullable = false, length = 20)
    private String tel;

    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public UserEntity(@NonNull String userId, @NonNull String name, @NonNull String pw, @NonNull String address1, @NonNull String address2, @NonNull String address3, @NonNull String tel) {
        this.userId = userId;
        this.name = name;
        this.pw = pw;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.tel = tel;
    }
}
