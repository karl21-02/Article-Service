package com.project.blog.global.jpa;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// jpa에서 Entity선언 시 생성자 함수 필수 생성!
@MappedSuperclass // 이 클래스를 상속받는 모든 객체에게 필요한 공통 정보가 필요할때(시간 정보 2개)
@EntityListeners(AuditingEntityListener.class) // EntityListeners란 JPA Entity에서 이벤트가 발생할 때마다 특정 로직을 실행시킬 수 있는 어노테이션. 시간 정보가 생성, 변경 시 자동 저장!!
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // strategy 사용시 기본 키 생성을 DB에 위임
    private Long id;
    @CreatedDate // 데이터가 생성된 시간 정보
    private LocalDateTime createdDate;
    @LastModifiedDate // 데이터가 마지막으로 수정된 시간 정보
    private LocalDateTime modifiedDate;
}
