package com.vila;

import java.util.Map;

public class ProductImages {

    private static final Map<String, String> MAP = Map.ofEntries(
        Map.entry("Drift Pant",           "/com/vila/images/drift-pant.webp"),
        Map.entry("Transit Pant",         "/com/vila/images/transit-pant.webp"),
        Map.entry("Rally Jogger",         "/com/vila/images/rally-jogger-black.webp"),
        Map.entry("Easy Jogger",          "/com/vila/images/easy-jogger.webp"),
        Map.entry("Studio Wide-Leg",      "/com/vila/images/studio-wide-leg.webp"),
        Map.entry("Court Tee",            "/com/vila/images/court-tee.webp"),
        Map.entry("Halftrack Zip",        "/com/vila/images/halftrack-zip.webp"),
        Map.entry("Terrain Quarter-Zip",  "/com/vila/images/terrain-qz.webp"),
        Map.entry("Current Hoodie",       "/com/vila/images/current-hoodie.webp"),
        Map.entry("Ground Crew",          "/com/vila/images/ground-crew.webp"),
        Map.entry("Rest Day Crew",        "/com/vila/images/rest-day-crew.webp"),
        Map.entry("Ridge Puffer",         "/com/vila/images/ridge-puffer.jpg")
    );

    public static String get(String productName) {
        return MAP.getOrDefault(productName, null);
    }
}
