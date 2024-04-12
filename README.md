# 구인구직 사이트
![2024-03-11 12 13 52](https://github.com/9oj0e/project-job-plan/assets/126438339/f0b0e1f1-e70d-462f-bf9f-c3fdc937c9d3)

# 시연
## PPT
[KDT 2차 미니프로젝트 4조 최종(마무리).pdf](https://github.com/9oj0e/project-jobplan-v2/files/14957270/KDT.2.4.pdf)



# 진행상황 확인
(이미지를 클릭하시면 링크로 이동합니다.)
## [![v1 진행상황](https://github.com/9oj0e/project-job-plan/assets/126438339/f0b0e1f1-e70d-462f-bf9f-c3fdc937c9d3)](https://j0jh96.notion.site/v1-9ae1b6a3045d4d6cad3f35c540a660f0?pvs=74)
- v1 진행 상황 (완료)
## [![v2 진행상황](https://github.com/9oj0e/project-job-plan/assets/126438339/f0b0e1f1-e70d-462f-bf9f-c3fdc937c9d3)](https://j0jh96.notion.site/v2-264b24171b3848efa0279038d7a18528?pvs=25)
- v2 진행 상황

# 개발 환경
<span>
 <h3><img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Hand%20gestures/Eyes.png" width=30" /> IDE</h3>
 <img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white"/>
<img src="https://img.shields.io/badge/Visual_Studio_Code-0078D4?style=for-the-badge&logo=visual%20studio%20code&logoColor=white"/>
  <h3><img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Hand%20gestures/Eyes.png" width=30" /> FrameWork</h3>
 <img src="https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
 <img src="https://img.shields.io/badge/BootStrap-8A2BE2?style=for-the-badge&logo=BootStrap&logoColor=white"/>
  <h3><img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Hand%20gestures/Eyes.png" width=30" /> DB</h3>
   <img src="https://img.shields.io/badge/H2-FFA500?style=for-the-badge&logo=H2&logoColor=white"/>
   <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white"/>
  <h3><img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Hand%20gestures/Eyes.png" width=30" /> 사용기술</h3>
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white"/>
<img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white"/>
<img src="https://img.shields.io/badge/mustache-F7DF1E?style=for-the-badge&logo=Mustache&logoColor=white"/>
  <h3><img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Hand%20gestures/Eyes.png" width=30" /> 언어</h3>
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white"/>
 </span>
 <br>
  <br>
  <span>
 <h3>:컴퓨터:협업 툴</h3>
<img src="https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white"/>
<img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"/>
</span>
<br>

## 프로젝트 아키텍쳐 : RestAPI 설계

## 테이블 설계
![Screenshot_6](https://github.com/9oj0e/project-job-plan/assets/153602328/09a3287a-b0d2-48da-960a-96ddb952d411)

## 협업 전략
### GitHub
- Branch : 이름 이니셜 날짜 시간
- commit : 작업 내용에 대한 요약 담기
- comment : 문제를 겪은 부분 정리
- issue : 버그나 해결사항 올리기
### naming conventions [코드 컨밴션]
- 패키지 : lowerCase, domain별 패키지 만들기
- 파일 : camelCase
- 클래스/메서드/필드값 : pascalCase
- 일반 테이블 (단수 명사) : `user`, `board`, `resume`, `rating`, `skill`
- 행위 테이블 (원형 부정사) : `apply`, `subscribe`
- DTO : Request/Response + 목적지 정보 (ServiceName/ControllerName) + 기타(전치사+명사)
  - e.g. Request : `CreateDTO`, `UpdateDTO`, Response : `EmployerDTO`, `DetailDTO`...
- Controller : `post`/`detail`&`listings`/`update`/`delete`
- Service : `createEntity`/`getEntity`/`setEntity`/`removeEntity`
- Repository : `save`/`find`/`update`/`delete` + By/With + Entity&Record
- css : ?
### 회의
- 매일 회의록 작성
  - 진행 상황 보고
  - 문제점 발견 및 피드백
  - 건의사항
- 해결 과제
  - 할 일 목록 작성
