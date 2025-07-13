package api;

public class DriverUtils {
    public static void killDriverProcess() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
                Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
            } else if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec("pkill -f chromedriver");
                Runtime.getRuntime().exec("pkill -f geckodriver");
            }
        } catch (Exception e) {
            System.err.println("Failed to kill driver process: " + e.getMessage());
        }
    }
}
