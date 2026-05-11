<div align="center">
  <img src="./doc/iconpepper.png" width="40%" alt="YourBestFriendPepper logo">
</div>

# YourBestFriendPepper

> **Facultative Internship (6 months)**
> **LIRMM**
> Developed by **Bouaboud Karima**

**YourBestFriendPepper** is an Android application designed for the **Pepper** humanoid robot (SoftBank Robotics). The app offers children aged **5 to 8** an interactive journey to identify, express, and understand emotions through storytelling and interactive quizzes about the adventures of Helmouth the mammoth.

---

## Target Audience

* **Age Range:** 5 – 8 years
* **Settings:** kindergartens, educational workshops, child–robot mediation

---

##  Educational Goals

| Goal                      | Description                                                            |
| ------------------------- | ---------------------------------------------------------------------- |
| **Emotion recognition**   | Identify and understand emotions through the stories of Helmouth the mammoth. |
| **Cognitive engagement**  | Encourage children to answer comprehension and emotional quizzes.      |
| **Multimodal engagement** | Combine oral narration **and** interactive quizzes to reinforce learning. |

---

## 🔍 Research Question

> **“How does a speech-touch bimodal interaction with the Pepper humanoid robot affect emotion-recognition and verbalization in preschoolers (5–8 years)?”**

This question guides the experimental study described in the internship report. The present README focuses on the software implementation.

---

## User Flow

1. **Welcome Screen:** illustration + **Start** button.
2. **Main Menu:** two main features — Listen to a story • Comprehension quiz.
3. **Stories:**
   * *La moumoute du mammouth Helmouth*
   * *Le dessin de Helmouth*
   * *Le mammouth Helmouth joue au foot*
   * *Helmouth a perdu sa confiance*
4. **Quiz:** multiple‑choice questions to validate understanding of the stories and emotions.
<img src="./doc/Pepper-Enfant TER.png" width="80%" alt="YourBestFriendPepper screen flow">

---

## 🛠️ Technology Stack

| Technology                                    | Purpose                                                                             |
| --------------------------------------------- | ----------------------------------------------------------------------------------- |
| **Android Studio Bumblebee 2021.1.1 Patch 1** | Development & deployment on Pepper                                                  |
| **QiSDK**                                     | Access to Pepper’s voice, motion, and tactile sensors (robot running **NAOqi 2.9**) |
| **Kotlin**                                    | Main language (fragment‑based architecture)                                         |
| **Room / SQLite**                             | Local persistence (quiz answers, logs)                                              |

---

## ⚡ Setup & Requirements

1. **Start Pepper:** press the button under the screen **once** to boot.
2. **Network:** connect Pepper and your laptop to the same Wi‑Fi or mobile hotspot.
3. **ADB Connection:** pull down Pepper’s notification bar to get the IP address, then:

   ```bash
   adb connect <PEPPER_IP>:5555
   ```
6. **Android Studio:**

   * Open the project with **Android Studio Bumblebee**.
   * Verify Pepper appears as a connected device.
   * If not:

     ```bash
     adb kill-server
     adb start-server
     adb connect <PEPPER_IP>:5555
     ```

---

## ▶️ Run the App

1. Boot Pepper.
2. Establish **ADB connection**.
3. In Android Studio, select Pepper and press ▶️ to install and launch the app.

---

## Code Structure

```
app/
 ├─ ui/
 │   ├─ MainActivity.kt       
 │   └─ Fragments/
 │       ├─ StorySelectionFragment.kt # story selection list
 │       ├─ ListenStoryFragment.kt    # reads the story
 │       └─ …
 ├─ data/                     # Room database (quiz, logs)
 └─ assets/animations/        # .qianim animation files
```

---

> *“Pepper isn’t just a robot — he’s children’s best friend!”* 🤖💛
