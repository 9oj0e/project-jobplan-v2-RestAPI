-- user_tb (기업 유저)
INSERT INTO user_tb
(username, password, name, birthdate, gender, phone_number, address, email,
 is_employer, employer_id_number, business_name, created_at)
VALUES ('emp1', '1234', '이해진', '1967-06-22', 'M', '01012345678', '전북', 'lhj@naver.com',
        true, '0128275512', '네이버', now());
INSERT INTO user_tb
(username, password, name, birthdate, gender, phone_number, address, email,
 is_employer, employer_id_number, business_name, created_at)
VALUES ('emp2', '1234', '김범수', '1966-03-27', 'M', '01012345678', '전남', 'briankim@kakao.com',
        true, '0128275512', '카카오', now());
INSERT INTO user_tb
(username, password, name, birthdate, gender, phone_number, address, email,
 is_employer, employer_id_number, business_name, created_at)
VALUES ('emp3', '1234', '켄타로', '1900-01-01', 'M', '01012345678', '서울', 'ly@yahoo.com',
        true, '0128275512', '라인', now());
INSERT INTO user_tb
(username, password, name, birthdate, gender, phone_number, address, email,
 is_employer, employer_id_number, business_name, created_at)
VALUES ('emp4', '1234', '김범', '1978-10-07', 'M', '01012345678', '서울', 'beomkim@coupang.com',
        true, '0128275512', '쿠팡', now());
INSERT INTO user_tb
(username, password, name, birthdate, gender, phone_number, address, email,
 is_employer, employer_id_number, business_name, created_at)
VALUES ('emp5', '1234', '김봉진', '1976-10-10', 'M', '01012345678', '전남', 'bongjhin@baemin.com',
        true, '0128275512', '배달의민족', now());

-- user_tb (개인 유저)
INSERT INTO user_tb
(username, password, name, birthdate, gender, phone_number, address, email, education_level, school_name, major,
 is_employer, created_at)
VALUES ('user1', '1234', '류재성', '2000-01-01', 'M', '01012345678', '서울', 'hgd456@domain.com', '대졸', '서울대학교', '컴퓨터공학',
        false, now());
INSERT INTO user_tb
(username, password, name, birthdate, gender, phone_number, address, email, education_level, school_name, major,
 is_employer, created_at)
VALUES ('user2', '1234', '장현정', '2000-01-01', 'M', '01012345678', '울산', 'lgt456@domain.com', '대졸', '영남대학교', '컴퓨터공학',
        false, now());
INSERT INTO user_tb
(username, password, name, birthdate, gender, phone_number, address, email, education_level, school_name, major,
 is_employer, created_at)
VALUES ('user3', '1234', '김성재', '2000-01-01', 'M', '01012345678', '인천', 'hgb456@domain.com', '초대졸', '구미대학교', '소프트웨어공학',
        false, now());
INSERT INTO user_tb
(username, password, name, birthdate, gender, phone_number, address, email, education_level, school_name, major,
 is_employer, created_at)
VALUES ('user4', '1234', '이서현', '2000-01-01', 'M', '01012345678', '제주', 'kdb456@domain.com', '초대졸', '부천대학교', '소프트웨어공학',
        false, now());
INSERT INTO user_tb
(username, password, name, birthdate, gender, phone_number, address, email, education_level, school_name, major,
 is_employer, created_at)
VALUES ('user5', '1234', '조정현', '1996-04-01', 'M', '01012345678', '진주', 'hmc456@domain.com', '대졸', '경상국립대학교', '무역통상',
        false, now());
INSERT INTO user_tb
(username, password, name, birthdate, gender, phone_number, address, email, education_level, school_name,
 is_employer, created_at)
VALUES ('user6', '1234', '전두광', '1931-01-18', 'M', '01012345678', '대전', 'jdg456@domain.com', '고졸', '하나고등학교',
        false, now());

-- board_tb
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (1, '솔루션 운영/개발자 (2년 이상)',
        '저희 회사와 함께 성장할 수 있는 역량 있고 창의적인 분들의 지원을 기대합니다. 포지션 상세.. 주요 업무 : 게시판, 백 오피스 구축, 연동, api 연동. 자격 요건 : 풀스택. 우대 사항 : PHP를 전문적으로 다루시는 분. 혜택 및 복지 : 4대 보험, 주 5일제',
        '풀스택', '신입', '최저시급', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (2, 'S/W Front-End',
        '끊임없는 도전을 함께할 동료를 찾고 있습니다. 포지션 상세.. 주요 업무 : 데스크탑, 모바일 웹, 스마트폰 앱을 위한 웹뷰페이지 개발 자격 요건 :  5년 이상의 웹 프론트엔드 개발 경력 혹은 그에 준하는 실력을 갖추신 분 우대 사항 : React, Vue, Angular 등 모던 SPA 프레임워크 사용에 능숙하신 분 혜택 및 복지 : 4대 보험, 주 5일제',
        '프론트', '경력', '연봉협의', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (3, 'AI 비전 플랫폼 개발',
        '반려로봇 개발에 따른 LLM AI 개발자를 채용합니다. 포지션 상세.. 주요 업무 :  한국어, 영어 TTS(Text to Speech) 모델 알고리즘 개발 자격 요건 :  음성 데이터 처리 및 기계 학습 관련 1년 이상의 경력 우대 사항 : Python, Pytorch, Tensorflow, C++ 사용 가능 혜택 및 복지 : 4대 보험, 주 5일제',
        'AI', '경력', '최저시급', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (4, '클라우드 DevOps 엔지니어',
        '새롭고 신나는 로드맵에 동행해주실 도전의식과 창의성이 가득한 하클의 식구, 멤버를 기다립니다. 포지션 상세.. 주요 업무 : 실감형 AR Commerce 컨텐츠 , AR Advertisement 개발. 자격 요건 : 풀스택. 우대 사항 : 자료구조,운영체제,알고리즘,네트워크에 대한 이해가 있으신분. 혜택 및 복지 : 4대 보험, 주 5일제',
        '클라우드', '신입', '연봉협의', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (5, '솔루션 운영/개발자 (2년 이상)',
        '보안 솔루션 연구 개발 경력직 채용. 포지션 상세.. 주요 업무 : 신규 제품 프로토타입 개발. 자격 요건 : Java(필수) 외 1개 이상. 우대 사항 : 보안 솔루션 회사 근무 경험 또는 연구 실적. 혜택 및 복지 : 4대 보험, 주 5일제',
        '풀스택', '경력', '연봉협의', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (1, 'S/W Front-End',
        '저희 회사와 함께 성장할 수 있는 역량 있고 창의적인 분들의 지원을 기대합니다. 포지션 상세.. 주요 업무 : SSEM 서비스 화면 개발 및 운영. 자격 요건 : HTML5, CSS3, javasript, jQuery. 우대 사항 : Javascipt 혹은 jQuery를 사용 해보신 분. 혜택 및 복지 : 4대 보험, 주 5일제',
        '프론트', '신입', '최저시급', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (2, '백엔드 개발자(팀장/리더)',
        '진심으로 좋은 분과 함께하고자 하는 마음이 조금이나마 직접 전달되었으면 좋겠습니다. 포지션 상세.. 주요 업무 : 개발팀 ︎총괄(리드), 백엔드 개발 실무. 자격 요건 : 개발경력 4년 이상 (팀장/팀리더 경력). 우대 사항 : MongoDB 사용하여 대규모 서비스 개발 경험. 혜택 및 복지 : 4대 보험, 주 5일제',
        '개발팀 총괄', '경력', '연봉협의', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (3, 'DBMS 기술 엔지니어',
        '데이터베이스에 관심이 있거나 데이터베이스 전문가를 꿈꾸는 많은 인재들의 지원을 기다리겠습니다. 포지션 상세.. 주요 업무 : CUBRID DBMS 고객 기술지원. 자격 요건 : Linux, Windows 시스템 사용 능력. 우대 사항 : Java, PHP, Python 등 프로그래밍 가능자. 혜택 및 복지 : 4대 보험, 주 5일제',
        'DBMS', '경력 무관', '연봉협의', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (4, 'Server Engineer',
        '3PRO 서비스 전반 백엔드 시스템 엔지니어링. 포지션 상세.. 주요 업무 : 내/외부의 다양한 클라이언트를 위한 REST API 개발, Server Engineer 2. 자격 요건 : Typescript에 대한 이해와 활용 경험이 있는 분. 우대 사항 :  100만 이상의 사용자를 가진 시스템을 설계/개발/운영한 경험이 있는 분. 혜택 및 복지 : 4대 보험, 주 5일제',
        '백엔드', '경력', '연봉협의', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (5, 'python 개발자(5년 이상)',
        '함께 성장할 파이썬 개발자를 모십니다. 포지션 상세.. 주요 업무 : python을 이용한 코어 모듈 고도화 작업. 자격 요건 : Python. 우대 사항 : 객체지향 프로그래밍 개발 실무 경험이 있는 분. 혜택 및 복지 : 4대 보험, 주 5일제',
        '백엔드', '경력', '연봉협의', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (1, '웹 서비스 개발자',
        '엔터테인먼트 컨텐츠에 촉각을 전달할 수 있는 햅틱 기기와 소프트웨어를 글로벌 고객에게 제공합니다. 포지션 상세.. 주요 업무 : Web 기반 서비스 개발 및 운영(bHaptics Designer, Studio, Developer Portal 등). 자격 요건 : React 개발 3년 이상이신 분. 우대 사항 : Full-Stack 경험이 있으신 분. 혜택 및 복지 : 4대 보험, 주 5일제',
        '프론트', '경력', '연봉협의', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (2, 'AI 개발자(신입)',
        '"정도기술로 세상을 이롭게 한다"는 기업이념을 가지고 있습니다. 포지션 상세.. 주요 업무 : 메타버스 AI 모델 설계 및 학습. 자격 요건 : Linux 서버. 우대 사항 : 기계학습 및 인공지능 관련 이해 혹은 관련분야 전공자. 혜택 및 복지 : 4대 보험, 주 5일제',
        'AI', '신입', '최저시급', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (3, '게임 기획',
        '오늘에 멈추지 않고 더 나은 답을 향해 끊임없이 고민하고 함께 변화할 사람을 찾습니다. 포지션 상세.. 주요 업무 : 라이브 서비스 진행 중인 포코 시리즈 프로젝트 게임 기획. 자격 요건 : 퍼즐 장르에 대한 이해와 관심을 가지신 분. 우대 사항 : 추상적인 아이디어를 논리적으로 정돈할 수 있으신 분. 혜택 및 복지 : 4대 보험, 주 5일제',
        '기획', '신입', '최저시급', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (4, '영상처리 개발자',
        '로보틱스 및 인공지능 기술을 바탕으로 의료기기 혁신에 함께할 소중한 인재를 모십니다. 포지션 상세.. 주요 업무 : 의료용 내시경 영상에 대한 영상처리 알고리즘 개발. 자격 요건 :  Linux 기반 C/C++, Python Programming 경험. 우대 사항 : 해당 분야 석사/박사 학위. 혜택 및 복지 : 4대 보험, 주 5일제',
        '프로그래밍', '신입', '최저시급', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (5, 'Cloud Security Engineer',
        '클라우드 보안 비즈니스를 리드해 나갈 분을 찾습니다. 포지션 상세.. 주요 업무 : AWS, GCP, Azure와 같은 Public Cloud 및 On-premise 환경에 적합한 보안 컴플라이언스(Compliance as Code) 및 보안 정책(Policy as Code)을 연구. 자격 요건 : 셀프 모티베이션에 능숙하신 분. 우대 사항 : 정보보호학, 컴퓨터 관련 학과 학부(석사) 졸업(예정자). 혜택 및 복지 : 4대 보험, 주 5일제',
        '기획자', '신입', '최저시급', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (1, '정보보안 엔지니어',
        '개인의 역량, 적성, 포부와 맞는 커리어 플랜을 만들어보세요. 포지션 상세.. 주요 업무 : 정보보안 정책에 따라 정보보호 시스템 및 솔루션 및 장비를 운영. 자격 요건 : 정보보호 기술과 시스템 운영 경험. 우대 사항 : 보안 인증(ISO27001, ISMS-P, CSAP 등) 취득 과정에서 수행 경험이 있으신 분. 혜택 및 복지 : 4대 보험, 주 5일제',
        '보안', '신입', '최저시급', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (2, 'Product Manager',
        '함께 채용 시장을 혁신하고, 인재 검증 플랫폼을 만들어갈 개발자를 찾습니다. 포지션 상세.. 주요 업무 : 해당 포지션은 평판조회 플랫폼 지원자/작성자 영역을 집중적으로 기획합니다. 자격 요건 : 5년 이상의 서비스 기획 및 PM 경험 . 우대 사항 : 플랫폼의 Admin 기획을 진행한 경험 . 혜택 및 복지 : 4대 보험, 주 5일제',
        '기획', '경력', '연봉협의', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (3, 'JAVA기반 시스템 설계/개발 (모빌리티 시스템)',
        '디지털 혁신의 중심, 웅진에서 세상을 바꾸는 기회에 도전하세요. 포지션 상세.. 주요 업무 : 자사 모빌리티 솔루션(WDMS) 프로젝트 수행. 자격 요건 :  설계/개발 경력 10년 이상 . 우대 사항 : Vue.js, React.js, javascript(ES6) 개발 경험. 혜택 및 복지 : 4대 보험, 주 5일제',
        '프로그래밍', '경력', '연봉협의', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (4, '블록체인 전송 시스템 운영 담당자',
        '무한한 상상력을 현실로 만드는 위메이드와 함께 하실 분은 지원해 주세요. 포지션 상세.. 주요 업무 : 게시판, 백 오피스 구축, 연동, api 연동. 자격 요건 : IT 관련 학과를 졸업하신 분 (전문학사 이상). 우대 사항 : 블록체인에 대해 관심이 있으신 분. 혜택 및 복지 : 4대 보험, 주 5일제',
        '시스템 운영', '신입', '최저시급', '2024-02-26', '2024-03-08', now());
INSERT INTO board_tb
(user_id, title, content, field, position, salary, opening_date, closing_date, created_at)
VALUES (5, '광고 솔루션 백앤드 개발자',
        '광고솔루션 JAVA, Python 백앤드 개발자 구인합니다. 포지션 상세.. 주요 업무 : AdTech 관련 솔루션 개발. 자격 요건 : 과장/차장급 (경력 8년 이상). 우대 사항 : Vuejs를 이용한 데이터 시각화 경험. 혜택 및 복지 : 4대 보험, 주 5일제',
        '백엔드', '경력 ', '연봉협의', '2024-02-26', '2024-03-08', now());

-- resume_tb
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (6, '새싹을 틔울 수 있는 씨앗이 되겠습니다.',
        '저는 컴퓨터 공학을 전공한 전문적인 IT 개발자로서, 프로그래밍에 대한 열정과 경험이 풍부합니다. 사용자 중심의 솔루션을 개발하여 비즈니스 문제를 해결하는 것을 즐기며, 끊임없이 새로운 기술을 익히고 발전시키는 것에 흥미를 느낍니다.',
        '백앤드 1년', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (7, '돌쇠 같이 일하는 미친아이 입사지원서.',
        '다양한 프로그래밍 언어 및 기술 스택에 대한 깊은 이해와 경험을 보유하고 있습니다. 주로 Java와 Python을 사용하여 웹 및 애플리케이션을 개발하였으며, Spring Framework 및 Django와 같은 프레임워크를 활용하여 프로젝트를 구축했습니다.',
        '프론트 1년', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (8, '대한민국 최고의 개발자를 꿈꿉니다.',
        '최근에는 은행 업계에서 작업했으며, 온라인 뱅킹 플랫폼의 보안 강화 및 성능 향상을 위한 프로젝트에 참여했습니다. 이를 통해 대용량 데이터 처리 및 보안 강화에 대한 전문 지식을 쌓았습니다.',
        '풀스택 1년', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (9, '매일 30분으로 내가 이뤄낸 것.',
        '팀에서의 원활한 소통과 협업을 중요하게 생각합니다. Agile 방법론을 따르며, 주기적인 스크럼 회의를 통해 팀원들과의 소통을 촉진하고 프로젝트 일정을 관리합니다.',
        '신입', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (10, '학점 2.3에서 편입 후 4.2로.', '새로운 기술 및 도구에 대한 학습에 적극적으로 참여하며, 온라인 코스를 통해 지속적인 전문적인 발전을 추구합니다.', '신입', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (11, '목표를 달성하기 위해 실행한 3단계 실행 계획.',
        '지속적인 성장과 함께 사용자 중심의 혁신적인 솔루션을 개발하여 비즈니스 가치를 극대화하는데 기여하고 싶습니다. 함께 일하는 팀원들과의 긍정적인 협력을 통해 동료들과 함께 성장하는 기회를 소중히 여기며, IT 업계의 발전에 기여하고자 합니다.',
        '신입', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (6, '꿈꾸고 믿고 대담해지고 실천하자.',
        '이전 프로젝트에서 팀 리더로서 프로젝트 일정 및 리소스를 관리하고, 팀원 간의 업무 분담을 조정하여 프로젝트 성과를 극대화했습니다. 이를 통해 리더십과 조직 능력을 키우는 데 중점을 두었습니다.',
        '백앤드 1년', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (7, '코드를 개선하는 개발자.',
        '복잡한 기술적 문제에 직면했을 때 신속하고 효과적으로 해결하는 능력을 갖추고 있습니다. 이를 통해 프로젝트 진행 중 발생하는 도전에 대응하여 효율적인 해결책을 제시하고 구현할 수 있습니다.',
        '프론트 1년', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (8, '당신은 내가 넘어야 할 작은 산에 불과하다.',
        'Clean Code와 같은 최적화된 코딩 관행을 따르고, 코드 품질을 유지하고 개선하는 데 주력했습니다. 코드 리뷰를 통해 팀 내 개발자들과 함께 고품질의 소프트웨어를 개발하는 데 기여했습니다.',
        '풀스택 1년', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (9, 'Java 개발자', '사용자 요구사항을 이해하고 그에 맞는 솔루션을 개발하는 과정에서 항상 고객의 관점을 고려합니다. 사용자 피드백을 수용하여 제품을 지속적으로 향상시키는 데 주력했습니다.',
        '신입', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (10, '자전거는 계속 나아가지 않으면 쓰러진다.',
        '코드 테스트와 자동화된 테스트 스위트를 통해 소프트웨어 품질을 보증합니다. 유닛 테스트, 통합 테스트 및 기능 테스트를 통해 버그를 최소화하고 안정적인 제품을 제공합니다.', '신입', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (11, '한번 물면 쉽게 놓지 않습니다.',
        '기술적인 개념을 비전문가에게도 이해하기 쉽게 설명하는 능력을 보유하고 있습니다. 커뮤니케이션을 통해 팀 간의 협력을 촉진하고 프로젝트의 목표 달성에 기여했습니다.', '신입', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (6, '대한민국의 미래.', '이력서내용', '백앤드 1년', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (7, '10년후 대한민국을 책임질 인재.',
        '다양한 산업 분야에서의 프로젝트 경험을 통해 해당 산업에 대한 깊은 이해를 갖추고 있습니다. 특정 산업 도메인에서의 요구 사항과 동향을 파악하여 최적의 기술 솔루션을 제공하는 데 기여하고 있습니다.',
        '프론트 1년', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (8, '회복의 유일한 길은 다시 시작하는 것이다.',
        '문제 해결 과정에서 창의적이고 혁신적인 접근 방식을 채택하여 기존의 해결책을 개선하고 새로운 아이디어를 발전시키는 데 관심을 갖고 있습니다. 이를 통해 프로젝트의 경쟁력을 향상시키는데 기여하고 있습니다.',
        '풀스택 1년', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (9, '배우는 것은 산에 오르는 것과 같다.',
        'Agile 방법론을 적극적으로 채택하여 빠르게 변화하는 요구사항에 유연하게 대응하고, 지속적인 개선을 통해 소프트웨어를 효율적으로 개발하고 제공합니다. 스크럼 및 칸반 등의 Agile 방법론을 활용하여 팀 내 협업과 프로세스를 최적화했습니다.',
        '신입', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (10, '혁신은 180도 다른 시선에서 나온다.',
        '프로젝트 관련 문서 작성 및 관리에 주의를 기울여 왔습니다. 요구 사항 명세서, 기술 문서, 사용자 매뉴얼 등을 작성하여 프로젝트의 투명성을 유지하고 팀원 간의 이해를 돕는 역할을 수행했습니다.',
        '신입', now());
INSERT INTO resume_tb
    (user_id, title, content, career, created_at)
VALUES (11, '여전할 것인가, 역전할 것인가.',
        '지식을 지속적으로 업데이트하고 팀원들과의 지식 공유를 통해 팀 전체의 역량을 향상시키는 데 관심이 있습니다. 내부 교육 세션을 주도하고 기술 블로그에 기여하여 조직 내에서 지식 공유 문화를 유지하고 발전시켰습니다.',
        '신입', now());


-- apply_tb
INSERT INTO apply_tb
    (resume_id, board_id, created_at, status)
VALUES (1, 1, now(), 1);
INSERT INTO apply_tb
    (resume_id, board_id, created_at, status)
VALUES (2, 2, now(), 0);
INSERT INTO apply_tb
    (resume_id, board_id, created_at)
VALUES (3, 3, now());
INSERT INTO apply_tb
    (resume_id, board_id, created_at)
VALUES (4, 4, now());
INSERT INTO apply_tb
    (resume_id, board_id, created_at)
VALUES (5, 1, now());
INSERT INTO apply_tb
    (resume_id, board_id, created_at)
VALUES (6, 2, now());
INSERT INTO apply_tb
    (resume_id, board_id, created_at, status)
VALUES (1, 5, now(), 1);
INSERT INTO apply_tb
    (resume_id, board_id, created_at, status)
VALUES (2, 6, now(), 0);
INSERT INTO apply_tb
    (resume_id, board_id, created_at)
VALUES (3, 7, now());
INSERT INTO apply_tb
    (resume_id, board_id, created_at)
VALUES (4, 8, now());
INSERT INTO apply_tb
    (resume_id, board_id, created_at)
VALUES (5, 5, now());
INSERT INTO apply_tb
    (resume_id, board_id, created_at)
VALUES (2, 5, now());
INSERT INTO apply_tb
    (resume_id, board_id, created_at)
VALUES (4, 5, now());



-- 제안
-- offer_tb
INSERT INTO offer_tb
(resume_id, board_id, created_at, status)
VALUES (18, 16, now(), true);
INSERT INTO offer_tb
(resume_id, board_id, created_at, status)
VALUES (16, 17, now(), null);
INSERT INTO offer_tb
(resume_id, board_id, created_at, status)
VALUES (11, 7, now(), null);
INSERT INTO offer_tb
(resume_id, board_id, created_at, status)
VALUES (18, 12, now(), null);
INSERT INTO offer_tb
(resume_id, board_id, created_at, status)
VALUES (2, 13, now(), null);
INSERT INTO offer_tb
(resume_id, board_id, created_at, status)
VALUES (8, 8, now(), null);
INSERT INTO offer_tb
(resume_id, board_id, created_at, status)
VALUES (17, 3, now(), null);
INSERT INTO offer_tb
(resume_id, board_id, created_at, status)
VALUES (10, 13, now(), null);
INSERT INTO offer_tb
(resume_id, board_id, created_at, status)
VALUES (13, 13, now(), null);

-- 평점
INSERT INTO rating_tb
    (rater_id, subject_id, rating, created_at)
VALUES (1, 6, 5, now());
INSERT INTO rating_tb
    (rater_id, subject_id, rating, created_at)
VALUES (1, 6, 4, now());
INSERT INTO rating_tb
    (rater_id, subject_id, rating, created_at)
VALUES (1, 6, 3, now());

-- subscribe_tb (기업이 이력서를 구독)
INSERT INTO subscribe_tb
    (user_id, resume_id, created_at)
VALUES (1, 1, now());
INSERT INTO subscribe_tb
    (user_id, resume_id, created_at)
VALUES (1, 2, now());
INSERT INTO subscribe_tb
    (user_id, resume_id, created_at)
VALUES (2, 3, now());
INSERT INTO subscribe_tb
    (user_id, resume_id, created_at)
VALUES (2, 4, now());
INSERT INTO subscribe_tb
    (user_id, resume_id, created_at)
VALUES (3, 5, now());
INSERT INTO subscribe_tb
    (user_id, resume_id, created_at)
VALUES (3, 6, now());
INSERT INTO subscribe_tb
    (user_id, resume_id, created_at)
VALUES (4, 7, now());
INSERT INTO subscribe_tb
    (user_id, resume_id, created_at)
VALUES (4, 8, now());
INSERT INTO subscribe_tb
    (user_id, resume_id, created_at)
VALUES (5, 9, now());
INSERT INTO subscribe_tb
    (user_id, resume_id, created_at)
VALUES (5, 10, now());

-- subscribe_tb (개인이 공고를 구독)
INSERT INTO subscribe_tb
    (user_id, board_id, created_at)
VALUES (6, 2, now());
INSERT INTO subscribe_tb
    (user_id, board_id, created_at)
VALUES (7, 3, now());
INSERT INTO subscribe_tb
    (user_id, board_id, created_at)
VALUES (7, 4, now());
INSERT INTO subscribe_tb
    (user_id, board_id, created_at)
VALUES (8, 5, now());
INSERT INTO subscribe_tb
    (user_id, board_id, created_at)
VALUES (8, 6, now());
INSERT INTO subscribe_tb
    (user_id, board_id, created_at)
VALUES (9, 7, now());
INSERT INTO subscribe_tb
    (user_id, board_id, created_at)
VALUES (9, 8, now());
INSERT INTO subscribe_tb
    (user_id, board_id, created_at)
VALUES (10, 9, now());
INSERT INTO subscribe_tb
    (user_id, board_id, created_at)
VALUES (10, 10, now());
INSERT INTO subscribe_tb
    (user_id, board_id, created_at)
VALUES (11, 11, now());
INSERT INTO subscribe_tb
    (user_id, board_id, created_at)
VALUES (11, 12, now());

-- skill_tb (공고 우대 스킬)
INSERT INTO skill_tb(board_id, user_id, name)
VALUES (1, null, 'java');
INSERT INTO skill_tb(board_id, user_id, name)
VALUES (1, null, 'javaScript');
INSERT INTO skill_tb(board_id, user_id, name)
VALUES (2, null, 'java');
INSERT INTO skill_tb(board_id, user_id, name)
VALUES (5, null, 'HTML');
INSERT INTO skill_tb(board_id, user_id, name)
VALUES (6, null, 'Spring');

-- skill_tb (스킬 보유 회원)
INSERT INTO skill_tb(board_id, user_id, name)
VALUES (null, 6, 'java');
INSERT INTO skill_tb(board_id, user_id, name)
VALUES (null, 6, 'javaScript');
INSERT INTO skill_tb(board_id, user_id, name)
VALUES (null, 7, 'java');
INSERT INTO skill_tb(board_id, user_id, name)
VALUES (null, 8, 'HTML');
INSERT INTO skill_tb(board_id, user_id, name)
VALUES (null, 8, 'Spring');
INSERT INTO skill_tb(board_id, user_id, name)
VALUES (null, 8, 'MySQL');