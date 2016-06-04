package HM4;

/** Created by Brent Burbidge on 6/4/2016. **/
public class RSSFeed {

    private String title;
    private String description;
    private String link;
    private String author;
    private String guid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "RSSFeed [Title=" + title + ", description=" +
                description + ", author=" + author + ", link=" +
                link + ", guid=" + guid + "]";
    }


}
