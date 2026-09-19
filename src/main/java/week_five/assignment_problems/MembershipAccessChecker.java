package week_five.assignment_problems;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Problem 1 & 2: Membership Field Reach Checker & Reference Desk Subclass Reach
 *
 * Requirements:
 * - classifyAccess(...) determines ALLOWED or DENIED across 5 contexts.
 * - summarizeByModifier(...) groups counts per modifier in order: private, default, protected, public.
 * - firstDeniedAttempt(...) scans sequentially and early-exits on the first denied attempt.
 */
public class MembershipAccessChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier.toLowerCase()) {
            case "public":
                return "ALLOWED";

            case "protected":
                if ("SAME_CLASS".equals(accessorContext) ||
                    "SAME_PACKAGE".equals(accessorContext) ||
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "default":
                if ("SAME_CLASS".equals(accessorContext) ||
                    "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "private":
                if ("SAME_CLASS".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        // Maintain fixed order: private, default, protected, public
        Map<String, int[]> counts = new LinkedHashMap<>();
        counts.put("private", new int[]{0, 0});
        counts.put("default", new int[]{0, 0});
        counts.put("protected", new int[]{0, 0});
        counts.put("public", new int[]{0, 0});

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String mod = attempt[0].toLowerCase();
                    String ctx = attempt[1];
                    String decision = classifyAccess(mod, ctx);

                    if (counts.containsKey(mod)) {
                        if ("ALLOWED".equals(decision)) {
                            counts.get(mod)[0]++;
                        } else {
                            counts.get(mod)[1]++;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, int[]> entry : counts.entrySet()) {
            if (!first) {
                sb.append(" | ");
            }
            first = false;
            sb.append(entry.getKey()).append(": ")
              .append(entry.getValue()[0]).append(" allowed / ")
              .append(entry.getValue()[1]).append(" denied");
        }

        return sb.toString();
    }

    public static String firstDeniedAttempt(String[][] attempts) {
        if (attempts == null) {
            return "None Denied";
        }

        for (int i = 0; i < attempts.length; i++) {
            String[] attempt = attempts[i];
            if (attempt != null && attempt.length >= 2) {
                String mod = attempt[0];
                String ctx = attempt[1];
                if ("DENIED".equals(classifyAccess(mod, ctx))) {
                    return mod + " via " + ctx + " (attempt #" + (i + 1) + ")";
                }
            }
        }

        return "None Denied";
    }

    public static void main(String[] args) {
        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println("Summary by modifier:\n" + summarizeByModifier(attempts));

        String[][] testDenied = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println("\nFirst denied attempt: " + firstDeniedAttempt(testDenied));

        String[][] testNone = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println("First denied attempt (none): " + firstDeniedAttempt(testNone));
    }
}