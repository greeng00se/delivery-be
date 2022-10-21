## 개요

- [우아한 객체지향](https://www.youtube.com/watch?v=dJ5C4qRqAgA) 유튜브와 오브젝트를 보고 주문을 위한 최소 기능 제품을 만들어보고 싶어 시작한 프로젝트입니다.

## 사용 기술

### [BE](https://github.com/greeng00se/delivery-be)

- Spring Boot, Spring Security, Spring Data JPA
- JUnit5, Spock

### Infra

- AWS EC2, S3, RDS, Beanstalk
- Jenkins
- Sonar Cloud
- Cloudflare Pages(FE)

### [FE](https://github.com/greeng00se/delivery-owner-fe)

- Nuxt3, pinia
- Vuetify3

## 구성도

![diagram](https://user-images.githubusercontent.com/58586537/191187654-b279daa9-3b56-45ae-9a76-0dc7bd9f6f40.png)

- 브랜치 전략은 Github flow를 사용하였습니다.
- jenkins를 사용하여 main 브랜치에 코드가 통합된다면 자동으로 빌드, 테스트, 배포까지 되도록 설정하였습니다.
- 추가 배치를 사용한 롤링 방식을 사용하여 무중단 배포를 할 수 있도록 설정하였습니다.

## 현재까지의 DB Diagram

![image](https://user-images.githubusercontent.com/58586537/197103808-4cfa3099-2e8c-475d-b5b3-f08988191889.png)

- [dbdocs.io](https://dbdocs.io/thegreengoose7/Delivery?view=relationships)
