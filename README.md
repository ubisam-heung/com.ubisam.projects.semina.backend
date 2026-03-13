# Ubisam-Semina

## 개요
Spring Boot 기반의 세미나 backend 코드입니다.

## 폴더 구조

```
com.ubisam.projects.semina.backend/
├── mvnw, mvnw.cmd, pom.xml         # 프로젝트 설정
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ubisam/projects/semina/backend/
│   │   │        ├── Application.java            # Spring Boot 메인 클래스
│   │   │        ├── ApplicationApiConfig.java   # API 설정 클래스
│   │   │        ├── ApplicationStompConfig.java # Stomp 설정 클래스
│   │   │        ├── api/                        # REST API 컨트롤러 계층
│   │   │        ├── domain/                     # 도메인(엔티티,리포지토리, 서비스)
│   │   │        └── stomp/                      # Stomp 코드
│   │   └── resources/
│   │        └── application.properties          # 환경 및 DB 설정
│   └── test/
│        ├── java/
│        │   └── com/ubisam/projects/semina/backend/
│        │        ├── ApplicationTests.java      # 통합 테스트 코드
│        │        └── api/
│        │             ├── helloes/
│        │             │    ├── HelloDocs.java   # Hello API 문서화/테스트 유틸
│        │             │    └── HelloTests.java  # Hello API 테스트
│        │             └── worlds/
│        │                  ├── WorldDocs.java   # World API 문서화/테스트 유틸
│        │                  └── WorldTests.java  # World API 테스트
│        └── resources/
│             └── application.properties         # 테스트 환경 설정
└── target/
```

## 실행 방법
1. `.\mvnw spring-boot:run` 또는 `./mvnw spring-boot:run`로 실행
2. `src/main/resources/application.properties`에서 DB 등 환경 설정 확인
3. 테스트는 `.\mvnw test` 또는 `./mvnw test` 참고

## 개발 환경
- Java 17 이상
- Spring Boot
- Maven
