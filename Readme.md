# 又一個 Spring Boot 網頁 (Yet Another Spring Boot Web)

## 專案簡介
本系統為一個基礎的Spring Boot Web網頁實作，使用者可瀏覽商品列表，登入後將喜愛的商品加入個人喜好清單，並可修改、刪除喜好資料。系統為前後端分離架構，採用 Vue 3 作為前端框架，Spring Boot 作為後端服務，並使用 RESTful API 做資料交換，透過 JWT 及 Spring Security 管理身份驗證。
---

## 技術架構

### Frontend 前端 (Web Server)
- Vue.js (Vue 3)

### Backend 後端 (Application Server)
- Java + Spring Boot + Gradle

### DB 資料庫 (任一關聯式資料庫)
- H2 (Spring Boot 內建)

---

## 專案架構與套件說明

後端分層設計，包括展示層、業務層、資料層及共用層：

- **展示層 Controller**  
  `com.annservice.product_preference_api.controller`
- **業務層 Service**  
  `com.annservice.product_preference_api.service`
- **資料層 Repository**  
  `com.annservice.product_preference_api.repository`
- **資料交流模型 DTO (Data Transfer Object)**  
  `com.annservice.product_preference_api.dto`

---

## 系統需求與安全性

- 使用 Stored Procedure 操作資料庫，提高 SQL 執行效率與安全性。
- 異動多個資料表時，實作 Transaction 確保資料一致性。
- 防止 SQL Injection 和 XSS 攻擊，保障系統安全。

---

## 使用技術版本

- Java JDK 21 LTS
- Spring Boot 3.2.12
- Gradle 8.14.3

---

## 專案建立方式

- 使用 Spring Initializr 建立專案
- 設定：
  - Project: Gradle-Groovy
  - Language: Java
  - Spring Boot: 3.4.7 (後修改成 3.2.12)
  - Packaging: Jar
  - Java Version: 21
  - Dependencies:
    - Spring Web
    - Spring Data JPA
    - H2 Database
    - Validation
    - Spring Boot DevTools
    - Lombok
    - Spring Security

---

## 關於機密資訊設定

為避免推送機密資訊至 Git，機密設定放置於 `application-dev.properties`。  
請依照範本檔案 `application-template.properties` 填入相關設定，並將 `application.properties` 中的 `spring.profiles.active=template` 修改為適用的環境設定檔。

---

## 主要功能

### 1. 使用者管理
- 支援根據 `userid` 取得使用者基本資料 (帳號、姓名、Email 等)。
- 登入頁面尚未實作，系統依路由參數帶入 userid。

### 2. 商品列表
- 從後端獲取所有商品清單，顯示商品名稱、價格、手續費率等資訊。
- 使用者可設定購買數量並將商品加入個人喜好清單。

### 3. 喜好商品清單
- 根據目前使用者的 `userid` 從後端撈取喜好清單。
- 顯示每筆喜好商品的詳細資料（產品名稱、帳號、數量、總金額、總手續費、電子信箱）。
- 可刪除不再喜好的商品。
- 支援在表格中直接編輯數量，並透過 PUT API 更新後端資料。

---

## 使用方式

### 1. 啟動後端服務
```bash
cmd gradlew build
cmd gradlew bootrun
```

### 2. 啟動前端
```bash
cmd npm runserv
```


## 仍待實現

使用 Stored Procedure 操作資料庫。
目前是直接使用JPA對資料進行操作, 也是藉由其prepare statement來防止sql injection。
對於輸入資料的檢查與邏輯的驗證還需要更仔細地編寫
