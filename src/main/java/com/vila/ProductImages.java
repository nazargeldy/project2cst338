package com.vila;

import java.util.Map;

public class ProductImages {

    private static final Map<String, String> MAP = Map.ofEntries(
        Map.entry("Drift Pant",           "/com/vila/images/drift-pant.jpg"),
        Map.entry("Transit Pant",         "/com/vila/images/transit-pant.jpg"),
        Map.entry("Rally Jogger",         "/com/vila/images/rally-jogger-black.jpg"),
        Map.entry("Easy Jogger",          "/com/vila/images/easy-jogger.jpg"),
        Map.entry("Studio Wide-Leg",      "/com/vila/images/studio-wide-leg.jpg"),
        Map.entry("Court Tee",            "/com/vila/images/court-tee.jpg"),
        Map.entry("Halftrack Zip",        "/com/vila/images/halftrack-zip.jpg"),
        Map.entry("Terrain Quarter-Zip",  "/com/vila/images/terrain-qz.jpg"),
        Map.entry("Current Hoodie",       "/com/vila/images/current-hoodie.jpg"),
        Map.entry("Ground Crew",          "/com/vila/images/ground-crew.jpg"),
        Map.entry("Rest Day Crew",        "/com/vila/images/rest-day-crew.jpg"),
        Map.entry("Ridge Puffer",         "/com/vila/images/ridge-puffer.jpg")
    );

    public static String get(String productName) {
        return MAP.getOrDefault(productName, null);
    }
}
