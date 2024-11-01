package com.example.mebelshik.Config;

public class S3Configuration {
    private static String BUCKET_NAME = "d2197f97-art-mebel";
    private static String ACCESS_KEY = "OBDND5YEKY0SP2V12WLK";
    private static String SECRET_KEY = "2EyWhnQ1cmlZUdB7dJdqusvZ0wGaCaBjf2og87om";
    private static String REGION = "ru-1";


    public static String getBucketName() {return BUCKET_NAME;}
    public static String getAccessKey() {return ACCESS_KEY;}
    public static String getSecretKey() {return SECRET_KEY;}
    public static String getRegion() {return REGION;}
}
