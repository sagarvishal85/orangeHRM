# OrangeHRM Automation Framework 🚀

This project is a Selenium-based test automation framework for [OrangeHRM Demo](https://opensource-demo.orangehrmlive.com/web/index.php/auth/login), built with Java, TestNG, and Page Object Model (POM). The framework is designed for login, candidate management, and resume validation with full environment configuration and logging.

---

## ✅ Features

- 🔐 **Secure Login** using environment variables (`.env`)
- 🧪 **TestNG Test Suite** with positive and negative login cases
- 🔄 **Page Object Model** for better maintainability
- 📂 **Candidate Creation** including resume upload
- 🔍 **Validation** of submission, listing, and resume link
- 📸 **Screenshot Capture** of candidate profile
- 🧾 **Logs & Reports** using SLF4J + Logback
- 📦 **Maven** for dependency management and test execution

---

2. Create .env File in Root

HRM_USERNAME=Admin
HRM_PASSWORD=admin123
BASE_URL=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
RESUME_PATH=C:\\Users\\yourname\\Documents\\resume.pdf

3. Dependencies

    Java 11+

    Selenium 4+

    TestNG

    WebDriverManager

    Apache Commons IO

    SLF4J + Logback

    dotenv-java
