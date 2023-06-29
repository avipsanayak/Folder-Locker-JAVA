//Folder and Locker
import java.io.File;
import java.util.Scanner;
public class folderlocker 
{
    private static final String LOCK_FILE_EXTENSION = ".locked";
    private static final String PASSWORD = "password123";
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the password: ");
        String enteredPassword = scanner.nextLine();
        if (!enteredPassword.equals(PASSWORD)) 
        {
            System.out.println("Invalid password. Exiting the program.");
            return;
        }
        System.out.print("Enter the path of the folder to lock/unlock: ");
        String folderPath = scanner.nextLine();
        File folder = new File(folderPath);
        if (!folder.exists()) 
        {
            System.out.println("Folder does not exist.");
            return;
        }
        System.out.print("Enter 'lock' to lock the folder or 'unlock' to unlock the folder: ");
        String operation = scanner.nextLine();
        if (operation.equalsIgnoreCase("lock")) 
        {
            lockFolder(folder);
        } 
        else if (operation.equalsIgnoreCase("unlock")) 
        {
            unlockFolder(folder);
        }
        else 
        {
            System.out.println("Invalid operation.");
        }
    }

    private static void lockFolder(File folder) 
    {
        if (isFolderLocked(folder)) 
        {
            System.out.println("Folder is already locked.");
            return;
        }
        String lockedFolderPath = folder.getAbsolutePath() + LOCK_FILE_EXTENSION;
        File lockedFolder = new File(lockedFolderPath);
        if (folder.renameTo(lockedFolder)) 
        {
            System.out.println("Folder locked successfully.");
        } 
        else 
        {
            System.out.println("Failed to lock the folder.");
        }
    }

    private static void unlockFolder(File folder) 
    {
        if (!isFolderLocked(folder)) 
        {
            System.out.println("Folder is not locked.");
            return;
        }
        String unlockedFolderPath = folder.getAbsolutePath().replace(LOCK_FILE_EXTENSION, "");
        File unlockedFolder = new File(unlockedFolderPath);
        if (folder.renameTo(unlockedFolder)) 
        {
            System.out.println("Folder unlocked successfully.");
        }
        else 
        {
            System.out.println("Failed to unlock the folder.");
        }
    }

    private static boolean isFolderLocked(File folder) 
    {
        String folderName = folder.getName();
        return folderName.endsWith(LOCK_FILE_EXTENSION);
    }
}
