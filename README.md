# 카카오톡 클론 코딩

## 🛠 기술 스택
<br />
<div align=center>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
  <br />
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/socket.io-010101?style=for-the-badge&logo=socket.io&logoColor=white">
  <img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white">
    <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <br />
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
  <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black">
  <img src="https://img.shields.io/badge/redux-%23593d88.svg?style=for-the-badge&logo=redux&logoColor=white">
  <br />
</div>

## 📢 주요 기능
<ul>
<li> 로그인/회원가입 </li>
<li> 채팅 리스트 조회 </li>
<li> ✨ 실시간 채팅 ✨ </li>
</ul>
<br /> <br />

## 📽 시연 영상

<br /> <br />

## WHAT WE MAKE

<br /> <br />

## ⚽ 트러블 슈팅
> **연관 관계 문제** <br />
> 연관 관계 문제와 entity 자체로 반환하면서 순환 참조 발생
> 두 개의 문제가 동시에 발생하면서 선행 트러블 파악 불가
  >> 해결! <br /> 연관 관계 삭제 후 dto로 반환

<br />
  
> **웹소켓 문제** <br />
> 웹소켓에 대한 지식 부족
  >> 해결! <br /> 다양한 기술 블로그와 영상 자료 참고
  
<br />
  
> **@AuthenticationPrincipal 문제** <br />
> 웹소켓은 @MessageMapping을 통해 메세지 주고 받음, @AuthenticationPrincipal 통해 사용자 정보 불러오려 했으나 null 반환
  >> 해결! <br /> @AuthenticationPrincipal은 REST/MVC methods 전용, <br /> @MessageMapping은 REST API가 아니기 때문에 http header 정보 불러올 수 없음 -> ChatMessage entity 안에 있는 Sender field에서 사용자 정보 불러옴
  

<br />

## ⌛ 시간이 더 있었다면 도전했을 기술들
<ul>
<li>친구 추가</li>
<li>1:1 채팅</li>
<li>프로필 사진 첨부</li>
<li>지도</li>
</ul>
<br /> <br />

## 🎨 와이어 프레임
<img src = "https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F61047bbb-e63c-434e-b3b6-abbbb67af5fd%2FFrame_1_(1).png?id=5beff6d2-3601-493b-8916-b68956a74152&table=block&spaceId=0be2dc4f-eddb-4575-ab82-ebc6e9ad838f&width=1900&userId=6d08975a-ddbd-485c-80a4-40401675e07a&cache=v2" width = "95%"></img>
<br /> <br />

## 📌 ERD
**프로젝트 기획 단계**
<img src = "https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F4a193344-fdea-4d16-9980-db1b7e7cff72%2FUntitled.png?id=77d552bc-45f9-4b4d-8a95-5a16b55a11a5&table=block&spaceId=0be2dc4f-eddb-4575-ab82-ebc6e9ad838f&width=1860&userId=6d08975a-ddbd-485c-80a4-40401675e07a&cache=v2" width = "95%"></img>
<br /> <br />
**프로젝트 구현**
![image](https://user-images.githubusercontent.com/109057365/209909723-0a57559a-3123-48c9-a802-716920c67d81.png)
<br /> <br />


## 📃 API 명세서
[API 명세서](https://www.notion.so/f5ee37fc5b664dad9bd9767653a88496?v=a5f4ae1dda90416e860c340697e1528b)
<br /> <br />
