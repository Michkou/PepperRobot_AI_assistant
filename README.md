<div align="center">
  <img src="./doc/iconpepper.png" width="40%" alt="Logo YourBestFriendPepper">
</div>

# YourBestFriendPepper

> **Stage de Recherche et Développement (6 mois)**
> **LIRMM**
> Développé par **Bouaboud Karima**

**YourBestFriendPepper** est une application Android interactive conçue pour le robot humanoïde **Pepper** (SoftBank Robotics), accompagnée d'une plateforme web complémentaire. L'application propose aux enfants de **5 à 8 ans** une expérience immersive de narration (Storytelling) autour des aventures d'Helmouth le mammouth.

---

## 🎯 Objectifs Pédagogiques

| Objectif | Description |
| :--- | :--- |
| **Compréhension Narrative** | Évaluer la capacité de l'enfant à retenir et comprendre les éléments clés d'une histoire. |
| **Reconnaissance Émotionnelle** | Aider l'enfant à identifier les émotions vécues par les personnages (colère, tristesse, fierté...). |
| **Engagement Multimodal** | Combiner la voix, les gestes du robot et les interactions tactiles sur tablette pour maintenir l'attention de l'enfant. |

---

## 🔍 Question de Recherche

L'application a été développée comme outil technologique principal pour répondre à la problématique scientifique suivante :

> **« Dans quelle mesure la présence physique (Robot vs Web) et l'expressivité émotionnelle (Avec vs Sans émotion) d'un robot conteur influencent-elles la compréhension de l'histoire et la perception de la machine par l'enfant ? »**

Cette étude comparative repose sur un protocole rigoureux de 8 conditions expérimentales croisant l'incarnation physique et les indices émotionnels. Ce *README* se concentre sur l'implémentation logicielle de ce protocole.

---

## 📱 Parcours Utilisateur (User Flow)

1. **Écran d'accueil :** Illustration thématique et bouton **Démarrer**.
2. **Menu Principal :** Accès aux fonctionnalités principales — Mode Écoute Simple • Mode Questionnaire Expérimental.
3. **Flux Expérimental (Nouveau protocole) :**
   * **Questionnaire Avant (Pré-test) :** Saisie de l'âge, vérification des connaissances préalables et 10 questions sur la perception anticipée du robot (échelle de Likert visuelle).
   * **Compréhension de l'Histoire :** Quiz dynamique de 8 questions généré selon la condition expérimentale.
   * **Questionnaire Après (Post-test) :** 10 questions finales sur le ressenti de l'enfant, suivies de l'envoi transparent et automatique des données vers le Cloud (Firebase).

---

## 🛠️ Technologies Utilisées (Stack Technologique)

| Technologie | Objectif |
| :--- | :--- |
| **Android Studio (Bumblebee)** | Environnement de développement principal pour l'application Pepper. |
| **QiSDK (NAOqi 2.9)** | Bibliothèque d'accès aux capteurs vocaux, de mouvement et tactiles de Pepper. |
| **Kotlin** | Langage de programmation principal (architecture basée sur des Fragments). |
| **Firebase Firestore** | Base de données Cloud NoSQL centralisant les sessions expérimentales et les réponses des enfants pour l'analyse statistique. |
| **React / Vite** | Frameworks web pour le développement du site interactif (condition "Web" de l'expérience). |

---

## ⚡ Installation & Prérequis

1. **Démarrer Pepper :** Appuyez une fois sur le bouton situé sous la tablette pour l'allumer.
2. **Réseau :** Connectez Pepper et votre ordinateur portable au même réseau Wi-Fi ou partage de connexion.
3. **Connexion ADB :** Faites glisser la barre de notification de Pepper vers le bas pour lire son adresse IP, puis tapez dans votre terminal :
   ```bash
   adb connect <PEPPER_IP>:5555
   ```
4. **Android Studio :**
   * Ouvrez le projet avec **Android Studio Bumblebee**.
   * Assurez-vous que Pepper apparaît bien dans la liste des appareils connectés.
   * En cas de problème de connexion :
     ```bash
     adb kill-server
     adb start-server
     adb connect <PEPPER_IP>:5555
     ```

---

## ▶️ Lancer l'Application

1. Démarrez le robot Pepper.
2. Établissez la connexion ADB.
3. Dans Android Studio, sélectionnez l'appareil Pepper et appuyez sur **▶️ (Run)** pour installer et lancer l'application.

---

## 📂 Structure du Code

```text
app/
 ├─ ui/
 │   ├─ MainActivity.kt       
 │   └─ Fragments/
 │       ├─ StorySelectionFragment.kt      # Menu des histoires basiques
 │       ├─ ListenStoryFragment.kt         # Moteur de lecture de l'histoire
 │       ├─ ExperimentSelectionFragment.kt # Interface des 8 conditions (EL1, NL1...)
 │       ├─ SurveyAvantFragment.kt         # Questions pré-expérience (UI & Logique)
 │       ├─ StoryComprehensionFragment.kt  # Quiz dynamique de compréhension
 │       └─ SurveyApresFragment.kt         # Questions post-expérience & envoi Firebase
 ├─ data/                                  # Modèles (Story, Question) & SessionManager
 └─ assets/animations/                     # Fichiers de mouvements du robot (.qianim)
```

---

<div align="center">
  <i>« Pepper n'est pas juste un robot — c'est le meilleur ami des enfants ! »</i> 🤖💛
</div>
