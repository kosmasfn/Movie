# Movie Android App

**Movie** is a modern Android application built using **Jetpack Compose**, **Hilt**, and using MVVM clean architectural principles.  
This project is designed demonstrating real-world architecture, scalability, and best practices.

---

## Table of Contents

- Features
- Tech Stack
- Architecture Overview
- Architecture Diagrams
- Project Setup
- Build & Configuration
- Environment Configuration
- Build Types
- Project Structure
- How to Run

---

## Features

- Genre List Tab
- Movie List Tab
- Movie Details
- Trailer
- Review
- Endless Scroll for Movie List & Reviews using pagination
- ViewModel-driven state management
- Dependency injection with Hilt
- Networking using Retrofit, Gson, RxJava2
- Lottie animations

---

## Tech Stack

- Kotlin
- Jetpack Compose
- Material3 + Material
- Hilt
- Retrofit + Gson + RxJava2
- StateFlow

---

## Architecture Overview

The project follows **MVVM + Clean Architecture** principles:

- **UI layer** handles rendering and user interaction
- **Domain layer** contains business logic (use cases)
- **Data layer** manages data sources and mapping
- **Dependency Injection** handled via Hilt modules

The architecture ensures:
- Clear separation of concerns
- Testability
- Scalability
- Maintainability
---

## Architecture Diagrams

### High-Level Architecture

```
UI (Compose)
   ↓
ViewModel
   ↓
UseCase (Domain)
   ↓
Repository (Data)
   ↓
Network / Data Sources
   ↑
StateFlow → UI
```

---

## Project Setup

### Prerequisites

- Android Studio: Otter 3 Feature Drop | 2025.2.3
- Kotlin: 2.3.0
- JDK: 11
- Gradle: 9.1.0
- Min SDK: 24
- Target SDK: 36
- Compile SDK: 36

---

## Environment Configuration

Add the following to `gradle.properties`:

```
BASE_URL=https://api.themoviedb.org/3/
```

Access it in code via:

```kotlin
BuildConfig.BASE_URL
```

---

## Project Structure

```
com.kosmasfn.amarbank
│
├── data
│   ├── network       # Retrofit API definitions
│   ├── repository    # Repository implementations
│   ├── service       # Remote data sources / services
│   ├── Mapper.kt     # Data ↔ Domain mappers
│   └── DataModel.kt
│
├── di
│   ├── DataModule.kt
│   ├── NetworkModule.kt
│   ├── RepositoryModule.kt
│   └── UseCaseModule.kt
│
├── domain
│   ├── usecase       # Business logic
│   └── DomainModel.kt
│
├── ui
│   ├── component     # Reusable Compose components
│   ├── model         # UI models
│   ├── theme         # Compose theme
│   ├── utils         # UI utilities
│   ├── MainActivity.kt
│   └── Mapper.kt     # Domain ↔ UI mappers
│
└── MovieApplication.kt
```

---

## How to Run

1. Clone the repository
2. Add `BASE_URL` & `POSTER_BASE_URL` (required to display image) to `gradle.properties`
3. Add `BEARER_TOKEN` to `gradle.properties` (register at [themoviedb.org]() to get TOKEN)
4. Open the project in Android Studio
5. Sync Gradle
6. Run on an emulator or physical device 🚀

