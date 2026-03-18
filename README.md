# 🚀 Mini E-Commerce Backend (Java Core - Console)

## 📌 Overview

Đây là project **Mini E-Commerce Backend chạy bằng Console** nhằm mô phỏng cách một hệ thống backend thực tế hoạt động.

> ⚡ Không có UI – tập trung hoàn toàn vào:

- Business Logic
- Data Processing
- Clean Architecture

---

## 🎯 Mục tiêu

- Luyện **Java Core (OOP, Collections, Exception, File I/O)**
- Hiểu cách tổ chức **backend system**
- Áp dụng:
  - Encapsulation
  - Polymorphism
  - Layered Architecture
  - Logging

---

## 🏗️ Project Structure

```
MiniE-Commerce/
│
├── src/
│   └── ecommerce/
│        ├── model        # Entity classes (User, Product, Order...)
│        ├── service      # Business logic
│        ├── repository   # Data layer (in-memory)
│        ├── exception    # Custom exceptions
│        ├── util         # Utilities (Logger,...)
|        ├── payment      # Payment Strategy
|        ├── discount     # Discount Strategy
|        ├── Enum         # Enum OrderStatus, LogLevel
│        └── app          # Main application (console UI)
│
├── data/
│   └── orders.txt        # Lưu order (giả lập database)
│
├── logs/
│   ├── info.log           # Log thông thường
│   └── error.log         # Log lỗi
│
└── README.md
```

---

## 📦 Features

### 👤 User Management

- Register user
- Login
- Deposit money

**Rules:**

- Balance không được âm
- Chỉ thay đổi qua method

---

### 🛍️ Product Management

- Add product
- View product
- Search product (Stream API)

**Rules:**

- Stock không âm
- Không mua nếu hết hàng

---

### 🛒 Cart System

- Add to cart
- Remove from cart
- View cart
- Clear cart

---

### 📦 Order System

- Checkout
- Pay order
- Cancel order
- Ship order

**Order Status Flow:**

```
CREATED → PAID → SHIPPED
CREATED/PAID → CANCELLED
```

**Rules:**

- Không được chuyển trạng thái sai
- Kiểm tra stock khi checkout
- Trừ tiền user khi thanh toán

---

### 💸 Discount System (Polymorphism)

Interface:

```
Discount
```

Implementations:

- `PercentageDiscount`
- `FixedDiscount`

---

### 💳 Payment System (Strategy Pattern)

Interface:

```
Payment
```

Implementations:

- `CreditCardPayment`
- `PaypalPayment`
- `WalletPayment`

---

### ⚠️ Exception Handling

Custom Exceptions

---

### 💾 File Persistence

Lưu order vào file:

```
data/orders.txt
```

Format:

```
OrderID|UserID|Total|Status|Date
```

### 📝 Logging System

Custom Logger:

- `logInfo()`
- `logError()`

Ghi vào:

```
logs/info.log
logs/error.log
```

**Log các sự kiện:**

- Register / Login
- Checkout / Payment / Ship
- Errors (business logic)

---

## ▶️ How to Run

### 1. Compile

```bash
javac app/App.java
```

### 2. Run

```bash
java app.App
```

---

## 🧠 Kiến thức áp dụng

- OOP (Encapsulation, Abstraction, Polymorphism)
- Java Collections (Map, List)
- Stream API
- Exception Handling
- File I/O
- Clean Code & Refactoring

---

---

## Bounus Concurrency (Advanced)

- Giả lập nhiều user mua cùng product:

- Tạo 10 threads

- Mỗi thread thực hiện checkout()

- Xử lý race condition bằng:

  synchronized

  hoặc AtomicInteger

---

## 🚀 Future Improvements

- Chuyển sang dùng Database (MySQL)
- Build REST API (Spring Boot)
- Thêm Authentication (JWT)
- Logging framework (Log4j)

---

## 📌 Author

- Mini project phục vụ học Java Backend
- Focus vào tư duy thiết kế hệ thống

---

## 💡 Ghi chú

Đây là project console nhưng:

> ⚡ Logic được thiết kế giống backend thật

👉 Có thể dễ dàng nâng cấp lên:

- Spring Boot
- REST API
- Production system

---

**Happy Coding 🚀**
