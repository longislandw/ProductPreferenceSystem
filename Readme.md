# 金融商品喜好清單系統 (Financial Product Preference System)

## 專案簡介
本系統為一個簡易的金融商品喜好清單管理平台，使用者可瀏覽金融商品列表，將喜愛的商品加入個人喜好清單，並可修改、刪除喜好資料。系統採用 Vue 3 作為前端框架，Spring Boot 作為後端服務，並使用 RESTful API 做資料交換，透過 JWT 及 Spring Security 管理身份驗證。

---

## 主要功能

### 1. 使用者管理
- 支援根據 `userid` 取得使用者基本資料 (帳號、姓名、Email 等)。(http://localhost:8080/A0000000001)
- 登入頁面尚未實作，系統依路由參數帶入 userid。

### 2. 金融商品列表
- 從後端獲取所有金融商品清單，顯示商品名稱、價格、手續費率等資訊。
- 使用者可設定購買數量並將商品加入個人喜好清單。

### 3. 喜好金融商品清單
- 根據目前使用者的 `userid` 從後端撈取喜好清單。
- 顯示每筆喜好商品的詳細資料（產品名稱、帳號、數量、總金額、總手續費、電子信箱）。
- 可刪除不再喜好的商品。
- 支援在表格中直接編輯數量，並透過 PUT API 更新後端資料。

---

## 專案架構

### 前端 (Vue 3)

- **App.vue**  
  管理整體版面，根據路由參數載入使用者資料並顯示。
- **ProductList.vue**  
  顯示金融商品清單並支援加入喜好清單。
- **LikeList.vue**  
  顯示使用者喜好清單，支援刪除與行內修改功能。

### 後端 (Spring Boot)

- **Controller**  
  提供 RESTful API 端點，包括：  
  - `/api/user/{userid}`：取得使用者資訊  
  - `/api/products`：取得金融商品列表  
  - `/api/like-list/{userid}`：取得該使用者喜好清單  
  - `/api/like-list` (POST)：新增喜好清單某項目  
  - `/api/like-list` (DELETE)：刪除喜好清單某項目  
  - `/api/like-list` (PUT)：更新喜好清單資料
- **Service**  
  實作業務邏輯，如資料驗證、交易控制。
- **Repository**  
  與資料庫進行 CRUD 操作。

---

## 使用方式

### 1. 啟動後端服務
```bash
./mvnw gradlew build
./mvnw gradlew bootrun
```

### 2. 啟動前端
```bash
./mvnw npm runserv
```