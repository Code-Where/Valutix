# 🔐 VaultIx — Secure Password Manager

VaultIx is a **modern, secure, and offline-first Android password manager** built with **Jetpack Compose**, focusing on **strong security**, **clean architecture**, and **premium user experience**.

---

## ✨ Features

- Add, edit, and delete passwords  
- Bottom-sheet based flows  
- Auto password generator  
- Password strength indicator  
- Progressive password tips (shown one-by-one)  
- Password visibility toggle  
- Empty-state UI when no passwords exist  
- Animated splash screen  

---

## 🛡️ Security

- Local encrypted storage  
- Biometric authentication (Fingerprint / Face)  
- Auto-lock when app goes to background  
- No cloud sync (privacy-first)  
- No plaintext password exposure  

---

## 🎨 UI & UX

- Built entirely with Jetpack Compose  
- Material 3 design system  
- Smooth animations and transitions  
- Clean and minimal interface  

---

## 🧱 Tech Stack

- **Language:** Kotlin  
- **UI:** Jetpack Compose, Material 3  
- **Architecture:** MVVM  
- **DI:** Hilt (Dagger)  
- **Database:** Room  
- **State Management:** StateFlow  
- **Security:** Android Biometric API  

---

## 🏗️ Project Structure

```text
com.abhishek.vaultIx
│
├── data
│   ├── local
│   │   ├── dao
│   │   ├── database
│   │   └── entity
│   └── repository
│
├── domain
│   └── repository
│
├── ui
│   ├── components
│   ├── screens
│   ├── states
│   └── viewmodels
│
├── di
│   └── hilt modules
│
└── util
```

# 🔐 Password Strength Levels
<div align="center">

|      Strength Level           |         Weak        |       Medium        |        Strong       |     Very Strong     |
|:-----------------------------:|:-------------------:|:-------------------:|:-------------------:|:-------------------:|
|           Color               | 🔴 Red              |🟡 Yellow           |🟠 Orange            |🟢 Green            |

</div>

## 🚀 Getting Started

1️⃣ Clone the repository
git clone https://github.com/your-username/VaultIx.git

2️⃣ Open in Android Studio

- Android Studio Hedgehog or later recommended

- Min SDK: 26+

3️⃣ Run the app

- Use a real device for biometric testing

# 📸 Screenshots

# 👨‍💻 Author

### Abhishek Dhawan
###### Android Developer | Kotlin | Jetpack Compose

Building secure, meaningful, and beautiful apps.

