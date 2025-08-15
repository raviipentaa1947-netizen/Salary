# Salary Calculator (Android, Java)

An Android app that calculates net monthly salary with:
- Base salary
- Tax and medical deductions
- PF 10%
- Dabba units (× ₹30)
- Leave days (base/30 per day)
- Automatic 5th Monday bonus (adds one day pay if current month has 5 Mondays)

## Screens
- Splash screen (2 seconds)
- Main calculator screen

## Build (Android Studio)
1. Open Android Studio → **Open** → select this folder.
2. Let Gradle sync.
3. Run on a device/emulator.

## Custom App Icon & Splash
- Adaptive launcher icon defined in `mipmap-anydpi-v26/ic_launcher*.xml` with a ₹ logo.
- Splash vector drawable at `res/drawable/splash_logo.xml`. Replace with your own.
- To use a PNG splash, drop `splash.png` into `res/drawable` and set `@drawable/splash_logo` to `@drawable/splash` in `activity_splash.xml`.

## Package
- `com.example.salarycalculator`

## Notes
- This project includes Gradle wrapper files for convenience.
- If you change the package, update it in the manifest and Java package declarations.
