package financemanager;

import java.awt.Dimension;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

public class RSSFeedChecker {
    Resources resource = new Resources();

    public RSSFeedChecker() throws Exception {
        JEditorPane website = new JEditorPane(resource.RSS_FEED);
        website.setEditable(false);

        JFrame frame = new JFrame("Farm Finance Manager - RSS Feed");
        frame.add(new JScrollPane(website));
        frame.setSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}