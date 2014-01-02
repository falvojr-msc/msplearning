M-SPLearning
===========

Master's project called M-SPLearning, the goal is to apply the concepts of Software Product Lines (SPL) in the domain of M-Learning applications.

Environment
-----------------------
+ Download and install **Apache Maven**[¹](#references)
+ Download and install **Android SDK**[²](#references)
    + Run the "SDK Manager" and install or upgrade only the following items:
        + Tools
            + Android SDK Tools
            + Android SDK Plataform-tools
        + Android 4.1.2 (API 16) - *M-SPLearning required API*
            + SDK Platform
            + ARM EABI v7a System Image
		+ Android 4.0 (API 14) - *ActionBarSherlock required API*
            + SDK Platform
        + Extras
            + Android Support Repository
            + Android Support Library
            + Google USB Driver
+ Download and install **Eclipse IDE for Java EE Developers**[³](#references)
    + Install **Android Development Tools (ADT) Plugin**[⁴](#references)
    + Install **Android Configurator for M2E Plugin**[⁵](#references)

Execution
-----
+ Clone **ActionBarSherlock**[⁶](#references) repository on your computer:
```
git clone https://github.com/JakeWharton/ActionBarSherlock.git
```

+ Clone M-SPLearning repository on your computer:
```
git clone https://github.com/veniltonjr/msplearning.git
```

+ On Eclipse import as "Existing Maven Projects" the following projects:
```
ActionBarSherlock
├── actionbarsherlock <--
│   └── pom.xml
├── actionbarsherlock-fest
│   └── pom.xml
├── actionbarsherlock-i18n
│   └── pom.xml
├── actionbarsherlock-samples
│   ├── demos
│   │   └── pom.xml
│   ├── fragments
│   │   └── pom.xml
│   ├── known-bugs
│   │   └── pom.xml
│   ├── roboguice
│   │   └── pom.xml
│   ├── styled
│   │   └── pom.xml
│   └── pom.xml
└── pom.xml
```
```
M-SPLearning
├── android-app <--
│   └── pom.xml
├── restful-app <--
│   └── pom.xml
├── entity      <--
│   └── pom.xml
├── repository  <--
│   └── pom.xml
├── service     <--
│   └── pom.xml
└── pom.xml
```

References
---------
¹ Available [here](http://maven.apache.org/download.html). *We're using Maven 3.0.5*. 

¹ Available [here](http://developer.android.com/sdk). *We're using Android SDK 22.3 (USE AN EXISTING IDE)*. 

² Available [here](http://www.eclipse.org/downloads). *We're using Eclipse Kepler (4.3.1) SR1*.

⁴ Available [here](http://developer.android.com/sdk/installing/installing-adt.html). *We used the option "Help > Install New Software..." for installation*.

⁵ Available [here](http://rgladwell.github.io/m2e-android). *We used the option "Help > Eclipse Marcketplace..." for installation*.

⁶ Available [here](http://actionbarsherlock.com/). *We're using ActionBarSherlock 4.4.0*.
