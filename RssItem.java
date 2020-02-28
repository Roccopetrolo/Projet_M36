package fr.univ_artois.rtbethune;

public class RssItem {

    public String title, author, link, text;
    public String date;

    @Override
    public String toString() {
        return this.title;
    }
}
