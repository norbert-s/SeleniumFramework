package testSetup.setters;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;


public interface GlobalSettingsGetterMethods {
    static String getBranchId(){
        if(System.getProperty("branch_id")==null) return "master";
        else return System.getProperty("branch_id");
    }

    static String getEmailTo() {
        return System.getProperty("emailto");
    }


    default int getwaitForTime() {
        if(System.getProperty("waitfortime")==null) return 30;
        else return Integer.parseInt(System.getProperty("waitfortime"));
    }

    static int getwaitForTime_static() {
        if(System.getProperty("waitfortime")==null) return 30;
        else return Integer.parseInt(System.getProperty("waitfortime"));
    }


    static String getEnvironment(){
        return System.getProperty("practiceenvironment");
    }
    static String getEnvironment_static(){
        return System.getProperty("practiceenvironment");
    }


    static boolean getHeadless() {
        try{
            if (System.getProperty("headless") == null||System.getProperty("headless").contains("true")) return true;
            else if(System.getProperty("headless").contains("false")){
                return false;
            }
            else{
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }


    static void setLogLevelBasedOnEnv(){
        try{
            String debugLevel = System.getProperty("debuglevel");
            if(System.getProperty("debuglevel")==null)  Configurator.setRootLevel(Level.ERROR);
            else if(debugLevel.contains("debug")){
                Configurator.setRootLevel(Level.DEBUG);
            }
            else if(debugLevel.contains("warn")){
                Configurator.setRootLevel(Level.WARN);
            }
            else if(debugLevel.contains("error")){
                Configurator.setRootLevel(Level.ERROR);
            }
            else{
                Configurator.setRootLevel(Level.ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }


    static int setRetryNumberOfTimes(){
        try{
            if(System.getProperty("retryfailed")==null){
                return 2;
            }else if(Integer.parseInt(System.getProperty("retryfailed"))>=0&&Integer.parseInt(System.getProperty("retryfailed"))<=3){
                return Integer.parseInt(System.getProperty("retryfailed"));
            }
            else{
                return 2;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }


        /**
         * @see #isItFrontEndPipeLine()
         * if it is the frontend pipeline ( it should be true only when the run is triggered from the frontend pipeline
         * and it will be using another set of email/hashtag combination as per URLGetterForDataProviders class
        */
    static boolean isItFrontEndPipeLine(){
        try{
            if(System.getProperty("isitfrontend")==null){
                return false;
            }
            else if(System.getProperty("isitfrontend").contains("true")){
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }
    /**
     * @see  #getDebugLevel()
     * getter of debug level, it is used in the setup logger
    */
    static String getDebugLevel(){
        String debugLevel = System.getProperty("debuglevel");
        if(System.getProperty("debuglevel")==null)  return "error";
        else if(debugLevel.contains("debug")){
            return "debug";
        }
        else if(debugLevel.contains("warn")){
            return "warn";
        }
        else if(debugLevel.contains("error")){
            return "error";
        }
        else{
            return "error";
        }
    }
        /**
         * @see  #isIncognitoNeeded()
         * by default incognito is used
         */
    static boolean isIncognitoNeeded(){
        if(System.getProperty("isincognitoneeded")==null||System.getProperty("isincognitoneeded").equals("true")) return true;
        else {
            return false;
        }
    }
}