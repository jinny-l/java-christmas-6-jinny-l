![](https://img.shields.io/badge/Last_Upadate-2023--11--14-blue)

# 🎄 크리스마스 프로모션 미션
- 미션 기간: `23-11-09` ~ `23-11-15` [1w]
- 우아한테크코스 프리코스 웹 백엔드 6기 미션으로 `12월 이벤트 플래너`를 구현하는 프로젝트이다.

<br/>

## 🖥️ 실행 예시

```
안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
3
주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1
12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
 
<주문 메뉴>
티본스테이크 1개
바비큐립 1개
초코케이크 2개
제로콜라 1개
 
<할인 전 총주문 금액>
142,000원
 
<증정 메뉴>
샴페인 1개
 
<혜택 내역>
크리스마스 디데이 할인: -1,200원
평일 할인: -4,046원
특별 할인: -1,000원
증정 이벤트: -25,000원
 
<총혜택 금액>
-31,246원
 
<할인 후 예상 결제 금액>
135,754원
 
<12월 이벤트 배지>
산타
```

<br/>

## 📂 패키지 구조
```shell
.
├── Application.java
├── ChristmasPromotionController.java
├── benefit   // 이벤트 혜택 도메인
├── date      // 이벤트 일정 도메인
├── global    // 전역적으로 사용되는 클래스를 모아둔 패키지
├── order     // 주문 도메인
├── payment   // 주문/혜택/결제 금액 도메인
├── plan      // 이벤트 일정 + 주문 도메인
├── promotion // 크리스마스 프로모션 도메인
└── view
```

<br/>

## ✨ 기능 목록

### 도메인 기능
**이벤트 일정**
- [X] 계획된 이벤트 일정을 저장하는 기능
- [X] 이벤트 플래너에 입력한 방문 날짜에 해당하는 이벤트 날짜를 조회하는 기능

**메뉴 주문**
- [X] 메뉴를 저장하는 기능
- [X] 이벤트 플래너에 입력한 메뉴를 주문하는 기능
  - 메뉴 개수는 최소 1개 이상
  - 중복 메뉴 주문 불가능
  - 음료만 주문 시, 주문 불가능
  - 최대 주문 개수는 20개

**할인 혜택**
- [X] 할인 혜택을 산정하는 기능
  - 총 주문 금액이 10,000 이상 시 적용
  - 크리스마스 디데이 할인 적용 여부 확인
  - 평일/주말 할인 적용 여부 확인
  - 특별 할인 적용 여부 확인
  - 증정 이벤트 적용 여부 확인

**금액**
- [X] 주문 금액을 계산하는 기능
- [X] 할인 금액을 계산하는 기능
- [X] 할인 후 예상 결제 금액을 계산하는 기능

**배지**
- [X] 혜택 금액에 따른 배지를 부여하는 기능

### 입력 기능
- [X] 식당 방문 날짜 입력 기능
- [X] 주문하는 메뉴 입력 기능


### 출력 기능
- [X] 이벤트 플래너 시작 메시지 출력 기능 
- [X] 이벤트 혜택 출력 기능

<br/>

## ✔️ 이벤트 시나리오

|         | 디데이 할인 | 평일 할인 | 주말 할인 | 특별 할인 | 증정 이벤트 |
|---------|--------|-------|-------|-------|--------|
| 시나리오 1  | O      | O     | X     | O     | O      |
| 시나리오 2  | O      | O     | X     | O     | X      |
| 시나리오 3  | O      | O     | X     | X     | O      |
| 시나리오 4  | O      | O     | X     | X     | X      |
| 시나리오 5  | O      | X     | O     | X     | O      |
| 시나리오 6  | O      | X     | O     | X     | X      |
| 시나리오 7  | O      | X     | X     | O     | O      |
| 시나리오 8  | O      | X     | X     | O     | X      |
| 시나리오 9  | O      | X     | X     | X     | O      |
| 시나리오 10 | O      | X     | X     | X     | X      |
| 시나리오 11 | X      | O     | X     | O     | O      |
| 시나리오 12 | X      | O     | X     | O     | X      |
| 시나리오 13 | X      | O     | X     | X     | O      |
| 시나리오 14 | X      | O     | X     | X     | X      |
| 시나리오 15 | X      | X     | O     | X     | O      |
| 시나리오 16 | X      | X     | O     | X     | X      |
| 시나리오 17 | X      | X     | X     | O     | O      |
| 시나리오 18 | X      | X     | X     | O     | X      |
| 시나리오 19 | X      | X     | X     | X     | O      |
| 시나리오 20 | X      | X     | X     | X     | X      |
