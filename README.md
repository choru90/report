# Spring Boot jpa API Boilerplate
- spring boot boilerplate 프로젝트

## 구성
Spring Boot & JPA & flyway & querydsl 이용하여 RESTful API 전용 프로젝트

### 폴더 구조
공통구조와 feature 도메인 구조

__src/main/java/net.huray 부분__
* project
  * common: 공통 영역
    * code: 공통 코드 열거형 
    * config: 공통 구성 관련 - WebConfig 등
    * constant: 공통 상수값 관련
    * controller: 공통 컨틀러
    * data: 데이터 관련 
      * dto: 공통 DTO 클래스
      * entity: 공통 엔티티 클래스
      * response: 공통 응답 클래스
    * exception: 공통 예외 코드 및 처리
    * filter: 공통 필터
    * util: 공통 유틸
  * health: feature 도메인 - 예시
    * data: 데이터 관련
      * dto: feature DTO 클래스
      * entity: feature 엔티티 클래스
      * repository: feature 리포지토리 클래스
    * service: feature 서비스 
    * HealthController.java: 컨트롤러 클래스
  * application.java: 프로젝트 시작 클래스

__src/main/resources 부분__
* db.migration: 데이터베이스 마이그레이션 flyway 경로
  * V1__init.sql: flyway V1 init.sql 파일
* application.yml: spring application 설정 yml 파일 - 환경변수
* logback.xml: log4j 로그 xml 설정 파일 - 액티브프로필에 따른 설정
* logback-dev.xml: logback 액티브프로필 세부 설정, 로그 출력 방식, 로그로테이션 등


### 사용 라이브러리
build.gradle 참고하여 작성 (2022-09-12 기준) 
* JAVA 11
#### plugin
  * 'org.springframework.boot' version 2.6.7
  * 'com.ewerk.gradle.plugins.querydsl' version 1.0.10 
#### dependencies
  * starter 관련
    * spring-boot-starter-data-jpa:2.6.7
    * spring-boot-starter-jdbc:2.6.7
    * spring-boot-starter-validation:2.6.7
    * spring-boot-starter-web:2.6.7
    * spring-boot-starter-actuator:2.6.7
    * spring-boot-starter-webflux:2.6.7
    * spring-boot-starter-test:2.6.7 - Unit Test 부분 적용
  * Database > dbdriver - mysql & mariadb, 필요시 추가
    * mysql:mysql-connector-java:8.0.28
    * org.mariadb.jdbc:mariadb-java-client:2.7.5
  * Database > flyway DB 마이그레이션 관리
    * org.flywaydb:flyway-core:8.0.5
  * Database > querydsl
    * com.querydsl:querydsl-jpa:5.0.0 - 버전 변수 명시
  * micrometer & prometheus
    * io.micrometer:micrometer-registry-prometheus:1.8.4
  * log4j - 취약점 대응 
    * ch.qos.logback:logback-core:1.2.10
    * ch.qos.logback:logback-classic:1.2.10
    * org.slf4j:slf4j-api:1.7.32
    * org.slf4j:jul-to-slf4j:1.7.32
    * org.apache.logging.log4j:log4j-to-slf4j:2.17.1
    * org.apache.logging.log4j:log4j-api:2.17.1
  * Unit Test - junit-jupiter 적용 
    * testImplementation org.springframework.boot:spring-boot-starter-test:2.6.7
      * exclude module: 'junit'
    * testImplementation org.junit.jupiter:junit-jupiter-api:5.8.2
    * testImplementation org.junit.jupiter:junit-jupiter-params:5.8.2
    * testImplementation org.junit.jupiter:junit-jupiter-engine:5.8.2
    
## 초기 세팅
프로젝트 초기 세팅 관련 설정법 기술

### STEP1 - 프로젝트명
#### STEP1-1 /src/main/resources/application.yml
```yaml
spring:
  application:
    name: project_name # FIXME STEP1 - 프로젝트명
```
* 프로젝트명 적용

#### STEP1-2 /build.gradle
```groovy
group = 'net.huray.project_name' /* FIXME STEP1 - 프로젝트명 */
version = '0.0.1-SNAPSHOT'
```
* 프로젝트명 적용, 버전 확인

### STEP2 - 프로젝트 폴더 & 패키지명 변경
#### STEP2-1 /settings.gradle
```groovy
rootProject.name = 'boilerplate-springboot-jpa-flyway-querydsl' /* FIXME STEP1 - 프로젝트명 */
```
* 프로젝트 폴더명으로 변경

#### STEP2-2 java/net/huray/project Refactor 패키지 경로 프로젝트명 적용
* `java/net/huray/project` 패키지 경로 중 `project` 부분 프로젝트명 적용하여 일괄 변경

#### STEP2-3 프로젝트 전체 폴더명 변경
* `boilerplate-springboot-jpa-flyway-querydsl` 파일명 프로젝트명으로 변경
  * STEP2-2 적용한 것과 동일하게 

### STEP3 - 환경변수 세팅
#### STEP3-1 /src/main/resources/application.yml
```yaml
spring: 
  ...omited...
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: ${DB_URL}        # TODO STEP3 - 환경변수
    username: ${DB_USER}  # TODO STEP3 - 환경변수
    password: ${DB_PASS}  # TODO STEP3 - 환경변수
  ...omited...
  flyway:
    enabled: true
    baseline-on-migrate: true # flyway_shcema_history 자동 생성 옵션 true, 이미 있는 경우 false
    url: ${DB_URL}        # TODO STEP3 - 환경변수
    user: ${DB_USER}      # TODO STEP3 - 환경변수
    password: ${DB_PASS}  # TODO STEP3 - 환경변수
  ...omited...
```
* 각종 환경 변수 IntelliJ 실행 설정 부분 환경변수 세팅
  * 데이터베이스 연결 정보 및 기타 설정값 application.yml 값 참고

### STEP4 - 데이터베이스 관련

#### STEP4-1 /build.gradle 라이브러리 데이터베이스 관련 확인
```groovy
dependencies {
    ...omitted...
    
    /* -- Database -- */
    // dbdriver FIXME STEP4 - 데이터베이스
    implementation 'mysql:mysql-connector-java:8.0.28'
    implementation 'org.mariadb.jdbc:mariadb-java-client:2.7.5'
    /* runtimeOnly 'com.h2database:h2' // TODO H2 필요시 사용 */
    
    ...omitted...
}
```
* 사용할 데이터베이스 연결 라이브러리 확인

#### STEP4-2 /src/main/resources/application.yml 
```yaml
  jpa:
    database: mysql                                         # FIXME STEP4 - 데이터베이스
    database-platform: org.hibernate.dialect.MySQL8Dialect  # FIXME STEP4 - 데이터베이스
    hibernate:
      ddl-auto: validate                                    # FIXME STEP4 - 데이터베이스
    show-sql: true
    generate-ddl: false
    properties:
      hibernate:
        format_sql: true
```
* database 종류 및 platform 관련 설정
  * 데이터베이스 종류와 버전에 따라 다르므로 공식 문서 참고 

### STEP5 - 액티브프로파일
* jvm active profile 값 설정, IntelliJ 설정으로 처리
  * 기본 설정값 관련해서는 예시용으로 작성한 logback-dev.xml `dev` 로 설정해야 작동

## DB 마이그레이션 JPA 적용법
* [Spring Boot > JPA & FLYWAY 적용](https://huraypositive.atlassian.net/l/cp/gMXac95v)

## Prometheus 적용법
* [Spring Boot > Prometheus & Grafana 적용 서버 성능 모니터링 - 1부 기초 설정](https://huraypositive.atlassian.net/wiki/spaces/Backend/pages/2439086085/Spring+Boot+Prometheus+Grafana+-+1?atlOrigin=eyJpIjoiMDRiN2IyOWUzY2Q5NDU2NGI4MTliMzI1ZmZhNThkYmIiLCJwIjoiYyJ9)

## 서버 시간 관련 설정 
```java
@SpringBootApplication
public class Application {

  @PostConstruct
  public void config() {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
```
* `TimeZone.getTimeZone("Asia/Seoul")` 설정 - 백엔드팀 협약

## CORS 설정

### WebConfig 클래스 설정
/src/main/java/net/huray/project/common/config/WebConfig.java
```java
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

  /**
   * CORS 모두 개방 정책
   * @param registry CORS 레지스트리 설정 객체
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("*")
        .allowedHeaders("*");
  }
}
```
* 현재 기본 설정은 API 전용이므로 모든 주소와 메소드에 열려있음

## Test 방식

* Unit Test, JMeter 사용 관련 내용
  * [Unit Test](https://pool-olive-3e4.notion.site/Unit-Test-9ba14ff3b3b14f0b891ebc02c5548d93)
  * [JMeter](https://huraypositive.atlassian.net/wiki/spaces/dev/pages/2354577486/Jmeter)
