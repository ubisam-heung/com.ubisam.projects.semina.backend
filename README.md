# Ubisam-Semina

## 개요
Spring Boot 기반의 REST API 예제 프로젝트입니다. JPA를 활용한 데이터 관리와 계층화된 구조로 유지보수성과 확장성을 고려했습니다.

## 폴더 구조

```
myownstudy/
├── mvnw, mvnw.cmd, pom.xml         # 프로젝트 설정
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ubisam/example2/
│   │   │        ├── Application.java            # Spring Boot 메인 클래스
│   │   │        ├── ApplicationApiConfig.java   # API 설정 클래스
│   │   │        ├── api/                        # REST API 컨트롤러 계층
│   │   │        └── domain/                     # 도메인(엔티티,리포지토리, 서비스)
│   │   └── resources/
│   │        └── application.properties          # 환경 및 DB 설정
│   └── test/
│        ├── java/
│        │   └── com/ubisam/example2/
│        │        └── ApplicationTests.java      # 테스트 코드
│        └── resources/
│             └── application.properties         # 테스트 환경 설정
└── target/
```

## 실행 방법
1. `mvnw` 또는 `./mvnw spring-boot:run`로 실행
2. `src/main/resources/application.properties`에서 DB 등 환경 설정 확인
3. 테스트는 `ApplicationTests.java` 참고

## 개발 환경
- Java 17 이상
- Spring Boot
- Maven
