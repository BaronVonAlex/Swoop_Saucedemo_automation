# Test Automation Engineering - Swoop_Saucedemo_automation

## Project Overview

This project involves creating and executing automated tests for two different web applications, [swoop.ge](https://swoop.ge) and [saucedemo.com](https://saucedemo.com), using Selenide. The tests include validating various functionalities and ensuring the robustness of the web applications. The project emphasizes using Page Object Model, Fluent Interface patterns, and generating comprehensive Allure reports.

## Table of Contents
1. [Installation](#installation)
2. [Project Structure](#project-structure)
3. [Test Cases](#test-cases)
   - [Swoop Tests](#swoop-tests)
   - [SauceDemo Tests](#sauce-demo-tests)
4. [TestNG Configuration](#testng-configuration)
5. [Conflict Resolution](#conflict-resolution)
6. [Project Development Requirements](#project-development-requirements)
7. [Grading](#grading)

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/BaronVonAlex/Swoop_Saucedemo_automation.git
    cd Project2
    ```

2. Create and switch to the development branch:
    ```sh
    git checkout -b project_dev
    ```

3. Set up your environment:
    - Ensure you have Java JDK 8 or higher installed.
    - Install Maven.

4. Install project dependencies:
    ```sh
    mvn clean install
    ```

## Project Structure

Project2
│
├── src
│ ├── main
│ │ └── java
│ │ └── com
│ │ └── yourusername
│ │ ├── swoop
│ │ │ ├── pages
│ │ │ └── tests
│ │ └── saucedemo
│ │ ├── pages
│ │ └── tests
│ └── test
│ └── resources
│ └── testng.xml
│
├── pom.xml
└── README.md

## Test Cases

### Swoop Tests

#### 1. `rangeTest`
- **Severity:** Critical
- **Priority:** High
- **Steps:**
  1. Navigate to [swoop.ge](https://swoop.ge)
  2. Go to "დასვენება".
  3. Set a price range in the "ფასი" element.
  4. Verify that all returned elements on the first page fall within the selected range.
- **DataProvider:** Provides 5 different lower and upper bounds for price range.

#### 2. `favouriteOfferTest`
- **Severity:** Major
- **Priority:** Medium
- **Steps:**
  1. Navigate to [swoop.ge](https://swoop.ge)
  2. Go to "კატეგორიები".
  3. Hover on any category and choose any sub-category.
  4. Attempt to add the first returned item to the favorites list.
  5. Verify that the login page appears.
  6. Check that the vouchers are not sold out.

#### 3. `shareOfferTest`
- **Severity:** Major
- **Priority:** Medium
- **Steps:**
  1. Navigate to [swoop.ge](https://swoop.ge)
  2. Go to "კატეგორიები".
  3. Hover on any category and choose any sub-category.
  4. Attempt to share the first returned item.
  5. Validate that a Facebook login page window appears.

#### 4. `noOffersSoldTest`
- **Severity:** Minor
- **Priority:** Low
- **Steps:**
  1. Navigate to [swoop.ge](https://swoop.ge)
  2. Go to any category.
  3. Find an offer that has "გაყიდულია 0" text.
  4. Validate that the progress bar in the card is entirely empty.

#### 5. `clearFilterTest`
- **Severity:** Medium
- **Priority:** Medium
- **Steps:**
  1. Navigate to [swoop.ge](https://swoop.ge)
  2. Modify location, payment method, and price filters in any category.
  3. Clear the filters using the delete button.
  4. Validate that the filters are reset to default.

### SauceDemo Tests

#### 1. `successfulLoginTest`
- **Severity:** Critical
- **Priority:** High
- **Steps:**
  1. Navigate to [saucedemo.com](https://saucedemo.com)
  2. Select `standard_user` credentials from the database.
  3. Log in with this user.
  4. Validate that all images on the landing page are loaded.

#### 2. `bannedUserLoginTest`
- **Severity:** Critical
- **Priority:** High
- **Steps:**
  1. Navigate to [saucedemo.com](https://saucedemo.com)
  2. Select `locked_out_user` from the database.
  3. Log in with this user.
  4. Validate that the error message "Epic sadface: Sorry, this user has been locked out." appears.
  5. Validate that the red X icon is visible.

#### 3. `problematicLoginTest`
- **Severity:** Major
- **Priority:** Medium
- **Steps:**
  1. Navigate to [saucedemo.com](https://saucedemo.com)
  2. Select `problem_user` from the database.
  3. Log in with this user.
  4. Validate that the product images are loaded (test should fail if the images are not loaded).

#### 4. `logOutTest`
- **Severity:** Major
- **Priority:** Medium
- **Steps:**
  1. Navigate to [saucedemo.com](https://saucedemo.com)
  2. Select `standard_user` from the database.
  3. Log in with this user.
  4. Log out.
  5. Validate that the Username and Password inputs are empty.
