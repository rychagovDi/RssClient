package ru.rychagov.justrss.data;

public class Post {

  private String title;
  private String description;
  private String link;
  private String pubDate;

  public Post(String title, String description, String pubDate, String link) {
    this.title = title;
    this.description = description;
    this.pubDate = pubDate;
    this.link = link;
  }

  public String getTitle() {
    return this.title;
  }

  public String getLink() {
    return this.link;
  }

  public String getDescription() {
    return this.description;
  }

  public String getPubDate() {
    return this.pubDate;
  }

  @Override
  public String toString() {
    return getTitle() + "  ( " + this.getPubDate() + " )";
  }

}
