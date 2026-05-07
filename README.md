# 🎮 JZelda Project – Practical Plan & Strategy (Duo)

## 1. Feasibility

Yes, it is possible to complete the project in **5.5 weeks** with two people, even with **intermediate Java knowledge (up to interfaces)** — as long as the scope remains **simple, focused, and well-organized**.

---

## 2. Project Objective

The goal is **NOT** to build a complex or polished game, but to:

* Fully satisfy all project requirements
* Correctly apply software design principles
* Deliver a functional, stable, and well-structured project

---

## 3. “AI” Requirement Clarification

Artificial Intelligence is **NOT explicitly required**.

The specification only requires:

> “Different enemy behaviors”

This can be achieved with simple logic, such as:

* Random movement
* Directly following the player
* Basic patrol patterns

❗ No advanced algorithms (e.g., pathfinding or machine learning) are needed.

---

## 4. Required Technical Features

Your project must include:

* **MVC architecture** *(mandatory)*
* **Observer pattern** *(mandatory)*
* GUI using **JavaFX** or **Swing**
* Use of **Java Streams (`Stream<T>`)**
* Audio playback
* Basic animations/effects
* User profile system *(stats, nickname, avatar)*

### Game Requirements

* At least **16 levels** *(duo)*

* At least **5 enemy types** *(duo)*

* Gameplay elements:

  * Score
  * Lives
  * Currency *(rupie)*
  * Items
  * Shop
  * Ranking

* A **level editor** *(simple but functional)*

---

## 5. Time Commitment

### Minimum (to stay on track)

* ~3 hours/day per person
* 20–25 hours/week per person

### Recommended (safer)

* 3–4 hours/day on weekdays
* 4–6 hours/day on weekends
* 25–35 hours/week per person

### Total Effort

* ~150–180 hours per person over 5.5 weeks

---

## 6. Work Division (Duo)

### 👤 Person A – Core Game Logic

* Game loop
* Player movement
* Collision system
* Enemy behaviors
* Game mechanics *(score, lives, combat)*
* Level loading

### 👤 Person B – Interface & Systems

* GUI *(JavaFX/Swing)*
* Menus and HUD
* Profile system
* Audio system
* Level editor
* Observer implementation *(UI updates)*

### 🤝 Shared Responsibilities

* UML diagram
* MVC design
* Design patterns
* Integration
* Documentation

---

## 7. Development Strategy

### Key Principles

* Keep everything **simple and modular**
* Build a **playable version early**
* Avoid unnecessary complexity

---

## 8. Suggested Timeline (5.5 Weeks)

### Week 1

* UML diagram
* MVC structure
* Basic window setup

### Week 2

* Player movement
* Map rendering
* Basic enemy

✅ **Goal:** Playable prototype

---

### Week 3

* Collision system
* Combat
* Second enemy type
* Score and lives

---

### Week 4

* GUI *(menus, HUD)*
* Profile system
* Audio

---

### Week 5

* 16 levels *(simple design)*
* Level editor *(basic version)*

---

### Final Days

* Testing and bug fixing
* Javadoc
* Final report

---

## 9. Key Risks to Avoid

* Starting without a UML diagram
* Mixing game logic with UI *(breaking MVC)*
* Not implementing Observer properly
* Overcomplicating the level editor
* Leaving documentation to the last minute

---

## 10. Final Recommendation

Focus on:

* ✅ Correct architecture *(MVC + Observer)*
* ✅ Simple but complete gameplay
* ✅ Clean and organized code

---

## 🧠 Mindset

> Think of it as:
> **“A well-designed academic project, not a commercial game.”**
