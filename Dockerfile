# 이미지
FROM gradle:8.1.1-jdk21 AS build
# 소스코드 복사 후 빌드
WORKDIR /app
COPY . .
# test 태스크 제외 (시간 단축)
RUN ./gradlew clean build -x test

FROM eclipse-temurin:21
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar", "app.jar"]