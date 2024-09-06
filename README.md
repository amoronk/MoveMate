# Movemate Clone

## Overview
This project replicates the screens and animations from the [Movemate Shipments Mobile App](https://dribbble.com/shots/21617837-Movemate-Shipments-Mobile-App) using **Jetpack Compose**. The goal was to closely match the design and animations for the **Home**, **Search**, **Calculate**, and **Shipment** screens.

## Screens Implemented

### 1. **Home Screen**
- Displays shipment tracking information with a list of shipments and recent actions.
- **Animations**:
    - The top bar and tabs animate into view.
    - Shipment items animate from the bottom.

### 2. **Search Screen**
- Contains a search bar where users can filter shipments or history.
- **Animations**:
    - Search bar and search results animate from the bottom up.

### 3. **Calculate Screen**
- Provides fields for calculating shipping costs based on sender/receiver location, weight, and category.
- **Animations**:
    - Text fields and buttons animate from the bottom up.
    - The categories section slides in from the right.

### 4. **Shipment Screen**
- Displays shipment history, categorized into tabs like "All", "Completed", etc.
- **Animations**:
    - The top bar and tabs animate in smoothly.
    - Shipment items animate from the bottom as you switch tabs.

## Resources Used
1. **Color Picker**: [RedKetchup Color Picker](https://redketchup.io/color-picker) was used to extract colors from the design.
2. **Icons**: Icons were sourced from [SVGRepo](https://www.svgrepo.com/).
3. **Image Background Removal**: [Remove.bg](https://www.remove.bg/upload) was used to remove backgrounds from images like the shipment freight icon and logo.

## Bottom Navigation Bar
The bottom navigation bar highlights the current screen:
- The selected icon is tinted with the primary color, and an indicator is added above the selected icon.

## Libraries Used
- **Jetpack Compose**: The entire UI is built using Jetpack Compose.
- **Compose Navigation**: Used to handle screen transitions between Home, Search, Calculate, and Shipment screens.
- **Material 3**: Material design components were used for a clean and modern look.
- **Dark Mode**: The project supports dark mode, ensuring a seamless experience in both light and dark themes.

## How to Run the Project
1. Clone the repository.
2. Open it in Android Studio.
3. Run the project on an Android device or emulator.

## Next Steps
- Fine-tune the animations.

This project replicates the Movemate Shipments app’s UI and animations using Jetpack Compose. Feel free to explore and modify it!
