package com.example.dlcsp.data;

import com.example.dlcsp.model.*;

import java.util.List;

public final class MockData {

    private MockData() {}

    public static final List<LearningModule> MODULES = List.of(
            new LearningModule("mod-1", 1, "Spotting Phishing Links",
                    "Learn how to inspect URLs and avoid fake websites designed to steal credentials.",
                    5, 12, 100, true, 90, "Email & Web",
                    List.of(
                            new LessonStep("s1", "What is a Phishing Link?", "Understanding deceptive URLs",
                                    "Phishing links look like real website addresses, but have subtle spelling changes (like paypa1.com instead of paypal.com) or strange extensions.",
                                    "Always look at the domain directly before the first single slash \"/\" in an address.",
                                    null,
                                    new LessonQuiz("Which of the following addresses is a legitimate bank domain?",
                                            List.of("http://secure-login.mybank.verify-acc.com",
                                                    "https://www.mybank.com/login",
                                                    "http://mybank.com.scam-domain.org",
                                                    "https://mybank-support-team.net"),
                                            1,
                                            "Only https://www.mybank.com/login actually belongs to mybank.com. The others belong to verify-acc.com, scam-domain.org, and mybank-support-team.net.")),
                            new LessonStep("s2", "Inspecting Sender Addresses", "Looking beyond the display name",
                                    "Scammers can put any name as the display name (e.g., \"Official Bank Support\"), but their actual email address might be \"support99@mail-free-server.xyz\".",
                                    "Click or tap on the sender name to reveal the full email address inside the angle brackets < >.",
                                    null,
                                    new LessonQuiz("What should you do if an email claims to be your electricity company but comes from \"bill-support@gmail.com\"?",
                                            List.of("Click the payment link immediately to avoid power cutoff",
                                                    "Reply with your credit card details",
                                                    "Do not click anything and check your bill on the official website or app",
                                                    "Forward the email to all your friends"),
                                            2,
                                            "Utility companies send emails from their official corporate domain, never generic free Gmail or Yahoo accounts."))
                    )),
            new LearningModule("mod-2", 2, "Protecting Your UPI & Bank Apps",
                    "Essential rules for money transfers, QR codes, and PIN privacy.",
                    6, 15, 67, false, 80, "Payments",
                    List.of(
                            new LessonStep("s3", "The Golden Rule of UPI PIN", "PIN is ONLY for sending money",
                                    "You NEVER need to enter your UPI PIN, ATM PIN, or password to RECEIVE money. If someone tells you \"Enter your PIN to claim ₹5,000 / $500\", it is a 100% scam.",
                                    "Entering your PIN ALWAYS deducts money from your account.",
                                    null,
                                    new LessonQuiz("A buyer on an online marketplace sends a QR code and says \"Scan this QR and enter your PIN to receive the payment\". What should you do?",
                                            List.of("Scan and enter your PIN right away",
                                                    "Ask for a higher amount before scanning",
                                                    "Refuse and block the buyer because QR code + PIN is only for sending money",
                                                    "Share your bank card CVV number instead"),
                                            2,
                                            "Scanning a QR code and typing your PIN will immediately transfer money OUT of your bank account.")),
                            new LessonStep("s4", "Fake Payment Screenshots", "Verifying actual bank balance",
                                    "Fraudsters use fake payment generator apps that show a green checkmark \"Payment Successful\". Never trust a screenshot shown by a stranger.",
                                    "Always open your own banking app to verify that the money is credited before handing over goods.",
                                    null, null))
            ),
            new LearningModule("mod-3", 3, "Never Share Your OTP",
                    "Why One-Time Passwords are the keys to your digital vault.",
                    4, 8, 100, true, 95, "Authentication",
                    List.of(
                            new LessonStep("s5", "What does OTP actually mean?", "One Time Password",
                                    "An OTP is sent to authorize a transaction or login. Sharing your OTP is identical to handing someone your signed bank cheque.",
                                    null,
                                    "Bank officials, police, customer support, and lottery managers will NEVER ask for your OTP.",
                                    null)
                    )
            ),
            new LearningModule("mod-4", 4, "Fake Calls From \"Bank Officials\"",
                    "Recognizing urgent voice pressure, SIM-swap warnings, and KYC update scams.",
                    5, 10, 20, false, null, "Phone Scams",
                    List.of(
                            new LessonStep("s6", "Urgency & Fear Tactics", "\"Your account will be blocked in 2 hours!\"",
                                    "Scammers create artificial panic so you react emotionally instead of logically. If a caller threatens you with account blockage or legal action, disconnect immediately.",
                                    "Hang up and call the phone number printed directly on the back of your debit card.",
                                    null, null)
                    )
            ),
            new LearningModule("mod-5", 5, "Safe Browsing & Downloads",
                    "Avoiding malicious downloads, browser popups, and fake tech support alerts.",
                    5, 14, 25, false, null, "Browsing",
                    List.of(
                            new LessonStep("s7", "Fake Virus Popups", "Loud beeping and \"Call Microsoft\"",
                                    "Websites can display fullscreen popups claiming your computer is infected with 5 viruses. These are web browser tricks, not real antivirus scans.",
                                    null,
                                    "Never dial the phone number on a popup screen. Simply close the browser tab.",
                                    null)
                    )
            )
    );

    public static final List<ScamReport> SCAM_REPORTS = List.of(
            new ScamReport("rep-1", "#REQ-8942", "Oct 24, 09:15 AM", "Martha Simmons",
                    "martha.s@example.com", "+1 (555) 432-8812", "Security", "email",
                    "Suspected Phishing Email",
                    "Received an email claiming to be from Chase Bank stating my account was temporarily suspended due to unusual activity. The link pointed to chasetrust-security-portal.xyz asking for my username and social security number.",
                    "High", "Pending",
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuDbm6tCsN8sPA6LvVMq4piU3DAUGLVuNlQN5JJiTsTwEhBzuaKxRCaMT9PO1QO8tnENieE36hQ2cFYX8TkxoJimFjUJbjqgegTlXBTSaxg9mS4rM0rOMcEAz4HWCvrWfLRKxwNDqVIdp54ypWbZvN9HISHuZk_GDaZGwe9Nd3VY88MmGVKrFrGO0cjtWswie8WEG690CsQO6NuFT1-1NHGfBT0oJSbkNrqC1PNJeqwrWsc7ISfm5BSf8Q"),
            new ScamReport("rep-2", "#REQ-8941", "Oct 23, 14:30 PM", "Arthur Pendelton",
                    "arthur.p@example.com", "+1 (555) 762-1109", "Account", "text",
                    "Password Reset Assistance & Fake SMS",
                    "Received a text message claiming I won a Walmart $500 gift card, but I need to pay a $4.99 shipping fee with my debit card.",
                    "Medium", "Pending", null),
            new ScamReport("rep-3", "#REQ-8902", "Oct 24, 2023", "Eleanor Vance (User #4421)",
                    "eleanor.v@example.com", null, "Security", "email",
                    "Phishing Email Link",
                    "I received an email claiming my bank account was locked with a suspicious login button directing to a weird URL.",
                    "High", "Pending", null),
            new ScamReport("rep-4", "#REQ-8901", "Oct 24, 2023", "Arthur Pendelton (User #1109)",
                    "arthur.p@example.com", null, "SMS", "text",
                    "Suspicious SMS Gift Card",
                    "Got a text saying I won a gift card but I have to pay shipping first.",
                    "Medium", "Pending", null),
            new ScamReport("rep-5", "#REQ-8895", "Oct 23, 2023", "Martha Hughes (User #502)",
                    "martha.h@example.com", null, "Email", "email",
                    "Spam Newsletter",
                    "Keep getting these recipe emails I never signed up for with strange attachments.",
                    "Low", "Pending", null),
            new ScamReport("rep-6", "#REQ-8892", "Oct 23, 2023", "Harold Smith (User #774)",
                    "harold.s@example.com", null, "Website", "website",
                    "Fake Tech Support Pop-up",
                    "My screen froze with a loud beep and a number to call Microsoft immediately. They asked for remote access to my laptop.",
                    "High", "Pending", null)
    );

    public static final List<SimulationScenario> SIMULATIONS = List.of(
            new SimulationScenario("sim-1", "sms", "Bank Account Blocked SMS", "Beginner",
                    "You receive an urgent SMS message on your mobile phone while shopping.",
                    "VK-HDFCBK-ALERT (+1-800-FAKE)",
                    "DEAR CUSTOMER, YOUR HDFC/SBI BANK ACCOUNT HAS BEEN TEMPORARILY SUSPENDED DUE TO EXPIRED KYC. CLICK HERE TO RE-ACTIVATE IMMEDIATELY: http://hdfc-kyc-update-portal.cc/login OR YOUR ACCOUNT WILL BE PERMANENTLY TERMINATED WITHIN 2 HOURS.",
                    "http://hdfc-kyc-update-portal.cc/login",
                    List.of("Artificial urgency (\"TERMINATED WITHIN 2 HOURS\")",
                            "Unofficial domain (\".cc/login\" instead of official \".com\")",
                            "Threatening language demanding immediate action",
                            "Sent via HTTP instead of secure HTTPS"),
                    "Banks will never terminate your account within 2 hours over SMS or ask you to update KYC through an unverified third-party link. The official way is through your bank branch or verified mobile banking app.",
                    "report"),
            new SimulationScenario("sim-2", "upi", "OLX / Marketplace QR Code Payment", "Beginner",
                    "You are selling an old sofa online for $200. A buyer says \"I have sent the payment QR code to your WhatsApp. Scan it and enter your 4-digit PIN to receive $200 directly into your bank account.\"",
                    "Unknown Buyer (\"David Merchant\")",
                    "SCAN THIS QR CODE IN GOOGLE PAY / PHONEPE TO RECEIVE ₹15,000 / $200 CASHBACK FROM BUYER. ENTER 4-DIGIT PIN TO CONFIRM CREDIT.",
                    null,
                    List.of("Asking to enter PIN to \"RECEIVE\" money",
                            "PIN is ONLY entered when money goes OUT of your account",
                            "Buyer refused to meet in person or use platform payment"),
                    "You NEVER need to enter your PIN or scan a QR code to receive money into your bank account. Entering your PIN approves an outgoing debit payment.",
                    "block"),
            new SimulationScenario("sim-3", "popup", "Windows Security \"Trojan Alert\" Pop-Up", "Intermediate",
                    "While reading an online cooking blog, your browser suddenly goes fullscreen with flashing red warnings and an audio alert.",
                    "SYSTEM_CRITICAL_ERROR #0x80070002",
                    "🚨 CRITICAL SYSTEM WARNING: Your Computer is Infected with Trojan Spyware! Your credit card passwords and personal photos are being transmitted. DO NOT SHUT DOWN. Call Microsoft Certified Support Immediately at: 1-888-992-0199",
                    null,
                    List.of("Web browser tab showing \"Windows\" system errors",
                            "Loud synthesized audio alarm to trigger panic",
                            "Toll-free phone number asking you to call right away",
                            "Full-screen lock attempt (can be closed with Escape or closing browser)"),
                    "Legitimate operating systems like Windows and macOS never display browser popups with phone numbers to call. Scammers use this to get remote access via AnyDesk or TeamViewer and steal your bank savings.",
                    "delete"),
            new SimulationScenario("sim-4", "email", "Netflix \"Payment Declined\" Email", "Intermediate",
                    "You receive an email with the Netflix logo in your inbox.",
                    "Netflix Support <billing-service-noreply@netflx-account-renew.com>",
                    "Hi Friend, We were unable to authorize your monthly subscription renewal. Your membership is on hold. Please update your payment method within 24 hours to keep watching.",
                    "http://netflx-account-renew.com/billing",
                    List.of("Spelling mistake in sender domain (\"netflx\" missing \"i\")",
                            "Generic greeting \"Hi Friend\" instead of your actual account name",
                            "Suspicious billing domain"),
                    "Official subscription services always greet you by your registered name and use their exact domain (e.g. netflix.com). Always log in directly by typing netflix.com in your browser.",
                    "report")
    );

    public static final List<AuthorizedDevice> DEVICES = List.of(
            new AuthorizedDevice("dev-1", "Windows PC - Chrome", "Chicago, IL, USA", true, "Active now", "Chrome 128.0"),
            new AuthorizedDevice("dev-2", "iPhone 13 - Safari", "Chicago, IL, USA", false, "2 hours ago", "Mobile Safari 17.5")
    );

    public static final SystemSettingsConfig SETTINGS = new SystemSettingsConfig(
            "SafeGuard Digital", "admin@safeguard.net", "Eastern Time (ET)",
            true, true, 12, "mfa", 15);

    public static final List<MentorChatMessage> MENTOR_CHAT = List.of(
            new MentorChatMessage("m-1", "mentor",
                    "Welcome back! I noticed you were looking at how to create a strong password yesterday. Would you like to review that, or learn about spotting fake emails today?",
                    "10:00 AM",
                    List.of("Review Passwords", "Fake Emails", "Protect UPI", "Ask Any Question")));
}