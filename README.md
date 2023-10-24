
# The Challenge: Grogu Rides

## Motivation

In the field of software development, there are many different approaches to designing, implementing, and maintaining software applications. These approaches can vary widely depending on the programmer's experience, training, and personal preferences.

This challenge is designed to help team members understand and appreciate each other's unique perspectives on software architecture, patterns, and best practices. By sharing our personal approaches to these topics, we can learn from each other, improve our services, and discover new techniques and methods that we may not have considered before.

## Objectives

The primary objective of this challenge is to create a well-structured, understandable, and maintainable application that meets the specific needs of a green vehicle rental company, Grogu Rides. By doing so, participants will be able to demonstrate their skills and knowledge in JVM-based languages, Spring Boot, and other related technologies.

In addition to showcasing their technical abilities, participants will also have the opportunity to practice documenting their work, making decisions based on business needs, and working collaboratively with other team members. By doing so, they will develop important skills that will serve them well in their future projects and professional endeavors.

Overall, the challenge is intended to be a fun, educational, and rewarding experience that helps participants improve their skills and deepen their understanding of software development.

## Rules

- The project domain will be that of a "green" vehicle rental company: *Grogu Rides*.

- The user must create a code repository in their personal Gitlab space with the name: *username/the-challenge-grogu-rides*

- The repository must contain this explanation file of the challenge in markdown format in its root directory.

- The project must be done with a *JVM language* such as *Java or Kotlin* and make use of *Spring Boot*. The participant can choose whichever versions they want of the language, framework, libraries, or dependency manager.

- The project must have some type of *persistence system* (or several).

- The project may have calls to *external resources or APIs* (public and free).

- The project must be documented in some way, with special emphasis on the "why?" of the decisions rather than the "how?". You can use ADRs, a self-published book, or whatever you consider appropriate.

- The project must be self-contained and primarily designed for local execution. You can generate docker-compose files, scripts, or whatever you consider necessary.

- The project must implement one of these use cases:
    - API to register, query, modify, and delete vehicles (mandatory).
    - Simple user management (optional).
    - Simple rental/booking management (optional).
    - Any other subdomain (vehicle repair, marketing, customer service) (optional).

## Recommendations

- Use TDD or other techniques or resources as seen in Accelerate.

- The project should be properly tested.

- Use the architectures, patterns, practices, libraries, and infrastructure pieces that you consider appropriate, but remember to document the reasons for choosing each of them.

- Spend time whenever you want or in your Crafternoon.

- Have fun.


```
Gradle method:
./gradlew :spotlessJavaCheck
./gradlew :spotlessJava
./gradlew :spotlessJavaApply
./gradlew clean build
./gradlew test
./gradlew testIntegration
./gradlew testContract
./gradlew jacocoTestReport
    -> ./build/jacoco_report
./gradlew bootRun    
```