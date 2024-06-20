<h1 align="center">
  <a href="https://github.com/TellMeThe-Answer/Server" title="AwesomeCV Documentation">
    <!-- <img alt="AwesomeCV" src="https://github.com/stock-price-calculator/tradingbot/assets/77156858/e1be76c5-3bf5-478a-8bc4-c790ef10f3a2" width="100%" height="100%" /> -->

<img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/e805e4b9-b567-4fca-af6d-6a7e6bd23529" width="250"/>

  </a>
  <br />
    병해 분류 및 피해 현황 파악 서비스
</h1>
<p align="center">
   AI를 이용한 작물 병해 판단 및 피해 파악 서비스 입니다.

</p>

<div align="center">
  <img src="https://img.shields.io/badge/React-61DAFB?style=flat&logo=React&logoColor=white"/>

  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=flat&logo=javascript&logoColor=white"/>
    <img src="https://img.shields.io/badge/tailwindcss-06B6D4?style=flat&logo=tailwindcss&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=springboot&logoColor=white"/>

  <img src="https://img.shields.io/badge/Python-3776AB?style=flat&logo=python&logoColor=white"/>

<img src="https://img.shields.io/badge/Flask-000000?style=flat&logo=Flask&logoColor=white"/>

<img src="https://img.shields.io/badge/Anaconda-44A833?style=flat&logo=anaconda&logoColor=white"/>

<img src="https://img.shields.io/badge/Swagger-85EA2D?style=flat&logo=Swagger&logoColor=white"/>

  <img src="https://img.shields.io/badge/Colab-F9AB00?style=flat&logo=googlecolab&logoColor=white"/>

<img src="https://img.shields.io/badge/Pytorch-EE4C2C?style=flat&logo=Pytorch&logoColor=white"/>

<img src="https://img.shields.io/badge/Yolov5-00FFFF?style=flat&logo=yolo&logoColor=white"/>

 <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat&logo=amazonec2&logoColor=white"/>

<img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat&logo=amazons3&logoColor=white"/>

<img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat&logo=amazonrds&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white"/>

</div>

## 📌 개요
- 프로젝트 이름 : 병해 분류 및 피해 현황 파악 서비스
- 개발 배경 : 딥러닝 기술을 기반으로 농민 들이 농작물의 병해 여부 및 종류를 판단하고 이에 대응할 수 있도록 돕기 위한 서비스를 제작하였습니다. 
- 개발 언어 : JavaScript(프론트) , Java(백엔드), Python(AI서버)
- 프론트 : React, Tailwind
- 백엔드 : Spring Boot, Spring JPA, MySql, EC2, S3, RDS, Swagger
- AI 서버 : Flask, Anaconda, Swagger
- AI 모델 : Yolov5, Colab

## 👬 팀 소개
- 홍영환 - <a href="https://github.com/TellMeThe-Answer/Server">백엔드</a>, <a href="https://github.com/TellMeThe-Answer/Client">프론트</a> , <a href="https://github.com/TellMeThe-Answer/AI_Server">AI 서버</a>
- 조준희 - <a href="https://github.com/TellMeThe-Answer/Server">백엔드</a>, <a href="https://github.com/TellMeThe-Answer/Client">프론트</a>
- 성현주 - AI 모델
- 전재민 - <a href="https://github.com/TellMeThe-Answer/Client">프론트</a>

## 🖥️ 프로젝트 소개
- 병해 사진을 업로드하면 병해를 판별하여 결과를 사용자에게 알려주는 서비스 입니다.
- 신고하기 기능, 지도로 피해 현황 파악 기능을 통해 병해 예방에 도움을 주고자 하였습니다.
- Yolov5 객체인식 모델을 Colab을 이용하여 병해 판별 모델을 제작하였습니다.
- AI Hub에서 작물에 대한 병해 데이터를 이용하여 라벨링, 전처리 과정을 진행하였습니다.
- AI를 통해 병해를 판별해주는 서버, 사용자의 정보와 신고데이터를 관리하는 서버를 나누어 제작하였습니다.





<hr>



## ⚙️ 프로젝트 주요 기능

### 1. 🐛 병해 판별
- 판별하고자 하는 작물을 선택 후 병해에 대한 정보를 얻을 수 있습니다.
- 이미지를 업로드하고 병해가 발생한 부분만을 업로드 합니다.
- 병해 결과로 병해가 발생한 부분과 병해명을 확인할 수 있습니다.

### 2. 📢 병해 신고
- 병해 판단 결과로 병해 신고 기능을 이용할 수 있습니다.
- 이미지, 제목, 글 정보를 담아 신고할 수 있습니다.
- 위치 정보를 마커를 통해 움직이며 설정할 수 있습니다.


### 3. 🗺️ 병해 현황 파악
- 다른 사용자들의 신고 내역을 지도를 통해 확인할 수 있습니다. 지도 마커를 통해 신고 내역 위치를 확인하고 신고 병해에 대한 정보를 얻을 수 있습니다. 
- 마커의 색상에 따라서 작물의 종류를 파악할 수 있으며, 또한 신고 내역에 대한 상세정보를 확인할 수 있습니다.

### 4. 📗 병해 도감
- AI 모델로 판별할 수 없는 많은 병해들을 농민이 직접 병해의 대표 이미지를 보면서 비교하고 판단할 수 있도록 구현하였습니다.
- 각 병해명과 함께 나와 있는 이미지를 클릭하면 해당 병해의 방제법을 포함한 상세 정보를 확인할 수 있습니다.


### 5. 📌️ 이달의 병해 정보
- 작물마다 어떤 병해충의 발생위험이 높은지 3 단계(경보, 주의보, 예보)로 나누어 달마다 주의해야할 병해충에 대한 정보를 제공합니다.
- 1 월부터 12 월 까지 달마다 주의해야할 병해충, 병해의 종류를 파악할 수 있는 정보를 제공합니다.



<hr>


##  💾 시스템 설계도
<img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/c1aa786d-dee6-4f1a-beb4-a0d2525368cd" height="100%" width="100%" >

<hr>

##  💾 시스템 구조
<img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/8341a370-319f-4e1b-971c-ba7466893875" height="100%" width="100%" >
<hr>

## 🛠️ 아쉽거나 어려웠던점

<hr/>

## 📌 페이지 화면
### 1. 🐛 병해 판별
<div align="center">

| <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/9c2d59c2-d064-4d44-9d5c-b8074640b865"  width="100%"> | <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/54961c76-c0ee-44d6-8ad7-28a46746afeb"  width="100%" > | <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/46943e1f-9be9-4b47-b9eb-6c4f813d40b7"  width="100%"> |
|:-----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|
|                                              병해판별                                                                             |                                                            병해 간략 정보                                                            |                                                            이미지 업로드                                                            |

</div>

<div align="center">

| <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/209b964f-4003-4ae7-bc73-a03b6900c84e"  width="100%"> | <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/7403561a-0e2b-45b7-9109-ea64d32f1194"  width="100%" > | <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/ba901ae3-4759-4d32-979e-824383040ede"  width="100%"> |
|:-----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                            이미지 자르기                                                            |                                                             병해 진단                                                              |                                                             병해 결과                                                             |
</div>
<hr>

### 2. 📢 병해 신고
<div align="center">

| <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/250045da-3f6d-4200-a989-ce4aecd15375"  width="100%"> | <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/753024c2-384f-4897-aa4f-5170b2e91205"  width="100%" > | <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/1b2a5120-1ad4-4c30-bff6-8f97b5c0525c"  width="100%"> |
|:-----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                             신고하기                                                              |                                                         현재 위치 및 위치 검색                                                          |                                                           도로명 주소 검색                                                           |

</div>
<div align="center">

| <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/96bd20b9-78ae-4e24-ba4b-a8dbde9bd711"  width="100%"> | <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/43e3ebeb-3f00-4ad3-b95e-231bf9a16139"  width="100%" > | <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/0bd09edc-a6fd-4831-b957-144fb775da26"  width="100%"> |
|:-----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                           마커로 위치 설정                                                           |                                                            마커로 위치 설정                                                             |                                                             신고 완료                                                             |

</div>
<hr>

### 3. 🗺️ 병해 현황 파악
<div align="center">

| <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/d6ba0b71-2e73-445a-bf89-43abb8adc0f3"  width="100%"> | <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/b482ca2a-d24b-468d-af0c-cc4a30449dcd"  width="100%" > | <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/4cde9b38-5775-46ca-a337-ea84bcf32b5c"  width="100%"> |
|:-----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                           현황 파악 지도                                                            |                                                            신고 상세내역                                                             |                                                             신고 상세내역                                                           |

</div>
<hr>

### 4. 📗 병해 도감
<div align="center">

| <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/b35db91e-4d26-46da-9893-0eba457b0211"  width="100%"> | <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/27aaf932-47ca-42bd-932c-bb5f7b692d19"  width="100%" > | <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/4fa5f96f-e7d8-427f-8db7-aea5a218a285"  width="100%"> |
|:-----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                             병해 도감                                                             |                                                            병해 도감 1                                                             |                                                            병해 도감 2                                                            |

</div>
<hr>

### 5. 📌️ 이달의 병해 정보
<div align="center">

| <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/328506e7-7f88-45ff-8d47-a6361f76e17d"  width="80%"> | <img src="https://github.com/rong5026/WEBSTORYBOY_SOURCE/assets/77156858/42643686-fdc6-489c-b532-b28bfd930df1"  width="80%" > |
|:----------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|
|                                                           현황 파악 지도                                                           |                                                            신고 상세내역                                                            |   

</div>




