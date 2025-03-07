//package com.server.vet.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.Properties;
//
//@Component
//@AllArgsConstructor
//public class DatabaseBackupTask {
//
//    private final ResourceLoader resourceLoader;
//
//    /*This cron expression consists of 6 fields:
//    Seconds (0-59)
//    Minutes (0-59)
//    Hours (0-23)
//    Day of month (1-31)
//    Month (1-12)
//    Day of week (0-7, with 0 and 7 being Sunday)
//    The 0 0/5 * * * * expression means:
//    Run the task at the 0th second of every minute
//    Run the task every 5 minutes (i.e. at the 0th, 5th, 10th, etc. minutes of the hour)
//    Run the task every hour
//    Run the task every day
//    Run the task every month
//    Run the task every day of the week*/
//
////    @Scheduled(cron = "0 0 0 * * SUN")  // every sunday
//    @Scheduled(cron = "0 0/5 * * * *")
////    @PostConstruct
//    public void executeScript() {
//        try {
//
////            get from properties load the properties and get values
//
////            Properties properties = new Properties();
////            properties.load(getClass().getResourceAsStream("/example.properties"));
////
////            System.out.println("Test "+properties.getProperty("path"));
//
////
//
//
//            String tomcatBase = System.getProperty("catalina.base").replaceAll("\\\\", "/");
//            System.out.println("tomcatBase "+tomcatBase);
//
//            String pathJBoss = String.format("%s/webapps/scripts/script.sh", tomcatBase);
//            System.out.println("webApp "+pathJBoss);
//
////            String jBossPath = System.getProperty("webapps");
////            String pathJBoss = "/webapps/scripts/script.sh";
////            System.out.println("JBOSS "+pathJBoss);
//
//            try { ////Users/bangelesandreades/Desktop/Vet/backOweModule/target/classes/scripts/script.sh
//                int res = Runtime.getRuntime().exec("chmod -R 777 "+pathJBoss).waitFor();
//                if (res == 0) {
//                    System.out.println("Permition executed successfully");
//                } else {
//                    System.out.println("Permition Script execution failed");
//                }
//            } catch (InterruptedException | IOException e) {
//                e.printStackTrace();
//            }
//
//
//            ////local is workin get path
////            Resource resource = new ClassPathResource("scripts/script.sh");
////            System.out.println("Absolute "+new ClassPathResource("").getFile().getAbsolutePath());
////            String scriptPath = resource.getFile().getPath();
//            // Execute the script
//            Process process = Runtime.getRuntime().exec(pathJBoss);
//            int exitValue = process.waitFor();
//            if (exitValue == 0) {
//                System.out.println("Script executed successfully");
//            } else {
//                System.out.println("Script execution failed");
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
