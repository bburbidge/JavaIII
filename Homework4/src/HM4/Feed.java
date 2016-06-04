package HM4;


import java.util.ArrayList;
import java.util.List;

/** Created by Brent Burbidge on 6/4/2016. **/
public class Feed {
    final List<RSSFeed> entries = new ArrayList<>();
    private final String title;
    private final String link;
    private final String description;
    private final String language;
    private final String copyright;
    private final String pubDate;

    public Feed(String title, String link, String description, String language, String copyright, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = copyright;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getPubDate() {
        return pubDate;
    }

    public List<RSSFeed> getMessages() {
        return entries;
    }

    @Override
    public String toString() {
        return "Feed [title=" + title + ", pubDate=" + pubDate +
                ", language" + language + ", desciption=" + description +
                ", link=" + link + ", copyright=" + copyright + "]";
    }

}
