import HM4.Feed;
import HM4.FeedParser;
import HM4.RSSFeed;
import org.junit.Test;

/** Created by Brent Burbidge on 6/4/2016. **/
public class HM4Test {
    @Test
    public void testRSS() {
        FeedParser parser = new FeedParser("http://feeds.gawker.com/lifehacker/full#_ga=1.156627515.183870556.1465073075");
        Feed feed = parser.readFeed();
        System.out.println(feed);

        for (RSSFeed rss : feed.getMessages())
            System.out.println(rss);

    }

}
