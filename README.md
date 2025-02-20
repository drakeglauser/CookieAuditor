# CookieAuditer

CookieAuditer is a Java program that audits the cookies set by a web server, checking for security attributes such as `Secure`, `HttpOnly`, and `SameSite`. This tool is designed to analyze the security of cookies used in web applications. Namely the front end cookies.

## Features
- Requests a URL and retrieves any `Set-Cookie` headers.
- Analyzes cookies for the follwowing security attributes:
  - **Secure**: Ensures cookies are only sent over HTTPS.
  - **HttpOnly**: Prevents JavaScript access to cookies, reducing XSS risks.
  - **SameSite**: Controls cross-site cookie behavior to prevent CSRF attacks.
- Provides a report on whether cookies follow best security practices.

## Installation and Requirements
### **Prerequisites**
- **Java 8 or later** installed on your system.
- Internet access to audit cookies from remote servers.

## Running Program
- Download the CookieAuditer file, and run it in any application that can run Java files.
- File can be ran directly in a java IDE or can be compiled by terminal.
- Copy and paste a URL link to get it to run. If the code doesn't give any cookies, that doesn't mean the code doesn't work, only that the website has no set cookies.

## Limitation
This tool is for educational and informational purposes only. It should not be used as a security assessment tool. It does not intercept encrypted cookies and does not analyze JavaScript-based cookie manipulations.

- Only works with HTTP(S) responses and does not analyze client-side scripts that modify cookies.
- Does not detect security vulnerabilities beyond cookie attributes.

## Responsible use
This tool is intended for developers, security researchers, and website owners to evaluate their own cookie security settings. Users should:
- Not rely solely on this tool for security assessments. Instead, use it alongside comprehensive security best practices as the tool is meant for educational purposes.
- The tool should not be used on any websites that don't give permission to use.
  
Warning: A modified version of this tool could be used to scan websites for weak security settings and could possibly then be used to exploit them.

