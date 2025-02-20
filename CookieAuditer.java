package block2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * A Java program that requests a URL and audits the cookies
 * set by the server for security attributes.
 */
public class CookieAuditer {

    public static void main(String[] args) {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter a URL to audit (e.g., https://example.com): ");
            String urlString = consoleReader.readLine().trim();
            
            // make a HTTP connection
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(false); // dont follow redirects automatically
            connection.setRequestMethod("GET");
            
            // send request and get response
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // retrieve all headers
            Map<String, List<String>> headerFields = connection.getHeaderFields();

            // look for any "Set-Cookie" headers
            List<String> cookiesHeader = headerFields.get("Set-Cookie");
            if (cookiesHeader == null) {
                System.out.println("No 'Set-Cookie' headers found.");
                return;
            }

            System.out.println("\n Cookie Audit Report");
            for (String cookieLine : cookiesHeader) {
                auditCookie(cookieLine);
            }

        } catch (IOException e) {
            System.err.println("Error reading input or connecting: " + e.getMessage());
        }
    }

    /**
     * Parses a Set-Cookie header line and checks for common security attributes.
     */
    private static void auditCookie(String cookieLine) {
        // a typical Set-Cookie header might look like:
        // SESSIONID=abc123; Path=/; Secure; HttpOnly; SameSite=Strict

        String[] parts = cookieLine.split(";");
        String cookieNameValue = parts[0].trim();

        boolean hasSecure = false;
        boolean hasHttpOnly = false;
        String sameSiteValue = null;

        for (int i = 1; i < parts.length; i++) {
            String attribute = parts[i].trim().toLowerCase();
            if (attribute.equals("secure")) {
                hasSecure = true;
            } else if (attribute.equals("httponly")) {
                hasHttpOnly = true;
            } else if (attribute.startsWith("samesite=")) {
                sameSiteValue = attribute.substring("samesite=".length());
            }
        }

        System.out.println("Cookie: " + cookieNameValue);
        System.out.println(" - Secure?   " + (hasSecure ? "Yes" : "No"));
        System.out.println(" - HttpOnly? " + (hasHttpOnly ? "Yes" : "No"));
        System.out.println(" - SameSite: " + (sameSiteValue != null ? sameSiteValue : "None"));
        System.out.println();
    }
}
