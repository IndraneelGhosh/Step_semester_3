package week_eight.assignment_systems;

import java.util.*;

/**
 * Question 5: The Campus Notice Broadcaster
 */
interface NotificationChannel {
    void send(CampusStudent student, String message);
}

class EmailChannel implements NotificationChannel {
    public void send(CampusStudent student, String message) {
        System.out.println("[Email → " + student.getName() + "] " + message + ".");
    }
}

class AppChannel implements NotificationChannel {
    public void send(CampusStudent student, String message) {
        System.out.println("[App → " + student.getName() + "] " + message + ".");
    }
}

class SmsChannel implements NotificationChannel {
    public void send(CampusStudent student, String message) {
        System.out.println("[SMS → " + student.getName() + "] " + message + ".");
    }
}

class CampusStudent {
    private final String name;
    private final String department;
    private final List<NotificationChannel> preferredChannels;

    public CampusStudent(String name, String department, List<NotificationChannel> preferredChannels) {
        this.name = name;
        this.department = department;
        this.preferredChannels = preferredChannels;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public List<NotificationChannel> getPreferredChannels() { return preferredChannels; }
}

class Notice {
    private final String title;
    private final List<String> targetDepartments;

    public Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = targetDepartments != null ? targetDepartments : Collections.emptyList();
    }

    public String getTitle() { return title; }
    public List<String> getTargetDepartments() { return targetDepartments; }

    public boolean isValid() {
        return title != null && !title.trim().isEmpty() && !targetDepartments.isEmpty();
    }
}

class NoticeBoard {
    private final List<CampusStudent> students = new ArrayList<>();

    public void registerStudent(CampusStudent s) {
        students.add(s);
    }

    public void postNotice(Notice notice) {
        if (!notice.isValid()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return;
        }

        System.out.println("Notice '" + notice.getTitle() + "' posted to " + String.join(", ", notice.getTargetDepartments()) + ".");

        for (CampusStudent student : students) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getPreferredChannels()) {
                    channel.send(student, notice.getTitle());
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard noticeBoard = new NoticeBoard();

        // Asha (CSE) prefers Email and App
        CampusStudent asha = new CampusStudent("Asha", "CSE", Arrays.asList(new EmailChannel(), new AppChannel()));
        // Ravi (ECE) prefers SMS
        CampusStudent ravi = new CampusStudent("Ravi", "ECE", Collections.singletonList(new SmsChannel()));

        noticeBoard.registerStudent(asha);
        noticeBoard.registerStudent(ravi);

        // Admin posts notice 'Lab Closed Tomorrow' for CSE
        noticeBoard.postNotice(new Notice("Lab Closed Tomorrow", Collections.singletonList("CSE")));

        // Admin posts notice 'Fee Deadline Extended' for CSE and ECE
        noticeBoard.postNotice(new Notice("Fee Deadline Extended", Arrays.asList("CSE", "ECE")));

        // Admin attempts to post notice 'Sports Day' with no target department
        noticeBoard.postNotice(new Notice("Sports Day", Collections.emptyList()));
    }
}