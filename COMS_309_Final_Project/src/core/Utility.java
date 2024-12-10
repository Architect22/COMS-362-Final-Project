package core;

public class Utility {
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String addPadding(int amount) {
        String text = "";
        for (int i = 0; i < amount; ++i) {
            text += " ";
        }
        return text;
    }

    public static void createHeader(int width, String title) {
        int padding = (width - title.length()) / 2;
        System.out.println("|--------------------------------------------------------------|");
        int endPadding;
        if (title.length() % 2 == 0) {
            endPadding = padding - 2;
        } else {
            endPadding = padding - 3;
        }
        System.out.println(
                "|" + Utility.addPadding(padding) + title.toUpperCase() + Utility.addPadding(endPadding) + "|");
    }

    public static void displayLoadingAnimation(int loops, long frameDuration, String loadingMessage) {
        for (int i = 0; i < loops; ++i) {
            try {
                clearConsole();
                System.out.print(loadingMessage + " |");
                Thread.sleep(frameDuration);
                clearConsole();
                System.out.print(loadingMessage + " /");
                Thread.sleep(frameDuration);
                clearConsole();
                System.out.print(loadingMessage + " -");
                Thread.sleep(frameDuration);
                clearConsole();
                System.out.print(loadingMessage + " \\");
                Thread.sleep(frameDuration);
                clearConsole();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void displayLoadingAnimationKeepTerminal(int loops, long frameDuration, String loadingMessage) {
        System.out.println();
        for (int i = 0; i < loops; ++i) {
            try {
                System.out.print("\r" + loadingMessage + " |");
                Thread.sleep(frameDuration);
                System.out.print("\r" + loadingMessage + " /");
                Thread.sleep(frameDuration);
                System.out.print("\r" + loadingMessage + " -");
                Thread.sleep(frameDuration);
                System.out.print("\r" + loadingMessage + " \\");
                Thread.sleep(frameDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); 
        System.out.println();
    }
}
