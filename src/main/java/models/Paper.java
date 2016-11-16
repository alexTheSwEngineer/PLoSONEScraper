package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by atrposki on 11/14/2016.
 */
public class Paper {

    String id;
    List<String> cross_published_journal_name = new ArrayList<>();
    List<String> author_display = new ArrayList<>();
    List<String> figure_table_caption = new ArrayList<>();

    float alm_scopusCiteCount;
    float alm_citeulikeCount;
    float alm_mendeleyCount;
    float alm_twitterCount;
    float alm_facebookCount;
    float counter_total_all;
    //Sstring retraction;
    String eissn;
    Date publication_date= new Date();
    String article_type;
    String striking_image;
    String title_display;
    String Title;

    String link;
    String journalKey;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getCross_published_journal_name() {
        return cross_published_journal_name;
    }

    public void setCross_published_journal_name(List<String> cross_published_journal_name) {
        this.cross_published_journal_name = cross_published_journal_name;
    }

    public float getAlm_scopusCiteCount() {
        return alm_scopusCiteCount;
    }

    public void setAlm_scopusCiteCount(float alm_scopusCiteCount) {
        this.alm_scopusCiteCount = alm_scopusCiteCount;
    }

    public float getAlm_citeulikeCount() {
        return alm_citeulikeCount;
    }

    public void setAlm_citeulikeCount(float alm_citeulikeCount) {
        this.alm_citeulikeCount = alm_citeulikeCount;
    }

    public float getAlm_mendeleyCount() {
        return alm_mendeleyCount;
    }

    public void setAlm_mendeleyCount(float alm_mendeleyCount) {
        this.alm_mendeleyCount = alm_mendeleyCount;
    }

    public float getAlm_twitterCount() {
        return alm_twitterCount;
    }

    public void setAlm_twitterCount(float alm_twitterCount) {
        this.alm_twitterCount = alm_twitterCount;
    }

    public float getAlm_facebookCount() {
        return alm_facebookCount;
    }

    public void setAlm_facebookCount(float alm_facebookCount) {
        this.alm_facebookCount = alm_facebookCount;
    }

    public float getCounter_total_all() {
        return counter_total_all;
    }

    public void setCounter_total_all(float counter_total_all) {
        this.counter_total_all = counter_total_all;
    }

    public String getEissn() {
        return eissn;
    }

    public void setEissn(String eissn) {
        this.eissn = eissn;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }

    public String getArticle_type() {
        return article_type;
    }

    public void setArticle_type(String article_type) {
        this.article_type = article_type;
    }

    public List<String> getAuthor_display() {
        return author_display;
    }

    public void setAuthor_display(List<String> author_display) {
        this.author_display = author_display;
    }

    public String getStriking_image() {
        return striking_image;
    }

    public void setStriking_image(String striking_image) {
        this.striking_image = striking_image;
    }

    public String getTitle_display() {
        return title_display;
    }

    public void setTitle_display(String title_display) {
        this.title_display = title_display;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<String> getFigure_table_caption() {
        return figure_table_caption;
    }

    public void setFigure_table_caption(List<String> figure_table_caption) {
        this.figure_table_caption = figure_table_caption;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getJournalKey() {
        return journalKey;
    }

    public void setJournalKey(String journalKey) {
        this.journalKey = journalKey;
    }
}
